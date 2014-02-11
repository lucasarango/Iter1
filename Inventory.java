import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**************************************************************************************
 * Inventory is a state object managed by Player. It doesn't influence the game
 * on its own and is only accessible by the Player that owns it.
 **************************************************************************************/

public class Inventory {
	private ArrayList<Developer> developers;
	private ArrayList<Block> blocksLeft;
	private ArrayList<Block> oneBlocksLeft;
	private ArrayList<Block> twoBlocksLeft;

	private int actionTokens;

	public Inventory(Player p) {
		// Create initial developer list: 12 developers per player
		developers = new ArrayList<Developer>();
		for (int i = 0; i < 12; i++) {
			developers.add(new Developer(p));
		}

		// Create initial block list:
		// 5 two-space land tiles, 3 one-space rice field land tiles, 2
		// one-space village/city tile
		oneBlocksLeft = new ArrayList<Block>();
		twoBlocksLeft = new ArrayList<Block>();

		for (int i = 0; i < 3; i++) {
			// Create 3 one-space rice field land tiles
			oneBlocksLeft.add(new OneBlock(new RiceTile()));
		}
		for (int i = 0; i < 2; i++) {
			// Create 2 one-space village/city tile
			oneBlocksLeft.add(new OneBlock(new VillageTile()));
		}
		for (int i = 0; i < 5; i++) {
			// Create 5 two-space land tiles
			twoBlocksLeft.add(new TwoBlock());
		}
		actionTokens = 3;
	}

	public int getActionTokens() {
		return actionTokens;
	}

	public boolean useActionToken() {
		if (actionTokens > 0) {
			actionTokens--;
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Developer> getDevelopers() {
		return developers;
	}

	public ArrayList<Block> getBlocksLeft() {
		blocksLeft = new ArrayList<Block>();
		blocksLeft.addAll(oneBlocksLeft);
		blocksLeft.addAll(twoBlocksLeft);
		return blocksLeft;
	}

	public void removeBlock(Block block) {
		if (block instanceof OneBlock)
			oneBlocksLeft.remove(block);
		if (block instanceof TwoBlock)
			twoBlocksLeft.remove(block);
	}

	public void addBlock(Block block) {
		blocksLeft.add(block);
	}

	public ArrayList<Block> getTwoBlocksLeft() {
		return twoBlocksLeft;
	}

	public ArrayList<Block> getOneBlocksLeft() {
		return oneBlocksLeft;
	}

	public Block getTwoBlock() {
		return twoBlocksLeft.get(0);
	}

	public Block getOneBlock() {
		return oneBlocksLeft.get(0);
	}

}
