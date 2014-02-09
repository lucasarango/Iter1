public class Player {
	private int turnScore;
	private String name;
	private Color color;
	private List<Developer> developers;
	private List<Block> blocks;
	private int actionTokens;
	
	public Player(String name, Color color, List<Developer> developers, List<Block> blocks) {
		turnScore = 0;
		this.name = name;
		this.color = color;
		this.developers = developers;
		this.blocks = blocks;
		actionTokens = 3;
	}
	
	public boolean useActionToken() {
		if(actionToken > 0) {
			actionToken--;
			return true;
		}
		else {
			return false;
		}
	}
}