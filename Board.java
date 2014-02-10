import java.util.ArrayList;
<<<<<<< HEAD

public class Board {

	private Space[][] spaces;
	private ArrayList<Block> threeBlocksLeft;
	private int[] dimensions;

	public Board() {
		threeBlocksLeft = new ArrayList<Block>();

		for (int i = 0; i < 56; i++) {
			threeBlocksLeft.add(new ThreeBlock());
		}

		spaces = new Space[12][12];
=======
import java.util.*;

public class Board{

	private Space[][] spaces;
	private List<Block> threeBlocksLeft;
	private int[] dimensions;
	

	public Board(){
		threeBlocksLeft = new ArrayList<Block>();

		for(int i = 0; i < 56; i++)
		{
			threeBlocksLeft.add(new ThreeBlock());
		}
		
		//The board needs a buffer zone on all sides because blocks can be placed on a board with just one tile on a space
		spaces = new Space[14][14];
>>>>>>> board
		dimensions = new int[2];
		dimensions[0] = 12;
		dimensions[1] = 12;

<<<<<<< HEAD
		for (int i = 0; i < dimensions[0]; i++) {
			for (int j = 0; j < dimensions[1]; j++) {
				spaces[i][j] = new Space();
			}
		}

	}

	public Board(int x, int y) {
		threeBlocksLeft = new ArrayList<Block>();

		for (int i = 0; i < 56; i++) {
			threeBlocksLeft.add(new ThreeBlock());
		}

		spaces = new Space[x][y];
=======
		for(int i = 0; i < dimensions[0]+2; i++)
		{
			for(int j = 0; j < dimensions[1]+2; j++)
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
		//The board needs a buffer zone on all sides because blocks can be placed on a board with just one tile on a space
		spaces = new Space[x+2][y+2];
>>>>>>> board
		dimensions = new int[2];
		dimensions[0] = x;
		dimensions[1] = y;

<<<<<<< HEAD
		for (int i = 0; i < dimensions[0]; i++) {
			for (int j = 0; j < dimensions[1]; j++) {
=======
		for(int i = 0; i < dimensions[0]+2; i++)
		{
			for(int j = 0; j < dimensions[1]+2; j++)
			{
>>>>>>> board
				spaces[i][j] = new Space();
			}
		}

	}

<<<<<<< HEAD
	public Block getThreeBlock() {
		// initialize return value to false
		Block ret = threeBlocksLeft.get(0);
		;

		// check for existing block
		if (!threeBlocksLeft.isEmpty()) {
			// select block
			threeBlocksLeft.remove(0);
=======
	public Block getThreeBlock(){
		//initialize return value to false
		Block ret = null;

		//check for existing block
		if(!threeBlocksLeft.isEmpty())
		{
			//select block
			ret = threeBlocksLeft.remove(0);
>>>>>>> board
		}

		return ret;
	}

<<<<<<< HEAD
	public boolean returnThreeBlock(Block b) {
		// Put three block back if player doesn't want to place it
		if (threeBlocksLeft.size() >= 56) {
			threeBlocksLeft.add(b);
			return true;
		} else {
=======
	public boolean returnThreeBlock(Block b){
		//Put three block back if player doesn't want to place it
		if(threeBlocksLeft.size() >= 56)
		{
			threeBlocksLeft.add(b);
			return true;
		}
		else
		{
>>>>>>> board
			return false;
		}
	}

<<<<<<< HEAD
	public boolean placeBlock(Block b, Space s) {
		// assumes block has been checked and exists

		// initialize return value to false
=======
	public int getNumThreeBlocks()
	{
		return threeBlocksLeft.size();
	}
	
	public boolean placeBlock(Block b, Space s){
		//assumes block has been checked and exists

		//initialize return value to false
>>>>>>> board
		boolean ret = false;

		Tile[][] tiles = b.getGrid();

		int[] coord = findSpace(s);

<<<<<<< HEAD
		// iterate through grid and only place nonempty tiles
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				// check if empty part of grid
				if (tiles[i][j] != null) {
					// check for valid placement
					if (checkPlacement(b, spaces[coord[0] - 1][coord[1] - 1])) {
						spaces[coord[0] - 1][coord[1] - 1].placeBlock(b,
								tiles[i][j]);
=======
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
>>>>>>> board
					}
				}
			}
		}

		return ret;
	}

<<<<<<< HEAD
	private boolean checkPlacement(Block b, Space spaces) {
		// initialize return value

		/*
		 * Oops dont have to do error checking for(int i = 0; i < s.length; i++)
		 * { if(s[i].getBlock() = ) }
		 */

		return true;

	}

	private int[] findSpace(Space s) {
		// optimize this
		int[] ret = { -1, -1 };
		for (int i = 0; i < dimensions[0]; i++) {
			for (int j = 0; j < dimensions[1]; j++) {
				if (spaces[i][j].equals(s)) {
=======
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
		for(int i = 0; i < dimensions[0]+2; i++)
		{
			for(int j = 0 ; j < dimensions[1]+2; j++)
			{
				if(spaces[i][j].equals(s))
				{
>>>>>>> board
					ret[0] = i;
					ret[1] = j;
				}
			}
		}

		return ret;
	}

<<<<<<< HEAD
	public Space[][] getSpaces() {
		return this.spaces;
	}

=======
	public Space[][] getSpaces()
	{
		return this.spaces;
	}



>>>>>>> board
}
