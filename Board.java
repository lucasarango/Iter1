public class Board{

	Space[][] spaces;
	List<Block> threeBlocksLeft;
	int[] dimensions;
	
	Board(){
		
	}

	Board(int x, int y){
		threeBlocksLeft = new ArrayList<Block>();

		for(int i = 0; i < 56; i++)
		{
			threeBlocksLeft.add(new ThreeBlock());
		}
		
		spaces = new Space[12][12];
		dimensions = new int[2];
		dimensions[0] = 12;
		dimensions[1] = 12;
	}

	Board(int x, int y){
		threeBlocksLeft = new ArrayList<Block>();

		for(int i = 0; i < 56; i++)
		{
			threeBlocksLeft.add(new ThreeBlock());
		}
		
		spaces = new Space[x][y];
		dimensions = new int[2];
		dimensions[0] = x;
		dimensions[1] = y;
	}

	public boolean placeThreeBlock(Space s){
		//initialize return value to false
		boolean ret = false;

		//check for existing block
		if(!threeBlocksLeft.isEmpty())
		{

			//select block
			Block b = threeBlocksLeft.get(0);
			placeBlock(b, s);
			
		}

		return ret;
	}

	public boolean placeBlock(Block b, Space s){
		//assumes block has been checked and exists

		//initialize return value to false
		boolean ret = false;

		Tile[][] tiles = b.getGrid();

		int[] coord = findSpace(s);

		//iterate through grid and only place nonempty tiles
		for(int i = 0; i < tiles.length: i++)
		{
			for(int j = 0; j < tiles[i].length; j++)
			{
				//check if empty part of grid
				if(tiles.[i][j] != null)
				{
					//check for valid placement
					if(checkplacement(b, spaces[coord[0]-1]][coord[1]-1]))
					{
						spaces[coord[0]-1]][coord[1]-1].placeBlock(b, tiles[i][j]);
					}
				}
			}
		}

		return ret;
	}

	private boolean checkPlacement(Block b, Space[] s){
		//initialize return value
		boolean ret = false;

		/* Oops dont have to do error checking jk lol
		for(int i = 0; i < s.length; i++)
		{
			if(s[i].getBlock() =)
		}*/

		return true;
		
	}

	private int[] findSpace(Space s){
		//optimize this
		int[] ret = {-1,-1}
		for(int i = 0; i < dimensions[0]; i++)
		{
			for(int j = 0 ; j < dimensions[1]; j++)
			{
				if(spaces[i][j] == s)
				{
					ret[0] = i;
					ret[1] = j;
				}
			}
		}

		return ret;
	}



}