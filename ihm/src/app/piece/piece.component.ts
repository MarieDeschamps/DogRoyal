/**
 * Created by Camille on 10/04/2017.
 */
import { Component, Input, Output,EventEmitter } from '@angular/core';


@Component({
  selector: 'app-piece',
  templateUrl: '',
  styleUrls: ['']
})

export class PieceComponent{
@Input() position: number;
@Input() set stateOnBoard(value: boolean){
  while(this.position !== this.positionInitial+64){
    this.state = true;
  }
  this.state = false;
};

state: boolean;
positionInitial: number;
}
