/**
 * Created by Sara Mendez on 11/04/2017.
 */
import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Player, Deck, Players} from '../model';

@Component({
  selector: 'app-players',
  template: `
    <div class="playersStyle" *ngFor="let player of players;">
      <div *ngIf="whoPlayNow===player.id">
      <app-player [player]="player" (choosenElements)="chooseElements()">
        <app-hand [hand]="player.hand"></app-hand>
      </app-player>
      </div>
    </div>`,
  styles: [``]
})
export class PlayersComponent {
  @Input() whoPlayNow;
  @Input() players: Players;
  @Output() choosenElements: EventEmitter<boolean> = new EventEmitter();

  chooseElements() {
    this.choosenElements.emit(true);
  }
}

