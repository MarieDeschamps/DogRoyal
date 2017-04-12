import {Http, Response} from "@angular/http";
import "rxjs/add/operator/toPromise";



export class Exchange {
  beginPath = "dog/";

  constructor(private http: Http) {
  }

  getGamesChoice() {
    this.http.get(this.beginPath)
      .toPromise()
      .then((response: Response) => response.json());
  }
}
/*
  create(nbPlayers, nbPiecesByPlayer) {
    return fetch(this.beginPath + "create/{{nbPlayers}}/{{nbPiecesByPlayer}}", {method: "POST"});
  }

  /!*load (game) {
   return fetch(this.beginPath+"load/{{game}}", { method: "POST" });
   },*!/

  load() {
    return fetch(this.beginPath + "load", {method: "POST"});
  }

  pick5() {
    return fetch(this.beginPath + "pick5", {method: "PUT"});
  }

  play(player, card, piece) {
    return fetch(this.beginPath,
      {
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify(player, card, piece)
      })
  }
}
export default Exchange;
 */
