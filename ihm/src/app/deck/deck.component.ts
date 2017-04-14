/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Deck} from '../model';
import {Exchange} from "../exchange/exchange";

@Component({
  selector: 'app-deck',
  template: `
    <div>{{deck.pick}}</div>
    <button (click)="pickCard()"> Deal 5 cards</button>
  `,
  styles: [`div {
    /*border: 1px solid black;*/
    padding: 10px;
    background-image: url("../../assets/backCard.png");
    background-size: contain;
    background-repeat: no-repeat;
    background-position: center center;
    width: 63px;
    height: 100px;
    line-height: 100px;
    text-align: center;
    vertical-align: middle;
    font-size: 2em;
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
