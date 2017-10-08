package com.mathi.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository("userRoleDao")
public class UserRoleDaoImpl implements UserRoleDao{

	@PersistenceContext	
	private EntityManager entityManager;

//	@Autowired
//	private HibernateTemplate hibernateTemplate;

	@Override
	public Set<String> getUserRolesByEmail(String email) {		
		String hql = "SELECT ur.roleName FROM UserRole as ur WHERE ur.email = ?";
		List<String> result = entityManager.createQuery(hql,String.class).setParameter(1, email).getResultList();
		 Set<String> set = new HashSet<String>(result);
		return set;	
	}

//	@Override
//	public void insert(UserRole r) {
//		hibernateTemplate.save(r);
//	}

}
