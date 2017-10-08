package com.mathi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mathi.entity.User;

@Repository("userDao")
public class UserDaoImpl  implements UserDao{
	@PersistenceContext	
	private EntityManager entityManager;	


	@Override
	public List<User> getAllUsers() {
		String hql = "FROM User";
		return entityManager.createQuery(hql,User.class).getResultList();
	}


	@Override
	public User getUserByEmail(String email) {
		String hql = "FROM User as u WHERE u.email = ?";
		return entityManager.createQuery(hql,User.class).setParameter(1, email).getSingleResult();
	}	

}
