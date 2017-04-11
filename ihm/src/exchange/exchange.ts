export function Exchange() {
    let beginPath = "dog/"
}

Exchange.prototype = {
    getGamesChoice: function(){
        return fetch(this.beginPath, { method: "GET" });
    },

    create: function (nbPlayers, nbPiecesByPlayer) {
        return fetch(this.beginPath+"create/{{nbPlayers}}/{{nbPiecesByPlayer}}", { method: "POST" });
    },

    /*load: function (game) {
        return fetch(this.beginPath+"load/{{game}}", { method: "POST" });
    },*/

    load: function () {
        return fetch(this.beginPath+"load", { method: "POST" });
    },

    pick5: function(){
        return fetch(this.beginPath+"pick5", { method: "PUT" });
    },

    play: function(player, card, piece){
        return fetch(this.path,
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