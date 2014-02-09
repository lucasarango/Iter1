
public abstract class Tile {
	private Tile.TileTypes type;
	public enum TileTypes { PALACE, VILLAGE, RICE, IRRIGATION };
	
	public Tile.TileTypes getType()
	{
		return type;
	}
}
