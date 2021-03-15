import java.awt.*;
import java.awt.event.*;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

//import javax.swing.JPanel;
import javax.swing.*;


public class Pong extends JPanel implements ActionListener {
	
	private Timer timer; //used for movement (somehow)
	private final int DELAY = 10;
	
	private Player mc; //Main character object
	private Ball ball; //Ball object
	private Computer com; //Computer player
	private ScoreCounter playerCounter; //Counter for the player score
	private ScoreCounter comCounter; //Counter for the com score
	
	private String customization = "normal";
	
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
	
	//Used for physics clock
	private boolean clockActivate = true;
	private int clockTick = 1;
	
	
	
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
		mc = new Player(playerSpeed, customization);
		ball = new Ball(ballSpeed);
		com = new Computer(computerSpeed, customization);
		playerCounter =  new ScoreCounter(400, 20);
		comCounter = new ScoreCounter(600, 20);
		
		
		mc.changeY = playerSpeed;
		com.changeY = computerSpeed;
		ball.change = ballSpeed;
		
		//mc.setHeight(windowHeight);
		
		//used for detecting collisions
		collisionX = mc.getWidth();
		collisionY = mc.getHeight() - 20;
		
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
		g2d.drawImage( playerCounter.getImage(), (int) playerCounter.getX(), (int) playerCounter.getY(), this);
		g2d.drawImage( comCounter.getImage(), (int) comCounter.getX(), (int) comCounter.getY(), this);

		
		windowSIZE();
		
		//If the game is currently going on
		if (gameStart) {
			if (clockActivate) {
				clockTick();
			}
			ball.move(); //moves the ball
			computerAI();
		}
		
		//updates the screen with new ball's location
		repaint( ( (int) ball.getX() )-100, (int) ball.getY()-100, (int) ball.getWidth()+1000, (int) ball.getHeight()+1000 );
		repaint( (int) com.getX()-100, (int) com.getY()-100, (int) com.getWidth()+1000, (int) com.getHeight()+1000 );
		repaint( (int) playerCounter.getX()-100, (int) playerCounter.getY()-100, (int) playerCounter.getWidth()+1000, (int) playerCounter.getHeight()+1000 );
		repaint( (int) comCounter.getX()-100, (int) comCounter.getY()-100, (int) comCounter.getWidth()+1000, (int) comCounter.getHeight()+1000 );
		
		//if the game has ended
		if (!gameStart) {
			endScreen(g2d);
		}
		
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
			if (clockActivate) {
				clockTick();
			}
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
		
		//if the computer makes a goal
		if (ball.getX() <= 10) {
			comPoints++;
			ball = new Ball(ballSpeed);
			comCounter.counterUpdate(comPoints);
			System.out.println("Computer Points: " + comPoints + "\nPlayer Points: " + playPoints);
		} else if (ball.getX() >= windowWidth)  { //if the player makes a goal
			playPoints++;
			ball = new Ball(ballSpeed);
			playerCounter.counterUpdate(playPoints);
			System.out.println("Computer Points: " + comPoints + "\nPlayer Points: " + playPoints);
		}
		
		//End the game if one of them has gotten enough points
		if ( (comPoints >= desiredScore) || (playPoints >= desiredScore) ) {
			gameStart = false;
		}

		
	}
	
	//Gives variables to objects that need them
	private void windowSIZE() {
		mc.screenHeight = windowHeight;
		ball.screenHeight = windowHeight;
		ball.screenWidth = windowWidth;
		com.screenHeight = windowHeight;
	}
	
	private void endScreen(Graphics g2d) {
		
		/**
		 * This part is here to make the title
		 */
		Font font = new Font("Arial", Font.PLAIN, 48);
		
		JTextField score = new JTextField();
		
		g2d.setFont(font);
		
		g2d.setColor(Color.white);
		
		String output1 = "";
		String output3 = "Computer Points: " + comPoints;
		String output4 = "Player Points: " + playPoints;
		
		//Who won?
		if (comPoints >= desiredScore) {
			output1 = "You lose! Computer wins!";
		} else if (playPoints >= desiredScore) {
			output1 = "You win! Computer Loses!";
		}
		
		//Prints out end screen
		g2d.drawString(output1, (int) (windowWidth / 4), (int) (windowHeight / 4) );
		g2d.drawString(output3, (int) (windowWidth / 4), (int) ( (windowHeight / 3)) );
		g2d.drawString(output4, (int) (windowWidth / 4), (int) ( (windowHeight / 2.5)) );
		
		
		
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
	
	public void setCustomColor(String color) {
		customization = color;
	}
	
	public void clockTick() {
		try {
			//TimeUnit.SECONDS.sleep(4/5);
			//TimeUnit.MILLISECONDS.sleep(1);
			TimeUnit.MICROSECONDS.sleep(clockTick);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

}
