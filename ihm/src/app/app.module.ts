import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule, JsonpModule} from '@angular/http';

import {CardComponent} from './card/card.component';
import {DeckComponent} from './deck/deck.component';
import {HandComponent} from './hand/hand.component';
import {GameBoardComponent} from './gameBoard/gameBoard.component';
import {PieceComponent} from './piece/piece.component';
import {PlayerComponent} from  './player/player.component';
import {PlayersComponent} from './players/players.component';
import {NewGameFormComponent} from "./forms/newGameForm.component";
import {ChooseNewOrLoadFormComponent} from "./forms/chooseNewOrLoadForm.component";

import {AppComponent} from './app.component';
import {Exchange} from "./exchange/exchange";
import {LoadGameFormComponent} from "./forms/loadGameForm.component";


@NgModule({
  declarations: [
    AppComponent,
    DeckComponent,
    CardComponent,
    HandComponent,
    GameBoardComponent,
    PieceComponent,
    PlayerComponent,
    PlayersComponent,
    ChooseNewOrLoadFormComponent,
    NewGameFormComponent,
    LoadGameFormComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    JsonpModule
  ],
  providers: [Exchange],
  bootstrap: [AppComponent]
})
export class AppModule {
}
