package io.dog.dto;

import java.util.List;

public class GameBoard {

	int nbJoueurs;
	List<Piece> pieces;

	public int nbPositions() {
		return nbJoueurs * 16 - 1;
	}

	public void samePosition(Piece p) {
		if (!p.isArrived() && p.isStatus()) {
			for (Piece piece : pieces) {
				if (p.getId() != piece.getId() && p.getPosition()== piece.getPosition() && !piece.isArrived() && piece.isStatus()) {
					piece.resetToBeginning();
				}
			}
		}
	}

}
