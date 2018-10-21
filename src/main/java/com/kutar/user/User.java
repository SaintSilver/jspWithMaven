package com.kutar.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class User {
	@NotNull(message = "아이디를 입력하세요.")
	@Size(min =4, max = 12, message="아이디는 4자 이상 12자 이하가 되어야 합니다.")
	private String userId;
	
	@NotNull(message = "패스워드를 입력하세요.")
	@Size(min = 2, max = 10, message="패스워드는 2자 이상 12자 이하가 되어야 합니다.")
	private String password;
	
	@NotNull(message = "이름을 입력하세요.")
	@Size(min = 2, max = 5, message="이름은 2~5자까지 허용됩니다.")
	private String name;
	
	@Email
	private String email;
	
	public User(String userId, String password, String name, String email) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}
	
	public User() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	public boolean matchPassword(String newPassword) {
		return this.password.equals(newPassword);
	}
	
	public boolean isSameUser(String newUserId) {
		if(this.userId == null) {
			return false;
		}
		
		return this.userId.equals(newUserId);
	}

	public static boolean login(String userId, String password) throws UserNotFoundException, PasswordMissmatchException {
		UserDAO userDAO = new UserDAO();
		User user = null;
		try {
			user = userDAO.findByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user == null) {
			throw new UserNotFoundException();
		}
		if(!user.matchPassword(password)) {
			throw new PasswordMissmatchException();
		}
		
		return true;
	}
	
	

	
}
