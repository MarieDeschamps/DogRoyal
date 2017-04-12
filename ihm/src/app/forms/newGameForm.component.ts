/**
 * Created by AELION on 12/04/2017.
 */

import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Players} from '../model';


@Component({
  selector: 'app-newGameForm',
  template: `
    <h2>Choose Yourself!</h2>
    <div>
      Choose the number of players<input type="number" required [(ngModel)]='totalPlayers' name="nOfPlayers"/>
      Choose the number of pieces<input type="number" required [(ngModel)]='piecesPlayer' name="nOfPieces"/>
    </div>
    <button (click)="validate()">OK</button>
    <br/><br/><br/><br/>
  `,
  styles: [`
  `]
})
// <div *ngIf='position==i' class="piece" [style.backgroundColor]=' colorCase(i)'></div>
export class NewGameFormComponent {
  totalPlayers: number = 1;
  piecesPlayer: number = 1;

  @Output() validation: EventEmitter<{ totalPlayers: number; piecesPlayer: number; }> = new EventEmitter();

  validate() {
    this.validation.emit({
        totalPlayers: this.totalPlayers,
        piecesPlayer: this.piecesPlayer
      }
    );
  }
}
