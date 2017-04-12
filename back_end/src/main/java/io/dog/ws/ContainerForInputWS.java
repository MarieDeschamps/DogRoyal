package io.dog.ws;

import io.dog.dto.Card;
import io.dog.dto.Piece;
import io.dog.dto.Player;

public class ContainerForInputWS {
	Player player;
	Card card;
	Piece piece;
	
	public ContainerForInputWS() {
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public Piece getPiece() {
		return piece;
	}
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	

}
