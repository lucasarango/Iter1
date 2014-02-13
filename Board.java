import java.util.*;
import java.io.PrintWriter;

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
				spaces[i][j] = new Space(j,i);
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
				spaces[i][j] = new Space(j,i);
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
						possibleCoord[0] = coord[0]+i;
						possibleCoord[1] = coord[1]+j;
					}
					
					
					// check for valid placement
					if (checkPlacement(b, spaces[coord[0] + i][coord[1] + j])) {
						spaces[coord[0] + i][coord[1] + j].placeBlock(b, tiles[i][j]);
						
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

	public int[] findSpace(Space s) {
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

		if (highestDev != null){
			highestDev.score(score);
			
		}

	}

	
	private void checkIrrigationScore(int[] coord)
	{
		int x=coord[0],y=coord[1];
		Space s = spaces[x][y];
		ArrayList<ArrayList<Space>> visited = new ArrayList<ArrayList<Space>>();
		ArrayList<Boolean> closed = new ArrayList<Boolean>();
		for(int i=0; i<5; i++)
		{
			visited.add(new ArrayList<Space>());
			closed.add(true);
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
			visited.get(i).add(s);
			
			while (!queuePath.isEmpty())
			{
				if(s.getTile() == null)
				{
					closed.set(i, false);
					break;
				}
				s = queuePath.remove();
				coord = findSpace(s);
				x = coord[0];
				y = coord[1];
				
				if (spaces[x][y + 1].getTile() == null)	//check if any of the surrounding spaces are empty, if they are then this iteration is not closed
					closed.set(i, false);
				if (spaces[x][y - 1].getTile() == null)
					closed.set(i, false);
				if (spaces[x + 1][y].getTile() == null)
					closed.set(i, false);
				if (spaces[x - 1][y].getTile() == null)
					closed.set(i, false);
				
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
		for(int i=0; i<visited.size(); i++)//Checks if the checked irrigation paths are the same
			for(int j=i+1; j<visited.size(); j++)
			{
				if(visited.get(i).contains(visited.get(j).get(0)))
				{
					visited.remove(j);
					closed.remove(j);
					j--;
				}
			}
		for(int i=0; i<visited.size(); i++)
		{
			if(!closed.get(i))
				continue;
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
		
		//
		Tile temp = tempSpace.getTile();
		if(temp instanceof PalaceTile)
		{
			
			((PalaceTile)temp).levelUp(value);
			scorePalace((PalaceTile) temp, coord[0], coord[1]);
			ret = true;
		}

		return ret;
	}

	public void load(Scanner s)
	{
		while(s.hasNextLine() && !s.nextLine().equals("%Board%"));
		threeBlocksLeft.clear();
		int next = s.nextInt();
		for(int i = 0; i < next; i++)
			threeBlocksLeft.add(new ThreeBlock());
		next = s.nextInt();
		irrigationsLeft.clear();
		for(int i = 0; i < next; i++)
			irrigationsLeft.add(new OneBlock(new IrrigationTile()));
		dimensions[0] = s.nextInt();
		dimensions[1] = s.nextInt();
		spaces = new Space[dimensions[0]][dimensions[1]];
		for (int i = 0; i < dimensions[0]; i++)
			for (int j = 0; j < dimensions[1]; j++)
				spaces[i][j] = new Space(j,i);
		while(s.hasNext() && s.next().equals("position:"))
		{
			int y = s.nextInt();
			int x = s.nextInt();
			s.next("height:");
			int height = s.nextInt();
			
			System.out.print("position:" +y+" "+x+" height: "+height+" grid: ");
			s.next("grid:");
			
			Tile[][] t = new Tile[3][3];
			int tiles = 0;
			int sex = 0;
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					sex++;
					String str = s.next();
					System.out.print(str+" ");
					if (str.equals("+"))
						continue;
					else if (str.equals("P"))
						t[i][j] = new PalaceTile(s.nextInt());
					else if (str.equals("V"))
						t[i][j] = new VillageTile();
					else if (str.equals("I"))
						t[i][j] = new IrrigationTile();
					else if (str.equals("R"))
						t[i][j] = new RiceTile();
					tiles++;
				}
			}
			
			Block b;
			if(tiles == 1)
				b = new OneBlock(t);
			else if(tiles == 2)
				b = new TwoBlock(t);
			else if(tiles == 3)
				b = new ThreeBlock(t);
			else
				continue;
			for (int i = 0; i < 3; i++)
				for (int j = 0; j < 3; j++)
				{
					if(b.getGrid()[i][j] != null)
					{
						if((spaces[i+y][j+x].getTile() != null && spaces[i+y][j+x].getHeight()<height) || spaces[i+y][j+x].getTile() == null)
						{
							
							for(int currentHeight = 0; currentHeight < height; currentHeight++)
								spaces[i+y][j+x].placeBlock(b, b.getGrid()[i][j]);
						}
					}
				}
		}
	}
	
	public void save(PrintWriter p)
	{
		p.println("%Board%");
		p.println(threeBlocksLeft.size()+" "+irrigationsLeft.size()+" "+dimensions[0]+" "+dimensions[1]);
		ArrayList<int[]> blocksSaved = new ArrayList<int[]>();
		ArrayList<int[]> developersSaving = new ArrayList<int[]>();
		for(int i = 0; i < dimensions[0]; i++)
			for(int j = 0; j < dimensions[1]; j++)
			{
				if(spaces[i][j].getTile() != null)
				{
					Block b = spaces[i][j].getBlock();
					
					int[] info = new int[3];
					for(int y = 0; y < 3; y++)
						for(int x = 0; x < 3; x++)
							if(spaces[i][j].getTile() == b.getGrid()[y][x])
							{
								info[0]=i-y;
								info[1]=j-x;
							}
					info[2]=spaces[i][j].getHeight();
				
					boolean found = false;
					for(int x = 0; x<blocksSaved.size() && !found; x++)
						if(blocksSaved.get(x)[0] == info[0] && blocksSaved.get(x)[1] == info[1] && blocksSaved.get(x)[2] == info[2])
							found = true;
					if(found)
						continue;
					
				
					blocksSaved.add(info);
				
					p.print("position: "+info[0]+" "+info[1]+" height: "+info[2]+" grid: ");
					for(int y = 0; y < 3; y++)
					{
						for(int x = 0; x < 3; x++)
						{
							if (b.getGrid()[y][x] == null)
								p.print("+");
							else if (b.getGrid()[y][x] instanceof PalaceTile)
								p.print("P,"+((PalaceTile)spaces[y][x].getTile()).getValue());
							else if (b.getGrid()[y][x] instanceof VillageTile)
								p.print("V");
							else if (b.getGrid()[y][x] instanceof IrrigationTile)
								p.print("I");
							else if (b.getGrid()[y][x] instanceof RiceTile)
								p.print("R");
							p.print(" ");		
						}
						if(y != 2)
							p.print("| ");
					}
					p.println();
				}/*
				if(pos.isThereDeveloper(spaces[i][j]))
				{
					int[] pos = new int[2];
					pos[0] = i;
					pos[1] = j;
					developersSaving.add(pos);
				}*/
			}
		/*p.println("%Developers%");
		for(int i = 0; i < developersSaving.size(); i++)
		{
			int x = developersSaving.get(i)[0], y = developersSaving.get(i)[1];
			p.print(x+" "+y+" "); pos.getDeveloper(spaces[x][y]).save(p);
		}*/
	}
}