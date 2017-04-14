package io.dog.dto;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private List<Piece> pieces;
	private List<Card> hand;
	private int id;
	private Color color;
	
	public Player() {
		this.pieces = new ArrayList<>();
		this.hand = new ArrayList<>();
	}

	public Player(List<Piece> pieces, int number) {
		this.id = number;
		for (Piece piece : pieces) {
			piece.setInitialPosition(this.initialPosition());
		}
		this.pieces = pieces;
		this.hand = new ArrayList<>();
		this.color = Color.values()[number-1];
	}

	public Player(List<Piece> pieces, List<Card> cards, int number) {
		this.id=number;
		for (Piece piece : pieces) {
			piece.setInitialPosition(this.initialPosition());
		}
		this.pieces = pieces;
		this.hand = cards;
		this.color = Color.values()[number-1];
	}

	public Player(int nbPiece, List<Integer> pieceIds, List<Card> cards, int number) {
		List<Piece> pieces = new ArrayList<>();

		for (int i = 0; i < nbPiece; i++) {
			pieces.add(new Piece(pieceIds.get(i), (number - 1) * 16));
		}
		this.pieces = pieces;
		this.hand = cards;
		this.id = number;
		this.color = Color.values()[number-1];
	}

	public int initialPosition() {
		return (id - 1) * 16;
	}

	public void pick(Deck d) {
		if(this.hand==null){
			this.hand = new ArrayList<>();
		}
		boolean added = false;
		Card card = d.pick();
		for(int c = 0; c<this.hand.size();c++){
			if(this.hand.get(c).getValue()>card.getValue()){
				this.hand.add(c,card);
				added = true;
				break;
			}
		}
		if(!added){
			this.hand.add(card);
		}
	}

	public boolean playableCard(Card c, Piece p) {
		boolean movePiece;
		if (p.isArrived() == true) {
			movePiece = false;
		} else if (c.getValue() == 0) {
			movePiece = true;
		} else if (p.isReady()) {
			movePiece = true;
		} else {
			movePiece = false;
		}
		return movePiece;
	}

	public void disguardCard(Deck d, Card c) {
		hand.remove(c);
		d.addToDisguard(c);
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public List<Card> getHand() {
		return hand;
	}

	public int getId() {
		return id;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public void setHand(List<Card> cards) {
		this.hand = cards;
	}

	public void setId(int number) {
		this.id = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
