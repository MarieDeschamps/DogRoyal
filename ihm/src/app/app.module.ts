import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {CardComponent} from './card/card.component';
import {DeckComponent} from './deck/deck.component';
import {HandComponent} from './hand/hand.component';
import {GameBoardComponent} from './gameBoard/gameBoard.component';
import {PieceComponent} from './piece/piece.component';
import {PlayerComponent} from  './player/player.component';

import {AppComponent} from './app.component';


@NgModule({
  declarations: [
    AppComponent,
    DeckComponent,
    CardComponent,
    HandComponent,
    GameBoardComponent,
    PieceComponent,
    PlayerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
