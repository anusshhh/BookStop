package com.library.service;

import java.util.Map;

import com.library.dto.Admin;

public interface IAdminService {
	
	public boolean updateProfile(Admin admin);
	
	public boolean changePassword(Map<String,String> passwords,Admin admin);
}
