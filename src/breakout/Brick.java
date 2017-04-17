package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;

/**
 * Class contianing logic for each brick.
 * Includes logic to set the size of brick
 * and provide information on brick objects
 *
 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
 * @version 2/22/17
 */
public class Brick {
	/** Height dimension of brick object. */
	private final int HEIGHT;

	/** Width dimension of brick object. */
	private final int WIDTH;

	/** Distance between each brick object. */
	private static final int BRICK_SEPARATION = 3;

	/** Distance between the paddle and the lowest row of bricks. */
	private static final int PADDLE_BRICK_DIST = 450;

	/** Starting coordinates of brick objects. */
	private int startX, startY;

	/** Coordinates of Brick object. */
	private int x, y;

	/** The row of the Brick object. */
	private int row;

	/** Color of brick object. */
	private Color color;

	/**
	 * public constructor for Brick object. Sets the
	 * horizontal and vertical position of brick object
	 * @param game the game instance being used
	 * @param cl the column of the brick
	 * @param r the row of the brick
	 * @param c the color of the brick
	 */
	public Brick(final BreakoutPanel game, final int cl,
			final int r, final Color c) {
		HEIGHT = 22;
		WIDTH = (game.getBreakoutWidth()
				- (BreakoutPanel.getNumBricksInRow())
				* BRICK_SEPARATION)
				/ BreakoutPanel.getNumBricksInRow() - 2;
		this.color = c;
		startX = game.getBreakoutWidth() - 
				(WIDTH + 6 * BRICK_SEPARATION); 
		startY = game.getBreakoutHeight() - 
				PADDLE_BRICK_DIST - (HEIGHT / 2);
		x = startX - cl * (WIDTH + BRICK_SEPARATION);
		y = startY - r * (HEIGHT + BRICK_SEPARATION);
		this.row = r;
	}

    /**
     * Getter method for the top bound of brick.
     *
     * @return a line object for the top of the brick
     */
    public Line2D getTopBound() {
    	return new Line2D.Double(x, y, x + WIDTH, y);
    }

    /**
     * Getter method for the bottom bound of brick.
     *
     * @return a line object for the bottom of the brick
     */
    public Line2D getBottomBound() {
    	return new Line2D.Double(x, y + HEIGHT, x + WIDTH, y + HEIGHT);
    }

    /**
     * Getter method for the left bound of brick.
     *
     * @return a line object for the left side of the brick
     */
    public Line2D getLeftBound() {
    	return new Line2D.Double(x, y, x, y + HEIGHT);
    }

    /**
     * Getter method for the right bound of brick.
     *
     * @return a line object for the right side of the brick
     */
    public Line2D getRightBound() {
    	return new Line2D.Double(x + WIDTH, y, x + WIDTH, y + HEIGHT);
    }

    /**
     * Returns the row of the brick.
     * @return row the row of the brick
     */
    public int getRow() {
    	return this.row;
    }

    /**
     * Paints the panel rectangle object.
     *
     * @param g the brick object being painted
     */
    public void paint(final Graphics g) {
    	g.setColor(color);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
