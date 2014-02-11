public class PalaceTile extends Tile {
	private Integer value;
	public PalaceTile(Integer startValue)
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