/**
  * Created by Sara Mendez on 11/04/2017.
  */
import { Component, Input, Output} from '@angular/core';
import {Player, Deck, Players} from '../model';

@Component({
  selector: 'app-players',
  template: ``,
  styles: ['']
})
export class PlayersComponent{
@Input() players : Players;
}
