class TwoBlock extends Block {
	
	public TwoBlock(){
		tileGrid = new Tile[3][3];
		tileGrid[1][1] = new City();
		tileGrid[1][0] = new RiceField();
		//basically constructs it so that a ricefield is ontop of the city tile with the city tile in the center
	}

	public TwoBlock(Tile[][] t){
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
	
		int tile1x;
		int tile1y;
		//this part scans for where ever the rice tile is
		if(tileGrid[0][1] != null){
			tile2 = new Tile();
			tile2x = 0;
			tile2y = 1;
		}
		//#2
		if(tileGrid[1][0] != null){
			tile2 = new Tile();
			tile2x = 1;
			tile2y = 0;
		}
		//#3
		if(tileGrid[2][1] != null){
			tile1 = new Tile();
			tile1x = 2;
			tile1y = 1;
		}
		//#4
		if(tileGrid[1][2] != null){
			tile1 = new Tile();
			tile1x = 1;
			tile1y = 2;
		}

		//this is a two block rotation.
		else if(tile2 == null){
			if(tile1x == 0 && tile1y == 1){
				tileGrid[1][0] = new RiceField();
				tileGrid[tile1x][tile1y] = null;
				return true;
			}
			else if(tile1x == 1 && tile1y == 0){
				tileGrid[2][1] = new RiceField();
				tileGrid[tile1x][tile1y] = null;
				return true;
			}
			else if(tile1x == 2 && tile1y == 1){
				tileGrid[1][2] = new RiceField();
				tileGrid[tile1x][tile1y] = null;
				return true;
			}
			else if(tile1x == 1 && tile1y == 2){
				tileGrid[0][1] = new RiceField();
				tileGrid[tile1x][tile1y] = null;
				return true;
			}
			
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