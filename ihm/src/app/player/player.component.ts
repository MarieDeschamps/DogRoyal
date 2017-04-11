/**
 * Created by Camille on 10/04/2017.
 */
import { Component, Input, Output} from '@angular/core';
import {Player} from '../model';


@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styles: ['']
})
export class PlayerComponent{
  @Input() player : Player;


  }


