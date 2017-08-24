package com.bigbrain.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.arjuna.ats.jta.TransactionManager;

public class DaoManager {

	private static javax.transaction.TransactionManager tm = TransactionManager.transactionManager();
	//build the EntityManagerFactory as you would build in in Hibernate ORM
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
	    "ogm-mongodb");
	
	public static javax.transaction.TransactionManager getTransactionManager() {
		return tm;
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	
}
