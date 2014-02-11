public class Palace extends Tile {
	private Integer value;
	public Palace(Integer startValue)
	{
		value = startValue;
	}
	public void levelUp(Integer newValue)
	{
		value = newValue;
	}
	public Integer getValue()
	{
		return value;
	}
}