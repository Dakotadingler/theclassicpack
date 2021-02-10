import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Application extends JFrame {
	
	public int windowWidth = 1024;
	public int windowHeight = 768;
	
	private Pong game;
	

	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
            Application ex = new Application();
            ex.setVisible(true);
        });

	}
	
	public Application() {
		load();
	}
	
	private void load() {
		
		game = new Pong();
		
		game.windowWidth = windowWidth;
		game.windowHeight = windowHeight;
		
		add ( game );
		
		//add( new Pong() );
		
		
		
		setSize(windowWidth, windowHeight);
		
		setTitle("Pong");
		
		//getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
	}

}
