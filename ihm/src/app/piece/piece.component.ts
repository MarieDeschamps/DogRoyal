/**
 * Created by Camille on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Piece} from '../model';

@Component({
  selector: 'app-piece',
  template: `
    <div class="piece">
      <div>Piece {{piece.id}} position {{piece.position}}</div>
      <button class="toPlay" (click)="onChoosePiece()" [disabled]="!enableButtons"> Choose this piece</button>
    </div><br/>`,
  styles: [`
    .piece {
      border: 1px solid black;
    }
  `]
})

export class PieceComponent {
  @Input() piece: Piece;
  @Input() enableButtons;

  @Output() choosenPiece: EventEmitter<boolean> = new EventEmitter();
  thisPiece: boolean;

  onChoosePiece() {
    this.piece.choosePiece = true;
    this.choosenPiece.emit(this.thisPiece = true);
    console.log(this.piece);
  }

}
