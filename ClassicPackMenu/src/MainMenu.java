import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Scanner;

import javax.swing.*;

public class MainMenu {
	String name;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu window = new MainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenu() {
		start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void start() {
		name = JOptionPane.showInputDialog(null, "Enter your name: ");
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(51, 0, 102));
		frame.setBackground(new Color(51, 0, 51));
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel background = new JLabel(new ImageIcon("src/art assets/space.jpg"));
		background.setBounds(0, 0, 800, 600);
		
		
		JButton snake = new JButton("Play Snake");
		snake.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				new Frame();
		
			}
			
		});
		snake.setFont(new Font("Arial Black", Font.PLAIN, 11));
		snake.setForeground(new Color(0, 0, 0));
		snake.setBackground(new Color(0, 255, 0));
		snake.setBounds(350, 300, 100, 30);
		frame.getContentPane().add(snake);
		
		JButton pong = new JButton("Play Pong");
		pong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				new startScreenforPong();
			}
			
		});
		pong.setFont(new Font("Arial Black", Font.PLAIN, 11));
		pong.setForeground(new Color(0, 0, 0));
		pong.setBackground(new Color(0, 204, 0));
		pong.setBounds(350, 350, 100, 30);
		frame.getContentPane().add(pong);
		
		JLabel player = new JLabel("Welcome "+name+" To");
		player.setForeground(new Color(255, 204, 51));
		player.setFont(new Font("Arial Black", Font.PLAIN, 48));
		player.setHorizontalAlignment(SwingConstants.CENTER);
		player.setBounds(0, 100, 800, 70);
		frame.getContentPane().add(player);
		
		JLabel title = new JLabel("The Classic Pack");
		title.setForeground(new Color(255, 204, 51));
		title.setFont(new Font("Arial Black", Font.PLAIN, 48));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(150, 150, 500, 70);
		frame.getContentPane().add(title);
		
		JLabel directions = new JLabel("Select a Game to Play");
		directions.setForeground(new Color(51, 255, 0));
		directions.setHorizontalAlignment(SwingConstants.CENTER);
		directions.setFont(new Font("Arial Black", Font.PLAIN, 25));
		directions.setBounds(200, 250, 400, 40);
		frame.getContentPane().add(directions);
		frame.getContentPane().add(background);
	}

	public void close() {

		WindowEvent closeWindow = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
		
	}

}
