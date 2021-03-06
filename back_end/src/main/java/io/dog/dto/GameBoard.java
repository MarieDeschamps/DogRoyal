package io.dog.dto;

import java.util.List;

public class GameBoard {

	private int nbJoueurs;
	private List<Piece> pieces;

	public GameBoard(int nbJoueurs, List<Piece> pieces) {
		this.nbJoueurs = nbJoueurs;
		this.pieces = pieces;
	}

	public int nbPositions() {
		return nbJoueurs * 16;
	}
	
	public void startPiece(Piece p){
		if(p.isReady() || p.isArrived()){
			throw new IllegalArgumentException("The piece is not in the game!");
		}
		p.setReady(true);
	}
	
	public void movePiece(Piece p, int nbMoves){
		//Piece p = getPieceInTheList(piece);
		int newPosition;
		newPosition = p.getPosition()+nbMoves;
		newPosition = newPosition % this.nbPositions();
		if(newPosition==p.getInitialPosition()+1 && p.getPosition()<=(p.getInitialPosition()+this.nbPositions()-1)%this.nbPositions()){
			p.setArrived(true);
			p.setPosition(0);
		}else{
			p.setPosition(newPosition);
		}
	}

	public Piece samePosition(Piece p) {
		if (!p.isArrived() && p.isReady()) {
			for (Piece piece : pieces) {
				if (p.getId() != piece.getId() && p.getPosition()== piece.getPosition() && !piece.isArrived() && piece.isReady()) {
					piece.resetToBeginning();
					return piece;
				}
			}
		}
		return null;
	}
	
	public Piece getPieceInTheList(Piece p){
		if(!pieces.contains(p)){
			throw new IllegalArgumentException("The piece is not in the game!");
		}
		for (Piece piece : pieces) {
			if (p.getId() != piece.getId()) {
				return piece;
			}
		}
		return null;
	}

	public int getNbJoueurs() {
		return nbJoueurs;
	}

	public void setNbJoueurs(int nbJoueurs) {
		this.nbJoueurs = nbJoueurs;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

}
