package com.mathi.dao;

import java.util.Set;

public interface RolesPermissionDao {

	Set<String> getPermissionsByRoleNames(Set<String> roles);
}
