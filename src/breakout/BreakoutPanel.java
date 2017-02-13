package breakout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This class displays the Breakout game by placing the ball
 * and player paddle on the panel. It also contains logic to update
 * the game display and listen for keystrokes for the game.
 * 
 * @author Joey Seder, Jacob McCloughan, Jonah Bukowsky
 * @version 2/12/17
 */
public class BreakoutPanel extends JPanel implements ActionListener, KeyListener{
	
	/** instance of Breakout game */
	private Breakout game;
	
	/** ball object to be placed on panel */
    private Ball ball;
    
    /** player's paddle to be placed on panel */
    private Paddle player;
    
    /** the player's current score */
    private int score;

    /**
     * public constructor for BreakoutPanel. Adds ball and player to 
     * the game panel.
     * @param game the current Breakout game
     */
    public BreakoutPanel(Breakout game) {
        setBackground(Color.WHITE);
        this.game = game;
        ball = new Ball(game);
        player = new Paddle(game, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        Timer timer = new Timer(5, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    /**
     * Getter method for the current player paddle.
     * @return player the current player paddle object
     */
    public Paddle getPlayer() {
        return player;
    }

    /**
     * method to increment score when the ball breaks a brick
     */
    public void increaseScore() {
        //score++;//remove this to debug, so it never reaches 10
    }

    /**
     * Getter method for the player's score
     * @return score players current score
     */
    public int getScore() {
        return score;
    }

    /**
     * method to update the position of the ball and the player's paddle
     */
    private void update() {
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

    /**
     * override method that prints the game score and paints the ball and player paddle
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString(game.getPanel().getScore() + " : " + game.getPanel().getScore(), game.getWidth() / 2, 10);
        ball.paint(g);
        player.paint(g);
    }
}
