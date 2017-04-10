package io.dog.service;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.dog.dao.CardDao;

@Stateless
@Named
public class CardService {

	CardDao dao;

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	void after() {
		System.out.println("=============== @POSTCONSTRUCT UNIVERSE SERVICE ===========");
		this.dao = new CardDao(em);
	}

}
