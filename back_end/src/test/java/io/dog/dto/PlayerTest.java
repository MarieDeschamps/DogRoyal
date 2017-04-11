package io.dog.dto;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
	Player player;
	
	@Before
	public void before(){
		List<Piece> pieces = new ArrayList<>();
		pieces.add(new Piece(1, 0));
		pieces.add(new Piece(2, 16));
		pieces.add(new Piece(3, 32));
		pieces.add(new Piece(4, true, 10, false, 0));
		player = new Player(pieces, 1);
	}
	
	@Test
	public void initialPosition(){
		assertTrue(player.initialPosition()==0);
	}
	
	@Test
	public void pick(){
		List<Card> cs = new ArrayList<>();
		cs.add(new Card(1,3,false));
		Deck d = new Deck(cs);
		player.pick(d);
		assertTrue(player.getCards().size()==1);
		assertTrue(player.getCards().get(0).getId()==1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void pickMultipleError(){
		List<Card> cs = new ArrayList<>();
		cs.add(new Card(1,3,false));
		Deck d = new Deck(cs);
		player.pick(d, 5);
	}
	
	@Test
	public void pickMultiple(){
		int initNbCards;
		if(player.getCards()==null){
			initNbCards = 0;
		}else{
			initNbCards = player.getCards().size();
		}
		
		List<Card> cs = new ArrayList<>();
		cs.add(new Card(1,3,false));
		cs.add(new Card(2,4,false));
		cs.add(new Card(3,5,false));
		Deck d = new Deck(cs);
		player.pick(d, 2);
		assertTrue(player.getCards().size()==initNbCards+2);
	}
	
	@Test
	public void playableCard(){
		Card c = new Card(10,3,false);
		List<Card> cs = new ArrayList<>();
		cs.add(c);
		Deck d = new Deck(cs);
		player.pick(d);
		Piece p = player.getPieces().get(0);
		
		assertFalse(player.playableCard(c, p));
		
		p.setStatus(true);
		assertTrue(player.playableCard(c, p));
	}
	
	@Test
	public void disguardCard(){
		int initNbCards;
		if(player.getCards()==null){
			initNbCards = 0;
		}else{
			initNbCards = player.getCards().size();
		}
		Card c = new Card(10,3,false);
		List<Card> cs = new ArrayList<>();
		cs.add(c);
		Deck d = new Deck(cs);
		player.pick(d);
		player.disguardCard(d, c);
		assertTrue(d.getDisguard().get(d.getDisguard().size()-1).getId()==10);
		assertTrue(player.getCards().size()==initNbCards);
	}
}
