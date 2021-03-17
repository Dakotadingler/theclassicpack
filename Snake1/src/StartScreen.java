
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import java.awt.*;


public class StartScreen {
	
	public int width = 500;
	public int height = 700;
	//creates title
	public JLabel Title = new JLabel("<html>-----------------SNAKE-----------------<br/>"
			+ "<br/>-------------Instructions:------------------<br/>"
			+ "<br/>--------------Keys: WASD-----------------<br/>"
			+ "-----------Apples: +1 Point------------<br/> "
			+ "----Rotten Apples: -1 Point -1 Life-----<br/> "
			+ "--------------Only 3 Lives-------------<br/><br/>"
			+ "Choose the Snake Color, then choose difficulty to begin!<br/>"
			+ "</html>");
	//creates all buttons
	public JButton easyButton = new JButton("Easy");
	public JButton medButton = new JButton("Medium");
	public JButton hardButton = new JButton("Hard");
	
	public JFrame frame = new JFrame();
	public BorderLayout layout = new BorderLayout();
	
	public Font titleFont = new Font("Monospaced", Font.PLAIN, 30);
	static public Color snakeColor;	
	static public Color headColor;	
	static public int Difficulty;
	
	
	public static void main(String[] args) {
		
		new StartScreen();
		
	}
	
	public StartScreen() {
		//creates title graphics
		Title.setFont(titleFont); 
		Title.setForeground(Color.WHITE);
		Title.setBackground(Color.BLACK);
		Title.setOpaque(true);
		
		//creates buttons and button graphics
		easyButton.setPreferredSize(new Dimension(100,100));
		easyButton.setBackground(Color.GREEN);
		easyButton.setOpaque(true);
		easyButton.setBorderPainted(false);
		medButton.setPreferredSize(new Dimension(100,100));
		medButton.setBackground(Color.YELLOW);
		medButton.setOpaque(true);
		medButton.setBorderPainted(false);
		hardButton.setPreferredSize(new Dimension(100,100));
		hardButton.setBackground(Color.RED);
		hardButton.setOpaque(true);
		hardButton.setBorderPainted(false);
		
		
		Title.setPreferredSize(new Dimension(height, width) );
		frame.setLayout(layout); 
		frame.setTitle("Snake Menu"); 
		frame.setBackground(Color.GREEN);
		frame.add(Title, BorderLayout.PAGE_START );
		frame.add(easyButton, BorderLayout.LINE_START);
		frame.add(medButton, BorderLayout.CENTER);
		frame.add(hardButton, BorderLayout.LINE_END);
		
		//creates drop menu(combo box) for color choice
		String[] color = {"Choose a snake color","Green", "Blue", "Pink", "Red", "Orange", "Cyan"};
		final JComboBox<String> comboBox = new JComboBox<String>(color);
		comboBox.setPreferredSize(new Dimension(300,100) );
		
		//changes arrow button and overall layout of combo box
		comboBox.setUI(new BasicComboBoxUI(){
	        protected JButton createArrowButton()
	        {
	            BasicArrowButton arrowButton = new BasicArrowButton(BasicArrowButton.SOUTH, null, null, Color.GRAY, null);
	            return arrowButton;
	        }
	    });
		
		comboBox.setForeground(Color.GRAY);
		comboBox.setBackground(Color.BLACK);
		comboBox.setOpaque(true);
		comboBox.setVisible(true);
		
		frame.add(comboBox, BorderLayout.AFTER_LAST_LINE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setLocationRelativeTo(null); 
		frame.pack(); 
		frame.setVisible(true); 
		//if user clicks on easy button, difficulty is set to easy
		easyButton.addActionListener( new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Difficulty = 75;
				startSnake();
				
			}
		});
		//if user clicks on medium button, difficulty is set to medium
		medButton.addActionListener( new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Difficulty = 60;
				startSnake();
				
			}
		});
		//if user clicks on hard button, difficulty is set to hard
		hardButton.addActionListener( new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Difficulty = 45;
				startSnake();
				
			}
		});
		//if user selects a color, the snake is changed to that color
		comboBox.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String col = (String)comboBox.getSelectedItem();
				
				if (col == "Green") {
					snakeColor = new Color(0, 128, 0);
					headColor = new Color(0, 200, 0);
				} else if (col == "Blue") {
					snakeColor = new Color(0,0,200);
					headColor = new Color(0, 0, 255);
				} else if (col == "Pink") {
					snakeColor = new Color(255,105,180);
					headColor = new Color(255,182,193);
				} else if (col == "Red") {
					snakeColor = new Color(139,0,0);
					headColor = new Color(255,0,0);
				} else if (col == "Orange") {
					snakeColor = new Color(255,110,0);
					headColor = new Color(255,165,0);
				} else if (col == "Cyan") {
					snakeColor = new Color(0,200,200);
					headColor = new Color(0,255,255);
				} 
			}
		});
	}
	//is called in main to open new frame from Frame class
	public void startSnake() { 
		
		Frame ex = new Frame();
		ex.setVisible(true);
		ex.newFrame();
		
	}
}
