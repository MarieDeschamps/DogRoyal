/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';
import {Card} from '../model';

@Component({
  selector: 'app-card',
  template: `
    <div class="cardStyle">
      <div>{{card}}</div>
      <div *ngIf="chooseCard">
        <button (click)="onChoose()"> choose this card</button>
      </div>
    </div>
  `
  // style:
})

export class CardComponent {

  @Input() card: Card;
  @Input() chooseCard: boolean;
  @Output() chosen = new EventEmitter<null>();

  onChoose() {
    this.chosen.emit();
  }

}
