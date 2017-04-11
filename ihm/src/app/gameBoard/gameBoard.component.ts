/**
 * Created by Sara Mendez on 10/04/2017.
 */
import {Component, Input, Output} from '@angular/core';


@Component({
  selector: 'app-gameBoard',
  template: `
    <div class="cases">
      <div *ngFor='let i of cases;' class='case' style='border:1px solid black;' [style.backgroundColor]='colorCase(i)'>
        {{i}}
      </div>
    </div>
  `,
  styles: [`
    .case {
      height: 20px;
      flex: 1 1;
      border: 1px solid black;
    }

    .cases {
      display: flex;
      flex-flow: row nowrap;
    }

    .piece {

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

  cases = [];

  colorCase(i) {
    if (!this.position || !this.cases)
      return 'pink';

    let nbJoueurs = this.position.length;
    let nbCases = this.cases.length;

    console.log(nbCases);
    console.log(nbJoueurs);

    for (let j = 0; j < nbJoueurs; j++) {
      for (let p = 0; p < this.position[j].length; p++) {
        if (this.position[j][p] == i) {
          let red = (j * 3 * 17 * 23 + 50) % 256;
          let green = (nbJoueurs * j * j * 31 + 50) % 256;
          return `rgb(${red},${green},20)`;
        }
      }
    }

    return 'white';
  }
}


