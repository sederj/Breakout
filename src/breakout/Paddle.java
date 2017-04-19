package breakout;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class to contain logic for player paddle object.
 * Contains logic to set the size and movement of paddle
 *
 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
 * @version 2/22/17
 */
public class Paddle {

	/** Dimensions of player paddle. */
	private static final int WIDTH = 100, HEIGHT = 10;
	
	/** Image for padde. */
	private static Image paddleImage;

	/** Breakout game object. */
    private BreakoutPanel game;

    /** ints for left and right keystrokes. */
    private int left, right;

    /** position of paddle. */
    private int x, y;

    /** Speed of horizontal paddle movement. */
    private int xMove;

    /**
     * Public constructor for paddle object. Sets the starting position
     * of the paddle and the ints for the left and right keystrokes
     *
     * @param mGame current Breakout game object
     * @param mLeft Left keystroke int
     * @param mRight Right keystroke int
     */
    public Paddle(final BreakoutPanel mGame, final int mLeft,
    		final int mRight) {
        this.game = mGame;
        x = game.getBreakoutWidth() / 2;
        y = game.getBreakoutHeight() - 80;
        this.left = mLeft;
        this.right = mRight;
    }
    
    /** Load image for paddle. */
    public static void loadImages() {
    	try {
			paddleImage = ImageIO.read(new File("paddle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
     * updates the position of the player's paddle. Contains logic to
     * prevent it from moving off the screen to the left or right
     */
    public void update() {
        x += xMove;
        if (x <= (0))  {
        	//less than or equal to, because when speed
            //is above 3 it will skip past this #
            x = 0;
        } else if (x >= game.getBreakoutWidth() - WIDTH) {
            x = game.getBreakoutWidth() - WIDTH;
        }
    }

    /**
     * handles the logic for keystrokes. Updates the horizontal
     * movement of the paddle depending on the key pressed
     *
     * @param keyCode the code for the key pressed, left or right
     */
    public void pressed(final int keyCode) {
        if (keyCode == left) {
            xMove = -3; //speed of paddle movement
        } else if (keyCode == right) {
            xMove = 3; //speed of paddle movement
        }
    }

    /**
     * handles the logic for releasing a key. Stops
     * movement when a key is released
     *
     * @param keyCode the code of the key being released
     */
    public void released(final int keyCode) {
        if (keyCode == left || keyCode == right) {
            xMove = 0;
        }
    }

    /**
     * getter method for the bounds of the player paddle.
     *
     * @return a rectangle object representing the bounds of the paddle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    /**
     * Paints the panel rectangle object.
     *
     * @param g the paddle object being painted
     */
    public void paint(final Graphics g) {
        //g.fillRect(x, y, WIDTH, HEIGHT);
        g.drawImage(paddleImage, x, y, null);
    }
}
