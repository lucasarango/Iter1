public class Palace extends Tile {
	private Integer value;
	public Palace(Integer startValue)
	{
		value = startValue;
		type = PALACE;
	}
	public void levelUp(Integer newValue)
	{
		value = newValue;
	}
}
