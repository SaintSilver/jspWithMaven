package com.kutar.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

	private Connection conn = null;
	private PreparedStatement psmt = null;
	private ResultSet rs = null;

	public void executeUpdate(String sql, PreparedStatementSetter pss) {

		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			pss.setParameters(psmt);
			psmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			try {
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
		}
	}

	public void executeUpdate(String sql, Object... parameters) {
		executeUpdate(sql, createPsmtSetter(parameters));
	}

	/* select one */
	public <T> T executeQuery(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
		List<T> list = executeQueryList(sql, rm, pss);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	public <T> T executeQuery(String sql, RowMapper<T> rm, Object... parameters) {
		return executeQuery(sql, rm, createPsmtSetter(parameters));
	}

	/* select list */
	public <T> List<T> executeQueryList(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {

		try {
			conn = ConnectionManager.getConnection();
			psmt = conn.prepareStatement(sql);
			pss.setParameters(psmt);
			rs = psmt.executeQuery();

			List<T> list = new ArrayList<T>();
			while (rs.next()) {
				list.add(rm.mapRow(rs));
			}

			return list;

		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (psmt != null) {
					psmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				throw new DataAccessException(e);
			}
		}
	}

	public <T> List<T> executeQueryList(String sql, RowMapper<T> rm, Object... parameters) {
		return executeQueryList(sql, rm, createPsmtSetter(parameters));
	}

	private PreparedStatementSetter createPsmtSetter(Object... parameters) {
		return new PreparedStatementSetter() {
			@Override
			public void setParameters(PreparedStatement psmt) throws SQLException {
				for (int i = 0; i < parameters.length; i++) {
					psmt.setObject(i + 1, parameters[i]);
				}
			}
		};
	}

}
