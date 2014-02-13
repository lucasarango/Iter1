import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.util.List;
import java.util.Scanner;


public class Mediator
{
	
	public static final String SAVEPATH="savefile";
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
		Space[][] spaces = board.getSpaces();

		for(int x = 0; x < spaces[0].length; x++)
		{
			for(int y = 0; y < spaces[1].length; y++)
			{
				view.updateSpace(x, y, spaces[x][y].getTile(), spaces[x][y].getHeight());
			}
		}
		
	}

	public List<Block> getBlockList(){
		return game.getPlayerBlocks();
	}

	public List<Developer> getDevelopers(){
		return game.getPlayerDevelopers();
	}

	public void moveDeveloper(Developer d, int[] offset)
	{
		int[] oldspace = board.findDeveloper(d);

		if(board.moveDeveloper(d, offset)){

			view.updateDeveloper(offset[0] + oldspace[0], offset[1] + oldspace[1], game.getPlayerName());

			view.removeDeveloper(oldspace[0], oldspace[1]);
		}
		else{
			view.removeDeveloper(oldspace[0], oldspace[1]);

			view.developerCount(game.getPlayerName(), 1);
		}
			
	}

	public void placeBlock(Block b, int[] coord)
	{

		if(board.placeBlock(b, coord)){
			System.out.println("Block placed");
			updateSpace(coord, b);
		
			//check if one or two block
			if(b instanceof OneBlock){
				Tile[][] tiles = b.getGrid();
				Tile checker = tiles[1][1];

				if(checker instanceof VillageTile)
					System.out.println("its a villlage!");
				else
					System.out.println("not a village");
				
				view.updateOneBlockCount(game.getPlayerName(), checker instanceof VillageTile);
			}
			else if(b instanceof TwoBlock){
				view.updateTwoBlockCount(game.getPlayerName());
			}

		}
		else{
			System.out.println("Block not placed");
		}
			//notify error
	}

	public void placeDeveloper(Developer d, int[] coord)
	{
		if(board.placeDeveloper(d, coord)){
			view.updateDeveloper(coord[0], coord[1], game.getPlayerName());
			view.developerCount(game.getPlayerName(),-1);
		}
		else{
		}
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
		Block b = board.getIrrigationTileBlock();
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


	public void placePalace(int[] coord, int value){
		Block b = board.placePalace(coord, value);
		updateSpace(coord, b);
	}

	public void upgradePalace(int[] coord, int value)
	{
		System.out.println("trying to upgrade " + coord[0] + " " + coord[1]);
		if(board.upgradePalace(coord, value)){
			System.out.println("Palace did upgrade");
			Space[][] temp = board.getSpaces();
			Space ret = temp[coord[0]+1][coord[1]+1];
			
			updateSpace(coord, ret.getBlock());
		}
		else{
			System.out.println("Didnt upgrade");
		}
			
	}

	public void selectSpace(int[] news, int[] old){
		view.highlightSpace(news[0], news[1]);
		view.unHighlightSpace(old[0], old[1]);
	}
	
	public void deselectSpace(int[] old){
		view.unHighlightSpace(old[0], old[1]);
	}

	public void switchDeveloper(Developer current, Developer last)
	{
		int[] coord = new int[2];
		if(current != null){
		coord = board.findDeveloper(current);
		view.switchToDeveloper(coord[0], coord[1]);
		}
		if(last != null){
		coord = board.findDeveloper(last);
		view.switchFromDeveloper(coord[0], coord[1]);
		}

	}

	public void saveGame() throws FileNotFoundException
	{
		PrintWriter savefile = new PrintWriter(SAVEPATH);
			game.save(savefile);
			board.save(savefile);
		savefile.close();
	}

	public void loadGame() throws FileNotFoundException
	{
		File loaded = new File(SAVEPATH);
		Scanner scanner = new Scanner(loaded);
		game.load(scanner);
		board.load(scanner);
		scanner.close();
		for (Player p:game.playerList){
			for (Developer d: p.getDevelopers()){
				if (d.getSpace()!=null){
					int x=d.getSpace().x;
					int y = d.getSpace().y;
					d.move(board.getSpaces()[y][x]);
				}
			}
		}
	}


	private void updateSpace(int[] coord, Block b){
		System.out.println("updating space");
		Tile[][] tiles = b.getGrid();
		Space[][] temp = board.getSpaces();
		
		
		// iterate through grid and only place nonempty tiles
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				// check if empty part of grid
				if (tiles[i][j] != null) {
					// check for palace tile
					if (tiles[i][j] instanceof PalaceTile) {
						
						Space ret = temp[coord[0]+i][coord[1]+j];
						System.out.println("its a palace");
						view.updateSpace(coord[0]+i-1, coord[1]+j-1, ret.getTile(), ret.getHeight(), ((PalaceTile)ret.getTile()).getValue());
					}
					else{
						Space ret = temp[coord[0]+i][coord[1]+j];
						System.out.println("not a palace");
						System.out.println("Coordinates arjnje " + coord[0]+i + " " + coord[1]+j );
						if(ret.getTile() != null)
							System.out.println("not null");
						else
							System.out.println("its null oops");
						
						view.updateSpace(coord[0]+i-1, coord[1]+j-1, ret.getTile(), ret.getHeight());
					}
				}
			}
		}
		
		view.updateScore( game.getPlayerName(), game.getPlayerScore());
		
	}
	
	public boolean endTurn(){
		return game.endTurn();
	}

}
