import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Controller extends JFrame implements KeyListener { 
	
	protected static int DEVELOPER_LEFT 	= 0;
	protected static int DEVELOPER_RIGHT 	= 1;
	protected static int DEVELOPER_UP 		= 2;
	protected static int DEVELOPER_DOWN 	= 3;
	protected static int DEVELOPER_CHANGE	= KeyEvent.VK_TAB;
	
	protected static int BLOCK_LEFT			= KeyEvent.VK_LEFT;
	protected static int BLOCK_RIGHT		= KeyEvent.VK_RIGHT;
	protected static int BLOCK_UP			= KeyEvent.VK_UP;
	protected static int BLOCK_DOWN			= KeyEvent.VK_DOWN;
	protected static int BLOCK_CHANGE		= KeyEvent.VK_C;
	protected static int BLOCK_ROTATE		= KeyEvent.VK_R;
	protected static int BLOCK_PLACE		= KeyEvent.VK_P;
	
    protected static int ONEBLOCK           = KeyEvent.VK_1;
    protected static int TWOBLOCK           = KeyEvent.VK_2;
    protected static int THREEBLOCK         = KeyEvent.VK_3;

    protected static int IRRIGATION			= KeyEvent.VK_I;
	protected static int RICE				= KeyEvent.VK_R;

	protected static int ACTION_TOKEN_USE	= KeyEvent.VK_T;
	
	protected static int END_TURN			= KeyEvent.VK_ENTER;

	private ArrayList<Block> blockList;
    private ArrayList<Developer> developerList;
    //represents current developer
    private int developerIndex;

    private Block selectedBlock;
    private int[] coord = {0,0};
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
        this.setBounds(50,50,150,150);
        this.getContentPane().setLayout(new FlowLayout());
        controlOutput = new JLabel("Welcome to Java");
        this.add(controlOutput);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void initializeGame() {
    
    }
    
    @Override
	public void keyPressed(KeyEvent e) {
		// Move Developer
		if(e.getKeyCode() == DEVELOPER_LEFT)
			moveDeveloper(DEVELOPER_LEFT);
		else if(e.getKeyCode() == DEVELOPER_RIGHT)
			moveDeveloper(DEVELOPER_RIGHT);
		else if(e.getKeyCode() == DEVELOPER_UP)
			moveDeveloper(DEVELOPER_UP);
		else if(e.getKeyCode() == DEVELOPER_DOWN)
			moveDeveloper(DEVELOPER_DOWN);
		
		// Scroll developer
		else if(e.getKeyCode() == DEVELOPER_CHANGE)
			changeDeveloper();
		
		// Select/Scroll block
		else if(e.getKeyCode() == BLOCK_CHANGE)
			changeBlock();
		
		// Move block
		else if(e.getKeyCode() == BLOCK_LEFT)
			moveBlock(BLOCK_LEFT);
		else if(e.getKeyCode() == BLOCK_RIGHT)
			moveBlock(BLOCK_RIGHT);
		else if(e.getKeyCode() == BLOCK_UP)
			moveBlock(BLOCK_UP);
		else if(e.getKeyCode() == BLOCK_DOWN)
			moveBlock(BLOCK_DOWN);
		
		// Rotate block
		else if(e.getKeyCode() == BLOCK_ROTATE)
			rotateBlock();
		
		// Place block
		else if(e.getKeyCode() == BLOCK_PLACE)
			placeBlock();
	}
	
	@Override
	public void keyReleased(KeyEvent e) {

	}
	@Override
	public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == ONEBLOCK) {
        	controlOutput.setText("Press I for irrigation, V for village, and R for rice.")
        }
        else if (e.getKeyCode() == TWOBLOCK){
        	controlOutput.setText("Choose a direction (up, down, left, right)");
        }
        else if (e.getKeyCode() == THREEBLOCK) {
        	controlOutput.setText("Choose a direction (up, down, left, right)");
        }

        if(e.getKeyCode() == I)
	}
	
	private void moveDeveloper(int direction) {
        int[] a = {0 ,0}
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

    private void placeDeveloper(){
    	
    }

	private void changeDeveloper() {
	    if (developerIndex == developerList.size() - 1)
            developerIndex = 0;
        else
            developerIndex++;
    }

	private void changeBlock() {
		
	}

	private void moveBlock(int direction) {
		if(direction == BLOCK_LEFT) {
			coord[0] += -1;
		}
		else if(direction == BLOCK_RIGHT) {
			coord[0] += 1;
		}
		else if(direction == BLOCK_UP) {
			coord[1] += 1;
		}
		else if(direction == BLOCK_DOWN) {
		  	coord[1] += -1;
		}
		return;
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
	
	private boolean isDeveloperOnBoard() {
		Developer d = developerList.get(developerIndex);
		if(d.getSpace() instanceof Space) {
			return true;
		}
		return false;
	}
}
