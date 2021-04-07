//import java.util.*;
import javax.swing.*;

public class ApplicationPong extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1706504574895795864L;
	public int windowWidth = 1024;
	public int windowHeight = 768;
	private double speedOfComputer;
	private double speedOfPlayer;
	private double speedOfBall;
	private int theWinningScore;
	private String customColors;
	private String userName;
	
	private Pong game;
	
	
	/*
	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });

	}
	*/
	
	
	public ApplicationPong() {
		
	}
	
	public void load() {
		
		game = new Pong();
		
		game.windowWidth = windowWidth;
		game.windowHeight = windowHeight;
		
		game.setPlayerSpeed(speedOfPlayer);
		game.setComputerSpeed(speedOfComputer);
		game.setBallSpeed(speedOfBall);
		game.setWinningScore(theWinningScore);
		game.setCustomColor(customColors);
		game.setName( userName );
		game.setLink2(this);
		
		//If the player wants unlimited play
		if (theWinningScore == -1)
			game.unLimitedPlay = true;
		
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
	
	public void setName(String name) {
		userName = name;
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
	
	public void setCustomColor(String color) {
		customColors = color;
	}
	
	
	
	


}
