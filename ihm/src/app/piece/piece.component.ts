/**
 * Created by Camille on 10/04/2017.
 */
import { Component, Input, Output} from '@angular/core';
import {Piece} from '../model';

@Component({
  selector: 'app-piece',
  templateUrl: './piece.component.html',
  styles: ['']
})

export class PieceComponent{
@Input() piece:Piece ;

}
