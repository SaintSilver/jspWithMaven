package com.kutar.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {

	public void executeUpdate(String sql, PreparedStatementSetter pss) throws Exception {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			
			pss.setParameters(psmt);
			
			psmt.executeUpdate();

		} finally {
			if(psmt != null) {
				psmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
	}
	
	public void executeUpdate(String sql, Object... parameters) throws Exception {
		executeUpdate(sql, createPsmtSetter(parameters));
	}
	

	public <T> T executeQuery(String sql, RowMapper<T> rm, PreparedStatementSetter pss) throws Exception {
		Connection conn = null; 
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			pss.setParameters(psmt);
			
			rs = psmt.executeQuery();
			
			if (!rs.next()) {
				return null;
			}

			return rm.mapRow(rs);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public <T> T executeQuery(String sql, RowMapper<T> rm, Object... parameters) throws Exception {
		return executeQuery(sql, rm, createPsmtSetter(parameters));
	}

	private PreparedStatementSetter createPsmtSetter(Object... parameters) {
		return new PreparedStatementSetter() {
			@Override
			public void setParameters(PreparedStatement psmt) throws SQLException {
				for(int i = 0; i < parameters.length; i++) {
					psmt.setObject(i+1, parameters[i]);
				}				
			}
		};
	}
	
}
