package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Brick {
	
	/** Dimensions of brick object */
	private final int HEIGHT, WIDTH;
	
	/** Distance between each brick object */
	private final int BRICK_SEPARATION = 3;
	
	/** Distance between the paddle and the lowest row of bricks */
	private final int PADDLE_BRICK_DIST = 450;
	
	/** Starting coordinates of brick objects */
	private int startX, startY;
	
	/** Coordinates of Brick object */
	private int x, y;
	
	/** The row of the Brick object. */
	private int row;
	
	/** Color of brick object */
	private Color color;
	
	/**
	 * public constructor for Brick object. Sets the
	 * horizontal and vertical position of brick object
	 * @param x horizontal position of brick
	 * @param y vertical position of brick
	 */
	public Brick(Breakout game, int col, int row, Color color) {
		HEIGHT = 22;
		WIDTH = (game.getWidth() - (BreakoutPanel.getNumBricksInRow() - 0) * BRICK_SEPARATION) / BreakoutPanel.getNumBricksInRow() - 2;
		this.color = color;
		startX = game.getWidth() - (WIDTH + 4 * BRICK_SEPARATION);
		startY = game.getHeight() - PADDLE_BRICK_DIST - (HEIGHT / 2);
		x = startX - col * (WIDTH + BRICK_SEPARATION);
		y = startY - row * (HEIGHT + BRICK_SEPARATION);
		this.row = row;
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
     * Getter method for the top bound of brick
     * 
     * @return a line object for the top of the brick
     */
    public Line2D getTopBound() {
    	return new Line2D.Double(x, y, x + WIDTH, y);
    }
    
    /**
     * Getter method for the bottom bound of brick
     * 
     * @return a line object for the bottom of the brick
     */
    public Line2D getBottomBound() {
    	return new Line2D.Double(x, y + HEIGHT, x + WIDTH, y + HEIGHT);
    }
    
    /**
     * Getter method for the left bound of brick
     * 
     * @return a line object for the left side of the brick
     */
    public Line2D getLeftBound() {
    	return new Line2D.Double(x, y, x, y + HEIGHT);
    }
    
    /**
     * Getter method for the right bound of brick
     * 
     * @return a line object for the right side of the brick
     */
    public Line2D getRightBound() {
    	return new Line2D.Double(x + WIDTH, y, x + WIDTH, y + HEIGHT);
    }
    
    public int getRow() {
    	return this.row;
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
