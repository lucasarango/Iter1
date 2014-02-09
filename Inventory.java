import java.util.ArrayList;
import java.util.List;


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
		}
		for(int i = 0; i < 3; i++) {
			// Create 3 one-space rice field land tiles
		}
		for(int i = 0; i < 2; i++) {
			// Create 2 one-space village/city tile
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
}
