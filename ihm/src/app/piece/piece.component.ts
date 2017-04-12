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
      <button *ngIf="piece.choosePiece" (click)="onChoosePiece()"> Choose this piece</button>
    </div><br/>`,
  styles: [`
    .piece {
      border: 1px solid black;
    }
  `]
})

export class PieceComponent {
  @Input() piece: Piece;

  @Output() choosenPiece: EventEmitter<number> = new EventEmitter();

  onChoosePiece() {
    this.choosenPiece.emit();
  }
}
