package io.dog.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.dog.entities.CardDB;

/**
 * Created by AELION on 05/04/2017.
 */

@Startup
@Singleton
public class StartupService {

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	void after() {
		System.out.println("======================== AFTER STARTUP ================================");
		createData();
	}

	void createData() {
		CardDB one = new CardDB(3, false);
		em.persist(one);

	}

}
