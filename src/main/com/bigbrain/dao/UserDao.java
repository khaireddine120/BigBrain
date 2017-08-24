package com.bigbrain.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.bigbrain.config.DaoManager;
import com.bigbrain.entityBeans.User;
import com.bigbrain.utils.CryptoUtils;

public class UserDao {
	
	private static EntityManagerFactory emf = DaoManager.getEntityManagerFactory();
	
	public void saveUser(User user) {
        user.setPassword(CryptoUtils.encrypt(user.getPassword()));
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.flush();
		em.close();
		em.getTransaction().commit();
	}
	
	public boolean getUser(User user) {
		boolean userFound = false;     
        EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User userFromDB =  em.find(User.class, user.getUsername());
		if(userFromDB != null && userFromDB.getUsername().equals(user.getUsername()) && userFromDB.getPassword().equals(CryptoUtils.encrypt(user.getPassword()))) {
			userFound = true;
		}
		em.flush();
		em.close();
		em.getTransaction().commit();
		return userFound;
	}
	
}
