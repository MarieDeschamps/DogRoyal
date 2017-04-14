import {Http, Response} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {Injectable} from "@angular/core";


@Injectable()
export class Exchange {
  beginPath = "http://10.31.0.17:8080/back_end_-0.0.1-SNAPSHOT/game/dog/";

  constructor(private http: Http) {
  }

  getGamesChoice() {
    return this.http.get(this.beginPath)
      .toPromise()
      .then((response: Response) => response.json());
  }


  create(nbPlayers, nbPiecesByPlayer) {
    return this.http.get(this.beginPath + "create/" + nbPlayers + "/" + nbPiecesByPlayer)
      .toPromise()
      .then((response: Response) => response.json());
  }

  loadGamePlayer(game, player){
    return this.http.get(this.beginPath + "load/"+game+"/"+player)
      .toPromise()
      .then((response: Response) => response.json());
  }

  loadGame (game) {
   return this.http.get(this.beginPath + "load/"+game)
   .toPromise()
   .then((response: Response) => response.json());
   }

  load() {
    return this.http.get(this.beginPath + "load")
      .toPromise()
      .then((response: Response) => response.json());
  }

  pick5(game) {
    return this.http.get(this.beginPath + "pick5/"+game)
      .toPromise()
      .then((response: Response) => response.json());
  }


  play(players, whoPlayNow) {
    let card = players[whoPlayNow - 1].hand.filter(function (card) {
      return card.chooseCard === false;
    });
    let piece = players[whoPlayNow - 1].pieces.filter(function (piece) {
      return piece.choosePiece === true;
    });

    let data = {
      "player": {
        "id": whoPlayNow
      },
      "card": {
        "id": card[0].id
      },
      "piece": {
        "id": piece[0].id
      }
    }
    console.log(data);
    return this.http.put(this.beginPath + "play", data)
      .toPromise()
      .then((response: Response) => response.json());
  }
}

export default Exchange;

