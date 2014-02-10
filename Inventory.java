import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**************************************************************************************
 *  Inventory is a state object managed by Player. It doesn't influence the game on its
 *  own and is only accessible by the Player that owns it. 
 **************************************************************************************/

public class Inventory {
	private List<Developer> developers;
	private List<Block> blocksLeft;
	private int actionTokens;
	
	public Inventory() {
		// Create initial developer list: 12 developers per player
		developers = new ArrayList<Developer>();
		for(int i = 0; i < 12; i++) {
			developers.add(new Developer());
		}
		
		// Create initial block list:
		// 5 two-space land tiles, 3 one-space rice field land tiles, 2 one-space village/city tile
		blocksLeft = new ArrayList<Block>();
		for(int i = 0; i < 5; i++) {
			// Create 5 two-space land tiles
			blocksLeft.add(new TwoBlock());
		}
		for(int i = 0; i < 3; i++) {
			// Create 3 one-space rice field land tiles
			blocksLeft.add(new OneBlock(RICE));
		}
		for(int i = 0; i < 2; i++) {
			// Create 2 one-space village/city tile
			blocksLeft.add(new OneBlock(VILLAGE));
		}
		
		actionTokens = 3;
	}
	
	public int getActionTokens() {
		return actionTokens;
	}
	public boolean useActionToken() {
		if(actionTokens > 0) {
			actionTokens--;
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<Developer> getDevelopers() {
		return developers;
	}
	
	public List<Block> getBlocksLeft() {
		return blocksLeft;
	}
	
	public void removeBlock(Block block) {
		if(!blocksLeft.contains(block)) {
			throw new NoSuchElementException();
		}
		blocksLeft.remove(block);
	}

	public void addBlock(Block block) {
    blocksLeft.add(block);
	}
}
