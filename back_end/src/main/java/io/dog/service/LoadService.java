package io.dog.service;

import java.util.ArrayList;
import java.util.HashMap;
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
import io.dog.dto.Deck;
import io.dog.dto.Piece;
import io.dog.dto.Player;
import io.dog.entities.CardDB;
import io.dog.entities.GameDB;
import io.dog.entities.PieceDB;

@Stateless
@Named
public class LoadService {
	CardDao cdao;
	PieceDao pdao;
	GameDao gdao;

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	void after() {
		System.out.println("=============== @POSTCONSTRUCT LOAD SERVICE ===========");
		this.cdao = new CardDao(em);
		this.pdao = new PieceDao(em);
		this.gdao = new GameDao(em);
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

		if (list.isEmpty()) {
			System.out.println("List of Cards is empty");
			return new ArrayList<>();
		}

		List<Card> newList = new ArrayList<>();
		for (CardDB card : list) {
			newList.add(new Card(card.getId(), card.getValue()));
		}
		return newList;
	}

	public List<Piece> toPiece(List<PieceDB> list) {

		if (list.isEmpty()) {
			System.out.println("List of Pieces is Empty");
			return new ArrayList<>();
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

			newList.add(new Piece(piece.getId(), piece.isReady(), piece.getPosition(), arrived, initialPosition));
		}
		return newList;
	}
	
	// MEthods for game_id

	public HashMap<String, String> getGames() {
		List<GameDB> games = gdao.getFreeGames();
		HashMap<String, String> map = new HashMap<>();
		String playerlist = "";

		for (int i = 0; i < games.size(); i++) {
			if (i + 1 == games.size()) {
				playerlist = playerlist + games.get(i).getPlayer();
				map.put(String.valueOf(games.get(i).getGame_id()), playerlist);
				break;
			}

			if (games.get(i).getGame_id() == games.get(i + 1).getGame_id()) {
				playerlist = playerlist + games.get(i).getPlayer() + "_";
			} else {
				playerlist = playerlist + games.get(i).getPlayer();
				map.put(String.valueOf(games.get(i).getGame_id()), playerlist);
				playerlist = String.valueOf(games.get(i).getPlayer()) + "_";

			}
		}
		return map;
	}
	
	public Deck getDeck(int game_id) {

		List<CardDB> pickableDB = cdao.getPickablesCards(game_id);
		List<CardDB> disguardDB = cdao.getDiguardsCards(game_id);

		return new Deck(toCard(pickableDB), toCard(disguardDB));

	}

	public List<Player> getPlayers(int game_id) {

		int nbPlayers = pdao.getNbPlayers(game_id);

		List<Player> players = new ArrayList<>();
		List<CardDB> playercardsDB;
		List<PieceDB> playerspiecesDB;

		for (int i = 1; i < nbPlayers + 1; i++) {
			playercardsDB = cdao.getPlayersCards(i,game_id);
			playerspiecesDB = pdao.getPlayersPieces(i,game_id);
			players.add(new Player(toPiece(playerspiecesDB), toCard(playercardsDB), i));
		}

		return players;
	}
	
	

}
