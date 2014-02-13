import java.io.PrintWriter;
import java.io.File;

public class Mediator
{
	private GameMaster game;
	private Board board;
	private View view;
	private Controller controller;

	public Mediator(List<String> names)
	{
		game = new GameMaster(names, this);
		board = new Board(this);
		view = new View(names);

		//update board in view
		Spaces[][] spaces = board.getSpaces();

		for(int x = 0; x < spaces[0].length; x++)
		{
			for(int y = 0; y < spaces[1].length; y++)
			{
				view.updateSpace(x, y, spaces[x][y].getTile(), spaces[x][y].getHeight());
			}
		}
		
	}

	public void moveDeveloper(Developer d, int[] offset)
	{
		int[] oldspace = board.findDeveloper(d);

		if(board.moveDeveloper(d, offset)){

			view.updateDeveloper(offset[0] + oldspace[0], offset[1] + oldspace[1], game.getPlayerName());

			view.removeDeveloper(oldspace[0], oldspace[1]);
		}
		else
			//notify error
	}

	public void placeBlock(Block b, int[] coord)
	{
		if(board.placeBlock(b, board.getSpaces()[coord[0]][coord[1]]))
			view.updateSpace(coord[0], coord[1], board.getSpaces()[coord[0]][coord[1]]).getTile(), board.getSpaces()[coord[0]][coord[1]]).getHeight())
		else
			//notify error
	}

	public void placeDeveloper(Developer d, int[] coord)
	{
		if(board.placeDeveloper(d, coord))
			view.updateDeveloper(int[0], int[1], game.getPlayerName());
		else
			//notify error
	}

	public Block rotateBlock(Block b){
		if(b.rotate())
			return b;
		else
			return b;
	}

	public Block getThreeBlock(){
		Block b = board.getThreeBlock();
		if(b != null)
			return b;
		else{
			board.returnThreeBlock(b);
			return null;
		}
	}

	public boolean returnThreeBlock(Block b){
		return board.returnThreeBlock(b);
	}

	public Block getIrrigationTile(){
		Block b = board.getIrrigationTile();
		if(b != null)
			return b;
		else{
			board.returnIrrigationTile(b);
			return null;
		}
	}

	public boolean returnIrrigationTile(Block b){
		return board.returnIrrigationTile(b);
	}

	public boolean removeDeveloper()
	{
		
	}

	public void saveGame()
	{
		
	}

	public void loadGame()
	{

	}

}