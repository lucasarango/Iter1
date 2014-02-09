abstract class Block {
	abstract private Tile[][] tileGrid;

	abstract public static boolean rotate();
		
	abstract public getGrid();

	abstract public setGrid(tileGrid[][] t);
	/*
		one blocks
		it is variable

		two blocks
		rice city

		three blocks
		city and rice on either side
	*/
}