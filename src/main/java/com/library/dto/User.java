package com.library.dto;

public class User {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private long phone;
	private int userAdminId;
	
	
	
	public User() {
		super();
	}
	public User(String firstName, String lastName, String email, String password, long phone, int userAdminId) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userAdminId = userAdminId;
	}
	public User(int userId,String firstName, String lastName){
		super();
		this.userId=userId;
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	public User(int userId,String firstName, String lastName, String email, String password, long phone, int userAdminId) {
		super();
		this.userId=userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.userAdminId = userAdminId;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public int getUserAdminId() {
		return userAdminId;
	}
	public void setUserAdminId(int userAdminId) {
		this.userAdminId = userAdminId;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", phone=" + phone + ", userAdminId=" + userAdminId + "]";
	}
	
	
	

}
