package breakout;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * This is the runner method for the breakout game.
 * It creates a panel object and starts the Breakout game.
 *
 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
 * @version 4/19/17
 */
public class Breakout extends JFrame {

	/** default serial version UID. */
	private static final long serialVersionUID = 1L;

    /** panel which will hold current Breakout game. */
    private BreakoutPanel panel;
    
    /** JFrame object to hold game frame. */
    private JFrame frame;

    /**
     * public constructor for Breakout game. Creates panel which will
     * hold the Breakout game
     */
    public Breakout() {
    	frame = new JFrame("Breakout");
        frame.setBackground(Color.WHITE);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new BreakoutPanel();
        frame.getContentPane().add(panel);
        frame.setJMenuBar(panel.getMenu());

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    /**
     * Main method which runs Breakout game.
     * @param args arguments for running game
     */
    public static void main(final String[] args) {
        new Breakout();
    }
}

