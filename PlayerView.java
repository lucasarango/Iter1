import javax.swing.*;
import java.awt.*;

public class PlayerView extends JFrame{
	/**
	 * This class represents the player info display. It creates a single 
	 * visible window, split into a grid, 1 x 4, wherein each grid component
	 * will display the info for a different player. 
	 */
	 
	private JTextArea player1;
	private JTextArea player2;
	private JTextArea player3;
	private JTextArea player4;
	 
	public PlayerView() {
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
        this.setTitle("Player Info: ");
		this.setBounds(50, 50, 400, 250);
        this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new GridLayout(1, 4, 1 , 1));
		
		String inventoryBoilerplate = "Devs: \n1Blks: \n2Blks: \nAction " +
									  "Tokens: \nScore: ";
		player1 = new JTextArea("P 1:\n" + inventoryBoilerplate);
		player1.setEditable(false);
		add(player1);
		player2 = new JTextArea("P 2:\n" + inventoryBoilerplate);
		player2.setEditable(false);
		add(player2);
		player3 = new JTextArea("P 3:\n" + inventoryBoilerplate);
		player3.setEditable(false);
		add(player3);
		player4 = new JTextArea("P 4:\n" + inventoryBoilerplate);
		player4.setEditable(false);
		add(player4);
	}
}
	