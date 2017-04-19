package breakout;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class contains logic for the ball object. Includes
 * logic to update the position of the ball object and handles
 * various collisions in the game.
 *
 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
 * @version 2/12/17
 */
public class Ball {

	/** Dimensions of ball object. */
	private static final int WIDTH = 15, HEIGHT = 15;

	/** private instance of Breakout game. */
	private BreakoutPanel game;
	
	/** private random variable to determine initial ball direction */
	private static Random random = new Random();

	/** x and y are starting coordinates,
	 * xMove and yMove are how much the ball will move with each update. */
    private double x, y, xMove, yMove;
    
    /** private boolean to determine if ball travels through bricks. */
    private boolean fullMetalJacket;

    /** Arraylist of brick objects. */
    private ArrayList<Brick> bricks;

    /** Brick object to be removed after arraylist iteration. */
    private Brick removeBrick;

    /** object for the sound clip. */
    private Clip clip;

    /** stream for the audio clip. */
    private static AudioInputStream bounceStream;

    /**
     * Public constructor for ball object. Places ball in center of screen
     * @param mGame current game of Breakout being played
     */
	public Ball(final BreakoutPanel mGame) {
		fullMetalJacket = false;
		double speed = 2;
		xMove = 0.5 + (1.5 - 0.5) * random.nextDouble();
		yMove = Math.sqrt(Math.pow(speed, 2) - Math.pow(xMove, 2));
		xMove*= (random.nextInt(2) == 0) ? 1 : -1;
		try {
			bounceStream = AudioSystem.getAudioInputStream(
					new File("bouncesound.wav"));
			clip = (Clip) AudioSystem.getLine(
					new Line.Info(Clip.class));
			clip.open(bounceStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

		this.game = mGame;
        x = game.getBreakoutWidth() / 2;
        y = game.getBreakoutHeight() / 2;
	}

	/**
	 * Updates the position of the ball object.
	 * Contains logic to continue ball path
	 * and update position based on if the ball
	 * hits the sides of the screen or the player's paddle.
	 */
	public void update() {
		x += xMove;
        y += yMove;

        if (x < 0 || x > game.getBreakoutWidth() - WIDTH) {
            xMove = -xMove;
        }
        if (y < 0) {
        	yMove = -yMove;
        }

        try {
			checkCollision();
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if ball intersects with player's paddle or bricks.
	 * If so, vertical direction of the ball reverses
	 * @throws IOException an exception relating to failed write
	 * @throws LineUnavailableException an exception relating to a null line
	 */
	public void checkCollision()
			throws LineUnavailableException, IOException {
		if (bricks == null) {
			bricks = game.getBricks();
		}

        if (game.getPlayer().getBounds().intersects(getBounds())) {
        	yMove = -yMove;
        	clip.stop();
    		clip.flush();
    		clip.setFramePosition(0);
    		clip.start();
        }
        for (Brick brick : bricks) {
        	if (brick.getBottomBound().intersects(getBounds())
        			||
        			brick.getTopBound().intersects(getBounds())) {
        		if (!fullMetalJacket) {
        			yMove = -yMove;
        		}
        		removeBrick = brick;
        		clip.stop();
        		clip.flush();
        		clip.setFramePosition(0);
        		clip.start();
        	} else if (brick.getLeftBound().intersects(getBounds())
        			||
        			brick.getRightBound().intersects(getBounds())) {
        		if (!fullMetalJacket) {
        			xMove = -xMove;
        		}	
        		removeBrick = brick;
        		clip.stop();
        		clip.flush();
        		clip.setFramePosition(0);
        		clip.start();
        	}
        }

        if (removeBrick != null) {
        	game.removeBrick(removeBrick);
        	bricks = game.getBricks();
        	if (removeBrick.getSpecial() > 0) {
        		for (int i = 0; i < removeBrick.getSpecial(); i++) {
        			game.createBall(x, y);
        		}
        	}else if (removeBrick.getSpecial() < 0) {
        		fullMetalJacket = true;
        	}
        	removeBrick = null;
        }
    }
	
	/** 
	 * set the location of the ball
	 * @param x the x location of the ball
	 * @param y the y location of the ball
	 */
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * getter method for the bounds of the ball object.
	 * @return Rectangle object representing bounds of ball object
	 */
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, WIDTH, HEIGHT);
    }

    /**
     * creates the visual display for ball object.
     * @param g the graphic to create on screen
     */
    public void paint(final Graphics g) {
        g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
    }
}




