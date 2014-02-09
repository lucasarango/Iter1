import java.awt.Color;
import java.util.NoSuchElementException;
/**************************************************************************************
 *  Player is a state object managed by GameMaster. It doesn't influence the game on
 *  its own and is only accessible by GameMaster. 
 **************************************************************************************/
public class Player {
	private String name;
	private Color color;
	private Inventory inventory;
	private int score;
	
	public Player(String name, Color color) {
		score = 0;
		this.name = name;
		this.color = color;
		inventory = new Inventory();
	}
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
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
	
	public boolean useActionToken() {
		return inventory.useActionToken();
	}
	
	public void addToScore(int score) {
		this.score += score;
	}
	
	public boolean removeBlock(Block block) {
		try {
			inventory.removeBlock(block);
			return true;
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
}