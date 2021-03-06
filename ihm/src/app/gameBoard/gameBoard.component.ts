/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output} from '@angular/core';
import {Players} from '../model';


@Component({
  selector: 'app-gameBoard',
  template: `
    <div class="cases">
      <div *ngFor='let i of cases;' class='case' style='border:1px solid black;'
           [style.backgroundColor]='colorCase(i)'>
        {{i}}
      </div>
    </div>
  `,
  styles: [`
    .case {

      flex: 0 1 4em;
      border: 1px solid black;
      height: 3em;
    }

    .cases {
      display: flex;
      flex-flow: row wrap;
    }

  `]
})
// <div *ngIf='position==i' class="piece" [style.backgroundColor]=' colorCase(i)'></div>
export class GameBoardComponent {

  @Input() players: Players;

  @Input() set nbCases(value: number) {
    this.cases = [];
    for (let i = 0; i < value; i++) {
      this.cases.push(i)
    }
    ;
  };

  cases = [];

  colorCase(i) {
    if (!this.players || !this.cases) {
      return 'white';
    } else {
      let nbPlayer = this.players.length;
      for (let j = 0; j < nbPlayer; j++) {
        for (let p = 0; p < this.players[j].pieces.length; p++) {
          if (this.players[j].pieces[p].position == i) {
            if (this.players[j].pieces[p].ready === true) {
              return this.players[j].color;
            }
          }
        }
      }
      return 'white';
    }
  }
}


