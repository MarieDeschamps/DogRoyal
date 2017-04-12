/**
 * Created by Camille on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Piece, Player} from '../model';


@Component({
  selector: 'app-player',
  template: `
    <div>Player {{player.id}} with {{player.color}}  pieces
      <div *ngFor="let piece of player.pieces;let i=indexPiece">
        <app-piece [piece]="piece"
        ></app-piece>
      </div>
      <app-hand [hand]="player.hand"></app-hand>
    </div>`,
  styles: [``]
})
export class PlayerComponent {
  @Input() player: Player;
}


