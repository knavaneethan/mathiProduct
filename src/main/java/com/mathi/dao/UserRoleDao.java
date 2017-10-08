package com.mathi.dao;

import java.util.Set;

public interface UserRoleDao {

	public Set<String> getUserRolesByEmail(String email);

//	public void insert(UserRole r) ;
}
