/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output} from '@angular/core';
import {isNumber} from "util";

@Component({
  selector: 'app-gameBoard',
  template: `
    <div class="cases">
      <div *ngFor='let i of cases; ' class='case' style='border:0.5px solid black;' style-background="colorCase(i)">

      </div>
    </div>
  `,
  styles: [`
    .case {
      height: 20px;
      flex: 1 1;
      border: 0.5px solid black;
    }

    .cases {
      display: flex;
      flex-flow: row nowrap;
    }

    .piece {
      width: 3em;
      height: 3em;
      display: block;
      margin: 0 auto;
    }
  `]
})
// <div *ngIf='position==i' class="piece" [style.backgroundColor]=' colorCase(i)'></div>
export class GameBoardComponent {

  @Input() position: number [][];

  @Input() set nbCases(value: number) {
    this.cases = [];
    for (let i = 0; i < value; i++) {
      this.cases.push(i)
    }
    ;
  };

  colorCase(i) {
    let x = this.position.length;
    let t = this.cases.length;
    for (let j = 0; j < x; j++) {
      let nbCases = this.cases.length;

      for (let k = 0; k < t; t++) {
        let red = Math.floor(((nbCases - 1 - k) * 255) / (nbCases - 1));
        let green = Math.floor((k * 255) / (nbCases - 1))
        if (this.position[j][k] = this.position[j][i]) {
          return `rgb(${red},${green},0)`;
        }
      }
    }
  }


  cases = [];
}


