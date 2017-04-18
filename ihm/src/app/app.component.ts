import {Component, OnDestroy} from '@angular/core';
import {Deck, Players} from './model';
import {Exchange} from './exchange/exchange';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy {
  nOfPlayers: number;
  nOfPieces: number;
  winner: number;
  nbCases: number;
  start: boolean = false;
  playersTry: Players;
  whoPlayNow: number;
  newGame: boolean = false;
  loadGame: boolean = false;
  game_id: number;
  user_id: number;


  intervalReload = setInterval(this.reloadData, 5000);

  deckTry: Deck = {
    'pick': -1, 'discard': -1
  };

  cardChosen: boolean;
  pieceChosen: boolean;

  constructor(private exchange: Exchange) {
  }

  ngOnDestroy() {
    this.exchange.freePlayer(this.game_id, this.user_id);
  }

  chargeNewGame($event) {
    this.newGame = true;
    this.loadGame = false;
  }

  chargeAGame($event) {
    this.loadGame = true;
    this.newGame = false;
  }


  elementChoosen($event) {
    this.play();
  }

  createNewGame($event) {
    this.nOfPieces = $event.piecesPlayer;
    this.nOfPlayers = $event.totalPlayers;
    this.user_id = 1;
    this.create();
  };

  loadGameFirstTime($event) {
    this.game_id = $event.choosenGame;
    this.user_id = $event.choosenPlayer;
    this.exchange.loadGamePlayer(this.game_id, this.user_id).then(data => this.translateData(data));
  }

  distribuate($event) {
    let deal: boolean = $event;
    if (deal === true) {
      this.exchange.pick5(this.game_id).then(data => this.translateData(data));
    }
  }

  reloadData() {
    console.log("alert reload " + this.user_id);
    if (this.start && !this.winner && this.whoPlayNow != this.user_id) {
      this.load();
      if (this.whoPlayNow === this.user_id)
        setTimeout(() => {
          alert("It's your turn");
        }, 1000);
    }
  }

  play() {
    this.exchange.play(this.playersTry, this.whoPlayNow).then(data => this.translateData(data));
  }

  create() {
    this.newGame = false;
    this.exchange.create(this.nOfPlayers, this.nOfPieces)
      .then(data => this.translateData(data))
      .then(() => {
        if (this.user_id === 1) {
          setTimeout(() => {
            alert("It's your turn");
          }, 1000);
        }
      });
  }

  load() {
    this.loadGame = false;
    this.exchange.loadGame(this.game_id).then(data => this.translateData(data));
  }

  translateData(data) {
    console.log(data);
    if (!data.ok) {
      alert(data.message);
    } else {
      this.playersTry = data.players;
      this.deckTry.pick = data.deck.pickable.length;
      this.deckTry.discard = data.deck.disguard.length;
      this.winner = data.winner;
      this.whoPlayNow = data.whoPlayNow;
      this.start = true;
      this.nbCases = 16 * (this.playersTry.length);
      if (!this.game_id) {
        this.game_id = data.game_id;
      }
    }
    console.log("start = " + this.start)
  }

}

