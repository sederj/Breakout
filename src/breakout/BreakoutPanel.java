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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class displays the Breakout game by placing the ball and player paddle
 * on the panel. It also contains logic to update the game display and listen
 * for keystrokes for the game.
 *
 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
 * @version 2/22/17
 */
public final class BreakoutPanel extends JPanel implements ActionListener,
KeyListener {

	/** Width dimension of Java panel. */
	private static final int WIDTH = 1000;

	/** Height dimension of Java panel. */
	private static final int HEIGHT = 750;

	/** default serial version UID. */
	private static final long serialVersionUID = 1L;

	/** ball object to be placed on panel. */
	private transient Ball ball;

	/** player's paddle to be placed on panel. */
	private transient Paddle player;

	/** Arraylist of brick objects. */
	private transient ArrayList<Brick> bricks = new ArrayList<Brick>();

	/** the player's current score. */
	private int score;

	/** number of rows of bricks. */
	private static final int NUM_BRICK_ROWS = 8;

	/** number of bricks in each row. */
	private static final int NUM_BRICKS_IN_ROW = 10;

	/** starting time for timer. */
	private long startTime;

	/** image for explosion graphic. */
	private transient Image explosion;

	/** image for breakout menu. */
	private transient Image breakoutMenu;

	/** time length for the explosion graphic. */
	private long explosionTimer;

	/** sound clip for explosion sound. */
	private transient Clip explosionSound;

	/** boolean for menu option. */
	private boolean menu;

	/** JMenu item. */
	private JMenu fileMenu;

	/** JMenu bar. */
	private JMenuBar menuBar;

	/** New Game Item. */
	private JMenuItem newGame;

	/** Quit Game Item. */
	private JMenuItem quitGame;

	/** Button listener object. */
	private ButtonListener buttonListener = new ButtonListener();

	/**
	 * public constructor for BreakoutPanel. Adds ball and player
	 * to the game panel.
	 *
	 */
	public BreakoutPanel() {
		setBackground(Color.WHITE);
		createBricks();
		ball = new Ball(this);
		player = new Paddle(this, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

		setOpenVid();

		menu = true;
		Timer timer = new Timer(5, this);
		timer.start();
		this.startTime = System.currentTimeMillis();
		addKeyListener(this);
		setFocusable(true);

		//create menu bar
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar = new JMenuBar();

		newGame = new JMenuItem("New");
		newGame.addActionListener(buttonListener);
		fileMenu.add(newGame); 

		quitGame = new JMenuItem("Quit");
		fileMenu.add(quitGame);
		quitGame.addActionListener(buttonListener);
		menuBar.add(fileMenu);

		add(menuBar, fileMenu);
	}

	/**
	 * Getter method for the JMenuBar. Used to set the menu
	 * from Breakout class
	 * 
	 * @return menuBar 
	 */
	public JMenuBar getMenu() {
		return menuBar;
	}

	/**
	 * Method which initializes and sets the opening
	 * graphic of the exploding Breakout name.
	 */
	public void setOpenVid() {
		explosion = new ImageIcon("explosion.gif").getImage();
		try {
			breakoutMenu
			= ImageIO.read(new File("breakoutmenu.png"));

			explosionSound
			= (Clip) AudioSystem.getLine(new Line.Info(Clip.class));

			explosionSound.open(AudioSystem.getAudioInputStream(
					new File("explosionsound.wav")));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * method to generate the bricks for breakout game. adds bricks to an
	 * arraylist of brick objects
	 */
	public void createBricks() {
		for (int row = 0; row < NUM_BRICK_ROWS; row++) {
			Color rowColor = checkColor(row);
			for (int col = 0; col < NUM_BRICKS_IN_ROW; col++) {
				bricks.add(new Brick(this, col, row, rowColor));
			}
		}
	}

	/**
	 * method to set the correct color to each row of bricks.
	 *
	 * @param row the row to set the color of
	 * @return Color object
	 */
	private Color checkColor(final int row) {
		switch (row) {
		case 0:
		case 1:
			return Color.GREEN;
		case 2:
		case 3:
			return Color.YELLOW;
		case 4:
		case 5:
			return Color.ORANGE;
		case 6:
		default:
			return Color.RED;
		}
	}

	/**
	 * Getter method for the current player paddle.
	 *
	 * @return player the current player paddle object
	 */
	public Paddle getPlayer() {
		return player;
	}

	/**
	 * Getter method for the arraylist of bricks.
	 *
	 * @return bricks an arraylist of all brick objects
	 */
	public ArrayList<Brick> getBricks() {
		return bricks;
	}

	/**
	 * method to remove specified brick from bricks ArrayList.
	 *
	 * @param brick brick object to be removed
	 */
	public void removeBrick(final Brick brick) {
		increaseScore(brick.getRow());
		bricks.remove(brick);
	}

	/**
	 * method to increment score when the ball breaks a brick.
	 *
	 * @param row decides how much to increment score by
	 */
	public void increaseScore(final int row) {
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
	 * Getter method for the player's score.
	 *
	 * @return score players current score
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * method to update the position of the ball and the player's paddle.
	 */
	private void update() {
		if (menu || System.currentTimeMillis()
				- explosionTimer <= 1000) {
			return;
		}
		if (this.checkLoss()) {
			JOptionPane.showMessageDialog(null, "You lose!"
					+ "\nScore: " + this.score
					+ "\nTime: "
					+ (System.currentTimeMillis()
							- startTime) / 1000,
					"Failure", JOptionPane.WARNING_MESSAGE);

			int reply = JOptionPane.showConfirmDialog(null, 
					"Would you like to play again?", 
					"Game Over", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.NO_OPTION) {
				System.exit(0);
			} else if (reply == JOptionPane.YES_OPTION) {
				getPanel().removeAll();
				
				explosion.flush();
				menu = true;

				createBricks();
				ball = new Ball(getPanel());
				player = new Paddle(getPanel(), 
						KeyEvent.VK_LEFT, 
						KeyEvent.VK_RIGHT);
			} else if (reply == JOptionPane.CANCEL_OPTION) {
				System.exit(0);
			}
		}
		if (this.checkWin()) {
			JOptionPane.showMessageDialog(null,
					"You win!\nScore: " + this.score
					+ "\nTime: "
					+ (System.currentTimeMillis()
						- startTime) / 1000, "Victory",
					JOptionPane.INFORMATION_MESSAGE);

			int reply = JOptionPane.showConfirmDialog(null, 
					"Would you like to play again?", 
					"Game Over", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.NO_OPTION) {
				System.exit(0);
			} else if (reply == JOptionPane.YES_OPTION) {
				getPanel().removeAll();
				
				explosion.flush();
				menu = true;
				
				createBricks();
				ball = new Ball(getPanel());
				player = new Paddle(getPanel(), 
						KeyEvent.VK_LEFT, 
						KeyEvent.VK_RIGHT);
			} else if (reply == JOptionPane.CANCEL_OPTION) {
				System.exit(0);
			}
		}
		ball.update();
		player.update();
	}

	/**
	 * implemented method from ActionListener.
	 *
	 * @param e the action performed
	 */
	public void actionPerformed(final ActionEvent e) {
		update();
		repaint();
	}

	/**
	 * listener for keyboard strokes. Used when left or right arrow key are
	 * pressed
	 *
	 * @param e the action performed
	 */
	public void keyPressed(final KeyEvent e) {
		if (menu) {
			menu = false;
			explosionTimer = System.currentTimeMillis();
			return;
		}
		player.pressed(e.getKeyCode());
	}

	/**
	 * listener for keyboard release. Used when left or right arrow key are
	 * released
	 *
	 * @param e the key action performed
	 */
	public void keyReleased(final KeyEvent e) {
		player.released(e.getKeyCode());
	}

	/**
	 * implemented listener from KeyListener.
	 *
	 * @param e the key action performed
	 */
	public void keyTyped(final KeyEvent e) {
		return;
	}

	/**
	 * getter method for number of bricks in a row.
	 *
	 * @return the number of bricks in a row
	 */
	public static int getNumBricksInRow() {
		return NUM_BRICKS_IN_ROW;
	}

	/**
	 * override method that prints the game score and paints the ball
	 * and player paddle.
	 *
	 * @param g the graphic object to paint
	 */
	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Tahoma", Font.BOLD, 14));
		if (menu) {
			drawMenu(g);
		} else if (System.currentTimeMillis()
				- explosionTimer <= 1000) {
			drawExplosion(g);
			this.startTime = System.currentTimeMillis();
		} else {
			g.drawString("Score: " + this.getScore(),
					getBreakoutWidth() / 4, 10);
			g.drawString("Time: " + (System.currentTimeMillis()
					- this.startTime) / 1000,
					getBreakoutWidth() - getBreakoutWidth()
					/ 4, 10);
			ball.paint(g);
			player.paint(g);
			for (Brick brick : bricks) {
				brick.paint(g);
			}
		}
	}

	/**
	 * method to draw the breakout menu.
	 *
	 * @param g the graphic object to draw it on
	 */
	public void drawMenu(final Graphics g) {
		g.drawImage(breakoutMenu, 0, 0, this);
	}

	/**
	 * method to draw explosion graphic.
	 *
	 * @param g the graphic to draw it on
	 */
	public void drawExplosion(final Graphics g) {
		g.drawImage(explosion, 0, 0, this);
		explosionSound.start();
	}

	/**
	 * Checks if the game is over due to win.
	 *
	 * @return boolean based on if the game is won.
	 */
	private boolean checkWin() {
		if (bricks.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Checks if the game is over due to loss.
	 *
	 * @return boolean based on if the player has lost.
	 */
	private boolean checkLoss() {
		if (getBreakoutHeight() <= this.ball.getBounds().getY()) {
			return true;
		}
		return false;
	}

	/**
	 * Getter method for this BreakoutPanel object.
	 * @return this panel object
	 */
	private BreakoutPanel getPanel() {
		return this;
	}

	/**
	 * Getter method for the set width of panel.
	 * @return WIDTH final width of panel
	 */
	public int getBreakoutWidth() {
		return WIDTH;
	}

	/**
	 * Getter method for the set height of panel.
	 * @return HEIGHT final height of panel
	 */
	public int getBreakoutHeight() {
		return HEIGHT;
	}

	/**
	 * Inner class that implements the Actionlistener for
	 * the menu buttons.
	 * 
	 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
     * @version 2/22/17
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * Default action performed method to implement
		 * logic for newgame and quitgame buttons.
		 * 
		 * @param e ActionEvent object
		 */
		public void actionPerformed(final ActionEvent e) {

			//to start a new game
			if (e.getSource() == newGame){
				getPanel().removeAll();
				
				explosion.flush();
				menu = true;
				
				createBricks();
				ball = new Ball(getPanel());
				player = new Paddle(getPanel(), 
						KeyEvent.VK_LEFT, 
						KeyEvent.VK_RIGHT);

			}
			//to quit the game
			if (e.getSource() == quitGame){
				System.exit(0);
			}
		}
	}
}


