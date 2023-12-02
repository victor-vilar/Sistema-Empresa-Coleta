package com.victorvilar.projetoempresa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * 
 * @author Victor
 * @since 2022-02-24
 */
@Deprecated
public abstract class EntityManagerBuilder {
	
	private static EntityManager em;
		
	public static EntityManager getEntityManager() {
		if(em == null) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("landtec");
			em = emf.createEntityManager();
		}
		return em;
	}
	
	
}
