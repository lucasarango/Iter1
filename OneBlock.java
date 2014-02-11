
class OneBlock extends Block {

	public OneBlock(Tile t){
		tileGrid = new Tile[3][3];
		tileGrid[1][1] = t;
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