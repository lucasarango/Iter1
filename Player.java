import java.io.PrintWriter;
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

	/**this save function assumes it will be encapsulated in an xml tag*/
	public void save(PrintWriter p){
		p.append("name:"+this.name+"\n");
		p.append("<inv>\n");
		inventory.save(p);
		p.append("</inv>\n");
	}
	
	/**this load function assumes it will be encapsulated in an xml tag,
	 * and will exit when it sees a closing tag of any kind that it did
	 * not create*/
	public void load(Scanner reader){
		
		inventory = new Inventory(this);
		
		//this will be escaped with a break;
		while(true){
			
			String line=reader.nextLine().replace("//s+", "");
			
			if (line.startsWith("</")){
				break;
			
			}else if (line.startsWith("<inv>")){
				inventory.load(reader, this);
			}else{
				int colonIndex = line.indexOf(':');
				if (colonIndex>0){
					String tag = line.substring(0, colonIndex);
					String value = line.substring(colonIndex + 1, line.length());
					if (tag.equals("name")){
						this.name=value;
					}
				}
			}
		}//end while(true)
	}
}
