import java.util.*;

public class GameMaster {
	private Player currentPlayer;
	private int actionPoints;
	private int turnScore;
	private List<Player> playerList;
	private Position pos = new Position();

	public GameMaster(List<String> names) {
		playerList = new ArrayList<Player>();
		// Create new players and add them to the playerList
		for (int i = 0; i < names.size(); i++) {
			// Create player and add to list
			Player player = new Player(names.get(i));
			playerList.add(player);
		}
		currentPlayer = playerList.get(0);
		actionPoints = 6;
	}

	public String getPlayerName() {
		return currentPlayer.getName();
	}

	public int getActionPoints() {
		return actionPoints;

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

	public void endTurn() {
		// Reset stuff
		// Add score to player
		// Transition to new player
		int turn = playerList.indexOf(currentPlayer);
		turn++;
		if (turn >= playerList.size()) {
			turn -= playerList.size();
		}
		actionPoints = 6;
		currentPlayer.addToScore(turnScore);
		turnScore = 0;
		currentPlayer = playerList.get(turn);
	}

	public void placeBlock(Block block) {
		// Removes block from player inventory and decrements action points.
		// Returns true if successful.
		// Returning false doesn't distinguish between insufficient action
		// points or
		// the block not existing.
		if (actionPoints > 0) {
			currentPlayer.removeBlock(block);

		}
	}

	public void returnBlock(Block block) {
		currentPlayer.addBlock(block);
	}

	public boolean moveDeveloper(Developer developer, Space newSpace) {
		// Removes developer and decrements action points.
		// Returns true if successful.
		// Returns false if insufficient action points.
		if (Position.isThereDeveloper(developer.getSpace())) {
			pos.removePair(developer.getSpace());
		}
		pos.addPair(newSpace, developer);

		Space oldSpice = developer.getSpace();
		int cost = 1;

		// Implement a better way to do this

		/*
		 * if (newSpace.getTile().getType().equal ==
		 * oldSpice.getTile().getType()) { cost++; }
		 */

		if (cost > actionPoints) {
			return false;
		} else {
			developer.move(newSpace);
			actionPoints -= cost;
		}
		return true;
	}

	public void upgradePalace(int newLevel) {
		turnScore += newLevel / 2;
	}

	public void scoreIrrigationTiles(int numOfITiles) {
		turnScore += numOfITiles * 3;
	}

	public int getPlayerActionTokens() {
		return currentPlayer.getActionTokens();
	}

	// Probably Mediator actions, since it is specifically asking for ONE of
	// each
	public Block getOneBlock() {
		Block b = currentPlayer.getOneBlock();
		currentPlayer.removeBlock(b);

		return b;
	}

	public Block getTwoBlock() {
		Block b = currentPlayer.getTwoBlock();
		currentPlayer.removeBlock(b);

		return b;
	}

	public Developer getDeveloper(int i) {
		Developer d = currentPlayer.getDevelopers().get(i);

		return d;
	}

}
