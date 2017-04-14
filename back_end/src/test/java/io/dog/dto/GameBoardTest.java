package io.dog.dto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
	GameBoard board;
	
	@Before
	public void before(){
		List<Piece> pieces = new ArrayList<>();
		pieces.add(new Piece(1, 0));
		pieces.add(new Piece(2, 16));
		pieces.add(new Piece(3, 32));
		pieces.add(new Piece(4, true, 10, false, 0));
		board = new GameBoard(3, pieces);
	}
	
	@Test
	public void nbPositions() {
		assertTrue(board.nbPositions()==3*16);
	}
	
	@Test
	public void startPiece(){
		Piece p = board.getPieces().get(0);
		p.setPosition(5);
		board.startPiece(p);
		assertTrue(p.isReady()==true);
		assertTrue(p.getPosition()==0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void startPieceException(){
		Piece p = board.getPieces().get(0);
		board.startPiece(p);
		board.startPiece(p);
	}
	
	@Test
	public void movePiece(){
		Piece p = board.getPieces().get(2);
		board.startPiece(p);
		board.movePiece(p, 5);
		assertTrue(p.getPosition()==37);
		board.movePiece(p, 11);
		assertTrue(p.getPosition()==0);
		board.movePiece(p, 33);
		assertTrue(p.isArrived());
	}

	@Test
	public void samePosition() {
		Piece p = board.getPieces().get(0);
		assertTrue(board.samePosition(p)==null);
		
		board.startPiece(p);
		assertTrue(board.samePosition(p)==null);
		
		p.setPosition(10);
		assertTrue(board.samePosition(p)!=null);
		assertFalse(board.getPieces().get(3).isReady());
	}
}
