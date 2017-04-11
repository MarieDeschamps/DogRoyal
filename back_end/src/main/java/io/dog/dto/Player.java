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
		this.cards = new ArrayList<>();
	}
	
	public Player(List<Piece> pieces, List<Card> cards, int number) {
		this.number = number;
		for (Piece piece : pieces) {
			piece.setInitialPosition(this.initialPosition());
		}
		this.pieces = pieces;
		this.cards = cards;
	}

	public int initialPosition(){
		return (number-1)*16;
	}
	
	public void pick(Deck d){
		cards.add(d.pick());
	}
	
	public void pick(Deck d, int nbCards){
		cards.addAll(d.pick(nbCards));
	}
	
	public boolean playableCardOrDisguard(Deck d, Card c, Piece p){
		boolean movePiece;
		if(p.isArrived()==true){
			movePiece = false;
			this.disguardCard(d, c);
		}else if(c.isSpecial()==true){
			movePiece = true;
		}else if(p.isStatus()){
			movePiece = true;
		}else{
			movePiece = false;
			this.disguardCard(d, c);
		}
		return movePiece;
	}
	
	private void disguardCard(Deck d, Card c){
		cards.remove(c);
		d.addToDisguard(c);
	}
	
	
	
}
