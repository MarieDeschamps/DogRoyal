import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Deck, Players} from './model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  nOfPlayers: number;
  nOfPieces: number;
  @Input() Players;
  nbCases: number;
  start: boolean;
  winner = null;

  newGameData($event) {
    this.nOfPieces = $event.piecesPlayer;
    this.nOfPlayers = $event.totalPlayers;
    this.start = true;
    this.nbCases = 16 * (this.nOfPlayers);
  }
  ;

  deckTry: Deck = {
    'pick': 76, 'discard': 0
  };

  playersTry: Players = [{
    pieces: [
      {'position': 2, 'state': false, 'id': 3, 'choosePiece': true},
      {'position': 1, 'state': false, 'id': 4, 'choosePiece': true}
    ],
    hand: [
      {'value': 5, 'id': 5, 'chooseCard': true},
      {'value': 14, 'id': 6, 'chooseCard': true},
      {'value': 7, 'id': 7, 'chooseCard': true},
      {'value': 3, 'id': 8, 'chooseCard': true}
    ],
    color: 'blue'
  }, {
    pieces: [
      {'position': 5, 'state': false, 'id': 1, 'choosePiece': true},
      {'position': 9, 'state': false, 'id': 2, 'choosePiece': true}
    ],
    hand: [
      {'value': 14, 'id': 1, 'chooseCard': true},
      {'value': 5, 'id': 2, 'chooseCard': true},
      {'value': 8, 'id': 3, 'chooseCard': true},
      {'value': 1, 'id': 4, 'chooseCard': true}
    ],
    color: 'red'
  }];


}

