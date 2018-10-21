package com.kutar.user;

import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;

public class UserDAO {

	private JdbcTemplate template = new JdbcTemplate();
	
	public User findByUserId(String userId) {
		RowMapper<User> rm = rs -> new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
		String sql = "select * from users where userId=?";
		return template.executeQuery(sql, rm, userId);
	}
	
	public List<User> findUsers() {
		RowMapper<User> rm = rs -> new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
		String sql = "select * from users";
		return template.executeQueryList(sql, rm);
	}

	public void addUser(User user) {
		String sql = "insert into users values(?,?,?,?)";
		template.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public void removeUser(String userId) {
		String sql = "delete from users where userId =?";
		template.executeUpdate(sql, userId);
	}

	public void updateUser(User user) {
		String sql = "update users set password=?,name=?,email=? where userId=?";
		template.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}

}
