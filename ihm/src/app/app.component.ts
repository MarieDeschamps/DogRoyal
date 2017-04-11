import {Component, Input} from '@angular/core';
import {Player, Deck, Hand} from './model';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  nOfPlayers;
  nOfPieces;
  @Input()

  player1: Player = {
    pieces: [
      {'position': 0,'state' : false,'id':1},
      {'position' : 0, 'state' : false,'id':2}
      ],
    hand: {cards:[
      {'card':14, 'id':1},
      {'card':5, 'id':2},
      {'card':8, 'id':3},
      {'card':1 ,'id':4}
      ]},
    color: 'red'
  }
  player2: Player = {
    pieces: [
      {'position': 0,'state' : false,'id':3},
      {'position' : 0, 'state' : false,'id':4}
    ],
    hand: {cards:[
      {'card':5, 'id':5},
      {'card':14, 'id':6},
      {'card':7, 'id':7},
      {'card':3, 'id':8}
    ]},
    color: 'blue'
  }
  players = [this.player1,this.player2];

  chooseCard = false;

}
