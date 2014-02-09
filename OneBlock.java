
class OneBlock extends Block {

	public OneBlock(Tile.TileType t){
		tileGrid = new Tile[3][3];
		switch(t){
			case RICE:
				tileGrid[1][1] = new RiceField();
				break;
			case IRRIGATION:
				tileGrid[1][1] = new Irrigation();
				break;
			case VILLAGE:
				tileGrid[1][1] = new Village();
				break;
			default:
				tileGrid[1][1] = new Village();
		}
	}

	public OneBlock(Tile[][] t){
		tileGrid = t;
	}

	public boolean rotate(){
		return true; 
	}

	public Tile[][] getGrid(){
		return tileGrid;
	}

	public void setGrid(Tile[][] t){
		tileGrid = t;
	}
}