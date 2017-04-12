/**s
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Card, Hand} from '../model';

@Component({
  selector: 'app-hand',
  template: `
    <div class="cardsStyle">
      <div class="cardsStyle" *ngFor='let card of hand; let i=cardIndex;'>
        <app-card [card]='card.value'
                  [chooseCard]='card.chooseCard'
                  (choosenCard)='chooseThisCard(i)'></app-card>
      </div>
    </div>`,
  styles: [
      `.cardsStyle {
      display: flex;
      flex-flow: row nowrap;
      border: 1px solid black;
      font-size: 100%;
      text-align: center;
    }`
  ]
})
export class HandComponent {

  @Input() hand: Hand;
  @Output() choosenCard: EventEmitter<number> = new EventEmitter();

  chooseThisCard(indexCard) {
    this.choosenCard.emit(indexCard);
  }
}
