package com.locadora.project.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	public static void buildSessionFactory(String configFilename) {
		try {
			sessionFactory = new Configuration().configure(configFilename).buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Houve um erro na criação do SessionFactory" + e);
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutDown() {
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
		}
	}
}