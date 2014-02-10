public class Space {

	int height;
	Block block;
	Tile tile;

	Space() {
		height = 0;
		block = null;
		tile = null;
	}

	public void placeBlock(Block b, Tile t) {
		this.block = b;
		height++;
		this.tile = t;
	}

	public Block getBlock() {
		return this.block;
	}

	public Tile getTile() {
		return this.tile;
	}

	public int getHeight() {
		return this.height;
	}

	public boolean equals(Space s) {
		if (this.height == s.getHeight() && this.tile.equals(s.getTile())
				&& this.block.equals(s.getBlock())) {
			return true;
		} else {
			return false;
		}
	}
}
