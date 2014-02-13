public class Space{

	int height;
	Block block;
	Tile tile;
	int x,y;
	
	Space(){
		height = 0;
		block = null;
		tile = null;
	}

	public Space(int x,int y){
		height = 0;
		block = null;
		tile = null;
		this.x=x;
		this.y=y;
	}
	
	public void placeBlock(Block b, Tile t)
	{
		this.block = b;
		height++;
		this.tile = t;
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