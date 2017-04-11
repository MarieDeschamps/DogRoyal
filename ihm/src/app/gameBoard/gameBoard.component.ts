/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output} from '@angular/core';

@Component({
  selector: 'app-gameBoard',
  template: `
    <div class="gameBoard">
      <div class="casses"></div>
      <app-gameBoard>
        <div *ngFor='let i of nbOfCases; ' class='case' style='border:1px solid black;' [style.backgroundColor]='white'>
        </div>
      </app-gameBoard>

    </div>
  `,
  styles: [`.case {
    flex: 1 1;
    border: 1px solid black;
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
    //vert nbCase-1
    //rouge nbCase =0
    let nbCases = this.cases.length;
    let rouge = Math.floor(((nbCases - 1 - i) * 255) / (nbCases - 1));
    let vert = Math.floor((i * 255) / (nbCases - 1));
    return `rgb(${rouge},${vert},0)`;
  }


  cases = [];
}


