package io.dog.dto;

import java.util.List;

public class Dog {

	private List<Player> players;
	private Deck deck;
	
	public int nbPositions(){
		return players.size()*16-1;
	}
	
	public void play(int p){
		
	}
	
}
