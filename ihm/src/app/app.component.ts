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
  @Input()

  playersTry : Players = [{
    pieces: [
      {'position': 0,'state' : false,'id':3},
      {'position' : 0, 'state' : false,'id':4}
    ],
    hand:[
      {'value':5, 'id':5},
      {'value':14, 'id':6},
      {'value':7, 'id':7},
      {'value':3, 'id':8}
    ],
    color: 'blue'
  }, {
    pieces: [
      {'position': 0,'state' : false,'id':1},
      {'position' : 0, 'state' : false,'id':2}
    ],
    hand: [
      {'value':14, 'id':1},
      {'value':5, 'id':2},
      {'value':8, 'id':3},
      {'value':1 ,'id':4}
    ],
    color: 'red'
  }];

  chooseCard = false;

}
