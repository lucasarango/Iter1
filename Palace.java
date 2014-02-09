public class Palace extends Tile {
	protected Integer value;
	public Palace(Integer startValue)
	{
		value = startValue;
		type = Tile.TileTypes.PALACE;
	}
	public void levelUp(Integer newValue)
	{
		value = newValue;
	}
}