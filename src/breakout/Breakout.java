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
	
	/** default serial version UID */
	private static final long serialVersionUID = 1L;

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
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        panel = new BreakoutPanel(this);
        add(panel);
        setVisible(true);
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
