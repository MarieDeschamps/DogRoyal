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
import io.dog.service.LoadService;
import io.dog.service.PlayerService;
import io.dog.service.StartupService;

@Path("dog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
public class DogWS {

	@EJB
	StartupService startupService;
	
	@EJB
	PlayerService playerService;
	
	@EJB
	LoadService loadService;
	
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

		startupService.createDeck();
		startupService.createPiece(nbPlayers, nbPiecesByPlayer);

		return loadBdd();
	}
	
	@POST
	@Path("load")
	public ContainerForWS load(){
		return loadBdd();
	}
	
	@PUT
	@Path("pick5")
	public ContainerForWS pick5(){
		
		for (Player player : players) {
			for(int c = 0; c<5;c++){
				if(deck.isEmpty()){
					deck.shuffle();
					playerService.updateNewDeck();
		        }
				player.pick(deck);
			}
		}
		playerService.updatePickedCards(players);
		ContainerForWS result = new ContainerForWS(deck, players, whoPlayNow, true);
		return result;
	}
	
	@PUT
	@Path("play")
	public ContainerForWS play(Player player, Card card, Piece piece){
		if(!players.contains(player)){
			return new ContainerForWS(whoPlayNow, false,"the player is not in game");
		}
		if(player.getCards().isEmpty()){
			return new ContainerForWS(whoPlayNow, false,"the player has no cards");
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
			Piece comedPiece = gameBoard.samePosition(piece);
			List<Piece> modifiedPieces = new ArrayList<>();
			modifiedPieces.add(piece);
			if(comedPiece !=null){
				modifiedPieces.add(comedPiece);
			}
			playerService.updatePieces(modifiedPieces);
			playerService.updateDisguardCard(card);
		}else{
			for(Card c : player.getCards()){
				for(Piece p : player.getPieces()){
					if(player.playableCard(c, p)){
						return new ContainerForWS(whoPlayNow, false,"It exists a card in the player hand that can be played in a player piece");
					}
				}
			}
			player.disguardCard(deck, card);
			playerService.updateDisguardCard(card);
		}
		
		return new ContainerForWS(deck, players, whoPlayNow++,true);
	}
	
	private ContainerForWS loadBdd() {
		deck = loadService.getDeck();
		players = loadService.getPlayers();
		whoPlayNow = 1;
		for (int p=1;p<players.size();p++) {
			if(players.get(p).getCards().size()>players.get(p-1).getCards().size()){
				whoPlayNow = players.get(p-1).getNumber();
				break;
			}
		}
		return new ContainerForWS(deck, players, whoPlayNow, true);
	}
	
}
