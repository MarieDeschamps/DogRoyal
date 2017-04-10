/**
 * Created by Camille on 10/04/2017.
 */
import { Component, Input, Output} from '@angular/core';


@Component({
  selector: 'app-piece',
  templateUrl: './piece.component.html',
  styleUrls: ['']
})

export class PieceComponent{
@Input() position: number;
state: boolean =false;
@Output() set stateOnBoard(value: boolean){
  
  while(this.position >= this.positionInitial && this.position !== this.positionInitial+64){
    this.state = true;
  }
  this.state = false;
};
positionInitial: number;
}
