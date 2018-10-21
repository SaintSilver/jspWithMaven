package com.kutar.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	
	public static User TEST_USER = new User("userId", "password", "name", "asdf@adf.asd");

	private UserDAO userDAO;
	
	@Before
	public void init() {
		userDAO = new UserDAO();
	}
	
	@Test
	public void matchPassword() {
		assertTrue(TEST_USER.matchPassword("password"));
	}
	
	@Test
	public void notMatchPassword() {
		User user = new User("userId", "password", "name", "asdf@adf.asd");
		assertFalse(TEST_USER.matchPassword("password2"));
	}
	
	@Test
	public void login() throws Exception {
		assertTrue(User.login(TEST_USER.getUserId(), TEST_USER.getPassword()));
	}
	
	@Test(expected=UserNotFoundException.class)
	public void loginWhenNotExistedUser() throws Exception {
		User.login("userId2", TEST_USER.getPassword());
	}
	
	@Test(expected=PasswordMissmatchException.class)
	public void loginWhenPasswordMissmatch() throws Exception {
		userDAO.addUser(TEST_USER);
		User.login(TEST_USER.getUserId(), "asdf");
	}
	

}
