public class Board{

	Space[][] spaces;
	List<Block> threeBlocksLeft;
	
	Board(){
		
	}

	Board(int x, int y){
		threeBlocksLeft = new ArrayList<Block>();

		for(int i = 0; i < 56; i++)
		{
			threeBlocksLeft.add(new ThreeBlock());
		}
		
		spaces = new Space[12][12];
	}

	Board(int x, int y){
		threeBlocksLeft = new ArrayList<Block>();

		for(int i = 0; i < 56; i++)
		{
			threeBlocksLeft.add(new ThreeBlock());
		}
		
		spaces = new Space[x][y];
	}

	public boolean placeThreeBlock(Space s){
		//initialize return value to false
		boolean ret = false;

		//check for existing block
		if(!threeBlocksLeft.isEmpty())
		{

			//select block
			Block b = threeBlocksLeft.get(0);
			//check for valid placement
			if(checkplacement(b, s))
			{
				s.placeBlock(b);
				threeBlocksLeft.remove(0);
				ret = true
			}
			
		}

		return ret;
	}

	public boolean checkPlacement(Block b, Space[] s){
		//initialize return value
		boolean ret = false;

		/* Oops dont have to do error checking jk lol
		for(int i = 0; i < s.length; i++)
		{
			if(s[i].getBlock() =)
		}*/

		return true;
		
	}

}