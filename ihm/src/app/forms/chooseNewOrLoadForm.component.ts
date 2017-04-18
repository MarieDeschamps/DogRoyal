/**
 * Created by Sara Mendez on 14/04/2017.
 */
import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Players} from '../model';


@Component({
  selector: 'app-chooseNewOrLoadForm',
  template: `    
    <div>
      <button (click)=" newGame()">New Game</button>
      <button (click)=" loadGame()">Load Game</button>
    </div>
  `,
  styles: [`
  `]
})

export class ChooseNewOrLoadFormComponent {

  @Output() createNewGame: EventEmitter<boolean> = new EventEmitter();
  @Output() loadAGame: EventEmitter<boolean> = new EventEmitter();

  newGame() {
    this.createNewGame.emit(true);
  }
  loadGame() {
    this.loadAGame.emit(true);
  }

}
