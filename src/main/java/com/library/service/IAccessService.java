package com.library.service;

import com.library.dto.Admin;
import com.library.dto.User;

/**
 * Access Service caters for login/register of admin/user.
 * 
 * @author Anush Padubidri
 * @version 1.0
 *
 */
public interface IAccessService {
	/**
	 * Service for admin login.
	 * 
	 * @return {@link Admin} if matching email and password is found.
	 */

	public Admin adminLogin(String email, String password);

	/**
	 * Service for admin registeration.
	 * 
	 * @return {@code true} if insertion of admin data is successful
	 */
	public boolean adminRegister(Admin admin);

	/**
	 * Service for admin login.
	 * 
	 * @return {@link User} if matching email and password is found.
	 */
	public User userLogin(String email, String password);

	/**
	 * Service for admin login.
	 * 
	 * @return {@code true} if insertion of user data is successful
	 */
	public boolean userRegister(User user);
}
