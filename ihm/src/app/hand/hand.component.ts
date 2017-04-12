/**s
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Card, Hand} from '../model';

@Component({
  selector: 'app-hand',
  template: `    
      <div class="cardsStyle" *ngFor='let card of hand; let i=cardIndex;'>
        <app-card [card]='card'
                  (choosenCard)='chooseThisCard(i)'></app-card>
      </div>
  `,
  styles: [
      `
    `
  ]
})
export class HandComponent {

  @Input() hand: Hand;
  @Output() choosenCard: EventEmitter<number> = new EventEmitter();

  chooseThisCard(indexCard) {
    this.choosenCard.emit(indexCard);
  }
}
