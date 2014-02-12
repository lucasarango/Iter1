import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import javax.swing.*;

public class Controller extends JFrame implements KeyListener { 

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
    
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    
    }
}
