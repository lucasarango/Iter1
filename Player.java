import java.util.*;

/**************************************************************************************
 * Player is a state object managed by GameMaster. It doesn't influence the game
 * on its own and is only accessible by GameMaster.
 **************************************************************************************/
public class Player {
	private String name;
	private Inventory inventory;
	private int score;

	public Player(String name) {
		score = 0;
		this.name = name;
		inventory = new Inventory(this);
	}

	public String getName() {
		return name;
	}

	public List<Developer> getDevelopers() {
		return inventory.getDevelopers();
	}

	public List<Block> getBlocksLeft() {
		return inventory.getBlocksLeft();
	}

	public int getActionTokens() {
		return inventory.getActionTokens();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean useActionToken() {
		return inventory.useActionToken();
	}

	public void addToScore(int score) {
		this.score += score;
	}

	/*
	 * public boolean removeBlock(Block block) { try {
	 * inventory.removeBlock(block); return true; } catch
	 * (NoSuchElementException e) { return false; } }
	 */

	public void addBlock(Block block) {
		inventory.addBlock(block);
	}

	public Block getOneBlock() {
		return inventory.getOneBlock();

	}

	public Block getTwoBlock() {
		return inventory.getTwoBlock();

	}

	public boolean removeTwoBlock(Block block) {
		try {
			inventory.removeBlock(block);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean removeBlock(Block block) {
		try {
			inventory.removeBlock(block);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
