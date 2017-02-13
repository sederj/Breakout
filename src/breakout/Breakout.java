package breakout;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * This is the runner method for the breakout game.
 * It creates a panel object and starts the Breakout game.
 * 
 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
 * @version 2/12/17
 */
public class Breakout extends JFrame {
	
	/** Dimensions of java panel */
    private final static int WIDTH = 1000, HEIGHT = 750;
    
    /** panel which will hold current Breakout game */
    private BreakoutPanel panel;

    /**
     * public constructor for Breakout game. Creates panel which will
     * hold the Breakout game
     */
    public Breakout() {
        setSize(WIDTH, HEIGHT);
        setTitle("Breakout");
        setBackground(Color.WHITE);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new BreakoutPanel(this);
        add(panel);
    }

    /**
     * Getter method for game panel
     * @return panel current Breakout game panel
     */
    public BreakoutPanel getPanel() {
        return panel;
    }

    /**
     * Main method which runs Breakout game
     * @param args
     */
    public static void main(String[] args) {
        new Breakout();
    }
}
