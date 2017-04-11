package io.dog.dto;

import java.util.ArrayList;
import java.util.List;

public class Player {
	List<Piece> pieces;
	List<Card> cards;
	int number;
	
	public Player(List<Piece> pieces, int number) {
		this.number = number;
		for (Piece piece : pieces) {
			piece.setInitialPosition(this.initialPosition());
		}
		this.pieces = pieces;
	}

	public Player(List<Piece> pieces, List<Card> cards, int number) {
		for (Piece piece : pieces) {
			piece.setInitialPosition(this.initialPosition());
		}
		this.pieces = pieces;
		this.cards = new ArrayList<>();
	}

	public Player(int nbPiece, List<Integer> pieceIds, List<Card> cards, int number) {
		List<Piece> pieces = new ArrayList<>();

		for (int i = 0; i < nbPiece; i++) {
			pieces.add(new Piece(pieceIds.get(i), (number - 1) * 16));
		}
		this.pieces = pieces;
		this.cards = cards;
		this.number = number;
	}

	public int initialPosition() {
		return (number - 1) * 16;
	}

	public void pick(Deck d) {
		if(this.cards==null){
			this.cards = new ArrayList<>();
		}
		this.cards.add(d.pick());
	}

	public void pick(Deck d, int nbCards) {
		if(this.cards==null){
			this.cards = new ArrayList<>();
		}
		this.cards.addAll(d.pick(nbCards));
	}

	public boolean playableCardOrDisguard(Deck d, Card c, Piece p) {
		boolean movePiece;
		if (p.isArrived() == true) {
			movePiece = false;
			this.disguardCard(d, c);
		} else if (c.isSpecial() == true) {
			movePiece = true;
		} else if (p.isStatus()) {
			movePiece = true;
		} else {
			movePiece = false;
			this.disguardCard(d, c);
		}
		return movePiece;
	}

	private void disguardCard(Deck d, Card c) {
		cards.remove(c);
		d.addToDisguard(c);
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public List<Card> getCards() {
		return cards;
	}

	public int getNumber() {
		return number;
	}

}
