package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Brick {
	
	/** Dimensions of brick object */
	private final int HEIGHT, WIDTH;
	
	/** Distance between each brick object */
	private final int BRICK_SEPARATION = 5;
	
	/** Distance between the paddle and the lowest row of bricks */
	private final int PADDLE_BRICK_DIST = 450;
	
	/** Starting coordinates of brick objects */
	private int startX, startY;
	
	/** Coordinates of Brick object */
	private int x, y;
	
	/** Color of brick object */
	private Color color;
	
	/**
	 * public constructor for Brick object. Sets the
	 * horizontal and vertical position of brick object
	 * @param x horizontal position of brick
	 * @param y vertical position of brick
	 */
	public Brick(Breakout game, int col, int row, Color color) {
		HEIGHT = 20;
		WIDTH = (game.getWidth() - (BreakoutPanel.getNumBricksInRow() - 0) * BRICK_SEPARATION) / BreakoutPanel.getNumBricksInRow();
		this.color = color;
		startX = game.getWidth() - (WIDTH + 4 * BRICK_SEPARATION);
		startY = game.getHeight() - PADDLE_BRICK_DIST - (HEIGHT / 2);
		x = startX - col * (WIDTH + BRICK_SEPARATION);
		y = startY - row * (HEIGHT + BRICK_SEPARATION);
	}
	
	/**
     * getter method for the bounds of the brick
     * 
     * @return a rectangle object representing the bounds of the brick object
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
    /**
     * Paints the panel rectangle object
     * 
     * @param g the brick object being painted
     */
    public void paint(Graphics g) {
    	g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
