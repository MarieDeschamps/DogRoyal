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
      <button *ngIf="card.chooseCard" (click)="onChooseCard()"> choose this card</button>
    </div>
  `,
  styles: [`
    .card {
      border: 1px solid black;
    }`]
})

export class CardComponent {

  @Input() card: Card;
  @Output() choosenCard = new EventEmitter();
  thisCard: boolean=false;

  onChooseCard() {
    this.card.chooseCard = false;
    this.choosenCard.emit(this.thisCard = true);
    console.log(this.card)
  }

}
