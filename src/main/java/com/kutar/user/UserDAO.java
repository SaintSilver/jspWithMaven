package com.kutar.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.kutar.jdbc.JdbcTemplate;
import com.kutar.jdbc.RowMapper;

public class UserDAO {

	private JdbcTemplate template = new JdbcTemplate();

	public void addUser(User user) {

		String sql = "insert into users values(?,?,?,?)";
		template.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
	}

	public User findByUserId(String userId) {

		RowMapper<User> rm = new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs) throws SQLException{
				return new User(
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4));
			}
		};

		String sql = "select * from users where userId=?";
		return template.executeQuery(sql, rm, userId);
	}

	public void removeUser(String userId) {

		String sql = "delete from users where userId =?";
		template.executeUpdate(sql, userId);
	}

	public void updateUser(User user) {

		String sql = "update users set password=?,name=?,email=? where userId=?";
		template.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
	}

	public List<User> findUsers(){
		RowMapper<User> rm = new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		};

		String sql = "select * from users";
		return template.executeQueryList(sql, rm);
	}
}
