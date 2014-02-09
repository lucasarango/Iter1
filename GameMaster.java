import java.util.*;
import java.awt.Color;

public class GameMaster {
	private Player currentPlayer;
	private int actionPoints;
	private int turnScore;
	private List<Player> playerList;
	
	public GameMaster(List<String> names, List<Color> colors) {
		playerList = new ArrayList<Player>();
		
		// Create new players and add them to the playerList
		for(int i = 0; i < names.size(); i++) {
			// Create player and add to list
			Player player = new Player(names.get(i), colors.get(i));
			playerList.add(player);
		}
	}
	
	public String getPlayerName() {
		return currentPlayer.getName();
	}
	
	public Color getPlayerColor() {
		return currentPlayer.getColor();
	}
	
	public List<Developer> getPlayerDevelopers() {
		return currentPlayer.getDevelopers();
	}
	
	public List<Block> getPlayerBlocks() {
		return currentPlayer.getBlocksLeft();
	}
	
	public int getPlayerScore() {
		return currentPlayer.getScore();
	}
	
	public boolean useActionToken() {
		return currentPlayer.useActionToken();
	}
	
	public boolean endTurn() {
		// Reset stuff
		// Add score to player
		// Transition to new player
	}
	
	// Removes block from player inventory and decrements action points.
	// Returns true if successful.
	// Returning false doesn't distinguish between insufficient action points or the block not existing.
	public boolean placeBlock(Block block) {
		if(actionPoints > 0) {
			return currentPlayer.removeBlock(block);
		}
		return false;
	}
	
	
	// Removes developer and decrements action points.
	// Returns true if successful.
	// Returns false if insufficient action points.
	public boolean moveDeveloper(Developer developer, Space newSpace) {
		Space oldSpice = developer.getSpace();
		int cost = 1;
		if(newSpace.getTile().getType() == oldSpice.getTile().getType()) {
			cost++;
		}
		if(cost > actionPoints) {
			return false;
		}
		else {
			developer.move(newSpace);
		}
	}
}
