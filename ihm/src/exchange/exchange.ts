import {Http, Response} from "@angular/http";
import "rxjs/add/operator/toPromise";



export class Exchange {
  beginPath = "dog/";

  constructor(private http: Http) {
  }

  getGamesChoice() {
    return this.http.get(this.beginPath)
      .toPromise()
      .then((response: Response) => response.json());
  }


  create(nbPlayers, nbPiecesByPlayer) {
    return this.http.post(this.beginPath + "create/{{nbPlayers}}/{{nbPiecesByPlayer}}","")
        .toPromise()
        .then((response: Response) => response.json());
  }

  /*load (game) {
   return this.http.post(this.beginPath + "load/{{game}}","")
        .toPromise()
        .then((response: Response) => response.json());
    }*/

  load() {
    return this.http.post(this.beginPath + "load","")
        .toPromise()
        .then((response: Response) => response.json());
  }

  pick5() {
    return this.http.put(this.beginPath + "pick5", "")
        .toPromise()
        .then((response: Response) => response.json());
  }

  play(player, card, piece) {
    let data = {
        "player": player,
        "card" : card,
        "piece": piece 
    }
    return this.http.post(this.beginPath+"play",JSON.stringify(data))
        .toPromise()
        .then((response: Response) => response.json());
  }
}

export default Exchange;
 
