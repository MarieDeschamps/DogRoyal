package io.dog.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    List<Card> pickable;
    List<Card> disguard;

    public Deck() {
    	this.pickable = new ArrayList<>();
    	this.disguard = new ArrayList<>();
    }
    
    public Deck(List<Card> cards) {
    	this.pickable = new ArrayList<>();
    	this.disguard = new ArrayList<>();
    	this.generateDeck(cards);
    }
    
    public Deck(List<Card> pickable, List<Card> disguard) {
    	this.pickable = pickable;
    	this.disguard = disguard;
    }

    public void generateDeck(List<Card> cards){
        this.disguard = cards;
        this.shuffle();
    }

    public int size() {
        return this.pickable.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public Card pick() {
    	if(this.size()+this.disguard.size()<1){
    		throw new IllegalArgumentException("not enough cards in the full deck");
    	}
    	if(this.isEmpty()){
            this.shuffle();
        }
        Card c = this.pickable.get(0);
        this.pickable.remove(0);
        return c;
    }

    public List<Card> pick(int nbCards) {
    	if(this.size()+this.disguard.size()<nbCards){
    		throw new IllegalArgumentException("not enough cards in the full deck");
    	}
        List<Card> cardsPicked = new ArrayList<>();
        for (int i = 1; i <= nbCards; i++) {
            if(this.isEmpty()){
                this.shuffle();
            }
            cardsPicked.add(this.pick());
        }
        return cardsPicked;
    }

    public void addToDisguard(Card c) {
        this.disguard.add(c);
    }

    public void shuffle(){
        Collections.shuffle(this.disguard);
        this.pickable.addAll(this.disguard);
        this.disguard = new ArrayList<>();
    }

	public List<Card> getPickable() {
		return pickable;
	}

	public void setPickable(List<Card> pickable) {
		this.pickable = pickable;
	}

	public List<Card> getDisguard() {
		return disguard;
	}

	public void setDisguard(List<Card> disguard) {
		this.disguard = disguard;
	}
    
    
    
}