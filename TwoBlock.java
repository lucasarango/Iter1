class TwoBlock extends Block {
	
	public TwoBlock() {
		tileGrid = new Tile[3][3];
		tileGrid[1][1] = new VillageTile();
		tileGrid[1][0] = new RiceTile();
		// basically constructs it so that a ricefield is ontop of the city tile
		// with the city tile in the center
	}

	public TwoBlock(Tile[][] t) {
		tileGrid = t;
	}

	public boolean rotate() {
		// this code works around the idea that we are rotating around one tile,
		// the central tile which is at [1][1]
		// This means that there are only 4 possibilities for the other blocks
		// to be at.
		// let's scan the 8 squares around the central tile. We will have three
		// cases

		// A lot of this is just straight hard coded.
		// [][][]
		// [][][]
		// [][][]

		// this part scans for where ever the rice tile is
		if (tileGrid[0][1] != null) {
			tileGrid[1][0] = new RiceTile();
			tileGrid[0][1] = null;
			return true;
		}
		// #2
		else if (tileGrid[1][0] != null) {
			tileGrid[2][1] = new RiceTile();
			tileGrid[1][0] = null;
			return true;
		}
		// #3
		else if (tileGrid[2][1] != null) {
			tileGrid[1][2] = new RiceTile();
			tileGrid[2][1] = null;
			return true;
		}
		// #4
		else if (tileGrid[1][2] != null) {
			tileGrid[0][1] = new RiceTile();
			tileGrid[1][2] = null;
			return true;
		}
		// this is a two block rotation

		// just incase it breaks somehow
		return false;
	}

	public Tile[][] getGrid() {
		return tileGrid;
	}

	public void setGrid(Tile[][] t) {
		tileGrid = t;
	}

}