import java.io.PrintWriter;
import java.util.*;

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

	/**this save function assumes it will be encapsulated in an xml tag*/
	public void save(PrintWriter p){
		for (Developer d:developers){
			p.append("dev:");
			if (d.getSpace()!=null){
				p.append(d.getSpace().x+""+d.getSpace().y);
			}
			p.append('\n');
		}
		for (Block block: oneBlocksLeft){
			p.append("<"+OneBlock.class.getName()+">\n");
			block.save(p);
			p.append("</"+OneBlock.class.getName()+">\n");
		}
		for (Block block: twoBlocksLeft){
			p.append("<"+TwoBlock.class.getName()+">\n");
			block.save(p);
			p.append("</"+TwoBlock.class.getName()+">\n");
		}
	}
	
	/**this load function assumes it will be encapsulated in an xml tag,
	 * and will exit when it sees a closing tag of any kind that it did
	 * not create*/
	public void load(Scanner reader, Player owner){
		
		//clear current lists
		developers=new ArrayList<Developer>();
		oneBlocksLeft=new ArrayList<Block>();
		twoBlocksLeft=new ArrayList<Block>();
		
		//this will be escaped with a break;
		while(true){
			
			String line=reader.nextLine().replace("//s+", "");
			
			if (line.startsWith("</")){
				break;
			
			}else if (line.startsWith("<")){
				String className = line.substring(1, line.indexOf(">"));
				Block newb=null;
				ArrayList<Block> destination = null;
				if (className.equals(OneBlock.class.getName())){
					newb = new OneBlock(new VillageTile());
					destination=oneBlocksLeft;
				}
				if (className.equals(TwoBlock.class.getName())){
					newb = new TwoBlock();
					destination=twoBlocksLeft;
				}
				if (newb!=null){
					newb.load(reader);
					destination.add(newb);
				}
			}else{
				int colonIndex = line.indexOf(':');
				if (colonIndex>0){
					String tag = line.substring(0, colonIndex);
					String value = line.substring(colonIndex + 1, line.length());
					if (tag.equals("dev")){
						Developer newb =new Developer(owner); 
						developers.add(newb);
						if (value.length()>0){
							String[] xy=value.split(",");
							newb.move(new Space(Integer.parseInt(xy[0]), Integer.parseInt(xy[1])));
						}
					}
				}
			}
		}//end while(true)
	}
}