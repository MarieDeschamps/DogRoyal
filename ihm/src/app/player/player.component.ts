/**
 * Created by Camille on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Player} from '../model';


@Component({
  selector: 'app-player',
  template: `
    <div>Player {{player.color}}
      <div *ngFor="let piece of player.pieces;let i=indexPiece">
        <app-piece [piece]="piece"
                   (choosenPiece)="chooseThisPiece(i)"></app-piece>
      </div>
      <app-hand [hand]="player.hand" ></app-hand>
    </div>`,
  styles: [``]
})
export class PlayerComponent {
  @Input() player: Player;
  @Output() choosenPiece: EventEmitter<number> = new EventEmitter();

  chooseThisPiece(indexPiece) {
    this.choosenPiece.emit(indexPiece)
  }

}


