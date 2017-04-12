/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Card} from '../model';

@Component({
  selector: 'app-card',
  template: `
    <div class="card" *ngIf="card.chooseCard==true">
      <div>{{card.value}}</div>
      <button *ngIf="card.chooseCard" (click)="onChooseCard(card)"> choose this card</button>
    </div>
  `,
  styles:[`
    .card {
    border: 1px solid black;
  }`]
})

export class CardComponent {

  @Input() card: Card;

  @Output() choosenCard: EventEmitter<Card> = new EventEmitter();

  onChooseCard(card) {
    card.chooseCard=false;
    this.choosenCard.emit(card);
    console.log(this.card);
  }

}
