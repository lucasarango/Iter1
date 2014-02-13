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
		placeBlock(new OneBlock(new IrrigationTile()), spaces[1][2]);
		placeBlock(new OneBlock(new IrrigationTile()), spaces[5][7]);
		placeBlock(new OneBlock(new IrrigationTile()), spaces[3][11]);

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
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				// check if empty part of grid
				if (tiles[i][j] != null) {
					// check for valid placement
					if (checkPlacement(b, spaces[coord[0] + i][coord[1] + j])) {
						spaces[coord[0] + i][coord[1] + j].placeBlock(b,
								tiles[i][j]);
						// check if a scoreable tile
						if (tiles[i][j] instanceof PalaceTile)
							scorePalace((PalaceTile) tiles[i][j], coord[0] + i,
									coord[1] + j);
						else if (tiles[i][j] instanceof IrrigationTile)
							scoreIrrigationTile((IrrigationTile) tiles[i][j],
									coord[0] + i, coord[1] + j);
					}
				}
			}
		}

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

	private void scoreIrrigationTile(IrrigationTile tile, int x, int y) {
		Developer highestDev = findHighestDeveloper(spaces[x][y]);
		int score = 0;

		// DFS for number of surrounded irrigation tiles
		if (highestDev != null)
			highestDev.score(score);

	}

	private Developer findHighestDeveloper(Space s) {
		ArrayList<Space> visited = new ArrayList<Space>();

		Developer highestDev = null;

		// check for which algorithm to use to search
		if (s.getTile() instanceof PalaceTile) {
			// DFS for highest rank developer in the surrounding city;
			int[] coord = findSpace(s);
			int x = coord[0];
			int y = coord[1];
			Queue<Space> queuePath = new LinkedList<Space>();
			Iterator<Space> it = queuePath.iterator();

			queuePath.add(spaces[x][y]);
			visited.add(spaces[x][y]);

			while (it.hasNext()) {
				s = queuePath.remove();
				// FOUND A DEVELOPER
				if (Position.isThereDeveloper(s)) {
					return pos.getDeveloper(s);
				}
				if (spaces[x][y + 1].getTile() instanceof VillageTile
						&& !visited.contains(spaces[x][y + 1])) {
					queuePath.add(spaces[x][y + 1]);
				}
				if (spaces[x][y - 1].getTile() instanceof VillageTile
						&& !visited.contains(spaces[x][y - 1])) {
					queuePath.add(spaces[x][y - 1]);
				}
				if (spaces[x + 1][y].getTile() instanceof VillageTile
						&& !visited.contains(spaces[x + 1][y])) {
					queuePath.add(spaces[x + 1][y]);
				}
				if (spaces[x - 1][y + 1].getTile() instanceof VillageTile
						&& !visited.contains(spaces[x - 1][y])) {
					queuePath.add(spaces[x - 1][y]);
				}
			}
		} else if (s.getTile() instanceof IrrigationTile) {
			/*
			 * highest rank developer in the surrounding tiles;
			 * 
			 * // check there is no tie, if there is then return nothing }
			 */
		}
		return highestDev;
	}

	public Space[][] getSpaces() {
		return this.spaces;
	}

	public boolean moveDeveloper(Developer d, int[] offset){
		
		//find current index
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

		if(spaces[coord[0]][coord[1]] instanceof PalaceTile)
		{
			spaces[coord[0]][coord[1]].levelUp(value);
			scorePalace(spaces[coord[0]][coord[1]].getTile(), coord[0], coord[1]);
			ret = true;
		}

		return ret;
	}

}