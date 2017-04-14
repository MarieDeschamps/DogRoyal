/**
 * Created by Camille on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Piece, Player} from '../model';


@Component({
  selector: 'app-player',
  template: `
    <div>Player {{player.id}} with {{player.color}} pieces
      <div *ngIf="whoPlayNow===player.id">
        <div *ngFor="let piece of player.pieces;let i=indexPiece">
          <app-piece [piece]="piece" (choosenPiece)="chooseThisPiece($event)"></app-piece>
        </div>
      </div>
      <div class="showAllPieces">
        Home !
        <div *ngFor="let piece of player.pieces;let i=indexPiece">
          <div *ngIf="!piece.ready" class="showPieces"><img src="../../assets/piece.jpg"
                                                            style="width:3em;height:3em;vertical-align: middle;">{{piece.id}}
          </div>
        </div>
      </div>
      <div *ngIf="whoPlayNow===player.id">
        <app-hand [hand]="player.hand" (choosenCard)="chooseThisCard($event)"></app-hand>
      </div>
      <div class="showAllPiecesFinish">
        Arrived ! :
        <div *ngFor="let piece of player.pieces;let i=indexPiece">
          <div *ngIf="piece.arrived" class="showPieces"><img src="../../assets/piece.jpg"
                                                             style="width:auto;height:auto;vertical-align: middle;">{{piece.id}}
          </div>
        </div>
      </div>
    </div>`,
  styles: [`
    .showAllPiecesFinish {
      display: flex;
      flex-direction: row;
      vertical-align: middle;
      line-height: 5.5em;

    }

    .showAllPieces {
      display: flex;
      flex-direction: row;

      vertical-align: middle;
      line-height: 5.5em;
    }

    .showPieces {
      border: 1px solid black;
      flex: 0 0;

    }`]
})
export class PlayerComponent {
  @Input() whoPlayNow;
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

