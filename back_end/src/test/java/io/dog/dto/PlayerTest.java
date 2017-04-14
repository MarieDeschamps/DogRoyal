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
		cs.add(new Card(1,3));
		Deck d = new Deck(cs);
		player.pick(d);
		assertTrue(player.getHand().size()==1);
		assertTrue(player.getHand().get(0).getId()==1);
	}
	
	@Test
	public void playableCard(){
		Card c = new Card(10,3);
		List<Card> cs = new ArrayList<>();
		cs.add(c);
		Deck d = new Deck(cs);
		player.pick(d);
		Piece p = player.getPieces().get(0);
		
		assertFalse(player.playableCard(c, p));
		
		p.setReady(true);
		assertTrue(player.playableCard(c, p));
	}
	
	@Test
	public void disguardCard(){
		int initNbCards;
		if(player.getHand()==null){
			initNbCards = 0;
		}else{
			initNbCards = player.getHand().size();
		}
		Card c = new Card(10,3);
		List<Card> cs = new ArrayList<>();
		cs.add(c);
		Deck d = new Deck(cs);
		player.pick(d);
		player.disguardCard(d, c);
		assertTrue(d.getDisguard().get(d.getDisguard().size()-1).getId()==10);
		assertTrue(player.getHand().size()==initNbCards);
	}
}
