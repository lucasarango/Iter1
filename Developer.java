public class Developer {
	private Space location;
	private Player player;

	
	public Developer(Player p) {
		this.player = p;
	}
	
	public boolean move(Space s) {
		this.location=s;
		return true;
	}
	
	public Space getSpace() {
		return location;
	}

	public void score(int score){
		player.score(score);
	}
}