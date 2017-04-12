/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Card} from '../model';

@Component({
  selector: 'app-card',
  template: `
    <div class="cardStyle">
      <div>{{card.value}}</div>
      <div *ngIf="card.chooseCard">
        <button (click)="onChooseCard()"> choose this card</button>
      </div>
    </div>
  `
  // style:
})

export class CardComponent {

  @Input() card: Card;

  @Output() choosenCard: EventEmitter<number> = new EventEmitter();

  onChooseCard() {
    this.choosenCard.emit();
  }

}
