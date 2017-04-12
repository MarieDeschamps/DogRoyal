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
public class StartupService {
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

			cdao.create(new CardDB(2, false));
			cdao.create(new CardDB(3, false));
			cdao.create(new CardDB(5, false));
			cdao.create(new CardDB(6, false));
			cdao.create(new CardDB(8, false));
			cdao.create(new CardDB(9, false));
			cdao.create(new CardDB(10, false));
			cdao.create(new CardDB(12, false));

		}

		for (int i = 0; i < 20; i++) {
			cdao.create(new CardDB(0, true));
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
