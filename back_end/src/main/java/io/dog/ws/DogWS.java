package io.dog.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.dog.dto.Card;
import io.dog.dto.Deck;
import io.dog.dto.GameBoard;
import io.dog.dto.Piece;
import io.dog.dto.Player;
import io.dog.service.StartupService;

@Path("dog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class DogWS {

	@EJB
	StartupService startupService;
	
	Deck deck;
	GameBoard gameBoard;
	List<Player> players;
	int whoPlayNow;
	
	@GET
	public List<Integer> gamesChoice(){
		List<Integer> games = new ArrayList<>();
		//get la liste des ids des parties
		return games;
	}
	
	@POST
	@Path("create/{nbPlayers}/{nbPiecesByPlayer}")
	public ContainerForWS create(@PathParam("nbPlayers") int nbPlayers, @PathParam("nbPiecesByPlayer") int nbPiecesByPlayer){
		if(nbPlayers<2){
			return new ContainerForWS(0, false, "There must be almost 2 players");
		}
		if(nbPiecesByPlayer<=0){
			return new ContainerForWS(0, false, "There must be almost 1 piece by player");
		}
		//creation de la bdd avec startupService
		//load des donnees depuis la bdd
		
		//TODO
		return null;
	}
	
	@POST
	@Path("load")
	public ContainerForWS load(){
		//load des donnees depuis la bdd
		//TODO
		return null;
	}
	
	@PUT
	@Path("pick5")
	public ContainerForWS pick5(){
		for (Player player : players) {
			player.pick(deck, 5);
		}
		ContainerForWS result = new ContainerForWS(deck, players, whoPlayNow, true);
		return result;
	}
	
	@PUT
	@Path("play")
	public ContainerForWS play(Player player, Card card, Piece piece){
		if(!players.contains(player)){
			return new ContainerForWS(whoPlayNow, false,"the player is not in game");
		}
		if(!player.getCards().contains(card)){
			return new ContainerForWS(whoPlayNow, false, "the player have not this card");
		}
		if(!player.getPieces().contains(piece)){
			return new ContainerForWS(whoPlayNow, false, "the player have not this piece");
		}
		
		if(player.playableCard(card, piece)){
			if(piece.isStatus()==false){
				gameBoard.startPiece(piece);
			}else{
				gameBoard.movePiece(piece, card.getValue());
			}
			gameBoard.samePosition(piece);
		}else{
			for(Card c : player.getCards()){
				for(Piece p : player.getPieces()){
					if(player.playableCard(c, p)){
						return new ContainerForWS(whoPlayNow, false,"It exists a card in the player hand that can be played in a player piece");
					}
				}
			}
			player.disguardCard(deck, card);
		}
		
		return new ContainerForWS(deck, players, whoPlayNow++,true);
	}
}
