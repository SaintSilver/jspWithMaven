package com.kutar.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

	public <T> T executeQuery(String sql, PreparedStatementSetter pss, RowMapper<T> rm) throws Exception {
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
	
}
