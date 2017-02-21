package breakout;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	
	/** Dimensions of ball object */
	private static final int WIDTH = 15, HEIGHT = 15;
	
	/** private instance of Breakout game */
	private Breakout game;
	
	/** x and y are starting coordinates, xMove and yMove are how much the ball will move with each update */
    private int x, y, xMove = 2, yMove = 2;
    
    /** Arraylist of brick objects */
    private ArrayList<Brick> bricks;
    
    /** Brick object to be removed after arraylist iteration */
    private Brick removeBrick;
    
    private Clip clip;
    
    private AudioInputStream bounceStream;

    /**
     * Public constructor for ball object. Places ball in center of screen
     * @param game current game of Breakout being played
     */
	public Ball(Breakout game)
	{
		try {
			bounceStream = AudioSystem.getAudioInputStream(new File("bouncesound.wav"));
			clip = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			clip.open(bounceStream);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		
		this.game = game;
        x = game.getWidth() / 2;
        y = game.getHeight() / 2;
	}
	
	/**
	 * Updates the position of the ball object. Contains logic to continue ball path
	 * and update position based on if the ball hits the sides of the screen or the 
	 * player's paddle.
	 */
	public void update() {
		x += xMove;
        y += yMove;

        if (x < 0 || x > game.getWidth() - WIDTH - 29)
            xMove = -xMove;
        if (y < 0)
        	yMove = -yMove;

        // TODO: add logic to update score. 
        // TODO: add logic to update lives when ball passes below paddle
        
        try {
			checkCollision();
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if ball intersects with player's paddle or bricks. If so, vertical direction of the ball reverses
	 * @throws IOException 
	 * @throws LineUnavailableException 
	 */
	public void checkCollision() throws LineUnavailableException, IOException {
		if (bricks == null)
			bricks = game.getPanel().getBricks();

        if (game.getPanel().getPlayer().getBounds().intersects(getBounds())) {
        	yMove = -yMove;
        	clip.stop();
    		clip.flush();
    		clip.setFramePosition(0);
    		clip.start();
        }
        for (Brick brick : bricks) {
        	if (brick.getBottomBound().intersects(getBounds()) || brick.getTopBound().intersects(getBounds())) { 
        		yMove = -yMove;
        		removeBrick = brick;
        		clip.stop();
        		clip.flush();
        		clip.setFramePosition(0);
        		clip.start();
        	}
        	else if (brick.getLeftBound().intersects(getBounds()) || brick.getRightBound().intersects(getBounds())) {
        		xMove = -xMove;
        		removeBrick = brick;
        		clip.stop();
        		clip.flush();
        		clip.setFramePosition(0);
        		clip.start();
        	}
        }
        
        if(removeBrick != null) {
        	game.getPanel().removeBrick(removeBrick);
        	bricks = game.getPanel().getBricks();
        	removeBrick = null;
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
