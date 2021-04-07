import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//import javax.swing.JPanel;
import javax.swing.*;


public class Pong extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1237313979050845997L;
	private Timer timer; //used for movement (somehow)
	private final int DELAY = 10;
	
	private Player_Pong mc; //Main character object
	private Ball_Pong ball; //Ball object
	private Computer_Pong com; //Computer player
	private ScoreCounter_Pong playerCounter; //Counter for the player score
	private ScoreCounter_Pong comCounter; //Counter for the com score
	private Background_Pong secret;
	
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
	
	//detects if the game is paused or not
	private boolean pause = false;
	//if the player wants unlimited play
	public boolean unLimitedPlay = false;
	
	//used for username and where it should go
	private String userName;
	private int userNameX = 450;
	private int userNameY = 150;
	
	//Links the application object that calls Pong so the game can be closed
	private ApplicationPong link2;
	
	private Clip clip; //used for music
	
	
	
	public Pong() {
		
		
		
	}
	
	public void setName(String newName) {
		userName = newName;
	}
	
	//Links the application object so the game can be closed
	public void setLink2(ApplicationPong newLink) {
		link2 = newLink;
	}

	@Override
	public void actionPerformed(ActionEvent e) { //if an action is performed
		
		//System.out.println(mc.changeY + "\t" + com.changeY + "\t" + ball.change);
		
		step();
		
	}
	
	
	public void initSetup() {
		
		//pause = false;
		
		
		addKeyListener( new TAdapter() ); //used to notice buttons
		setBackground( Color.black ); //sets the background of the window to black
		setFocusable(true);
		
		//Player.setHeight(windowHeight);
		
		if (customization == "3") {
			secret = new Background_Pong(200, 200);
		}
		
		//creates objects inside the game
		mc = new Player_Pong(playerSpeed, customization);
		ball = new Ball_Pong(ballSpeed);
		com = new Computer_Pong(computerSpeed, customization);
		playerCounter =  new ScoreCounter_Pong(400, 20);
		comCounter = new ScoreCounter_Pong(600, 20);
		
		
		if ( (userName.equalsIgnoreCase("RickRolled") ) && (customization != "3") )
			checkMusic(0); //RickRoll the player
		else if (customization == "3")
			checkMusic(1); //The number 3 has epic music
		else if (userName.equalsIgnoreCase("weird"))
			checkMusic(2); //battleblock theater music (switched it lol)
		
		
		
		
		mc.changeY = playerSpeed;
		com.changeY = computerSpeed;
		ball.change = ballSpeed;
		
		gameStart = true;
		
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
		
		
		if (customization == "3") {
			g2d.drawImage( secret.getImage(), (int) secret.getX(), (int) secret.getY(), this);
			
			ball.updateImage(true);
			
		} else {
			ball.updateImage(false);
		}
		
		
		g2d.drawImage( mc.getImage(), (int) mc.getX(), (int) mc.getY(), this);
		g2d.drawImage( ball.getImage(), (int) ball.getX(), (int) ball.getY(), this);
		g2d.drawImage( com.getImage(), (int) com.getX(), (int) com.getY(), this);
		g2d.drawImage( playerCounter.getImage(), (int) playerCounter.getX(), (int) playerCounter.getY(), this);
		g2d.drawImage( comCounter.getImage(), (int) comCounter.getX(), (int) comCounter.getY(), this);
		g2d.drawString(userName, userNameX, userNameY);
		
		
		
		windowSIZE();
		
		//Used to display the players name
		Font font = new Font("Arial", Font.PLAIN, 48);
		g2d.setFont(font);
		g2d.setColor(Color.white);
		g2d.drawString(userName, userNameX, userNameY);
		
		
		//If the game is currently going on
		if ( (gameStart) && (!pause) ) {
			if (clockActivate) {
				clockTick();
			}
			
			ball.move(); //moves the ball
			computerAI();
		}
		
		if (customization == "3") {
			repaint( (int) secret.getX(), (int) secret.getY(), secret.getWidth(), secret.getHeight() );
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
		
		if ( (gameStart) && (!pause) ) {
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
			
			//This block of code is for pausing the game
			int key = e.getKeyCode();
			
			//if the player has pressed the pause button
			if (key == KeyEvent.VK_ESCAPE) {
				
				if ( (!pause) && (gameStart) )//if the game is not paused, then pause it
					pause = true;
				else //if the game is already paused, then unpause it
					pause = false;
				
				//if the user wants to close the game after the game ends, then close the JFrame that opened the Pong game
				if (!gameStart) {
					link2.dispose();
					System.exit(0);
				}
					
				
				System.out.println("ESC Pressed:\t" + gameStart + "\t" + pause);
				
			}
			
			//if the player has pressed the space bar
			if (key == KeyEvent.VK_SPACE) {
				
				//if the game has ended
				if (!gameStart) {
					//restart the game
					gameStart = true;
					playPoints = 0;
					comPoints = 0;
					pause = false;
					//initSetup();
					mc = new Player_Pong(playerSpeed, customization);
					ball = new Ball_Pong(ballSpeed);
					com = new Computer_Pong(computerSpeed, customization);
					playerCounter =  new ScoreCounter_Pong(400, 20);
					comCounter = new ScoreCounter_Pong(600, 20);
					System.out.println(gameStart + "\t" + pause);
					
				}
				
				
			}
				
			
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
			ball = new Ball_Pong(ballSpeed);
			comCounter.counterUpdate(comPoints);
			if (customization == "3")
				secret.update();
			System.out.println("Computer Points: " + comPoints + "\nPlayer Points: " + playPoints);
		} else if (ball.getX() >= windowWidth)  { //if the player makes a goal
			playPoints++;
			ball = new Ball_Pong(ballSpeed);
			playerCounter.counterUpdate(playPoints);
			if (customization == "3")
				secret.update();
			System.out.println("Computer Points: " + comPoints + "\nPlayer Points: " + playPoints);
		}
		
		//End the game if one of them has gotten enough points
		if ( ( (comPoints >= desiredScore) || (playPoints >= desiredScore) ) && !(unLimitedPlay)) {
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
		
		//JTextField score = new JTextField();
		
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
	
	private void checkMusic(int code) {
		/*
		 * 0 - if the player needs to be rickrolled
		 * 1 - the number 3 has to come with super epic music to go along with that sick epic gameplay am i right? metal gear solid 2 music is good right?
		 * 2 - wow the user wants to be weird, how odd. Let's give them some weird music from battleblock theater i guess
		 */
		
		AudioInputStream audioInputStream;
		
		String URL = "";
		
		if (code == 0) //if the player needs to be rickrolled then load the rickroll music
			URL = "src/art assets/audio/Rickrolled.wav";
		else if (code == 1) //if the player deserves to see the almighty number 3, then give them what they came here for
			URL = "src/art assets/audio/Ball.wav";
			//URL = "src/art assets/audio/Secret.wav";
		else if (code == 2)
			URL = "src/art assets/audio/Secret.wav";
		
		//if music should be played
		if ( (code == 0) || (code == 1) || (code == 2) ) {
			
			try {
				//load the music
				audioInputStream = AudioSystem.getAudioInputStream(new File(URL) );
				AudioFormat af = audioInputStream.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, af);
				clip = (Clip) AudioSystem.getLine(info);
				clip.open(audioInputStream);
				
				clip.loop( Integer.MAX_VALUE - 1); //makes it so the song will loop (will loop a long time but not infinity though
				
				clip.start();
				
				//Thread.sleep( clip.getFrameLength() );
				
				//clip.close();
			} catch (UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	
	
	
	

}
