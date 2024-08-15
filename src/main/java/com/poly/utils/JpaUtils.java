package com.poly.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtils {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ASM_OE");

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	public static void shutdown() {
		getEntityManager().close();
	}
}
