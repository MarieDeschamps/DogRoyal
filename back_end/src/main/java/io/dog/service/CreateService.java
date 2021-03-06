package io.dog.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.dog.dao.CardDao;
import io.dog.dao.GameDao;
import io.dog.dao.PieceDao;
import io.dog.entities.CardDB;
import io.dog.entities.GameDB;
import io.dog.entities.PieceDB;

/**
 * Created by AELION on 05/04/2017.
 */

@Startup
@Singleton
public class CreateService {
	CardDao cdao;
	PieceDao pdao;
	GameDao gdao;

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	void after() {
		System.out.println("======================== AFTER STARTUP ================================");
		this.cdao = new CardDao(em);
		this.pdao = new PieceDao(em);
		this.gdao = new GameDao(em);
	}

	public void createDeck(int game_id) {

		for (int i = 0; i < 7; i++) {

			cdao.create(new CardDB(2, game_id));
			cdao.create(new CardDB(3, game_id));
			cdao.create(new CardDB(5, game_id));
			cdao.create(new CardDB(6, game_id));
			cdao.create(new CardDB(8, game_id));
			cdao.create(new CardDB(9, game_id));
			cdao.create(new CardDB(10, game_id));
			cdao.create(new CardDB(12, game_id));

		}

		for (int i = 0; i < 20; i++) {
			cdao.create(new CardDB(0, game_id));
		}
	}

	public void createPiece(int nbPlayers, int nbPieces, int game_id) {
		for (int i = 0; i < nbPlayers; i++) {
			for (int j = 0; j < nbPieces; j++) {
				pdao.create(new PieceDB(i + 1, 16 * (i), game_id));
			}
		}
	}

	public void createGame(int game_id, int nbPlayers) {
		for (int i = 0; i < nbPlayers; i++) {
			gdao.create(new GameDB(game_id, i + 1));
		}
	}

	public void deleteGame(int game_id) {
		cdao.deleteAll(game_id);
		pdao.deleteAll(game_id);
		gdao.deleteAll(game_id);
	}

	public int createAll(int nbPlayers, int nbPieces) {
		List<Integer> game_list = gdao.getGameIds();
		int game_id = 1;
		for (Integer id : game_list) {
			if (id == game_id) {
				game_id++;
			} else {
				break;
			}
		}

		this.createGame(game_id, nbPlayers);
		this.createDeck(game_id);
		this.createPiece(nbPlayers, nbPieces, game_id);
		return game_id;
	}

}
