import javax.swing.*;
import java.awt.*;

public class BoardView extends JFrame{
	/**
	 * This class represents the board display. It creates a single 
	 * visible window, split into a grid display.
	 */
	 
	private JLabel[][] spaces;
	 
	public BoardView() {
        try {
            initialize();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
	}
	
	/**
	 * Initialize the contents of the this.
	 */
	private void initialize() throws Exception {
		//First we set up the window. 
        this.setTitle("Java");
		this.setBounds(50, 50, 910, 700);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(14, 14, 4, 4));
		
		//Now we initialize the 144 game spaces
		spaces = new JLabel[14][14];
		for (int i = 0; i < 14; i++)
			for (int x = 0; x < 14; x++) {
				spaces[i][x] = new JLabel("X");
				add(spaces[i][x]);
			}
			
	}
	
}