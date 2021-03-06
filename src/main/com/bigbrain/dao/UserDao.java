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
	
	public User getUser(String email, String password) {
		User userFromDB = null;
        EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		userFromDB =  em.find(User.class, email);
		if(userFromDB != null && (!userFromDB.getEmail().equals(email) ||  !CryptoUtils.decrypt(userFromDB.getPassword()).equals(password) || !userFromDB.getActive())) {
			userFromDB = null;
		}
		em.flush();
		em.close();
		em.getTransaction().commit();
		return userFromDB;
	}

	public void activateUser(String email, String codeActivation) {
		User userFromDB = null;
        EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		userFromDB =  em.find(User.class, email);
		if(userFromDB != null && userFromDB.getEmail().equals(email) && !userFromDB.getActive() && userFromDB.getCodeActivation().equals(codeActivation)) {
			userFromDB.setActive(true);
			em.merge(userFromDB);
		}
		em.flush();
		em.close();
		em.getTransaction().commit();
	}

	public User checkExisantUser(String email) {
		User userFromDB = null;
        EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		userFromDB =  em.find(User.class, email);
		em.flush();
		em.close();
		em.getTransaction().commit();
		return userFromDB;
	}
	
}
