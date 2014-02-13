import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class Controller extends JFrame implements KeyListener { 
	
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
	protected static int RICE				= KeyEvent.VK_R;
	
	protected static int PALACE_PLACE		= KeyEvent.VK_P;
	protected static int PALACE_UPGRADE		= KeyEvent.VK_U;

	protected static int ACTION_TOKEN_USE	= KeyEvent.VK_T;
	
	protected static int QUIT				= KeyEvent.VK_Q;	
	protected static int END_TURN			= KeyEvent.VK_ENTER;

	private ArrayList<Block> blockList;
    private ArrayList<Developer> developerList;
    //represents current developer
    private int developerIndex;

    private Block selectedBlock;
    private int[] coord = {0,0};
    
    private boolean placingBlock = false;
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
    	mediator = new Mediator();
    }
    
    @Override
	public void keyPressed(KeyEvent e) {
    	System.out.println("Key pressed");
    	int key = e.getKeyCode();
    	// State: Selecting Palace
    	if(upgradingPalace) {
	    	if(key == MOVE_LEFT) {
				moveCursor(MOVE_LEFT);
				setTextPalaceUpgrade();
				return;
	    	}
			else if(key == MOVE_RIGHT) {
				moveCursor(MOVE_RIGHT);
				setTextPalaceUpgrade();
				return;
			}
			else if(key == MOVE_UP) {
				moveCursor(MOVE_UP);
				setTextPalaceUpgrade();
				return;
			}
			else if(key == MOVE_DOWN) {
				moveCursor(MOVE_DOWN);
				setTextPalaceUpgrade();
				return;
			}
			else if(key == PALACE_UPGRADE) {
				palaceLevel++;
				setTextPalaceUpgrade();
				return;
			}
			else if(key == PALACE_PLACE) {
				upgradePalace();
				upgradingPalace = false;
				palaceLevel = 0;
				resetCursor();
				setTextMenu();
				return;
			}
			else if(key == QUIT) {
				upgradingPalace = false;
				palaceLevel = 0;
				resetCursor();
				setTextMenu();
				return;
			}
    	}
    	
    	// State: Placing Block
    	if(placingBlock) {
    		if(key == MOVE_LEFT) {
				moveCursor(MOVE_LEFT);
				setTextPlaceBlock();
				return;
	    	}
			else if(key == MOVE_RIGHT) {
				moveCursor(MOVE_RIGHT);
				setTextPlaceBlock();
				return;
			}
			else if(key == MOVE_UP) {
				moveCursor(MOVE_UP);
				setTextPlaceBlock();
				return;
			}
			else if(key == MOVE_DOWN) {
				moveCursor(MOVE_DOWN);
				setTextPlaceBlock();
				return;
			}
			else if(key == BLOCK_ROTATE) {
				rotateBlock();
				setTextPlaceBlock();
				return;
			}
			else if(key == BLOCK_PLACE) {
				placeBlock();
				placingBlock = false;
				resetCursor();
				setTextMenu();
				return;
			}
			
			else if(key == QUIT) {
				returnBlock();
				placingBlock = false;
				resetCursor();
				setTextMenu();
				return;
			}
    	}
    	
    	// State: Placing Developer
    	if(placingDeveloper) {
    		if(key == MOVE_LEFT) {
				moveCursor(MOVE_LEFT);
				setTextPlaceDeveloper();
				return;
	    	}
			else if(key == MOVE_RIGHT) {
				moveCursor(MOVE_RIGHT);
				setTextPlaceDeveloper();
				return;
			}
			else if(key == MOVE_UP) {
				moveCursor(MOVE_UP);
				setTextPlaceDeveloper();
				return;
			}
			else if(key == MOVE_DOWN) {
				moveCursor(MOVE_DOWN);
				setTextPlaceDeveloper();
				return;
			}
			else if(key == DEVELOPER_PLACE) {
				placeDeveloper();
				placingDeveloper = false;
				resetCursor();
				setTextMenu();
				return;
			}
			else if(key == QUIT || key == DEVELOPER_CHANGE) {
				placingDeveloper = false;
				resetCursor();
				setTextMenu();
				return;
			}
    	}
    	// State: Choosing block size
    	if(selectingBlockSize) {
    		if(key == ONEBLOCK) {
    			selectingBlockSize = false;
    			selectingOneBlock = true;
    			setTextSelectingOneBlock();
    			return;
    		}
    		else if(key == TWOBLOCK) {
    			selectBlock('2');
    			selectingBlockSize = false;
    			placingBlock = true;
    			setTextPlaceBlock();
    			return;
    		}
    		else if(key == THREEBLOCK) {
    			selectBlock('3');
    			selectingBlockSize = false;
    			placingBlock = true;
    			setTextPlaceBlock();
    			return;
    		}
    	}
    	// State: Choosing one-block size
    	if(selectingOneBlock) {
    		if(key == IRRIGATION) {
    			selectBlock('I');
    			selectingOneBlock = false;
    			placingBlock = true;
    			setTextPlaceBlock();
    			return;
    		}
    		else if(key == VILLAGE) {
    			selectBlock('V');
    			selectingOneBlock = false;
    			placingBlock = true;
    			setTextPlaceBlock();
    			return;
    		}
    		else if(key == RICE) {
    			selectBlock('R');
    			selectingOneBlock = false;
    			placingBlock = true;
    			setTextPlaceBlock();
    			return;
    		}
    	}
    	
		// Moving Developer
		if(key == DEVELOPER_LEFT)
			moveDeveloper(DEVELOPER_LEFT);
		else if(key == DEVELOPER_RIGHT)
			moveDeveloper(DEVELOPER_RIGHT);
		else if(key == DEVELOPER_UP)
			moveDeveloper(DEVELOPER_UP);
		else if(key == DEVELOPER_DOWN)
			moveDeveloper(DEVELOPER_DOWN);
		
		// Scroll developer
		else if(key == DEVELOPER_CHANGE) {
			changeDeveloper();
			if(!isDeveloperOnBoard()) {
				placingDeveloper = true;
			}
		}
		
		else if(key == PALACE_UPGRADE) {
			selectPalace();
			setTextPalaceUpgrade();
		}
		else if(key == BLOCK_PLACE) {
			setTextSelectingBlockSize();
			selectingBlockSize = true;
		}
		
		// Play action token
		else if(key == ACTION_TOKEN_USE) {
			//mediator.
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
            mediator.moveDeveloper(developerList.get(developerIndex), a);
		}
		else if(direction == DEVELOPER_RIGHT) {
            a[0] = 1;
            mediator.moveDeveloper(developerList.get(developerIndex), a);
		}
		else if(direction == DEVELOPER_UP) {
            a[1] = 1;
            mediator.moveDeveloper(developerList.get(developerIndex), a);
		}
		else if(direction == DEVELOPER_DOWN) {
            a[1] = -1;
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
    	mediator.placeDeveloper(developerList.get(developerIndex), coord)
    	placeDeveloper = false;
    }

	private void changeDeveloper() {
		Developer oldGuy = developerList.get(developerIndex);
	    developerIndex = (developerIndex + 1) % developerList.size();
	    mediator.switchDeveloper(developerList.get(developerIndex), oldGuy);	    
    }


	private void moveCursor(int direction) {
		int[] oldCoot = Arrays.copyOf(coord, 2);
		if(direction == MOVE_LEFT) {
			coord[0] += -1;
		}
		else if(direction == MOVE_RIGHT) {
			coord[0] += 1;
		}
		else if(direction == MOVE_UP) {
			coord[1] += 1;
		}
		else if(direction == MOVE_DOWN) {
		  	coord[1] += -1;
		}
		//mediator.selectSpace(coord, oldCoot);
		return;
	}
	
	private void resetCursor() {
		coord[0] = 0;
		coord[1] = 0;
	}
	
	private Tile getBlockType(Block b) {
		Tile[][] tileArray = b.getGrid();
		return tileArray[2][2];
	}
	
	private boolean selectBlock(char blockType) {
		if(blockType == 'V') {
			for(Block b : blockList) {
				if(getBlockType(b) instanceof VillageTile && b instanceof OneBlock) {
					selectedBlock = b;
					return true;
				}
			}
			return false;
		}
		else if(blockType == 'I') {
			selectedBlock = mediator.getIrrigationTile();
			if(getBlockType(selectedBlock) instanceof Tile && selectedBlock instanceof OneBlock)
				return true;
			else return false;
		}
		else if(blockType == 'R') {
			for(Block b : blockList) {
				if(getBlockType(b) instanceof VillageTile && b instanceof OneBlock) {
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
					return true;
				}
			}
			return false;
		}
		else if(blockType == '3') {
			selectedBlock = mediator.getThreeBlock();
			if(selectedBlock instanceof ThreeBlock)
				return true;
			else return false;
		}
		return false;
	}
	
	private boolean placeBlock() {
		mediator.placeBlock(selectedBlock, coord);
		return true;
	}
	
	private void returnBlock() {
		if((getBlockType(selectedBlock) instanceof VillageTile && selectedBlock instanceof OneBlock) ||
				selectedBlock instanceof ThreeBlock) {
			mediator.returnBlock(selectedBlock);
		}
		
	}
	
	private boolean isDeveloperOnBoard() {
		Developer d = developerList.get(developerIndex);
		if(d.getSpace() instanceof Space) {
			return true;
		}
		return false;
	}
	
	private void selectPalace() {
		upgradingPalace = true;
		
	}
	
	private void upgradePalace() {
		mediator.upgradePalace(coord, palaceLevel);
	}
	
	private void rotateBlock() {
		mediator.rotateBlock(selectedBlock);
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
		controlOutput.setText("<html><body>Placing block. Coordinate: " + Arrays.toString(coord) + ".<br>" + printBlock(selectedBlock) +
				"\n" + "Press R to upgrade, " +	"P to place palace, and Q to quit.</body></html>");
	}
	private void setTextPalaceUpgrade() {
		controlOutput.setText("<html><body>Upgrading palace. Coordinate: " + Arrays.toString(coord) + ", Level: " + palaceLevel + ".<br>Press P to confirm, " +
				"U to upgrade palace, and Q to quit.</body></html>");
	}
	private void setTextMenu() {
		controlOutput.setText("<html><body>U: Upgrade palace.<br>B: Place block.</html></body>"); // Main Menu
	}
	private void setTextPlaceDeveloper() {
		controlOutput.setText("<html><body>Placing Developer. Coordinate: " + Arrays.toString(coord) +
				"D to place developer, and Q to quit.</html></body>");
	}
	private void setTextSelectingBlockSize() {
		controlOutput.setText("<html><body>Choose block size:<br>1: One-space tile<br>2: Two-space tile<br>3: Three-space tile</html></body>");
	}
	private void setTextSelectingOneBlock() {
		controlOutput.setText("<html><body>Choose the one-tile space:<br>V: Village<br>I: Irrigation<br>R: Rice</html></body>");
	}
}
