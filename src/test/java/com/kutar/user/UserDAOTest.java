package com.kutar.user;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {

	private UserDAO userDao;
	
	@Before
	public void init() throws Exception {
		userDao = new UserDAO();
		userDao.removeUser(UserTest.TEST_USER.getUserId());
	}
	
	@Test
	public void addUser() throws Exception {
		userDao.addUser(UserTest.TEST_USER);
	}
	
	@Test
	public void findByUserId() throws Exception{
		User user = userDao.findByUserId("kutar37");
		assertNotNull(user);
	}
	
	@Test
	public void crud() throws Exception{
		User user = UserTest.TEST_USER;
		userDao.addUser(user);
		User dbUser = userDao.findByUserId(user.getUserId());
		assertEquals(UserTest.TEST_USER, user);
		
		//수정용 아이디
		User updateUser = new User(user.getUserId(), "uPassword","updatename","update@email.com");
		userDao.updateUser(updateUser);
		dbUser = userDao.findByUserId(updateUser.getUserId());
		assertEquals(updateUser, dbUser);
	}
	
	@Test
	public void 존재하지_않는_사용자_조회() throws Exception{
		User dbUser = userDao.findByUserId(UserTest.TEST_USER.getUserId());
		assertNull(dbUser);
	}

}
