package io.dog.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.dog.dao.CardDao;
import io.dog.dao.PieceDao;
import io.dog.dto.Card;
import io.dog.dto.Deck;
import io.dog.dto.Piece;
import io.dog.dto.Player;
import io.dog.entities.CardDB;
import io.dog.entities.PieceDB;

@Stateless
@Named
public class LoadService {
	CardDao cdao;
	PieceDao pdao;

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	void after() {
		System.out.println("=============== @POSTCONSTRUCT LOAD SERVICE ===========");
		this.cdao = new CardDao(em);
		this.pdao = new PieceDao(em);
	}

	public Deck getDeck() {

		List<CardDB> pickableDB = cdao.getPickablesCards();
		List<CardDB> disguardDB = cdao.getDiguardsCards();

		return new Deck(toCard(pickableDB), toCard(disguardDB));

	}

	public List<Player> getPlayers() {

		int nbPlayers = pdao.getNbPlayers();

		List<Player> players = new ArrayList<>();
		List<CardDB> playercardsDB;
		List<PieceDB> playerspiecesDB;

		for (int i = 1; i < nbPlayers + 1; i++) {
			playercardsDB = cdao.getPlayersCards(i);
			playerspiecesDB = pdao.getPlayersPieces(i);
			players.add(new Player(toPiece(playerspiecesDB), toCard(playercardsDB), i));
		}

		return players;
	}

	public List<Card> toCard(List<CardDB> list) {
		
		if (list.isEmpty()){
			System.out.println("List of Cards is empty");
			return null;
		}

		List<Card> newList = new ArrayList<>();
		for (CardDB card : list) {
			newList.add(new Card(card.getId(), card.getValue(), card.isSpecial()));
		}
		return newList;
	}

	public List<Piece> toPiece(List<PieceDB> list) {

		if (list.isEmpty()){
			System.out.println("List of Pieces is Empty");
			return null;
		}
		
		List<Piece> newList = new ArrayList<>();
		boolean arrived;
		int initialPosition;
		for (PieceDB piece : list) {
			if (piece.getPosition() < 0) {
				arrived = true;
			} else {
				arrived = false;
			}

			initialPosition = (piece.getPlayer() - 1) * 16;

			newList.add(new Piece(piece.getId(), piece.isStatus(), piece.getPosition(), arrived, initialPosition));
		}
		return newList;
	}

}
