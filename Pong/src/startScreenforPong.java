import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class startScreenforPong {

	//used for the Screen Width and Height
		public int windowWidth = 1024;
		public int windowHeight = 768;
		
		//Buttons
		public JButton butt1 = new JButton("Easy");
		public JButton butt2 = new JButton("Normal");
		public JButton butt3 = new JButton("Evil");
		
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

		public static void main(String[] args) {
			new startScreenforPong();
		}
		
		public startScreenforPong() {
			
			
			Title.setFont(titleFont); //sets font
			
			//size for buttons on screen
			butt1.setPreferredSize( new Dimension(100,100) );
			butt2.setPreferredSize( new Dimension(100,100) );
			butt3.setPreferredSize( new Dimension(100,100) );
			Title.setPreferredSize( new Dimension(windowHeight, 400) );
			
			frame.setLayout( layout ); //sets the layout of the window to the BorderLayout
			
			frame.setTitle("Test"); //gotta have a title on top :)
			
			//makes a drop down menu
			String[] winningScore = {"5", "10", "15", "20", "25"};
			final JComboBox<String> cb = new JComboBox<String>(winningScore);
			cb.setVisible(true);
			
			//adds the text and buttons to the window
			frame.add(Title, BorderLayout.PAGE_START );
			frame.add(butt1, BorderLayout.LINE_START);
			frame.add(butt2, BorderLayout.CENTER);
			frame.add(butt3, BorderLayout.LINE_END);
			
			butt1.addActionListener( new ActionListener() { //what does button1 do?
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Easy");
					
					speedCom = 0.7;
					speedPlay = 15.0;
					speedBall = 1.0;
					
					if (winScore == 0) {
						winScore = 10;
					}
					
					printSettings();
					
					setupPong();
					
					//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
			});
			
			butt2.addActionListener( new ActionListener() { //what does button2 do?
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Normal");
					
					speedCom = 1.0;
					speedPlay = 15.0;
					speedBall = 1.2;
					
					if (winScore == 0) {
						winScore = 10;
					}
					
					printSettings();
					
					setupPong();
					
					//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
			});
			
			butt3.addActionListener( new ActionListener() { //what does button3 do?
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Evil");
					
					speedCom = 12.0;
					speedPlay = 15.0;
					speedBall = 2.0;
					
					if (winScore == 0) {
						winScore = 10;
					}
					
					printSettings();
					
					setupPong();
					
					//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}
			});
			
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
					} else {
						winScore = 10;
					}
					
					
				}
				
			});
			
			//adds the drop down to the bottom
			frame.add(cb, BorderLayout.PAGE_END);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if the user hits the BIG SHINY RED BUTTON ON TOP then the program will close
			
			frame.setLocationRelativeTo(null); //go to the middle of the screen or else
			
			frame.pack(); //wrap everything in a nice little bow
			
			frame.setVisible(true); //now we can see the window! :)
			
		}
		
		public void printSettings() { //prints out the speeds and the winnning score 
			String output = "Player Speed:\t" + speedPlay +"\nComputer Speed:\t" + speedCom + "\nBall Speed:\t" + speedBall + "\nWinning Score:\t" + winScore + "\n";
			System.out.print(output);
		}
		
		public void setupPong() { //start the pong game with the following information
			
			Application ex = new Application();
			ex.setVisible(true);
			
			ex.setWindowWidth(windowWidth);
			ex.setWindowHeight(windowHeight);
			ex.setSpeedOfComputer(speedCom);
			ex.setSpeedOfPlayer(speedPlay);
			ex.setSpeedOfBall(speedBall);
			ex.setTheWinningScore(winScore);
			
			//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			
			ex.load();
			
		}

}
