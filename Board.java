import java.util.*;

public class Board {

	private Space[][] spaces;
	private List<Block> threeBlocksLeft;
	private List<Block> irrigationsLeft;
	private int[] dimensions;
	private Position pos = new Position();
	private Mediator mediator;

	public Board(Mediator m) {
		threeBlocksLeft = new ArrayList<Block>();

		for (int i = 0; i < 56; i++) {
			threeBlocksLeft.add(new ThreeBlock());
		}

		irrigationsLeft = new ArrayList<Block>();

		for (int i = 0; i < 12; i++) {
			irrigationsLeft.add(new OneBlock(new IrrigationTile()));
		}

		spaces = new Space[14][14];
		dimensions = new int[2];
		dimensions[0] = 14;
		dimensions[1] = 14;

		for (int i = 0; i < dimensions[0]; i++) {
			for (int j = 0; j < dimensions[1]; j++) {
				spaces[i][j] = new Space();
			}
		}
		placeBlock(new OneBlock(new IrrigationTile()), new int[] {1,3}) ;
		placeBlock(new OneBlock(new IrrigationTile()), new int[] {5,7});
		placeBlock(new OneBlock(new IrrigationTile()), new int[] {3,11});

		this.mediator = m;

	}

	public Board(Mediator m, int x, int y) {
		threeBlocksLeft = new ArrayList<Block>();

		for (int i = 0; i < 56; i++) {
			threeBlocksLeft.add(new ThreeBlock());
		}

		spaces = new Space[x + 2][y + 2];
		dimensions = new int[2];
		dimensions[0] = x + 2;
		dimensions[1] = y + 2;

		for (int i = 0; i < dimensions[0]; i++) {
			for (int j = 0; j < dimensions[1]; j++) {
				spaces[i][j] = new Space();
			}
		}

		this.mediator = m;

	}

	public Block getThreeBlock() {
		// initialize return value to false
		Block ret = null;

		// check for existing block
		if (!threeBlocksLeft.isEmpty()) {
			// select block
			ret = threeBlocksLeft.remove(0);
		}

		return ret;
	}

	public Block placePalace(int[] coord, int value){
		Block palace = new OneBlock(new PalaceTile(value));
		
		placeBlock(palace, coord);

		return palace;
	}

	public boolean returnThreeBlock(Block b) {
		// Put three block back if player doesn't want to place it
		if (threeBlocksLeft.size() >= 56) {
			threeBlocksLeft.add(b);
			return true;
		} else {
			return false;
		}
	}

	public int getNumThreeBlocks() {
		return threeBlocksLeft.size();
	}

	public Block getIrrigationTileBlock() {
		// initialize return value to false
		Block ret = null;

		// check for existing block
		if (!irrigationsLeft.isEmpty()) {
			// select block
			ret = irrigationsLeft.remove(0);
		}

		return ret;
	}

	public boolean returnIrrigationTile(Block b) {
		// Put three block back if player doesn't want to place it
		if (irrigationsLeft.size() >= 12) {
			irrigationsLeft.add(b);
			return true;
		} else {
			return false;
		}
	}

	public int getIrrigationTilesLeft() {
		return irrigationsLeft.size();
	}

	public boolean placeBlock(Block b, int[] coord) {
		// assumes block has been checked and exists

		// initialize return value to false
		boolean ret = false;

		Tile[][] tiles = b.getGrid();

		if(coord[0] >= dimensions[0] || coord[1] >= dimensions[1])
			return false;

		// iterate through grid and only place nonempty tiles
		boolean possibleScore = false;
		int[] possibleCoord = new int[2];
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				// check if empty part of grid
				if (tiles[i][j] != null)
				{
					//checks for possible irrigation score condition
					
					if (spaces[coord[0] + i][coord[1] + j+1].getTile() instanceof IrrigationTile	||
						spaces[coord[0] + i][coord[1] + j-1].getTile() instanceof IrrigationTile	||
						spaces[coord[0] + i+1][coord[1] + j].getTile() instanceof IrrigationTile	||
						spaces[coord[0] + i-1][coord[1] + j].getTile() instanceof IrrigationTile	||
						tiles[i][j] instanceof IrrigationTile)
					{
						possibleScore = true;
						possibleCoord[0] = coord[0+i];
						possibleCoord[1] = coord[1+j];
					}
					
					
					// check for valid placement
					if (checkPlacement(b, spaces[coord[0] + i][coord[1] + j])) {
						spaces[coord[0] + i][coord[1] + j].placeBlock(b, tiles[i][j]);
						System.out.println("The space at" +  Arrays.toString(coord) + " is " + spaces[coord[0]+i][coord[1]+j].getTile().getClass().getSimpleName());
						ret = true;
						// check if a scoreable tile
						if (tiles[i][j] instanceof PalaceTile)
							scorePalace((PalaceTile) tiles[i][j], coord[0] + i,
									coord[1] + j);
					}
				}
			}
		}
		
		if(possibleScore)
			checkIrrigationScore(possibleCoord);

		return ret;
	}

	private boolean checkPlacement(Block b, Space s) {
		// initialize return value
		/*
		 * Oops dont have to do error checking for(int i = 0; i < s.length; i++)
		 * { if(spaces[i].getBlock() = ) }
		 */

		return true;

	}

	private int[] findSpace(Space s) {
		// optimize this
		int[] ret = { -1, -1 };
		for (int i = 0; i < dimensions[0]; i++) {
			for (int j = 0; j < dimensions[1]; j++) {
				if (spaces[i][j].equals(s)) {
					ret[0] = i;
					ret[1] = j;
				}
			}
		}

		return ret;
	}

	private void scorePalace(PalaceTile tile, int x, int y) {
		Developer highestDev = findHighestDeveloper(spaces[x][y]);
		int score = 0;
		// check if owner is current player
		// if(check here)
		score = tile.getValue() / 2;

		if (highestDev != null)
			highestDev.score(score);

	}

	
	private void checkIrrigationScore(int[] coord)
	{
		int x=coord[0],y=coord[1];
		Space s = spaces[x][y];
		ArrayList<ArrayList<Space>> visited = new ArrayList<ArrayList<Space>>();
		boolean[] closed = new boolean[4];
		for(int i=0; i<5; i++)
		{
			visited.add(new ArrayList<Space>());
			closed[i] = true;
		}
		
		for(int i=0; i<5; i++)
		{
			Queue<Space> queuePath = new LinkedList<Space>();
			if(i==0)
				s = spaces[x][y];
			if(i==1)
				s = spaces[x][y+1];
			else if(i==2)
				s = spaces[x][y-1];
			else if(i==3)
				s = spaces[x+1][y];
			else if(i==4)
				s = spaces[x-1][y];
			queuePath.add(s);
			
			while (!queuePath.isEmpty())
			{
				s = queuePath.remove();
				coord = findSpace(s);
				x = coord[0];
				y = coord[1];
				
				
				if (spaces[x][y + 1].getTile() == null)	//check if any of the surrounding spaces are empty, if they are then this iteration is not closed
					closed[i]=false;
				if (spaces[x][y - 1].getTile() == null)
					closed[i]=false;
				if (spaces[x + 1][y].getTile() == null)
					closed[i]=false;
				if (spaces[x - 1][y].getTile() == null)
					closed[i]=false;
				
				if (spaces[x][y + 1].getTile() instanceof IrrigationTile	//usual parse through irrigation tiles
						&& !visited.get(i).contains(spaces[x][y + 1])) {
					queuePath.add(spaces[x][y + 1]);
					visited.get(i).add(spaces[x][y + 1]);
				}
				if (spaces[x][y - 1].getTile() instanceof IrrigationTile
						&& !visited.get(i).contains(spaces[x][y - 1])) {
					queuePath.add(spaces[x][y - 1]);
					visited.get(i).add(spaces[x][y - 1]);
				}
				if (spaces[x + 1][y].getTile() instanceof IrrigationTile
						&& !visited.contains(spaces[x + 1][y])) {
					queuePath.add(spaces[x + 1][y]);
					visited.get(i).add(spaces[x + 1][y]);
				}
				if (spaces[x - 1][y].getTile() instanceof IrrigationTile
						&& !visited.get(i).contains(spaces[x - 1][y])) {
					queuePath.add(spaces[x - 1][y]);
					visited.get(i).add(spaces[x - 1][y]);
				}
			}
		}
		for(int i=0; i<visited.size(); i++)
			for(int j=0; j<visited.size(); j++)
			{
				if(visited.get(i).contains(visited.get(j).get(0)))
				{
					visited.remove(j);
					j--;
				}
			}
		for(int i=0; i<visited.size(); i++)
		{
			coord = findSpace(visited.get(i).get(0));
			x = coord[0];
			y = coord[1];
			scoreIrrigationTile(x,y);
		}
	}
	
	private void scoreIrrigationTile(int x, int y) {
		Developer highestDev = findHighestDeveloper(spaces[x][y]);


		int[] coord;
		Space s = spaces[x][y];
		ArrayList<Space> visited = new ArrayList<Space>();
		Queue<Space> queuePath = new LinkedList<Space>();

		queuePath.add(s);
		visited.add(s);

		while (!queuePath.isEmpty())
		{
			s = queuePath.remove();
			coord = findSpace(s);
			x = coord[0];
			y = coord[1];
			
			if (spaces[x][y + 1].getTile() instanceof IrrigationTile
					&& !visited.contains(spaces[x][y + 1])) {
				queuePath.add(spaces[x][y + 1]);
				visited.add(spaces[x][y + 1]);
			}
			if (spaces[x][y - 1].getTile() instanceof IrrigationTile
					&& !visited.contains(spaces[x][y - 1])) {
				queuePath.add(spaces[x][y - 1]);
				visited.add(spaces[x][y - 1]);
			}
			if (spaces[x + 1][y].getTile() instanceof IrrigationTile
					&& !visited.contains(spaces[x + 1][y])) {
				queuePath.add(spaces[x + 1][y]);
				visited.add(spaces[x + 1][y]);
			}
			if (spaces[x - 1][y].getTile() instanceof IrrigationTile
					&& !visited.contains(spaces[x - 1][y])) {
				queuePath.add(spaces[x - 1][y]);
				visited.add(spaces[x - 1][y]);
			}
		}
		// DFS for number of surrounded irrigation tiles
		if (highestDev != null)
			highestDev.score(visited.size()*3);

	}

private Developer findHighestDeveloper(Space s) {
		ArrayList<Space> visited = new ArrayList<Space>();
		ArrayList<Player> players = new ArrayList<Player>();
		
		HashMap<Player, int[]> map = new HashMap<Player, int[]>();
		HashMap<Player, Developer> highestDevs = new HashMap<Player, Developer>();

		int highestVal = 0;
		
		// check for which algorithm to use to search
		if (s.getTile() instanceof PalaceTile)
		{
			// DFS for highest rank developer in the surrounding city;
			int[] coord;
			int x;
			int y;
			Queue<Space> queuePath = new LinkedList<Space>();

			queuePath.add(s);
			visited.add(s);

			while (!queuePath.isEmpty())
			{
				s = queuePath.remove();
				coord = findSpace(s);
				x = coord[0];
				y = coord[1];
				// FOUND A DEVELOPER
				if (!(s.getTile() instanceof PalaceTile) && Position.isThereDeveloper(s))
				{
					if(!players.contains(pos.getDeveloper(s).getPlayer()))	//First instance of player, add it to arraylist and create its int array in map
					{
						players.add(pos.getDeveloper(s).getPlayer());
						int[] temp = new int[50];
						for(int i = 0; i < 50; i++)
							temp[i] = 0;
						map.put(pos.getDeveloper(s).getPlayer(), temp);
					}
					if(s.getHeight()>highestVal)	//if height is greater than past heighest value, reset heighest devs so code will know what to pass at the end
					{
						highestVal = s.getHeight();
						highestDevs.clear();
						highestDevs.put(pos.getDeveloper(s).getPlayer(), pos.getDeveloper(s));
					}
					else if(s.getHeight() == highestVal)
					{
						highestDevs.put(pos.getDeveloper(s).getPlayer(), pos.getDeveloper(s));
					}
					int[] temp = map.get(pos.getDeveloper(s).getPlayer());
					temp[s.getHeight()]++;
					map.put(pos.getDeveloper(s).getPlayer(), temp);
				}
				if (spaces[x][y + 1].getTile() instanceof VillageTile
						&& !visited.contains(spaces[x][y + 1])) {
					queuePath.add(spaces[x][y + 1]);
					visited.add(spaces[x][y + 1]);
				}
				if (spaces[x][y - 1].getTile() instanceof VillageTile
						&& !visited.contains(spaces[x][y - 1])) {
					queuePath.add(spaces[x][y - 1]);
					visited.add(spaces[x][y - 1]);
				}
				if (spaces[x + 1][y].getTile() instanceof VillageTile
						&& !visited.contains(spaces[x + 1][y])) {
					queuePath.add(spaces[x + 1][y]);
					visited.add(spaces[x + 1][y]);
				}
				if (spaces[x - 1][y].getTile() instanceof VillageTile
						&& !visited.contains(spaces[x - 1][y])) {
					queuePath.add(spaces[x - 1][y]);
					visited.add(spaces[x - 1][y]);
				}
			}
			
		} else if (s.getTile() instanceof IrrigationTile) {
			int[] coord;
			int x;
			int y;
			Queue<Space> queuePath = new LinkedList<Space>();

			queuePath.add(s);
			visited.add(s);
			ArrayList<Space> check = new ArrayList<Space>();

			while (!queuePath.isEmpty())
			{
				s = queuePath.remove();
				coord = findSpace(s);
				x = coord[0];
				y = coord[1];
				
				if (!check.contains(spaces[x][y])) {
					check.add(spaces[x][y]);
				}
				if (!check.contains(spaces[x][y + 1])) {
					check.add(spaces[x][y + 1]);
				}
				if (!check.contains(spaces[x][y - 1])) {
					check.add(spaces[x][y - 1]);
				}
				if (!check.contains(spaces[x + 1][y])) {
					check.add(spaces[x + 1][y]);
				}
				if (!check.contains(spaces[x - 1][y])) {
					check.add(spaces[x - 1][y]);
				}
				
				
				if (spaces[x][y + 1].getTile() instanceof IrrigationTile
						&& !visited.contains(spaces[x][y + 1])) {
					queuePath.add(spaces[x][y + 1]);
					visited.add(spaces[x][y + 1]);
				}
				if (spaces[x][y - 1].getTile() instanceof IrrigationTile
						&& !visited.contains(spaces[x][y - 1])) {
					queuePath.add(spaces[x][y - 1]);
					visited.add(spaces[x][y - 1]);
				}
				if (spaces[x + 1][y].getTile() instanceof IrrigationTile
						&& !visited.contains(spaces[x + 1][y])) {
					queuePath.add(spaces[x + 1][y]);
					visited.add(spaces[x + 1][y]);
				}
				if (spaces[x - 1][y].getTile() instanceof IrrigationTile
						&& !visited.contains(spaces[x - 1][y])) {
					queuePath.add(spaces[x - 1][y]);
					visited.add(spaces[x - 1][y]);
				}
			}
			for(int checkI=0; checkI<check.size();checkI++)
			{
				s = check.get(checkI);
				if (Position.isThereDeveloper(s))
				{
					if(!players.contains(pos.getDeveloper(s).getPlayer()))
					{
						players.add(pos.getDeveloper(s).getPlayer());
						int[] temp = new int[50];
						for(int i = 0; i < 50; i++)
							temp[i] = 0;
						map.put(pos.getDeveloper(s).getPlayer(), temp);
					}
					if(s.getHeight()>highestVal)
					{
						highestVal = s.getHeight();
						highestDevs.clear();
						highestDevs.put(pos.getDeveloper(s).getPlayer(), pos.getDeveloper(s));
					}
					else if(s.getHeight() == highestVal)
					{
						highestDevs.put(pos.getDeveloper(s).getPlayer(), pos.getDeveloper(s));
					}
					int[] temp = map.get(pos.getDeveloper(s).getPlayer());
					temp[s.getHeight()]++;
					map.put(pos.getDeveloper(s).getPlayer(), temp);
				}
			}
		}
		Developer highestDev = null;
		for( int height=highestVal; highestDev == null && height >= 0; height--)
		{
			int most = 0;	//most developers on this level
			for(int p = 0; p < players.size(); p++)	//get most developers on this level
				if(map.get(players.get(p))[height] > most)
					most = map.get(players.get(p))[height];
			for(int p = 0; p < players.size(); p++)	//if player has less than most developers, remove it from array list
				if(map.get(players.get(p))[height] < most)
					players.remove(players.get(p));
			if(players.size() == 1)	//if there is only one player left, he had the highest developers
				highestDev = highestDevs.get(players.get(0));
		}
		return highestDev;
	}

	public Space[][] getSpaces() {
		return this.spaces;
	}

	public boolean moveDeveloper(Developer d, int[] offset){
		
		//find current index
		Space currentSpace = d.getSpace();
		int[] currentLocation = findSpace(currentSpace);

		//update location
		currentLocation[0] += offset[0];
		currentLocation[1] += offset[1];

		if(currentLocation[0] >= dimensions[0] || currentLocation[1] >= dimensions[1])
			return false;


		Space newSpace = spaces[currentLocation[0]][currentLocation[1]];

		//change developer space and update positions
		d.move(newSpace);
	
		pos.removePair(currentSpace);

		pos.addPair(newSpace, d);

		return true;
	}

	public boolean placeDeveloper(Developer d, int[] coord){
		

		if(coord[0] >= dimensions[0] || coord[1] >= dimensions[1])
			return false;


		Space newSpace = spaces[coord[0]][coord[1]];

		//change developer space and update positions
		d.move(newSpace);
		pos.addPair(newSpace, d);

		return true;
	}

	public int[] findDeveloper(Developer d){
		return findSpace(d.getSpace());
	}

	public boolean upgradePalace(int[] coord, int value){
		boolean ret = false;
		Space tempSpace = spaces[coord[0]+1][coord[1]+1];
		System.out.println("trying to upgrade " + coord[0] + " " + coord[1]);
		//System.out.println("The tile is of type " + tempSpace.getTile().getClass().getSimpleName());
		Tile temp = tempSpace.getTile();
		if(temp instanceof PalaceTile)
		{
			
			((PalaceTile)temp).levelUp(value);
			scorePalace((PalaceTile) temp, coord[0], coord[1]);
			ret = true;
		}

		return ret;
	}

}