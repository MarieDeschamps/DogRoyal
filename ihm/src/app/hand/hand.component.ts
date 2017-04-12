/**s
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Card, Hand} from '../model';

@Component({
  selector: 'app-hand',
  template: `
    <div class="cardsStyle">
      <app-card *ngFor='let card of cards; let i=cardIndex;'
                [nOfCard]='this.hand[card].value'
                [chooseCard]='chooseCard'
                (chosen)='onChosen(i)' style='border:1px solid black;font-size: 100%;text-align: center'></app-card>
    </div>`,
  styles: [
      `.cardsStyle {
      display: flex;
      flex-flow: row nowrap;
    }`
  ]
})


export class HandComponent {

  @Input() hand: Hand;
  @Input() chooseCard: boolean;
  @Output() chosen = new EventEmitter<number>();

  onChosen(cardIndex) {
    this.chosen.emit(cardIndex)
  }
}
