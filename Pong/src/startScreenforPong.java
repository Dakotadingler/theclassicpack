import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class startScreenforPong {

	//used for the Screen Width and Height
		public int windowWidth = 1024;
		public int windowHeight = 768;
		
		//Buttons
		//public JButton butt1 = new JButton("Easy");
		//public JButton butt2 = new JButton("Normal");
		//public JButton butt3 = new JButton("Evil");
		public JButton startTheGame = new JButton("Start");
		
		public JFrame frame = new JFrame(); //frame used for window
		//public JTextField txt1 = new JTextField("0"); //the text window the user enters into
		public BorderLayout layout = new BorderLayout(); //the organizational function used for this screen
		//Used for displaying the title and instructions
		public JLabel Title = new JLabel("<html>Pong<br/>Instructions:<br/>Move up with 'w' and move down with 's'.<br/>The difficulty changes the speed of the ball and CPU.<br/> Enter the number of points to win below then block the difficulty you would like.</html>");
		
		//font for text
		public Font titleFont = new Font("Arial", Font.PLAIN, 40);
		
		public double speedCom;
		public double speedPlay;
		public double speedBall;
		public int winScore;
		
		public String whichDifficulty;
		public String customization;
		private String userName = "Jacob";

		public static void main(String[] args) {
			
			startScreenforPong test = new startScreenforPong();
		}
		
		public startScreenforPong() {
			startScreen();
		}
		
		/**
		In order to have the userName put into the program
		1. load a blank constructor for startScreenforPong
		2. call the method sendUsername(userName) the parameter is the username the user has put in
		3. load startScreen()
		**/
		
		//load this method before running startScreenforPong
		public void sendUsername(String user_input) {
			userName = user_input;
		}
		
		public void startScreen() {
			
			if (userName == null)
				userName = "Player";
			
			
			Title.setFont(titleFont); //sets font
			
			//size for buttons on screen
			//butt1.setPreferredSize( new Dimension(100,100) );
			//butt2.setPreferredSize( new Dimension(100,100) );
			//butt3.setPreferredSize( new Dimension(100,100) );
			Title.setPreferredSize( new Dimension(windowHeight, 400) );
			startTheGame.setPreferredSize( new Dimension(100,100) );
			
			
			frame.setLayout( layout ); //sets the layout of the window to the BorderLayout
			
			frame.setTitle("Test"); //gotta have a title on top :)
			
			//makes a drop down menu for winning score
			String[] winningScore = {"5", "10", "15", "20", "25", "Unlimited"};
			final JComboBox<String> cb = new JComboBox<String>(winningScore);
			cb.setVisible(true);
			
			
			
			//Makes a drop down menu for difficulty
			String [] difficulty = {"Easy", "Medium", "Evil >:)"};
			final JComboBox<String> cb2 = new JComboBox<String>(difficulty);
			cb2.setVisible(true);
			
			//Makes a drop down menu for custom colors :3
			String[] customColors = {"Normal", "Red", "The Number 3", "Green", "Rainbow", "Blue"};
			final JComboBox<String> cb3 = new JComboBox<String>(customColors);
			cb3.setVisible(true);
			
			
			//grab the frequency for the CPU
			
			
			
			//adds the text and buttons to the window
			frame.add(Title, BorderLayout.PAGE_START );
			frame.add(cb, BorderLayout.LINE_START);
			frame.add(cb2, BorderLayout.CENTER);
			frame.add(cb3, BorderLayout.LINE_END);
			frame.add(startTheGame, BorderLayout.SOUTH);
			
			
			//lets the program grab the value from inside the drop down
			cb.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String temp = (String)cb.getSelectedItem();
					
					
					if (temp == "5") {
						winScore = 5;
					} else if (temp == "10") {
						winScore = 10;
					} else if (temp == "15") {
						winScore = 15;
					} else if (temp == "20") {
						winScore = 20;
					} else if (temp == "25") {
						winScore = 25;
					} else if (temp == "Unlimited"){
						winScore = -1;
					} else {
						winScore = 5;
					}
					
					
					
				}
				
			});
			
			cb2.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String temp = (String)cb2.getSelectedItem();
					
					if (temp == "Easy") {
						whichDifficulty = "Easy";
					} else if (temp == "Normal") {
						whichDifficulty = "Normal";
					} else if (temp == "Evil >:)") {
						whichDifficulty = "Evil";
					} else {
						whichDifficulty = "Easy";
					}
					
				}
				
			});
			
			cb3.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					String temp = (String) cb3.getSelectedItem();
					
					//{"Normal", "Red", "The Number 3", "Green", "Rainbow", "Blue"};
					
					if (temp == "Normal") {
						customization = "normal";
					} else if (temp == "Red") {
						customization = "red";
					} else if (temp == "The Number 3") {
						customization = "3";
					} else if (temp == "Green") {
						customization = "green";
					} else if (temp == "Rainbow") {
						customization = "rainbow";
					} else if (temp == "Blue") {
						customization= "blue";
					} else {
						customization = "normal";
					}
					
				}
				
			});
			
			startTheGame.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					//get speeds correct
					if (whichDifficulty == "Easy") {
						speedCom = 1.20;
						speedPlay = 15.0;
						speedBall = 2.0;
					} else if (whichDifficulty == "Normal") {
						speedCom = 1.6;
						speedPlay = 15.0;
						speedBall = 2.0;
					} else if (whichDifficulty == "Evil") {
						speedCom = 12.0;
						speedPlay = 15.0;
						speedBall = 2.5;
					} else { //do normal settings
						speedCom = 1.2;
						speedPlay = 15.0;
						speedBall = 2.0;
					}
					
					if (winScore == 0) {
						winScore = 5;
					}
					
					if (customization == null) {
						customization = "normal";
					}
					
					printSettings();
					
					setupPong();
					
				}
				
			});
			
			
			
			//adds the drop down to the bottom
			//frame.add(cb, BorderLayout.PAGE_END);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if the user hits the BIG SHINY RED BUTTON ON TOP then the program will close
			
			frame.setLocationRelativeTo(null); //go to the middle of the screen or else
			
			frame.pack(); //wrap everything in a nice little bow
			
			frame.setVisible(true); //now we can see the window! :)
			
		}
		
		private void printSettings() { //prints out the speeds and the winnning score 
			String output = "Player Speed:\t" + speedPlay +"\nComputer Speed:\t" + speedCom + "\nBall Speed:\t" + speedBall + "\nWinning Score:\t" + winScore + "\n" + "Player name:\t" + userName;
			System.out.println(output);
		}
		
		private void setupPong() { //start the pong game with the following information
			
			ApplicationPong ex = new ApplicationPong();
			ex.setVisible(true);
			
			ex.setWindowWidth(windowWidth);
			ex.setWindowHeight(windowHeight);
			ex.setSpeedOfComputer(speedCom);
			ex.setSpeedOfPlayer(speedPlay);
			ex.setSpeedOfBall(speedBall);
			ex.setTheWinningScore(winScore);
			ex.setCustomColor(customization);
			ex.setName(userName);
			
			//frame.dispose() closes the startScreen
			frame.dispose();
			
			ex.load();
			
		}

}
