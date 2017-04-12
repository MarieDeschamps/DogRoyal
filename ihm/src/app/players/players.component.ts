/**
  * Created by Sara Mendez on 11/04/2017.
  */
import { Component, Input, Output} from '@angular/core';
import {Player, Deck, Players} from '../model';

@Component({
  selector: 'app-players',
  template: `<div *ngFor="let player of players;">
  <app-player [player]="player">
    <app-hand [hand]="player.hand" [chooseCard]="false"></app-hand>
  </app-player>
  </div>`,
  styles: ['']
})
export class PlayersComponent{
@Input() players : Players;
}
