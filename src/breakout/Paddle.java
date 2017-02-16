package breakout;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle {
	
	/** Dimensions of player paddle */
	private static final int WIDTH = 60, HEIGHT = 10;
	
	/** Breakout game object */
    private Breakout game;
    
    /** ints for left and right keystrokes */
    private int left, right;
    
    /** position of paddle */
    private int x, y;
    
    /** Speed of horizontal paddle movement */
    private int xMove;

    /**
     * Public constructor for paddle object. Sets the starting position
     * of the paddle and the ints for the left and right keystrokes
     * 
     * @param game current Breakout game object
     * @param left Left keystroke int
     * @param right Right keystroke int
     */
    public Paddle(Breakout game, int left, int right) {
        this.game = game;
        x = game.getWidth() / 2;
        y = game.getHeight() - 80;
        this.left = left;
        this.right = right;
    }

    /**
     * updates the position of the player's paddle. Contains logic to
     * prevent it from moving off the screen to the left or right
     */
    public void update() {
        if (x > (10) && x < game.getWidth() - WIDTH - 29)
            x += xMove;
        else if (x <= (10)) //less than or equal to, because when speed is above 3 it will skip past this #
            x+= 5;
        else if (x >= game.getWidth() - WIDTH - 29)
            x-= 3;
    }

    /**
     * handles the logic for keystrokes. Updates the horizontal
     * movement of the paddle depending on the key pressed
     * 
     * @param keyCode the code for the key pressed, left or right
     */
    public void pressed(int keyCode) {
        if (keyCode == left)
            xMove = -3; //speed of paddle movement
        else if (keyCode == right)
            xMove = 3; //speed of paddle movement
    }

    /**
     * handles the logic for releasing a key. Stops
     * movement when a key is released
     * 
     * @param keyCode the code of the key being released
     */
    public void released(int keyCode) {
        if (keyCode == left || keyCode == right)
            xMove = 0;
    }

    /**
     * getter method for the bounds of the player paddle
     * 
     * @return a rectangle object representing the bounds of the paddle
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    /**
     * Paints the panel rectangle object
     * 
     * @param g the paddle object being painted
     */
    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
