/**
 * Created by Camille on 10/04/2017.
 */
import { Component, Input, Output,EventEmitter,forEach } from '@angular/core';
import {pieceComponent} from '../piece/piece.component';


@Component({
  selector: 'app-player',
  templateUrl: '',
  styleUrls: ['']
})
export class PlayerComponent{
  @Input() pieces: pieceComponent[];
  isWinner: boolean;
  @Output()


  }

}
