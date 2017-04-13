/**
 * Created by Camille on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Piece, Player} from '../model';


@Component({
  selector: 'app-player',
  template: `
    <div>Player {{player.id}} with {{player.color}} pieces
      <div *ngFor="let piece of player.pieces;let i=indexPiece">

        <app-piece [piece]="piece" (choosenPiece)="chooseThisPiece($event)"
        ></app-piece>

      </div>
      <app-hand [hand]="player.hand" (choosenCard)="chooseThisCard($event)"></app-hand>
    </div>`,
  styles: [``]
})
export class PlayerComponent {
  @Input() player: Player;
  @Output() choosenElements: EventEmitter<boolean> = new EventEmitter();

  thisCard: boolean = false;
  thisPiece: boolean = false;

  chooseThisPiece($event) {
    this.thisPiece = $event;
    this.chooseElements();
  }

  chooseThisCard($event) {
    this.thisCard = $event;
    this.chooseElements();
  }

  chooseElements() {
    if (this.thisCard === true && this.thisPiece === true) {
      this.choosenElements.emit(true);
    }
  }
}

