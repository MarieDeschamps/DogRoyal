/**s
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Card, Hand} from '../model';

@Component({
  selector: 'app-hand',
  template: `
    <div class="myHand">
    <div class="cardsStyle" *ngFor='let card of hand; let i=cardIndex;'>
      <app-card [card]='card'></app-card>
    </div>
    </div>
  `,
  styles: [`
  .myHand{
    display: flex;
    flex-direction: row;
  }
  `]
})
export class HandComponent {

  @Input() hand: Hand;
  @Output() choosenCard: EventEmitter<Card> = new EventEmitter();
}
