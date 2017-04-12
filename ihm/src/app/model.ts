export interface Player {
  pieces: Piece[];
  hand: Hand;
  color: string;


}

export interface Piece {
  position: number;
  state: boolean;
  id: number;
  choosePiece: boolean;
}


export interface Hand extends Array<Card> {

}

export interface Card {
  value: number;
  id: number;
  chooseCard: boolean;
}

export interface Deck {
  pick: number;
  discard: number;
}

export interface Players extends Array<Player> {

}
