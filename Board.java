import java.util.*;

public class Board{

	private Space[][] spaces;
	private List<Block> threeBlocksLeft;
	private List<Block> irrigationsLeft;
	private int[] dimensions;
	

	public Board(){
		threeBlocksLeft = new ArrayList<Block>();

		for(int i = 0; i < 56; i++)
		{
			threeBlocksLeft.add(new ThreeBlock());
		}
		
		irrigationsLeft = new ArrayList<Block>();

		for(int i = 0; i < 12; i++)
		{
			irrigationsLeft.add(new OneBlock(new IrrigationTile()));
		}
		
		spaces = new Space[14][14];
		dimensions = new int[2];
		dimensions[0] = 14;
		dimensions[1] = 14;

		for(int i = 0; i < dimensions[0]; i++)
		{
			for(int j = 0; j < dimensions[1]; j++)
			{
				spaces[i][j] = new Space();
			}
		}
		
	}

	public Board(int x, int y){
		threeBlocksLeft = new ArrayList<Block>();

		for(int i = 0; i < 56; i++)
		{
			threeBlocksLeft.add(new ThreeBlock());
		}
		
		spaces = new Space[x+2][y+2];
		dimensions = new int[2];
		dimensions[0] = x+2;
		dimensions[1] = y+2;

		for(int i = 0; i < dimensions[0]; i++)
		{
			for(int j = 0; j < dimensions[1]; j++)
			{
				spaces[i][j] = new Space();
			}
		}

	}

	public Block getThreeBlock(){
		//initialize return value to false
		Block ret = null;

		//check for existing block
		if(!threeBlocksLeft.isEmpty())
		{
			//select block
			ret = threeBlocksLeft.remove(0);
		}

		return ret;
	}

	public boolean returnThreeBlock(Block b){
		//Put three block back if player doesn't want to place it
		if(threeBlocksLeft.size() >= 56)
		{
			threeBlocksLeft.add(b);
			return true;
		}
		else
		{
			return false;
		}
	}

	public int getNumThreeBlocks()
	{
		return threeBlocksLeft.size();
	}
	
	
	public Block getIrrigationTileBlock(){
		//initialize return value to false
		Block ret = null;

		//check for existing block
		if(!irrigationsLeft.isEmpty())
		{
			//select block
			ret = irrigationsLeft.remove(0);
		}

		return ret;
	}

	public boolean returnIrrigationTile(Block b){
		//Put three block back if player doesn't want to place it
		if(irrigationsLeft.size() >= 12)
		{
			irrigationsLeft.add(b);
			return true;
		}
		else
		{
			return false;
		}
	}

	public int getIrrigationTilesLeft()
	{
		return irrigationsLeft.size();
	}
	
	
	public boolean placeBlock(Block b, Space s){
		//assumes block has been checked and exists

		//initialize return value to false
		boolean ret = false;

		Tile[][] tiles = b.getGrid();

		int[] coord = findSpace(s);

		//iterate through grid and only place nonempty tiles
		for(int i = 0; i < tiles.length; i++)
		{
			for(int j = 0; j < tiles[i].length; j++)
			{
				//check if empty part of grid
				if(tiles[i][j] != null)
				{
					//check for valid placement
					if(checkPlacement(b, spaces[coord[0]+i][coord[1]+j]))
					{
						spaces[coord[0]+i][coord[1]+j].placeBlock(b, tiles[i][j]);
						// check if a scoreable tile
						if (tiles[i][j] instanceof Palace)
							scorePalace(tiles[i][j], coord[0] + i, coord[1] + j);
						else if (tiles[i][j] instanceof Palace)
							scoreIrrigationTile(tiles[i][j], coord[0] + i, coord[1]+ j);
					}
				}
			}
		}

		return ret;
	}

	private boolean checkPlacement(Block b, Space s){
		//initialize return value
		boolean ret = false;

		/* Oops dont have to do error checking
		for(int i = 0; i < s.length; i++)
		{
			if(spaces[i].getBlock() = )
		}*/

		return true;
		
	}

	private int[] findSpace(Space s){
		//optimize this
		int[] ret = {-1,-1};
		for(int i = 0; i < dimensions[0]; i++)
		{
			for(int j = 0; j < dimensions[1]; j++)
			{
				if(spaces[i][j].equals(s))
				{
					ret[0] = i;
					ret[1] = j;
				}
			}
		}

		return ret;
	}
	
	private void scorePalace(Tile tiles, int x, int y) {
		Developer highestDev = findHighestDeveloper(spaces[x][y]);
		int score = 0;
		// check if owner is current player
		// if(check here)
		score = tiles.getValue() / 2;

		if (highestDev != null)
			highestDev.score(score);

	}

	private void scoreIrrigationTile(Tile tiles, int x, int y) {
		Developer highestDev = findHighestDeveloper(spaces[x][y]);
		int score = 0;

		// DFS for number of surrounded irrigation tiles
		if (highestDev != null)
			highestDev.score(score);

	}

	private Developer findHighestDeveloper(Space s) {
		Developer highestDev = null;
		// check for which algorithm to use to search
		if (s.getTile() instanceof Palace) {
			// DFS for highest rank developer in the surrounding city;
		} else if (s.getTile() instanceof IrrigationTile) {
			// DFS for highest rank developer in the surrounding tiles;

			// check there is no tie, if there is then return nothing
		}

		return highestDev;
	}

	public Space[][] getSpaces()
	{
		return this.spaces;
	}

}