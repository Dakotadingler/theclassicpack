import java.awt.*;
import java.awt.event.*;
//import java.util.*;
//import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

//import javax.swing.JPanel;
import javax.swing.*;


public class Pong extends JPanel implements ActionListener {
	
	private Timer timer; //used for movement (somehow)
	private final int DELAY = 10;
	
	private Player mc; //Main character object
	private Ball ball; //Ball object
	private Computer com; //Computer player
	
	private int collisionX; //the range that a collision happens
	private int collisionY;
	
	public int windowWidth;
	public int windowHeight;
	
	//Used for scoring
	private int comPoints;
	private int playPoints;
	
	//Used to start/stop the game
	private boolean gameStart = true;
	private int desiredScore;
	
	private double playerSpeed=10;
	private double computerSpeed=10;
	private double ballSpeed=10;
	
	
	
	public Pong() {
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) { //if an action is performed
		
		//System.out.println(mc.changeY + "\t" + com.changeY + "\t" + ball.change);
		
		step();
		
	}
	
	public void initSetup() {
		
		addKeyListener( new TAdapter() ); //used to notice buttons
		setBackground( Color.black ); //sets the background of the window to black
		setFocusable(true);
		
		//Player.setHeight(windowHeight);
		
		//creates objects inside the game
		mc = new Player(playerSpeed);
		ball = new Ball(ballSpeed);
		com = new Computer(computerSpeed);
		
		mc.changeY = playerSpeed;
		com.changeY = computerSpeed;
		ball.change = ballSpeed;
		
		//mc.setHeight(windowHeight);
		
		//used for detecting collisions
		collisionX = mc.getWidth();
		collisionY = mc.getHeight();
		
//		windowSET(); //This gives the window WIDTH and HEIGHT to game objects to use in AI calculations
		
		
		//Used for moving (moves every timer)
		timer = new Timer(DELAY, this);
		timer.start();
		
		
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		doDrawing(g);
		
		Toolkit.getDefaultToolkit().sync();
		
	}
	
	private void doDrawing(Graphics g) { //used to update the screen
		
		mc.changeY = playerSpeed;
		com.changeY = computerSpeed;
		ball.change = ballSpeed;
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage( mc.getImage(), (int) mc.getX(), (int) mc.getY(), this);
		g2d.drawImage( ball.getImage(), (int) ball.getX(), (int) ball.getY(), this);
		g2d.drawImage( com.getImage(), (int) com.getX(), (int) com.getY(), this);
		
		if (!gameStart) {
			startScreen(g2d);
		}

		
		windowSIZE();
		
		if (gameStart) {
			ball.move(); //moves the ball
			computerAI();
		}
		
		//updates the screen with new ball's location
		repaint( ( (int) ball.getX() )-100, (int) ball.getY()-100, (int) ball.getWidth()+1000, (int) ball.getHeight()+1000 );
		repaint( (int) com.getX()-100, (int) com.getY()-100, (int) com.getWidth()+1000, (int) com.getHeight()+1000 );
		
		//System.out.println("Ball:\t" + ball.getX() + "\t" + ball.getY() ); //prints the ball's X and Y on terminal
		
		//if a collision happens between the player paddle and the ball, detect it
		if ( (ball.getX() >= mc.getX() - collisionX) && (ball.getX() <= mc.getX() + collisionX) && (ball.getY() >= mc.getY() - collisionY + 5) && (ball.getY() <= mc.getY() + collisionY - 5) ) {
			ball.collision();
		}
		
		if ( (ball.getX() >= com.getX() - collisionX) && (ball.getX() <= com.getX() + collisionX) && (ball.getY() >= com.getY() - collisionY + 5) && (ball.getY() <= com.getY() + collisionY - 5) ) {
			ball.collision();
		}
		
		pointCheck();
		
		
	}
	
	private void step() { //if a key is pressed, do this
		
		if (gameStart) {
			mc.move(); //has the player move
		}
		
		repaint( (int) mc.getX()-100, (int) mc.getY()-100, (int) mc.getWidth()+1000, (int) mc.getHeight()+1000 );
		
		
		
		
	}
	
	private class TAdapter extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent e) {
			mc.keyPressed(e);
		}
		
		public void keyReleased(KeyEvent e) {
			mc.keyReleased(e);
		}
		
	}
	
	private void computerAI() {
		
		//computer AI
		
		// ( ( com.getY() < ball.getY() ) && (ball.getdY < 0) )
		if ( ( com.getY() < ball.getY() )   ) {
			com.posdY();
		}
		if ( ( com.getY() > ball.getY() )   ) {
			com.negdY();
		}
		
		
		com.move();
		
		//System.out.println("Computer Y:\t" + com.getY() + "\tdy:\t" + com.dy);
		
	}
	
	private void pointCheck() { //checks if the ball has made it to the 
		
		if (ball.getX() <= 10) {
			comPoints++;
			ball = new Ball(ballSpeed);
			System.out.println("Com Points++");
		} else if (ball.getX() >= windowWidth)  {
			playPoints++;
			ball = new Ball(ballSpeed);
			System.out.println("Player Points++");
		}

		
	}
	
	private void windowSIZE() {
		mc.screenHeight = windowHeight;
		ball.screenHeight = windowHeight;
		ball.screenWidth = windowWidth;
		com.screenHeight = windowHeight;
	}
	
	private void startScreen(Graphics g2d) {
		
		/**
		 * This part is here to make the title
		 */
		Font font = new Font("Arial", Font.PLAIN, 96);
		
		JTextField score = new JTextField();
		
		g2d.setFont(font);
		
		g2d.setColor(Color.white);
		
		g2d.drawString("Pong", (int) (windowWidth / 2.6), (windowHeight / 4) );
		
		
		
	}
	
	public void setPlayerSpeed(double speed) {
		playerSpeed = speed;
	}
	
	public void setComputerSpeed(double speed) {
		computerSpeed = speed;
	}
	
	public void setBallSpeed(double speed) {
		ballSpeed = speed;
	}
	
	public void setWinningScore(int score) {
		desiredScore = score;
	}
	
	
	
	
	
	

}
