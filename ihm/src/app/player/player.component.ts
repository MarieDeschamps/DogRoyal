/**
 * Created by Camille on 10/04/2017.
 */
import {Component, Input, Output} from '@angular/core';
import {Player} from '../model';


@Component({
  selector: 'app-player',
  template: `
    <div>Player {{player.color}}
      <div *ngFor="let piece of player.pieces;let i=indexPiece">
        <app-piece [piece]="piece"></app-piece>
      </div>
      <app-hand [hand]="player.hand" [chooseCard]="true"></app-hand>
    </div>`,
  styles: [``]
})
export class PlayerComponent {
  @Input() player: Player;
}


