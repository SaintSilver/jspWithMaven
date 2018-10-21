package com.kutar.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JdbcTemplate {

	public void executeUpdate(String sql, Object... parameters) throws Exception {
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < parameters.length; i++) {
				psmt.setObject(i+1, parameters[i]);
			}
			
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

	public <T> T executeQuery(String sql, RowMapper<T> rm, Object... parameters) throws Exception {
		Connection conn = null; 
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			
			for(int i = 0; i < parameters.length; i++) {
				psmt.setObject(i+1, parameters[i]);
			}
			
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
