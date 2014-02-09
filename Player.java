import java.util.NoSuchElementException;
import java.awt.Color;
/**************************************************************************************
 *  Player is a state object managed by GameMaster. It doesn't influence the game on
 *  its own and is only accessible by GameMaster. 
 **************************************************************************************/
public class Player {
	private int turnScore;
	private String name;
	private Color color;
	private Inventory inventory;
	private int actionTokens;
	
	public Player(String name, Color color) {
		turnScore = 0;
		this.name = name;
		this.color = color;
		inventory = new Inventory();
		actionTokens = 3;
	}
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public List<Developer> getDevelopers() {
		return developers;
	}
	
	public List<Block> getBlocksLeft() {
		return blocksLeft;
	}
	
	public int getActionTokens() {
		return actionTokens;
	}
	
	public boolean useActionToken() {
		return Inventory.useActionToken();
	}
	
	public Developer getDeveloper(int coord_x, int coord_y) throws NoSuchElementException{
		for(Developer d : developers) {
			if(d.getXCoord() == coord_x && d.getYCoord() == coord_y) {
				return d;
			}
		}
		throw new NoSuchElementException();
	}
}