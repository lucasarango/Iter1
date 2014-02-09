public class Space{

	int height;
	Block block;
	Tile tile;
	
	Space(){
		height = 0;
		block = null;
		tile = null;
	}

	public void placeBlock(Block b)
	{
		
		if(b.getTile() != null)
		{
			this.block = b;
			height++;
			this.tile = b.getTile();
		}
	}

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
}