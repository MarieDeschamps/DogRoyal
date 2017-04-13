package io.dog.ws;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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
import io.dog.service.UpdateService;
import io.dog.service.CreateService;

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
	public List<Integer> gamesChoice(){
		List<Integer> games = new ArrayList<>();
		games.add(1);
		games.add(2);
		//get la liste des ids des parties
		return games;
	}
	
	@GET
	@Path("create/{nbPlayers}/{nbPiecesByPlayer}")
	public ContainerForOutputWS create(@PathParam("nbPlayers") int nbPlayers, @PathParam("nbPiecesByPlayer") int nbPiecesByPlayer){
		if(nbPlayers<2){
			return new ContainerForOutputWS(0, false, "There must be almost 2 players");
		}
		if(nbPiecesByPlayer<=0){
			return new ContainerForOutputWS(0, false, "There must be almost 1 piece by player");
		}
		createService.deleteGame();
		createService.createDeck();
		createService.createPiece(nbPlayers, nbPiecesByPlayer);

		this.loadBdd();
		return new ContainerForOutputWS(deck, players, whoPlayNow, true);
	}
	
	@GET
	@Path("load")
	public ContainerForOutputWS load(){
		this.loadBdd();
		return new ContainerForOutputWS(deck, players, whoPlayNow, true);
	}
	
	@GET
	@Path("pick5")
	public ContainerForOutputWS pick5(){
		this.loadBdd();
		
		//guard
		for (Player player : players) {
			if(player.getCards()!=null && !player.getCards().isEmpty()){
				return new ContainerForOutputWS(whoPlayNow, false,"the players already have cards");
			}
		}
		
		for (Player player : players) {
			for(int c = 0; c<5;c++){
				if(deck.isEmpty()){
					deck.shuffle();
					updateService.updateNewDeck();
		        }
				player.pick(deck);
			}
		}
		updateService.updatePickedCards(players);
		ContainerForOutputWS result = new ContainerForOutputWS(deck, players, whoPlayNow, true);
		return result;
	}
	
	@PUT
	@Path("play")
	public ContainerForOutputWS play(ContainerForInputWS input){
		Player player = input.getPlayer();
		Card card = input.getCard();
		Piece piece = input.getPiece();
		this.loadBdd();
		
		//guards
		if(!players.contains(player)){
			return new ContainerForOutputWS(whoPlayNow, false,"the player is not in game");
		}
		player = players.get(players.indexOf(player));
		
		if(player.getId()!=whoPlayNow){
			return new ContainerForOutputWS(whoPlayNow, false,"it's not the player "+ player.getId() + " turn");
		}
		
		if(player.getCards().isEmpty()){
			return new ContainerForOutputWS(whoPlayNow, false,"the player has no cards");
		}
		
		if(!player.getCards().contains(card)){
			return new ContainerForOutputWS(whoPlayNow, false, "the player have not this card");
		}
		card  = player.getCards().get(player.getCards().indexOf(card));
		
		if(!player.getPieces().contains(piece)){
			return new ContainerForOutputWS(whoPlayNow, false, "the player have not this piece");
		}
		piece  = player.getPieces().get(player.getPieces().indexOf(piece));
		
		//play
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
			updateService.updatePieces(modifiedPieces);
			player.disguardCard(deck, card);
			updateService.updateDisguardCard(card);
		}else{
			for(Card c : player.getCards()){
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
		ContainerForOutputWS result = new ContainerForOutputWS(deck, players, whoPlayNow,true);
		if(winner){
			result.setWinner(player);
			createService.deleteGame();
		}
		return result;
	}
	
	private void loadBdd() {
		deck = loadService.getDeck();
		deck.shufflePickable();
		players = loadService.getPlayers();
		
		List<Piece> pieces = new ArrayList<>();
		for (Player p : players) {
			pieces.addAll(p.getPieces());
		}
		gameBoard = new GameBoard(players.size(), pieces);
		
		whoPlayNow = 1;
		for (int p=1;p<players.size();p++) {
			if(players.get(p).getCards()!= null && players.get(p-1).getCards()!= null && players.get(p).getCards().size()>players.get(p-1).getCards().size()){
				whoPlayNow = players.get(p).getId();
				break;
			}
		}
	}
	
}
