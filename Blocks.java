class Blocks {
	private Tile[][] tileGrid;

	public Blocks(){
		tileGrid[1][1] = new Tile();
	}

	public Blocks(tileGrid[][] t){
		tileGrid = t;
	}

	public static boolean rotate(){
		//let's scan the 8 squares around the central tile. We will have three cases. If 
		Tile tile1;
		Tile tile2;

		int tile1x;
		int tile1y;
		int tile2x;
		int tile2y;

		//this part scans for tile1 and tile2 and their coordinates
		for(int i = 0; i < 8; i++){

		}

		if(tile2 == NULL){
			if(tile1y > tile1x){
				tileGrid[tile1x+1][tile1y-1] = new Tile();
				tileGrid[tile1x][tile1y] = NULL;
			}
		}
	}

	public getGrid(){
		return tileGrid;
	}

	public setGrid(tileGrid[][] t){
		tileGrid = t;
	}
}