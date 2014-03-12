public class PalaceTile extends Tile {
	private Integer value;
	public PalaceTile(Integer startValue)
	{
		value = startValue;
	}
	public boolean levelUp(Integer newValue)
	{
		//Assumes input is always an even integer
		if(newValue > value  && newValue <= 10)
		{
      value = newValue;
      return true;
		}
		else
		{
      return false;
		}
	}
	public Integer getValue()
	{
		return value;
	}
}
