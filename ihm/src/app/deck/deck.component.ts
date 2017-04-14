/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Deck} from '../model';
import {Exchange} from "../exchange/exchange";

@Component({
  selector: 'app-deck',
  template: `
    <div> PICK {{deck.pick}}</div>
    <button (click)="pickCard()"> Deal 5 cards</button>
  `,
  styles: [`div {
    border: 1px solid black;
    padding: 10px;
    background-image: url("../../assets/dickCard.png");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center center;
    height: 5.5em;
  }`],
})

export class DeckComponent {
  @Input() deck: Deck;
  @Output() pick5Cards: EventEmitter<boolean> = new EventEmitter();
  deal: boolean;

  constructor(private exchange: Exchange) {
  }

  pickCard() {
    this.pick5Cards.emit(this.deal = true);
  }
}
