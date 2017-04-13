package io.dog.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.dog.dao.CardDao;
import io.dog.dao.PieceDao;
import io.dog.entities.CardDB;
import io.dog.entities.PieceDB;

/**
 * Created by AELION on 05/04/2017.
 */

@Startup
@Singleton
public class CreateService {
	CardDao cdao;
	PieceDao pdao;

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	void after() {
		System.out.println("======================== AFTER STARTUP ================================");
		this.cdao = new CardDao(em);
		this.pdao = new PieceDao(em);
	}

	public void createDeck() {

		for (int i = 0; i < 7; i++) {

			cdao.create(new CardDB(2, 0));
			cdao.create(new CardDB(3, 0));
			cdao.create(new CardDB(5, 0));
			cdao.create(new CardDB(6, 0));
			cdao.create(new CardDB(8, 0));
			cdao.create(new CardDB(9, 0));
			cdao.create(new CardDB(10, 0));
			cdao.create(new CardDB(12, 0));

		}

		for (int i = 0; i < 20; i++) {
			cdao.create(new CardDB(0, 0));
		}
	}

	public void createPiece(int nbPlayers, int nbPieces) {
		for (int i = 0; i < nbPlayers; i++) {
			for (int j = 0; j < nbPieces; j++) {
				pdao.create(new PieceDB(i + 1, 16 * (i)));
			}
		}
	}
	
	public void deleteGame(){
		cdao.deleteAll();
		pdao.deleteAll();
	}

}
