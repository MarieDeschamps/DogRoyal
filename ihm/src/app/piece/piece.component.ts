/**
 * Created by Camille on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Piece} from '../model';

@Component({
  selector: 'app-piece',
  template: `
    <div class="piece">
      <div>Piece position{{piece.position}}</div>
      <button *ngIf="piece.choosePiece" (click)="onChoosePiece(piece)"> Choose this piece</button>
    </div><br/>`,
  styles: [`
    .piece {
      border: 1px solid black;
    }
  `]
})

export class PieceComponent {
  @Input() piece: Piece;

  @Output() choosenPiece: EventEmitter<Piece> = new EventEmitter();

  onChoosePiece(piece) {
    this.choosenPiece.emit(piece);
    console.log(this.piece)
  }
}
