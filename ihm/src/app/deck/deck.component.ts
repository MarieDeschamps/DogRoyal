/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input} from '@angular/core';
import {Deck} from '../model';

@Component({
  selector: 'app-deck',
  template: `
    <div>DISCARD{{deck.discard}}</div><div> PICK{{deck.pick}}</div>`,
  styles: [`div {
    border: 1px solid black
  }`],
})

export class DeckComponent {
  @Input() deck: Deck;
}
