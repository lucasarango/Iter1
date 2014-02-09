class ThreeBlock extends Block {

	public ThreeBlock(){
		tileGrid = new Tile[3][3];
		tileGrid[1][1] = new Village();
		tileGrid[1][0] = new RiceField();
		tileGrid[1][2] = new RiceField();
		//basically it's an L shape with a city in the center and a ricefield to the right and left in the grid
	}

	public ThreeBlock(Tile[][] t){
		tileGrid = t;
	}

	//our three block case
	public boolean rotate(){
		
		//this part scans for where ever 
		if(tileGrid[0][1] != null && tileGrid[1][0] != null){
			tileGrid[2][1] = new RiceField();
			tileGrid[0][1] = null;
			return true;
		}
		//#2
		else if(tileGrid[1][0] != null && tileGrid[2][1] != null){
			tileGrid[1][2] = new RiceField();
			tileGrid[1][0] = null;
			return true;
		}
		//#3
		else if(tileGrid[2][1] != null && tileGrid[1][2] != null){
			tileGrid[0][1] = new RiceField();
			tileGrid[2][1] = null;
			return true;
		}
		//#4
		else if(tileGrid[1][2] != null && tileGrid[0][1] != null){
			tileGrid[1][0] = new RiceField();
			tileGrid[1][2] = null;
			return true;
		}
		//this is a two block rotation
		
		//just incase it breaks somehow
		return false;
	}
	public Tile[][] getGrid(){
		return tileGrid;
	}

	public void setGrid(Tile[][] t){
		tileGrid = t;
	}
}