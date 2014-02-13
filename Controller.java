import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

public class Controller extends JFrame implements KeyListener { 
	
	private boolean TEST = true;
	
	protected static int DEVELOPER_LEFT 	= 0;
	protected static int DEVELOPER_RIGHT 	= 1;
	protected static int DEVELOPER_UP 		= 2;
	protected static int DEVELOPER_DOWN 	= 3;
	protected static int DEVELOPER_CHANGE	= KeyEvent.VK_TAB;
	protected static int DEVELOPER_PLACE	= KeyEvent.VK_D;
	
	protected static int MOVE_LEFT			= KeyEvent.VK_LEFT;
	protected static int MOVE_RIGHT			= KeyEvent.VK_RIGHT;
	protected static int MOVE_UP			= KeyEvent.VK_UP;
	protected static int MOVE_DOWN			= KeyEvent.VK_DOWN;
	
	protected static int BLOCK_CHANGE		= KeyEvent.VK_C;
	protected static int BLOCK_ROTATE		= KeyEvent.VK_R;
	protected static int BLOCK_PLACE		= KeyEvent.VK_B;
	
    protected static int ONEBLOCK           = KeyEvent.VK_1;
    protected static int TWOBLOCK           = KeyEvent.VK_2;
    protected static int THREEBLOCK         = KeyEvent.VK_3;

    protected static int VILLAGE			= KeyEvent.VK_V;
    protected static int IRRIGATION			= KeyEvent.VK_I;
    protected static int PALACE				= KeyEvent.VK_P;
	protected static int RICE				= KeyEvent.VK_R;
	
	protected static int PALACE_PLACE		= KeyEvent.VK_P;
	protected static int PALACE_UPGRADE		= KeyEvent.VK_U;

	protected static int ACTION_TOKEN_USE	= KeyEvent.VK_T;
	
	protected static int QUIT				= KeyEvent.VK_Q;	
	protected static int END_TURN			= KeyEvent.VK_ENTER;

	private List<Block> blockList;
    private List<Developer> developerList;
    //represents current developer
    private int developerIndex;

    private Block selectedBlock;
    private int[] coord = {0,0};
    
    private boolean placingBlock = false;
    private boolean placingPalace = false;
    private boolean upgradingPalace	= false;
    private int palaceLevel	= 0;
    private boolean placingDeveloper = false;
    private boolean selectingBlockSize = false;
    private boolean selectingOneBlock = false;
    
    private Mediator mediator;
    
    JLabel controlOutput;
    
    //DELETE THIS
    public static void main(String[] args) {
        Controller control = new Controller();
    }
    
    public Controller() {
        //First create the small window from which we take input
        initializeControl();
        //Now initialize the other components of the game
        initializeGame();
        developerIndex = 0;
    }
    
    private void initializeControl() {
        this.setBounds(50,50,500,500);
        this.getContentPane().setLayout(new FlowLayout());
        controlOutput = new JLabel("Welcome to Java");
        setTextMenu();
        this.add(controlOutput);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        controlOutput.setFocusable(true);
        controlOutput.addKeyListener(this);
    }
    
    private void initializeGame() {
    	List<String> names = new ArrayList<String>();
    	names.add("Player 1");
    	names.add("Player 2");
    	names.add("Player 3");
    	names.add("Player 4");
    	mediator = new Mediator(names);

    	//Initial developer and block list
    	developerList = mediator.getDevelopers();
    	blockList = mediator.getBlockList();
    	if(TEST) System.out.println("Developers: " + developerList.toString() + "\nBlocks: " + blockList.toString());
    }
    
    @Override
	public void keyPressed(KeyEvent e) {
    	int key = e.getKeyCode();
    	// State: Upgrading Palace
    	if(upgradingPalace) {
	    	if(key == MOVE_LEFT) {
				moveCursor(MOVE_LEFT);
				setTextPalaceUpgrade();
	    	}
			else if(key == MOVE_RIGHT) {
				moveCursor(MOVE_RIGHT);
				setTextPalaceUpgrade();
			}
			else if(key == MOVE_UP) {
				moveCursor(MOVE_UP);
				setTextPalaceUpgrade();
			}
			else if(key == MOVE_DOWN) {
				moveCursor(MOVE_DOWN);
				setTextPalaceUpgrade();
			}
			else if(key == PALACE_UPGRADE) {
				palaceLevel++;
				setTextPalaceUpgrade();
			}
			else if(key == PALACE_PLACE) {
				mediator.deselectSpace(coord);
				upgradePalace();
				resetCursor();
				upgradingPalace = false;
				palaceLevel = 0;
				setTextMenu();
			}
			else if(key == QUIT) {
				upgradingPalace = false;
				palaceLevel = 0;
				resetCursor();
				mediator.deselectSpace(coord);
				setTextMenu();
			}
	    	return;
    	}
    	
    	// State: Placing Palace
    	if(placingPalace) {
	    	if(key == MOVE_LEFT) {
				moveCursor(MOVE_LEFT);
				setTextPlacingPalace();
	    	}
			else if(key == MOVE_RIGHT) {
				moveCursor(MOVE_RIGHT);
				setTextPlacingPalace();
			}
			else if(key == MOVE_UP) {
				moveCursor(MOVE_UP);
				setTextPlacingPalace();
			}
			else if(key == MOVE_DOWN) {
				moveCursor(MOVE_DOWN);
				setTextPlacingPalace();
			}
			else if(key == PALACE_UPGRADE) {
				palaceLevel++;
				setTextPlacingPalace();
			}
			else if(key == PALACE_PLACE) {
				mediator.deselectSpace(coord);
				placePalace();
				resetCursor();
				placingPalace = false;
				palaceLevel = 0;
				setTextMenu();
			}
			else if(key == QUIT) {
				placingPalace = false;
				palaceLevel = 0;
				mediator.deselectSpace(coord);
				resetCursor();
				setTextMenu();
			}
	    	return;
    	}
    	
    	// State: Placing Block
    	if(placingBlock) {
    		if(key == MOVE_LEFT) {
				moveCursor(MOVE_LEFT);
				setTextPlaceBlock();
	    	}
			else if(key == MOVE_RIGHT) {
				moveCursor(MOVE_RIGHT);
				setTextPlaceBlock();
			}
			else if(key == MOVE_UP) {
				moveCursor(MOVE_UP);
				setTextPlaceBlock();
			}
			else if(key == MOVE_DOWN) {
				moveCursor(MOVE_DOWN);
				setTextPlaceBlock();
			}
			else if(key == BLOCK_ROTATE) {
				rotateBlock();
				setTextPlaceBlock();
			}
			else if(key == BLOCK_PLACE) {
				placeBlock();
				mediator.deselectSpace(coord);
				resetCursor();
				placingBlock = false;
				setTextMenu();
			}
			else if(key == QUIT) {
				returnBlock();
				placingBlock = false;
				mediator.deselectSpace(coord);
				resetCursor();
				setTextMenu();
			}
    		return;
    	}
    	
    	// State: Placing Developer
    	if(placingDeveloper) {
    		if(key == MOVE_LEFT) {
				moveCursor(MOVE_LEFT);
				setTextPlaceDeveloper();
	    	}
			else if(key == MOVE_RIGHT) {
				moveCursor(MOVE_RIGHT);
				setTextPlaceDeveloper();
			}
			else if(key == MOVE_UP) {
				moveCursor(MOVE_UP);
				setTextPlaceDeveloper();
			}
			else if(key == MOVE_DOWN) {
				moveCursor(MOVE_DOWN);
				setTextPlaceDeveloper();
			}
			else if(key == DEVELOPER_PLACE) {
				placeDeveloper();
				mediator.deselectSpace(coord);
				resetCursor();
				placingDeveloper = false;
				setTextMenu();
			}
			else if(key == QUIT) {
				placingDeveloper = false;
				mediator.deselectSpace(coord);
				resetCursor();
				setTextMenu();
			}
    		return;
    	}
    	// State: Choosing block size
    	if(selectingBlockSize) {
    		if(key == ONEBLOCK) {
    			selectingBlockSize = false;
    			selectingOneBlock = true;
    			setTextSelectingOneBlock();
    		}
    		else if(key == TWOBLOCK) {
    			selectBlock('2');
    			selectingBlockSize = false;
    			placingBlock = true;
    			setTextPlaceBlock();
    		}
    		else if(key == THREEBLOCK) {
    			selectBlock('3');
    			selectingBlockSize = false;
    			placingBlock = true;
    			setTextPlaceBlock();
    		}
    		return;
    	}
    	// State: Choosing one-block size
    	if(selectingOneBlock) {
    		if(key == IRRIGATION) {
    			selectBlock('I');
    			selectingOneBlock = false;
    			placingBlock = true;
    			setTextPlaceBlock();
    		}
    		else if(key == VILLAGE) {
    			selectBlock('V');
    			selectingOneBlock = false;
    			placingBlock = true;
    			setTextPlaceBlock();
    		}
    		else if(key == PALACE) {
    			selectBlock('P');
    			selectingOneBlock = false;
    			placingPalace = true;
    			setTextPlacingPalace();
    		}
    		else if(key == RICE) {
    			selectBlock('R');
    			selectingOneBlock = false;
    			placingBlock = true;
    			setTextPlaceBlock();
    		}
    		return;
    	}
    	
		// Moving Developer
		if(key == DEVELOPER_LEFT) {
			moveDeveloper(DEVELOPER_LEFT);
			return;
		}
		else if(key == DEVELOPER_RIGHT) {
			moveDeveloper(DEVELOPER_RIGHT);
			return;
		}
		else if(key == DEVELOPER_UP) {
			moveDeveloper(DEVELOPER_UP);
			return;
		}
		else if(key == DEVELOPER_DOWN) {
			moveDeveloper(DEVELOPER_DOWN);
			return;
		}
		
		// Scroll developer
		else if(key == DEVELOPER_CHANGE) {
			changeDeveloper();
			return;
		}
		
		else if(key == DEVELOPER_PLACE) {
			for(int i = 0; i < developerList.size(); i++) {
				if(!isDeveloperOnBoard(i)) {
					developerIndex = i;
					break;
				}
			}
			placingDeveloper = true;
			setTextPlaceDeveloper();
			return;
		}
		
		else if(key == PALACE_UPGRADE) {
			selectPalace();
			setTextPalaceUpgrade();
			return;
		}
		else if(key == BLOCK_PLACE) {
			setTextSelectingBlockSize();
			selectingBlockSize = true;
			return;
		}
		
		// Play action token
		else if(key == ACTION_TOKEN_USE) {
			//mediator.
		}
		
		// End turn
		else if(key == END_TURN) {
			endTurn();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {

	}
	@Override
	public void keyTyped(KeyEvent e) {
        /*if (e.getKeyCode() == ONEBLOCK) {
        	controlOutput.setText("Press I for irrigation, V for village, and R for rice.");
        }
        else if (e.getKeyCode() == TWOBLOCK){
        	controlOutput.setText("Choose a direction (up, down, left, right)");
        }
        else if (e.getKeyCode() == THREEBLOCK) {
        	controlOutput.setText("Choose a direction (up, down, left, right)");
        }

        if(e.getKeyCode() == I)*/
	}
	
	private void moveDeveloper(int direction) {
        int[] a = {0 ,0};
		if(direction == DEVELOPER_LEFT) {
            a[0] = -1;
            if(TEST) System.out.println("Moving developer left");
            mediator.moveDeveloper(developerList.get(developerIndex), a);
		}
		else if(direction == DEVELOPER_RIGHT) {
            a[0] = 1;
            if(TEST) System.out.println("Moving developer right");
            mediator.moveDeveloper(developerList.get(developerIndex), a);
		}
		else if(direction == DEVELOPER_UP) {
            a[1] = 1;
            if(TEST) System.out.println("Moving developer up");
            mediator.moveDeveloper(developerList.get(developerIndex), a);
		}
		else if(direction == DEVELOPER_DOWN) {
            a[1] = -1;
            if(TEST) System.out.println("Moving developer down");
            mediator.moveDeveloper(developerList.get(developerIndex), a);
		}
	}

    private void selectDeveloperOffBoard(){
    	while(isDeveloperOnBoard())
    	{
    		developerIndex = (developerIndex++) % developerList.size();
    	}
    }

    private void placeDeveloper()
    {
    	if(TEST) System.out.println("Placing developer");
    	mediator.placeDeveloper(developerList.get(developerIndex), coord);
    	placingDeveloper = false;
    }

	private void changeDeveloper() {
		Developer oldGuy = developerList.get(developerIndex);
		for(int i = 0; i < developerList.size(); i++) {
			if(isDeveloperOnBoard(i)) {
				developerIndex = i;
			}
		}
		if(TEST) System.out.println("Switching developer");
	    mediator.switchDeveloper(developerList.get(developerIndex), oldGuy);	    
    }
	
	private void changeDeveloper(Developer oldGuy) {
		for(int i = 0; i < developerList.size(); i++) {
			if(isDeveloperOnBoard(i)) {
				developerIndex = i;
			}
			else if(i == developerList.size() - 1) {
				mediator.switchDeveloper(null, oldGuy);	
			}
		}
	    mediator.switchDeveloper(developerList.get(developerIndex), oldGuy);	    
    }


	private void moveCursor(int direction) {
		int[] oldCoot = Arrays.copyOf(coord, 2);
		if(direction == MOVE_LEFT) {
			coord[0] = (coord[0]-- < 0) ? 0 : coord[0];
		}
		else if(direction == MOVE_RIGHT) {
			coord[0] += 1;
		}
		else if(direction == MOVE_UP) {
			coord[1] += 1;
		}
		else if(direction == MOVE_DOWN) {
		  	coord[1] = (coord[1]-- < 0) ? 0 : coord[1];
		}
		mediator.selectSpace(coord, oldCoot);
		return;
	}
	
	private void resetCursor() {
		coord[0] = 0;
		coord[1] = 0;
	}
	
	private Tile getBlockType(Block b) {
		Tile[][] tileArray = b.getGrid();
		return tileArray[1][1];
	}
	
	private boolean selectBlock(char blockType) {
		if(blockType == 'V') {
			for(Block b : blockList) {
				if(TEST) System.out.println("block types");
				if(getBlockType(b) instanceof VillageTile && b instanceof OneBlock) {
					if(TEST) System.out.println("Obtaining village block");
					selectedBlock = b;
					return true;
				}
			}
			return false;
		}
		else if(blockType == 'I') {
			selectedBlock = mediator.getIrrigationTile();
			if(getBlockType(selectedBlock) instanceof Tile && selectedBlock instanceof OneBlock) {
				if(TEST) System.out.println("Obtaining irrigation block");
				return true;
			}
			else return false;
		}
		/*else if(blockType == 'P') {
			selectedBlock = mediator.getPalaceTile();
			if(getBlockType(selectedBlock) instanceof Tile && selectedBlock instanceof OneBlock)
				return true;
			else return false;
		}*/
		else if(blockType == 'R') {
			for(Block b : blockList) {
				if(getBlockType(b) instanceof VillageTile && b instanceof OneBlock) {
					if(TEST) System.out.println("Obtaining village block");
					selectedBlock = b;
					return true;
				}
			}
			return false;
		}
		else if(blockType == '2') {
			for(Block b : blockList) {
				if(b instanceof TwoBlock) {
					selectedBlock = b;
					if(TEST) System.out.println("Obtaining 2 block");
					return true;
				}
			}
			return false;
		}
		else if(blockType == '3') {
			selectedBlock = mediator.getThreeBlock();
			if(selectedBlock instanceof ThreeBlock) {
				if(TEST) System.out.println("Obtaining 3 block");
				return true;
			}
			else return false;
		}
		return false;
	}
	
	private boolean placeBlock() {
		if(TEST) System.out.println("Placing block");
		mediator.placeBlock(selectedBlock, coord);
		return true;
	}
	
	private void returnBlock() {
		if(selectedBlock instanceof ThreeBlock) {
			if(TEST) System.out.println("Returning 3 block");
			mediator.returnThreeBlock(selectedBlock);
		}
		else if((getBlockType(selectedBlock) instanceof VillageTile && selectedBlock instanceof OneBlock)) {
			if(TEST) System.out.println("Returning irrigation tile");
			mediator.returnIrrigationTile(selectedBlock);
		}
		
	}
	
	private boolean isDeveloperOnBoard() {
		Developer d = developerList.get(developerIndex);
		if(d.getSpace() instanceof Space) {
			return true;
		}
		return false;
	}
	
	private boolean isDeveloperOnBoard(int developerIndex) {
		Developer d = developerList.get(developerIndex);
		if(d.getSpace() instanceof Space) {
			return true;
		}
		return false;
	}
	
	private void selectPalace() {
		upgradingPalace = true;
		
	}
	private void placePalace() {
		if(TEST) System.out.println("Placing palace");
		mediator.placePalace(coord, palaceLevel);
	}
	
	private void upgradePalace() {
		if(TEST) System.out.println("Upgrading palace");
		mediator.upgradePalace(coord, palaceLevel);
	}
	
	private void rotateBlock() {
		if(TEST) System.out.println("Rotating block");
		mediator.rotateBlock(selectedBlock);
	}

	//Pull new block lists, new developer lists, etc
	private void endTurn()
	{
		if(TEST) System.out.println("Ending turn");
		placingBlock = false;
     	upgradingPalace	= false;
     	palaceLevel	= 0;
    	placingDeveloper = false;
    	selectingBlockSize = false;
        selectingOneBlock = false;

        Developer temp = developerList.get(developerIndex);
        mediator.endTurn();
        blockList = mediator.getBlockList();
        developerList = mediator.getDevelopers();
	}

	
	private String printBlock(Block b) {
		String s = "<html><body>";
		s += "============================<br>";
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (b.getGrid()[j][i] == null)
					s += "+";
				else if (b.getGrid()[j][i] instanceof PalaceTile)
					s += "P";
				else if (b.getGrid()[j][i] instanceof VillageTile)
					s += "V";
				else if (b.getGrid()[j][i] instanceof IrrigationTile)
					s += "I";
				else if (b.getGrid()[j][i] instanceof RiceTile)
					s += "R";
				s += " ";
			}
			s += "<br>";
		}
		s += "============================<br><html><body>";
		return s;
	}
	private void setTextPlaceBlock() {
		controlOutput.setText("<html><body>Placing block. Coordinates: " + Arrays.toString(coord) + ".<br>" + printBlock(selectedBlock) +
				"\n" + "Press R to rotate, B to place tile, and Q to quit.</body></html>");
	}
	private void setTextPalaceUpgrade() {
		controlOutput.setText("<html><body>Upgrading palace. Coordinates: " + Arrays.toString(coord) + ", Level: " + palaceLevel + ".<br>Press P to confirm, " +
				"U to upgrade palace, and Q to quit.</body></html>");
	}
	private void setTextPlacingPalace() {
		controlOutput.setText("<html><body>Placing palace. Coordinates: " + Arrays.toString(coord) + ", Level: " + palaceLevel + ".<br>Press P to confirm, " +
				"U to upgrade palace, and Q to quit.</body></html>");
	}
	private void setTextMenu() {
		controlOutput.setText("<html><body>U: Upgrade palace.<br>B: Place block.<br>D: Place developer.<br>TAB: Switch selected developer.<br>ENTER: End turn.</html></body>"); // Main Menu
	}
	private void setTextPlaceDeveloper() {
		controlOutput.setText("<html><body>Placing Developer. Coordinates: " + Arrays.toString(coord) +
				"D to place developer, and Q to quit.</html></body>");
	}
	private void setTextSelectingBlockSize() {
		controlOutput.setText("<html><body>Choose block size:<br>1: One-space tile<br>2: Two-space tile<br>3: Three-space tile</html></body>");
	}
	private void setTextSelectingOneBlock() {
		controlOutput.setText("<html><body>Choose the one-tile space:<br>V: Village<br>I: Irrigation<br>P: Palace<br>R: Rice</html></body>");
	}

}
