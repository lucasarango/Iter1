class ThreeBlock {

	public TwoBlock(){
		tileGrid = new Tile[3][3];
		tileGrid[1][1] = new City();
		tileGrid[1][0] = new RiceField();
		tileGrid[1][2] = new RiceField();
		//basically it's an L shape with a city in the center and a ricefield to the right and left in the grid
	}

	public TwoBlock(Tile[][] t){
		tileGrid = t;
	}

	//our three block case
	public static boolean rotate(){

		Tile tile1 = null;
		Tile tile2 = null;

		int tile1x;
		int tile1y;
		int tile2x;
		int tile2y;

		//this part scans for where ever 
		if(tileGrid[0][1] != null){
			if(tile1 != null){
				tile2 = new Tile();
				tile2x = 0;
				tile2y = 1;
			}
			else{
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
			else{
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
			else{
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
			else{
				tile1 = new Tile();
				tile1x = 1;
				tile1y = 2;
			}
		}

		//here the actual rotation happens
		if(tile1x == 0 && tile1y == 1 && tile2x == 1 && tile2y == 0){
			tileGrid[2][1] = new RiceField();
			tileGrid[tile1x][tile1y] = null;
			return true;
		}
		else if(tile1x == 1 && tile1y == 0 && tile2x == 2 && tile2y == 1){
			tileGrid[1][2] = new RiceField();
			tileGrid[tile1x][tile1y] = null;
			return true;
		}
		else if(tile1x == 2 && tile1y == 1 && tile2x == 1 && tile2y == 2){
			tileGrid[0][1] = new RiceField();
			tileGrid[tile1x][tile1y] = null;
			return true;
		}
		else if(tile1x == 1 && tile1y == 2 && tile2x == 0 && tile2y == 1){
			tileGrid[1][0] = new RiceField();
			tileGrid[tile1x][tile1y] = null;
			return true
		}
		//just in case
		return false;
	}
	public getGrid(){
		return tileGrid;
	}

	public setGrid(tileGrid[][] t){
		tileGrid = t;
	}
}