package io.dog.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.dog.dao.CardDao;
import io.dog.dao.GameDao;
import io.dog.dao.PieceDao;
import io.dog.dto.Card;
import io.dog.dto.Piece;
import io.dog.dto.Player;

@Stateless
@Named
public class UpdateService {
	CardDao cdao;
	PieceDao pdao;
	GameDao gdao;

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	void after() {
		System.out.println("=============== @POSTCONSTRUCT PLAYER SERVICE ===========");
		this.cdao = new CardDao(em);
		this.pdao = new PieceDao(em);
		this.gdao = new GameDao(em);
	}

	public void updatePickedCards(List<Player> players) {
		// Guards
		if (players.isEmpty()) {
			System.out.println("No players have moved");
			return;
		}

		for (Player player : players) {
			for (Card card : player.getHand()) {
				cdao.updatePickedCards(card.getId(), player.getId());
			}
		}
	}

	public void updateDisguardCard(Card card) {
		cdao.updateDisguardCard(card.getId());
	}

	public void updatePieces(List<Piece> pieces) {
		// Guards
		if (pieces.isEmpty()) {
			System.out.println("No pieces have moved");
			return;
		}
		
		for (Piece piece : pieces) {
			pdao.updatePiece(piece.getId(), piece.getPosition(), piece.isReady());
		}
	}
	
	public void updateNewDeck(int game_id){
		cdao.updateNewDeck(game_id);
	}
	
	public void updateFree(int game_id, int player, boolean free){
		gdao.updateFree(game_id, player, free);
	}

}
