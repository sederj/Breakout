package breakout;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * This class contains logic for the ball object. Includes
 * logic to update the position of the ball object and handles
 * various collisions in the game.
 * 
 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
 * @version 2/12/17
 */
public class Ball {
	
	/** Dimensions of ball object */
	private static final int WIDTH = 15, HEIGHT = 15;
	
	/** private instance of Breakout game */
	private Breakout game;
	
	/** x and y are starting coordinates, xMove and yMove are how much the ball will move with each update */
    private int x, y, xMove = 2, yMove = 2;
    
    /** Arraylist of brick objects */
    private ArrayList<Brick> bricks;

    /**
     * Public constructor for ball object. Places ball in center of screen
     * @param game current game of Breakout being played
     */
	public Ball(Breakout game)
	{
		this.game = game;
        x = game.getWidth() / 2;
        y = game.getHeight() / 2;
        bricks = game.getPanel().getBricks();
	}
	
	/**
	 * Updates the position of the ball object. Contains logic to continue ball path
	 * and update position based on if the ball hits the sides of the screen or the 
	 * player's paddle.
	 */
	public void update() {
		x += xMove;
        y += yMove;
        //commented section currently for scoring for pong. switch to check for brick collision 
        //check if it gets passed the paddle, then game over/decrement life
//        if (x < 0) { 
//            game.getPanel().increaseScore();
//            x = game.getWidth() / 2;
//            xMove = -xMove;
//        }
//        else if (x > game.getWidth() - WIDTH - 7) {
//            game.getPanel().increaseScore();
//            x = game.getWidth() / 2;
//            xMove = -xMove;
//        }
        //if ball hits left or right sides, reverse horizontal direction
        if (x < 0 || x > game.getWidth() - WIDTH - 29)
            xMove = -xMove;
        if (y < 0)
        	yMove = -yMove;
//        if (game.getPanel().getScore() == 10)
//            JOptionPane.showMessageDialog(null, "Player 1 wins", "Pong", JOptionPane.PLAIN_MESSAGE);
//        else if (game.getPanel().getScore() == 10)
//            JOptionPane.showMessageDialog(null, "Player 2 wins", "Pong", JOptionPane.PLAIN_MESSAGE);
        checkCollision();
	}
	
	/**
	 * Checks if ball intersects with player's paddle or bricks. If so, vertical direction of the ball reverses
	 */
	public void checkCollision() {
        if (game.getPanel().getPlayer().getBounds().intersects(getBounds())) 
        	yMove = -yMove;
        
        for (Brick brick : bricks) {
        	if (game.getPanel().getPlayer().getBounds().intersects(brick.getBounds())) {
        		//TODO: Change direction of ball
        		//TODO: make brick object disappear - add remove method in Brick class or something
        	}
        }
    }

	/**
	 * getter method for the bounds of the ball object
	 * @return Rectangle object representing bounds of ball object
	 */
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
    /**
     * creates the visual display for ball object
     * @param g the graphic to create on screen
     */
    public void paint(Graphics g) {
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
