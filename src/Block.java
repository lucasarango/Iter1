abstract class Block {
	protected Tile[][] tileGrid;

	abstract public boolean rotate();

	abstract public Tile[][] getGrid();

	abstract public void setGrid(Tile[][] t);
	/*
	 * one blocks it is variable
	 * 
	 * two blocks rice city
	 * 
	 * three blocks city and rice on either side
	 */
}