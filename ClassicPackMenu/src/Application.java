import java.awt.*;
import java.awt.event.ActionListener;

//import java.util.*;
import javax.swing.*;

public class Application extends JFrame {
	
	public int windowWidth = 1024;
	public int windowHeight = 768;
	private double speedOfComputer;
	private double speedOfPlayer;
	private double speedOfBall;
	private int theWinningScore;
	
	private Pong game;
	
	/*
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });

	}
	*/
	
	
	public Application() {
		
	}
	
	public void load() {
		game = new Pong();
		
		game.windowWidth = windowWidth;
		game.windowHeight = windowHeight;
		
		game.setPlayerSpeed(speedOfPlayer);
		game.setComputerSpeed(speedOfComputer);
		game.setBallSpeed(speedOfBall);
		game.setWinningScore(theWinningScore);
		
		game.initSetup();
		
		
		add ( game );
		
		//add( new Pong() );
		
		
		
		setSize(windowWidth, windowHeight);
		
		setTitle("Pong");
		
		//getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false); //restricts the window so it's not resizable
		
		setLocationRelativeTo(null);
		
		
	}
	

	/**
	 * @param windowWidth the windowWidth to set
	 */
	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	/**
	 * @param windowHeight the windowHeight to set
	 */
	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

	/**
	 * @param speedOfComputer the speedOfComputer to set
	 */
	public void setSpeedOfComputer(double speedOfComputer) {
		this.speedOfComputer = speedOfComputer;
	}

	/**
	 * @param speedOfPlayer the speedOfPlayer to set
	 */
	public void setSpeedOfPlayer(double speedOfPlayer) {
		this.speedOfPlayer = speedOfPlayer;
	}

	/**
	 * @param speedOfBall the speedOfBall to set
	 */
	public void setSpeedOfBall(double speedOfBall) {
		this.speedOfBall = speedOfBall;
	}

	/**
	 * @param theWinningScore the theWinningScore to set
	 */
	public void setTheWinningScore(int theWinningScore) {
		this.theWinningScore = theWinningScore;
	}
	
	


}
