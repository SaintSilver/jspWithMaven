package com.kutar.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kutar.support.JdbcTemplate;
import com.kutar.support.PreparedStatementSetter;
import com.kutar.support.RowMapper;

public class UserDAO {

	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

	public void addUser(User user) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setParameters(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, user.getUserId());
				psmt.setString(2, user.getPassword());
				psmt.setString(3, user.getName());
				psmt.setString(4, user.getEmail());
			}
		};

		JdbcTemplate template = new JdbcTemplate();

		String sql = "insert into users values(?,?,?,?)";
		template.executeUpdate(sql, pss);
	}

	public User findByUserId(String userId) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setParameters(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, userId);
			}
		};

		RowMapper<User> rm = new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		};

		JdbcTemplate template = new JdbcTemplate();

		String sql = "select * from users where userId=?";
		return template.executeQuery(sql, pss, rm);
	}

	public void removeUser(String userId) throws Exception {

		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setParameters(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, userId);
			}
		};
		JdbcTemplate template = new JdbcTemplate();

		String sql = "delete from users where userId =?";
		template.executeUpdate(sql, pss);
	}

	public void updateUser(User user) throws Exception {
		PreparedStatementSetter pss = new PreparedStatementSetter() {

			@Override
			public void setParameters(PreparedStatement psmt) throws SQLException {
				psmt.setString(1, user.getPassword());
				psmt.setString(2, user.getName());
				psmt.setString(3, user.getEmail());
				psmt.setString(4, user.getUserId());
			}
		};

		JdbcTemplate template = new JdbcTemplate();

		String sql = "update users set password=?,name=?,email=? where userId=?";
		template.executeUpdate(sql, pss);
	}
}
