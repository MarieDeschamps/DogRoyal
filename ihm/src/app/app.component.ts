import {Component} from '@angular/core';

interface PlayerState {
  piecePosition: number [];
  cards: number[];
  nOfPiecesLeft: number;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {


  casseStart(idPlayer) {
    (idPlayer - 1) * 16;
  };

  casseWin(idPlayer) {
    ((idPlayer - 1) * 16) - 1;
  }

  chooseCard = false;
  winner = null;

  chooseOneCard(nOfCard: number) {
    if (nOfCard == 14) {
      //TODO un pion commence a jouer
    } else {
      //TODO le joueur choisi un pion pour avancer
      //if position pion == position de un autre pion => position pion =0;
      //else poisition pion = position+nOfCard
      //check this.player wins
    }


  }

  chooseOnePiece(piecePosition: number[]) {

  }

}
