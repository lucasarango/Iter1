<<<<<<< HEAD
public class Space {
=======
public class Space{
>>>>>>> board

	int height;
	Block block;
	Tile tile;
<<<<<<< HEAD

	Space() {
=======
	
	Space(){
>>>>>>> board
		height = 0;
		block = null;
		tile = null;
	}

<<<<<<< HEAD
	public void placeBlock(Block b, Tile t) {
=======
	public void placeBlock(Block b, Tile t)
	{
>>>>>>> board
		this.block = b;
		height++;
		this.tile = t;
	}

<<<<<<< HEAD
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
=======
	public Block getBlock()
	{
		return this.block;
	}

	public Tile getTile()
	{
		return this.tile;
	}

	public int getHeight()
	{
		return this.height;
	}
>>>>>>> board
}
