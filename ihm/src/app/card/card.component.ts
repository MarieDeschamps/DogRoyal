/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-card',
  template: `
    <div class="cardStyle">
      <div>{{nOfCard}} </div>
      <div *ngIf="chooseCard">
        <button (click)="onChoose()"> choose this card</button>
      </div>
    </div>
  `
  // style:
})

export class CardComponent {

  @Input() nOfCard: number;
  @Input () chooseCard : boolean;
  @Output() chosen = new EventEmitter<null>();

  onChoose() {
    this.chosen.emit();
  }

}
