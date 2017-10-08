package com.mathi.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository("rolesPermissionDao")
public class RolesPermissionDaoImpl implements RolesPermissionDao{


	@PersistenceContext	
	private EntityManager entityManager;

	@Override
	public Set<String> getPermissionsByRoleNames(Set<String> roles) {
		String hql = "SELECT rp.permission FROM RolesPermission as rp WHERE rp.roleName IN (?1)";
		List<String> result = entityManager.createQuery(hql,String.class).setParameter(1, roles).getResultList();
		 Set<String> set = new HashSet<String>(result);
		return set;
	}
}
