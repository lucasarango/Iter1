
public abstract class Tile {
	private Integer type;
	public enum TileTypes { PALACE, VILLAGE, RICE, IRRIGATION };
	
	public Tile.TileTypes getType()
	{
		return type;
	}
}
