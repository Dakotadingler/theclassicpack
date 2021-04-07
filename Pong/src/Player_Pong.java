import java.awt.*;
import java.awt.event.KeyEvent;
//import java.util.*;
import javax.swing.*;


public class Player_Pong {
	
	//used for movement
	private double dy;
	
	//how "fast" the player moves
	public double changeY;
	
	//the position of the player
	private double x = 100;
	private double y = 100;
	
	//the width and height of the image of the player
	private int width;
	private int height;
	
	private Image image;
	
	//Screen Height
	public int screenHeight;
	
	public Player_Pong(double moveChange, String customization) {
		
		changeY = moveChange;
		
		imageLoad(customization);
		
	}
	
	
	private void imageLoad(String customization) { //loads the image into the program
		
		ImageIcon imageURL;
		
		switch (customization) {
		case "normal":
			imageURL = new ImageIcon("src/art assets/Paddle.png");
			break;
		case "red":
			imageURL = new ImageIcon("src/art assets/CustomColors/Red.png");
			break;
		case "blue":
			imageURL = new ImageIcon("src/art assets/CustomColors/Blue.png");
			break;
		case "green":
			imageURL = new ImageIcon("src/art assets/CustomColors/Green.png");
			break;
		case "rainbow":
			imageURL = new ImageIcon("src/art assets/CustomColors/Rainbow.png");
			break;
		case "3":
			imageURL = new ImageIcon("src/art assets/CustomColors/3.png");
			break;
		default:
			imageURL = new ImageIcon("src/art assets/Paddle.png");
		}
		
		image = imageURL.getImage();
		
		width = image.getWidth(null);
		height = image.getHeight(null);
		
	}
	
	public void move() { //moves the player
		y += dy;
		
		//System.out.println(screenHeight);
		
		if (y > screenHeight) { //this keeps the computer inside the game
			y = screenHeight;
		} else if (y < 0) {
			y = 0;
		}
		
	}
	
	public Image getImage() {
		return image;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void keyPressed(KeyEvent e) { //when a key is pressed, set dy = (+/-) changeY
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_W) {
			dy = -1* changeY;
		} else if (key == KeyEvent.VK_S) {
			dy = changeY;
		}
		
	}
	
	public void keyReleased(KeyEvent e) { //when a key is released, set dy = 0
		
		int key = e.getKeyCode();
		
		
		if ( (key == KeyEvent.VK_W) || (key == KeyEvent.VK_S) ) {
			dy = 0;
		}
		
	}

}
