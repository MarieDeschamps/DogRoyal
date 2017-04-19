package io.dog.ws;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
import io.dog.service.CreateService;
import io.dog.service.LoadService;
import io.dog.service.UpdateService;

@Path("dog")
@Produces(MediaType.APPLICATION_JSON)
@Consumes("application/json")
@RequestScoped
public class DogWS {

	@EJB
	CreateService createService;
	
	@EJB
	UpdateService updateService;
	
	@EJB
	LoadService loadService;
	
	Deck deck;
	GameBoard gameBoard;
	List<Player> players;
	int whoPlayNow;
	
	@GET
	public HashMap<String, String> getGames(){
		return loadService.getGames();
	}
	
	@GET
	@Path("create/{nbPlayers}/{nbPiecesByPlayer}")
	public ContainerForOutputWS createAll(@PathParam("nbPlayers") int nbPlayers, @PathParam("nbPiecesByPlayer") int nbPiecesByPlayer){
		if(nbPlayers<2){
			return new ContainerForOutputWS(0, false, "There must be almost 2 players");
		}
		if(nbPiecesByPlayer<=0){
			return new ContainerForOutputWS(0, false, "There must be almost 1 piece by player");
		}
	
		int game_id = createService.createAll(nbPlayers, nbPiecesByPlayer);

		this.loadBdd(game_id);
		updateService.updateFree(game_id, 1, false);
		return new ContainerForOutputWS(deck, players, whoPlayNow,true,game_id);
	}
	
	@GET
	@Path("load/{game_id}")
	public ContainerForOutputWS load(@PathParam("game_id") int game_id){
		//guards load
		if(!loadService.isGameExist(game_id)){
			return new ContainerForOutputWS(0, false, "Game doessn't exist");
		}
		this.loadBdd(game_id);
		return new ContainerForOutputWS(deck, players, whoPlayNow, true,game_id);
	}
	
	@GET
	@Path("load/{game_id}/{player_id}")
	public ContainerForOutputWS load(@PathParam("game_id") int game_id,@PathParam("player_id") int player_id){
		//guards load
		if(!loadService.isGameExist(game_id)){
			return new ContainerForOutputWS(0, false, "Game doessn't exist");
		}
		this.loadBdd(game_id);
		updateService.updateFree(game_id, player_id, false);
		return new ContainerForOutputWS(deck, players, whoPlayNow, true,game_id);
	}
	
	@GET
	@Path("pick5/{game_id}")
	public ContainerForOutputWS pick5(@PathParam("game_id") int game_id){
		//guards load
		if(!loadService.isGameExist(game_id)){
			return new ContainerForOutputWS(0, false, "Game doessn't exist");
		}
		
		this.loadBdd(game_id);
		
		//guard
		for (Player player : players) {
			if(player.getHand()!=null && !player.getHand().isEmpty()){
				return new ContainerForOutputWS(whoPlayNow, false,"the players already have cards");
			}
		}
		
		for (Player player : players) {
			for(int c = 0; c<5;c++){
				if(deck.isEmpty()){
					deck.shuffle();
					updateService.updateNewDeck(game_id);
		        }
				player.pick(deck);
			}
		}
		updateService.updatePickedCards(players);
		ContainerForOutputWS result = new ContainerForOutputWS(deck, players, whoPlayNow, true,game_id);
		return result;
	}
	
	@PUT
	@Path("play")
	public ContainerForOutputWS playByGameId(ContainerForInputWS input){
		Player player = input.getPlayer();
		Card card = input.getCard();
		Piece piece = input.getPiece();
		int game_id = loadService.getGameId(card.getId());
		
		//Guards before bdd
		
		if(!loadService.isGoodGameId(card.getId(), piece.getId())){
			return new ContainerForOutputWS(whoPlayNow, false,"card and piece are not in the same game");
		}
		
		this.loadBdd(game_id);
		
		//guards after
		if(!players.contains(player)){
			return new ContainerForOutputWS(whoPlayNow, false,"the player is not in game");
		}
		player = players.get(players.indexOf(player));
		
		if(player.getId()!=whoPlayNow){
			return new ContainerForOutputWS(whoPlayNow, false,"it's not the player "+ player.getId() + " turn");
		}
		
		if(player.getHand().isEmpty()){
			return new ContainerForOutputWS(whoPlayNow, false,"the player has no cards");
		}
		
		if(!player.getHand().contains(card)){
			return new ContainerForOutputWS(whoPlayNow, false, "the player have not this card");
		}
		card  = player.getHand().get(player.getHand().indexOf(card));
		
		if(!player.getPieces().contains(piece)){
			return new ContainerForOutputWS(whoPlayNow, false, "the player have not this piece");
		}
		piece  = player.getPieces().get(player.getPieces().indexOf(piece));
		
		//play
		if(player.playableCard(card, piece)){
			if(piece.isReady()==false){
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
			updateService.updatePieces(modifiedPieces); 
			player.disguardCard(deck, card);
			updateService.updateDisguardCard(card);
		}else{
			for(Card c : player.getHand()){
				for(Piece p : player.getPieces()){
					if(player.playableCard(c, p)){
						return new ContainerForOutputWS(whoPlayNow, false,"It exists a card in the player hand that can be played in a player piece");
					}
				}
			}
			player.disguardCard(deck, card);
			updateService.updateDisguardCard(card);
		}
		whoPlayNow++;
		boolean winner = true;
		for(Piece p : player.getPieces()){
			if(!p.isArrived()){
				winner = false;
			}
		}
		ContainerForOutputWS result = new ContainerForOutputWS(deck, players, whoPlayNow,true,game_id);
		if(winner){
			result.setWinner(player);
			createService.deleteGame(game_id);
		}
		return result;
	}
	
	@GET
	@Path("free/{game_id}/{player_id}")
	public void free(@PathParam("game_id") int game_id,@PathParam("player_id") int player_id){
		updateService.updateFree(game_id, player_id, true);
	}
	
	
	private void loadBdd(int game_id) {
		deck = loadService.getDeck(game_id);
		deck.shufflePickable();
		players = loadService.getPlayers(game_id);
		
		List<Piece> pieces = new ArrayList<>();
		for (Player p : players) {
			pieces.addAll(p.getPieces());
		}
		gameBoard = new GameBoard(players.size(), pieces);
		
		whoPlayNow = 1;
		for (int p=1;p<players.size();p++) {
			if(players.get(p).getHand()!= null && players.get(p-1).getHand()!= null && players.get(p).getHand().size()>players.get(p-1).getHand().size()){
				whoPlayNow = players.get(p).getId();
				break;
			}
		}
	}
	
	
	
}
