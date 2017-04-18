package io.dog.ws;

import java.util.List;

import io.dog.dto.Deck;
import io.dog.dto.Player;

public class ContainerForOutputWS{
	/**
	 * 
	 */
	Deck deck;
	List<Player> players;
	int whoPlayNow;
	Player winner;
	boolean ok;
	String message;
	int game_id;
	
	public ContainerForOutputWS(Deck deck, List<Player> players, int whoPlayNow, boolean ok) {
		this.deck = deck;
		this.players = players;
		if(whoPlayNow>players.size()){
			this.whoPlayNow = 1;
		}else{
			this.whoPlayNow = whoPlayNow;
		}
		this.winner = null;
		this.ok = ok;
		this.message = "";
	}
	
	public ContainerForOutputWS(int whoPlayNow, boolean ok, String message) {
		this.deck = null;
		this.players = null;
		this.whoPlayNow = whoPlayNow;
		this.winner = null;
		this.ok = ok;
		this.message = message;
	}
	
	public ContainerForOutputWS(Deck deck, List<Player> players, int whoPlayNow, boolean ok,int game_id) {
		this.deck = deck;
		this.players = players;
		if(whoPlayNow>players.size()){
			this.whoPlayNow = 1;
		}else{
			this.whoPlayNow = whoPlayNow;
		}
		this.winner = null;
		this.ok = ok;
		this.message = "";
		this.game_id=game_id;
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public int getWhoPlayNow() {
		return whoPlayNow;
	}

	public void setWhoPlayNow(int whoPlayNow) {
		this.whoPlayNow = whoPlayNow;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	
	
	
}
