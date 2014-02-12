import java.io.PrintWriter;
import java.io.File;

public class Mediator
{
	private GameMaster game;
	private Board board;
	private View view;
	private Controller controller;
	private File saveFile;

	public Mediator(GameMaster g, Board b, View v, Controller c)
	{
		game = g;
		board = b;
		view = v;
		controller = c;
	}

	public boolean moveDeveloper(Developer d, int x, int y)
	{
		game.moveDeveloper(game.getDeveloper(d), board.getSpaces()[x][y]));
	}

	public boolean placeTile(int tileSize, int x, int y)
	{
		int option;
		int rotationDone;

		if(tileSize == 1)
		{
			System.out.println("Do you want to place");
			System.out.println("1. Village 2. Irrigation");

			Block oneBlock = game.getOneBlock();

			//We need to update the view here (to display the 1 block)

			board.placeBlock(oneBlock, board.getSpaces()[x][y]);
		}
		if(tileSize == 2)
		{
			Block twoBlock = game.getTwoBlock();

			//Assumes controller has this method
			while(!controller.rotationDone(twoBlock))
			{
				twoBlock.rotate();
			}

			board.placeBlock(twoBlock, board.getSpaces()[x][y]);
		}
		if(tileSize == 3)
		{
			Block threeBlock = game.getThreeBlock();

			//Assumes controller has this method
			while(!controller.rotationDone(threeBlock))
			{
				threeBlock.rotate();
			}

			board.placeBlock(threeBlock, board.getSpaces()[x][y]);
		}
	}

	public boolean removeDeveloper()
	{
		
	}

	public void saveGame()
	{
		saveFile = new File("saveFile");
		PrintWriter writer = new PrintWriter();


		writer.close();
	}

	public void loadGame()
	{

	}

}