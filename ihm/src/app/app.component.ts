import {Component, Input} from '@angular/core';
import {Players, Player, Deck, Hand} from './model';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  nOfPlayers;
  nOfPieces;
  @Input() Players;
  @Input() choosen: number;

  playersTry: Players = [{
    pieces: [
      {'position': 2, 'state': false, 'id': 3, 'choosePiece': false},
      {'position': 3, 'state': false, 'id': 4, 'choosePiece': false}
    ],
    hand: [
      {'value': 5, 'id': 5, 'chooseCard': false},
      {'value': 14, 'id': 6, 'chooseCard': false},
      {'value': 7, 'id': 7, 'chooseCard': false},
      {'value': 3, 'id': 8, 'chooseCard': false}
    ],
    color: 'blue'
  }, {
    pieces: [
      {'position': 5, 'state': false, 'id': 1, 'choosePiece': false},
      {'position': 7, 'state': false, 'id': 2, 'choosePiece': false}
    ],
    hand: [
      {'value': 14, 'id': 1, 'chooseCard': false},
      {'value': 5, 'id': 2, 'chooseCard': false},
      {'value': 8, 'id': 3, 'chooseCard': false},
      {'value': 1, 'id': 4, 'chooseCard': false}
    ],
    color: 'red'
  }];

  chooseCard = false;

}
