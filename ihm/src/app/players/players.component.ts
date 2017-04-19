/**
 * Created by Sara Mendez on 11/04/2017.
 */
import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Player, Deck, Players} from '../model';

@Component({
  selector: 'app-players',
  template: `
    <div class="playersStyle" *ngFor="let player of players;">
      
      <app-player [player]="player" (choosenElements)="chooseElements()" [user_id]="user_id" [enableButtons]="user_id===whoPlayNow">
        <app-hand [hand]="player.hand"></app-hand>
      </app-player>
    
    </div>`,
  styles: [``]
})
export class PlayersComponent {
  @Input() whoPlayNow;
  @Input() user_id;
  @Input() players: Players;
  @Output() choosenElements: EventEmitter<boolean> = new EventEmitter();

  chooseElements() {
    this.choosenElements.emit(true);
  }
}

