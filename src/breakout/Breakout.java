package breakout;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * This is the runner method for the breakout game.
 * It creates a panel object and starts the Breakout game.
 *
 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
 * @version 2/22/17
 */
public class Breakout extends JFrame {

	/** default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** Width dimension of Java panel. */
    private static final int WIDTH = 1000;

    /** Height dimension of Java panel. */
    private static final int HEIGHT = 750;

    /** panel which will hold current Breakout game. */
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
     * Getter method for game panel.
     * @return panel current Breakout game panel
     */
    public BreakoutPanel getPanel() {
         return panel;
    }

    /**
     * Main method which runs Breakout game.
     * @param args arguments for running game
     */
    public static void main(final String[] args) {
        new Breakout();
    }
}

