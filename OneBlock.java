class OneBlock extends Block {

	public OneBlock(Tile.TileType t){
		tileGrid = new Tile[3][3];
		switch(t){
			case Tile.TileType.RICE:
				tileGrid[1][1] = new RiceField();
				break;
			case Tile.TileType.IRRIGATION:
				tileGrid[1][1] = new Irrigation();
				break;
			case Tile.TileType.VILLAGE:
				tileGrid[1][1] = new Village();
				break;
			default:
				tileGrid[1][1] = new Village();
		}
	}

	public OneBlock(Tile[][] t){
		tileGrid = t;
	}

	public static boolean rotate(){
		return true; 
	}

	public getGrid(){
		return tileGrid;
	}

	public setGrid(tileGrid[][] t){
		tileGrid = t;
	}
}