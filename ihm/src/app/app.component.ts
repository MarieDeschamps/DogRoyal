import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Deck, Players} from './model';
import {Exchange} from './exchange/exchange';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  nOfPlayers: number;
  nOfPieces: number;
  winner: number;
  nbCases: number;
  start: boolean;
  playersTry: Players;
  deckTry: Deck;
  whoPlayNow: number;

  constructor(private exchange: Exchange) {

  }

  newGameData($event) {
    this.nOfPieces = $event.piecesPlayer;
    this.nOfPlayers = $event.totalPlayers;
    this.nbCases = 16 * (this.nOfPlayers);

    this.exchange.create(this.nOfPlayers, this.nOfPieces);

  };

  create() {
    this.exchange.create(this.nOfPlayers, this.nOfPieces).then(data => {
      this.playersTry = data.players;
      this.deckTry.pick = data.deck.pickable.length;
      this.deckTry.discard = data.deck.disguard.length;
      this.winner = data.winner;
      if (!data.ok) {
        console.log(data.message);
      }
      this.whoPlayNow = data.whoPlayNow;
      this.start = true;
    });

  }

  load() {
    this.exchange.load().then(data => {
      this.playersTry = data.players;
      this.deckTry.pick = data.deck.pickable.length;
      this.deckTry.discard = data.deck.disguard.length;
      this.winner = data.winner;
      if (!data.ok) {
        console.log(data.message);
      }
      this.whoPlayNow = data.whoPlayNow;
      this.start = true;
    });

  }


  // winner = null;

  /*

   deckTry: Deck = {
   'pick': 76, 'discard': 0
   };
   */


  /*[{
   pieces: [
   {'position': 2, 'state': false, 'id': 3},
   {'position': 1, 'state': false, 'id': 4}
   ],
   hand: [
   {'value': 5, 'id': 5, 'chooseCard': true},
   {'value': 14, 'id': 6, 'chooseCard': true},
   {'value': 7, 'id': 7, 'chooseCard': true},
   {'value': 3, 'id': 8, 'chooseCard': true}
   ],
   color: 'blue',
   id: 1,
   }, {
   pieces: [
   {'position': 5, 'state': false, 'id': 1},
   {'position': 9, 'state': false, 'id': 2}
   ],
   hand: [
   {'value': 14, 'id': 1, 'chooseCard': true},
   {'value': 5, 'id': 2, 'chooseCard': true},
   {'value': 8, 'id': 3, 'chooseCard': true},
   {'value': 1, 'id': 4, 'chooseCard': true}
   ],
   color: 'red',
   id: 2,
   }];*/


}

