class Blocks {
	private Tile[][] tileGrid;

	public Blocks(){
		tileGrid = new Tile[3][3];
		tileGrid[1][1] = new Tile();
	}

	public Blocks(tileGrid[][] t){
		tileGrid = t;
	}

	public static boolean rotate(){
		//this code works around the idea that we are rotating around one tile, the central tile which is at [1][1]
		//This means that there are only 4 possibilities for the other blocks to be at.
		//let's scan the 8 squares around the central tile. We will have three cases

		//A lot of this is just straight hard coded.
		//[][][]
		//[][][]
		//[][][]

		Tile tile1 = null;
		Tile tile2 = null;

		int tile1x;
		int tile1y;
		int tile2x;
		int tile2y;

		//this part scans for tile1 and tile2 and their coordinates
		//#1
		if(tileGrid[0][1] != null){
			if(tile1 != null){
				tile2 = new Tile();
				tile2x = 0;
				tile2y = 1;
			}
			else {
				tile1 = new Tile();
				tile1x = 0;
				tile1y = 1;
			}
		}
		//#2
		if(tileGrid[1][0] != null){
			if(tile1 != null){
				tile2 = new Tile();
				tile2x = 1;
				tile2y = 0;
			}
			else {
				tile1 = new Tile();
				tile1x = 1;
				tile1y = 0;
			}
		}
		//#3
		if(tileGrid[2][1] != null){
			if(tile1 != null){
				tile2 = new Tile();
				tile2x = 2;
				tile2y = 1;
			}
			else {
				tile1 = new Tile();
				tile1x = 2;
				tile1y = 1;
			}
		}
		//#4
		if(tileGrid[1][2] != null){
			if(tile1 != null){
				tile2 = new Tile();
				tile2x = 1;
				tile2y = 2;
			}
			else {
				tile1 = new Tile();
				tile1x = 1;
				tile1y = 2;
			}
		}

		//this is a one block
		//there is no rotation for this scenario, so rotation fails
		if(tile1 == null && tile2 == null){
			//No need for rotation
			return false;
		}

		//this is a two block
		else if(tile2 == null){
			if(tile1x == 0 && tile1y == 1){
				tileGrid[1][0] = new Tile();
				tileGrid[tile1x][tile1y] = null;
				return true;
			}
			else if(tile1x == 1 && tile1y == 0){
				tileGrid[2][1] = new Tile();
				tileGrid[tile1x][tile1y] = null;
				return true;
			}
			else if(tile1x == 2 && tile1y == 1){
				tileGrid[1][2] = new Tile();
				tileGrid[tile1x][tile1y] = null;
				return true;
			}
			else if(tile1x == 1 && tile1y == 2){
				tileGrid[0][1] = new Tile();
				tileGrid[tile1x][tile1y] = null;
				return true;
			}
			
		}
		//our three block case
		else {
				if(tile1x == 0 && tile1y == 1 && tile2x == 1 && tile2y == 0){
				tileGrid[2][1] = new Tile();
				tileGrid[tile1x][tile1y] = null;
				return true;
			}
			else if(tile1x == 1 && tile1y == 0 && tile2x == 2 && tile2y == 1){
				tileGrid[1][2] = new Tile();
				tileGrid[tile1x][tile1y] = null;
				return true;
			}
			else if(tile1x == 2 && tile1y == 1 && tile2x == 1 && tile2y == 2){
				tileGrid[0][1] = new Tile();
				tileGrid[tile1x][tile1y] = null;
				return true;
			}
			else if(tile1x == 1 && tile1y == 2 && tile2x == 0 && tile2y == 1){
				tileGrid[1][0] = new Tile();
				tileGrid[tile1x][tile1y] = null;
				return true
		}
		//just incase it breaks somehow
		return false;
	}

	public getGrid(){
		return tileGrid;
	}

	public setGrid(tileGrid[][] t){
		tileGrid = t;
	}
}