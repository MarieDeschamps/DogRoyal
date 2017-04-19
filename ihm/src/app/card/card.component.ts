/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Card} from '../model';
import {discardPeriodicTasks} from "@angular/core/testing";

@Component({
  selector: 'app-card',
  template: `
    <div class="card" *ngIf="card.chooseCard==true">
      <div><img src="../../assets/{{card.value}}.png" alt="{{card.value}}" height="150" /></div>
      <button *ngIf="card.chooseCard" class="toPlay"(click)="onChooseCard()" [disabled]="!enableButtons"> choose this card</button>
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
  thisCard: boolean = false;
  @Input() enableButtons;

  onChooseCard() {
    this.card.chooseCard = false;
    this.choosenCard.emit(this.thisCard = true);
    console.log(this.card);
  }

}
