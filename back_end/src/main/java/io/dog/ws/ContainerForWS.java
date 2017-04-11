package io.dog.ws;

import java.util.List;

import io.dog.dto.Deck;
import io.dog.dto.Player;

public class ContainerForWS {
	Deck deck;
	List<Player> players;
	int whoPlayNow;
	boolean ok;
	String message;
	
	public ContainerForWS(Deck deck, List<Player> players, int whoPlayNow, boolean ok) {
		this.deck = deck;
		this.players = players;
		this.whoPlayNow = whoPlayNow%players.size();
		this.ok = ok;
		this.message = "";
	}
	
	public ContainerForWS(int whoPlayNow, boolean ok, String message) {
		this.deck = null;
		this.players = null;
		this.whoPlayNow = whoPlayNow;
		this.ok = ok;
		this.message = message;
	}
}
