package io.dog.dto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DeckTest {
	Deck d;

	@Before
	public void before() {
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(2, 1, false));
		cards.add(new Card(3, 2, false));
		cards.add(new Card(16, 2, false));

		d = new Deck(cards);
	}

	@Test
	public void generateDeck() {
		assertTrue(d.getPickable().get(0).getValue()==1 || d.getPickable().get(0).getValue()==2);
		assertFalse(d.getPickable().get(2).getValue()==7);
	}

	@Test
	public void sizeTest() {
		assertTrue(d.size() == 3);
	}

	@Test
	public void isEmpty() {
		Deck d2 = new Deck();
		assertTrue(d2.isEmpty());
		assertFalse(d.isEmpty());
	}

	@Test
	public void pick() {
		int size = d.size();
		Card c = d.pick();
		assertTrue(d.size() == size - 1);
	}

	@Test
	public void addToDisguard() {
		int size = d.getDisguard().size();
		d.addToDisguard(new Card(17, 3, false));
		assertTrue(d.getDisguard().size() == size + 1);
		assertTrue(d.getDisguard().get(d.getDisguard().size() - 1).getId() == 17);
	}

	@Test
	public void shuffle() {
		d.addToDisguard(new Card(17, 3, false));
		int sizePickable = d.size();
		int sizeDisguard = d.getDisguard().size();

		d.shuffle();

		assertTrue(d.getDisguard().size() == 0);
		assertTrue(d.size() == sizePickable + sizeDisguard);
	}
}
