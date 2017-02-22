package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class displays the Breakout game by placing the ball
 * and player paddle on the panel. It also contains logic to update
 * the game display and listen for keystrokes for the game.
 * 
 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
 * @version 2/22/17
 */
public class BreakoutPanel extends JPanel implements ActionListener, KeyListener{
	
	/** default serial version UID */
	private static final long serialVersionUID = 1L;

	/** instance of Breakout game */
	private Breakout game;
	
	/** ball object to be placed on panel */
    private Ball ball;
    
    /** player's paddle to be placed on panel */
    private Paddle player;
    
    /** Arraylist of brick objects */
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    
    /** the player's current score */
    private int score;
    
    /** number of rows of bricks */
    private static final int NUM_BRICK_ROWS = 8;
    
    /** number of bricks in each row */
    private static final int NUM_BRICKS_IN_ROW = 10;
    
    private long startTime;
    
    private Image explosion;
    
    private Image breakoutMenu;
    
    private long explosionTimer;
    
    private Clip explosionSound;
    
    private boolean menu;

	/**
     * public constructor for BreakoutPanel. Adds ball and player to 
     * the game panel.
     * @param game the current Breakout game
     */
    public BreakoutPanel(Breakout game) {
        setBackground(Color.WHITE);
        this.game = game;
        createBricks();
        ball = new Ball(game);
        player = new Paddle(game, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        
        explosion = new ImageIcon("explosion.gif").getImage();
        try {
			breakoutMenu = ImageIO.read(new File("breakoutmenu.png"));
			explosionSound = (Clip) AudioSystem.getLine(new Line.Info(Clip.class));
			explosionSound.open(AudioSystem.getAudioInputStream(new File("explosionsound.wav")));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
        
        menu = true;
        Timer timer = new Timer(5, this);
        timer.start();
        this.startTime = System.currentTimeMillis();
        addKeyListener(this);
        setFocusable(true);
    }
    
    public void createBricks() {
    	for (int row = 0; row < NUM_BRICK_ROWS; row++) {
    		Color rowColor = checkColor(row);
    		for (int col = 0; col < NUM_BRICKS_IN_ROW; col++) {
    			bricks.add(new Brick(game, col, row, rowColor));
    		}
    	}
    }
    
    private Color checkColor(int row) {
        switch (row) {
            case 0:
            case 1: return Color.GREEN;
            case 2:
            case 3: return Color.YELLOW;
            case 4:
            case 5: return Color.ORANGE;
            case 6:
            case 7: return Color.RED;
            case 8:
            case 9: return Color.CYAN;
        }
        return Color.CYAN;
    }

    /**
     * Getter method for the current player paddle.
     * @return player the current player paddle object
     */
    public Paddle getPlayer() {
        return player;
    }
    
    /**
     * Getter method for the arraylist of bricks
     * @return bricks an arraylist of all brick objects
     */
    public ArrayList<Brick> getBricks() {
    	return bricks;
    }
    
    public void removeBrick(Brick brick) {
    	increaseScore(brick.getRow());
    	bricks.remove(brick);
    }

    /**
     * method to increment score when the ball breaks a brick
     */
    public void increaseScore(int row) {
    	if (checkColor(row) == Color.RED) {
    		this.score = this.score + 7;
    	}
    	if (checkColor(row) == Color.ORANGE) {
    		this.score = this.score + 5;
    	}
    	if (checkColor(row) == Color.YELLOW) {
    		this.score = this.score + 3;
    	}
    	if (checkColor(row) == Color.GREEN) {
    		this.score = this.score + 1;
    	}
    }

    /**
     * Getter method for the player's score
     * @return score players current score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * method to update the position of the ball and the player's paddle
     */
    private void update() {
    	if (menu || System.currentTimeMillis() - explosionTimer <= 1000) {
        	return;
        }
        ball.update();
        player.update();
    }

    /**
     * implemented method from ActionListener
     */
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    /**
     * listener for keyboard strokes. Used when left or right arrow key are pressed
     */
    public void keyPressed(KeyEvent e) {
    	if (menu) {
    		menu = false;
    		explosionTimer = System.currentTimeMillis();
    		return;
    	}
        player.pressed(e.getKeyCode());
    }

    /**
     * listener for keyboard release. Used when left or right arrow key are released
     */
    public void keyReleased(KeyEvent e) {
        player.released(e.getKeyCode());
    }

    /**
     * implemented listener from KeyListener
     */
    public void keyTyped(KeyEvent e) {
        ;
    }
    
    public static int getNumBrickRows() {
		return NUM_BRICK_ROWS;
	}

	public static int getNumBricksInRow() {
		return NUM_BRICKS_IN_ROW;
	}

    /**
     * override method that prints the game score and paints the ball and player paddle
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Tahoma", Font.BOLD, 14));
        if (menu) {
        	drawMenu(g);
        }else if (System.currentTimeMillis() - explosionTimer <= 1000) {
        	drawExplosion(g);
        	this.startTime = System.currentTimeMillis();
        }else {
        	 g.drawString("Score: " + game.getPanel().getScore(), 
        			 game.getWidth() / 4, 10);
        	 g.drawString("Time: " + (System.currentTimeMillis() - this.startTime) / 1000, 
        			 game.getWidth() - game.getWidth() / 4, 10);
             ball.paint(g);
             player.paint(g);
             for (Brick brick : bricks) {
             	brick.paint(g);
             }
        }
    }
    
    public void drawMenu(Graphics g) {
    	g.drawImage(breakoutMenu, 0, 0, this);
    }
    
    public void drawExplosion(Graphics g) {
    	g.drawImage(explosion, 0, 0, this);
    	explosionSound.start();
    }
}
