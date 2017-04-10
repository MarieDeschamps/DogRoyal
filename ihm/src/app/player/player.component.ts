/**
 * Created by Camille on 10/04/2017.
 */
import { Component, Input, Output} from '@angular/core';
import {PieceComponent} from '../piece/piece.component';


@Component({
  selector: 'app-player',
  templateUrl: '',
  styleUrls: ['']
})
export class PlayerComponent{
  @Input() pieces: PieceComponent[];
  isWinner: boolean;
  @Output()


  }

}
