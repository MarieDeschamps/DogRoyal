/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-deck',
  template: `<div>{{nOfCardsLeft}} </div>`,
  // style: ['']
})

export class DeckComponent {
 @Input() nOfCardsLeft : number;
}
