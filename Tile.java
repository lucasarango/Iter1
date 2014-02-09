
public abstract class Tile {
	private Tile.TileType type;
	public enum TileType { PALACE, VILLAGE, RICE, IRRIGATION };
	
	public Tile.TileType getType()
	{
		return type;
	}
}
