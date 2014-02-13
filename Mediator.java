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
		else{
			view.removeDeveloper(oldspace[0], oldspace[1]);

			view.developerCount(1);
		}
			
	}

	public void placeBlock(Block b, int[] coord)
	{
		if(board.placeBlock(b, board.getSpaces()[coord[0]][coord[1]])){
			Space[][] temp = boarod.getSpaces();
			Space ret = temp[coord[0]][coord[1]];
			if(ret.getTile() instanceof PalaceTile){
				//view.updateSpace(coord[0], coord[1], ret.getTile(), ret.getHeight());
				updateSpace(coord, ret.getBlock());
			}
			else{
				updateSpace(coord, re.getBlock());
				//view.updateSpace(coord[0], coord[1], ret.getTile(), ret.getHeight(), (PalaceTile)ret.getTile().getValue());
			}
		
			//check if one or two block
			if(b instanceof OneBlock){
				Tiles[][] tiles = b.getGrid();
				Tile checker = tiles[1][1];
				view.updateOneBlockCount(game.getPlayerName(), checker instanceof VillageTile);
			}
			else if(b instance of TwoBlock){
				view.updateTwoBlockCount(game.getPlayerName());
			}

		}
		else
			//notify error
	}

	public void placeDeveloper(Developer d, int[] coord)
	{
		if(board.placeDeveloper(d, coord)){
			view.updateDeveloper(int[0], int[1], game.getPlayerName());
			view.developerCount(-1);
		}
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


	public void placePalace(int[] coord, int value){
		Block b = board.placePalace(coord, value);
		updateSpace(coord, b);
	}

	public void upgradePalace(int[] coord, int value)
	{
		if(board.upgradePalace(int[] coord, int value)){
			Space[][] temp = boarod.getSpaces();
			Space ret = temp[coord[0]][coord[1]];
			updateSpace(coord, ret.getBlock());
			//view.updateSpace(coord[0], coord[1], ret.getTile(), ret.getHeight(), (PalaceTile)ret.getTile().getValue());
		}
			
	}

	public void selectSpace(int[] news, int[] old){
		view.highlightSpace(news[0], news[1];
		view.unHighlightSpace(old[0], old[1]);
	}

	public void switchDeveloper(Developer current, Developer last)
	{
		int[] coord = new int[2];
		coord = board.findDeveloper(current);
		view.switchFromDeveloper(coord[0], coord[1]);

		coord = board.findDeveloper(last);
		view.switchToDeveloper(coord[0], coord[1]);

	}

	public void saveGame()
	{
		
	}

	public void loadGame()
	{

	}

	public List<Player> getPlayers(){
		return
	}

	private void updateSpace(int[] coord, Block b){
		
		new Tile[][] tiles = b.getGrid();

		Space[][] temp = boarod.getSpaces();
		Space ret = temp[coord[0]][coord[1]];

		// iterate through grid and only place nonempty tiles
		for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[i].length; j++) {
				// check if empty part of grid
				if (tiles[i][j] != null) {
					// check for palace tile
					if (tiles[i][j] instanceof PalaceTile) {
						view.updateSpace(coord[0]+i, coord[1]+j, ret.getTile(), ret.getHeight(), (PalaceTile)ret.getTile().getValue());
					}
					else{
						view.updateSpace(coord[0]+i, coord[1]+j, ret.getTile(), ret.getHeight());
					}
				}
			}
		}
		
	}

}