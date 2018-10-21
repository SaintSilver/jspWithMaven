package com.kutar.user;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserValidatorTest {

	private static Validator validator;
	private static final Logger logger = LoggerFactory.getLogger(UserValidatorTest.class);
	
	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void userIdIsNull() {
		User user = new User(null, "pw","name","");
		Set<ConstraintViolation<User>> constraintViolation = validator.validate(user);
		assertEquals(1, constraintViolation.size());
		logger.debug(constraintViolation.iterator().next().getMessage());
		//assertEquals("may not be null", constraintViolation.iterator().next().getMessage());
	}
	
	@Test
	public void userIdLength() throws Exception{
		User user = new User("us", "pw","name","");
		Set<ConstraintViolation<User>> constraintViolation = validator.validate(user);
		assertEquals(1, constraintViolation.size());
		logger.debug(constraintViolation.iterator().next().getMessage());
	}
	
	@Test
	public void email() throws Exception{
		User user = new User("user", "pw","name","email");
		Set<ConstraintViolation<User>> constraintViolation = validator.validate(user);
		assertEquals(1, constraintViolation.size());
		logger.debug(constraintViolation.iterator().next().getMessage());
	}
	
	@Test
	public void invalidUser() throws Exception{
		User user = new User("dd", "pw","name","email");
		Set<ConstraintViolation<User>> constraintViolation = validator.validate(user);
		assertEquals(2, constraintViolation.size());
		Iterator<ConstraintViolation<User>> violations = constraintViolation.iterator();
		while(violations.hasNext()) {
			ConstraintViolation<User> each = violations.next();
			logger.debug(each.getPropertyPath() + " : " + each.getMessage());
		}
	}

}
