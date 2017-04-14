/**
 * Created by AELION on 14/04/2017.
 */

import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {Games, Game} from '../model';
import {Exchange} from '../exchange/exchange';


@Component({
  selector: 'app-loadGameForm',
  template: `
    <h2>Join a Game</h2>
    <div>
      <span *ngIf="games">
      Games: <select [(ngModel)]="choosenGame">
        <option *ngFor="let game of games;" [ngValue]="game">{{game.id}}</option>
      </select>
      </span>
      <span *ngIf="choosenGame">
        Players: <select [(ngModel)]="choosenPlayer">
        <option *ngFor="let player of choosenGame.freePlayers" [ngValue]="player">{{player}}</option>
      </select>
      </span>
    </div>
    <button (click)="validate()" [disabled]="!isButtonEnabled()">OK</button>
    <br/><br/><br/><br/>
  `,
  styles: [`
  `]
})
// <div *ngIf='position==i' class="piece" [style.backgroundColor]=' colorCase(i)'></div>
export class LoadGameFormComponent implements OnInit{
  @Output() validation: EventEmitter<{ choosenGame: number; choosenPlayer: number; }> = new EventEmitter();
  games: Games = [{id: 3, freePlayers: [4]}, {id: 5, freePlayers: [3, 4, 5, 3]}];
  choosenGame: Game = null;
  choosenPlayer: number;

  constructor(private exchange: Exchange) {
  }

  ngOnInit(){
    this.loadGames();
  }

  isButtonEnabled() {
    return this.choosenGame && (this.choosenPlayer !== undefined && this.choosenPlayer > 0);
  }

  validate() {
    if (this.choosenGame && this.choosenGame.id >= 0 && this.choosenPlayer >= 0) {
      this.validation.emit({
        choosenGame: this.choosenGame.id,
        choosenPlayer: this.choosenPlayer
      });
    }
  }

  loadGames() {
    this.exchange.getGamesChoice().then(data => {
      for (let elem in data) {
        if (data.hasOwnProperty(elem)) {
          console.log(data);
          console.log(data[elem]);
          let freePlayersStringArray = data[elem].split("_");
          let game: Game = {
            "id": parseInt(elem),
            "freePlayers": freePlayersStringArray.map((elem) => parseInt(elem))
          };
          this.games.push(game);
        }
      }
    });
  }
}
