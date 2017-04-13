/**
 * Created by Sara Mendez on 11/04/2017.
 */
import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Player, Deck, Players} from '../model';

@Component({
  selector: 'app-players',
  template: `
    <div class="playersStyle" *ngFor="let player of players;">
      <app-player [player]="player" (choosenElements)="chooseElements()">
        <app-hand [hand]="player.hand"></app-hand>
      </app-player>
    </div>`,
  styles: [``]
})
export class PlayersComponent {
  @Input() players: Players;
  @Output() choosenElements: EventEmitter<boolean> = new EventEmitter();

  chooseElements() {
    console.log("Players : received");

    this.choosenElements.emit(true);
  }
}

