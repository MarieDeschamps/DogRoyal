/**s
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-hand',
  template: `
    <div class="cardsStyle">
      <app-card *ngFor='let nOfCard of cards; let i=index;'
                [nOfCard]='nOfCard'
                [chooseCard]='chooseCard'
                (chosen)='onChosen(i)'></app-card>
    </div>`,
  styles: [
      `.cardsStyle {
      display: flex;
      flex-flow: row nowrap;
    }`
  ]
})

export class HandComponent {

  @Input() cards: number[];
  @Input() chooseCard: boolean;
  @Output() chosen = new EventEmitter<number>();

  onChosen(cardIndex) {
    this.chosen.emit(cardIndex)
  }
}
