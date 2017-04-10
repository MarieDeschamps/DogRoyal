import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {CardComponent} from './card/card.component';
import {DeckComponent} from './deck/deck.component';
import {HandComponent} from './hand/hand.component';
import {GameBordComponent} from './gameBord/gameBord.component';

import {AppComponent} from './app.component';


@NgModule({
  declarations: [
    AppComponent,
    DeckComponent,
    CardComponent,
    HandComponent,
    GameBordComponent
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
