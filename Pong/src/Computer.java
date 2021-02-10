import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.*;

public class Computer {
	
	//Used for movement
	public double dy;
	
	//Used to change how "fast" the computer moves
	public double changeY = 0.7;
	
	//The position of the computer
	private double x = 900;
	private double y = 100;
	
	//the width and height of the image of the computer
	private int width;
	private int height;
	
	//Screen Height
	public int screenHeight = 768;
	
	private Image image;
	
	public Computer() {
		imageLoad();
		initialAI();
	}
	
	private void imageLoad() {
		
		ImageIcon imageURL = new ImageIcon("src/art assets/Paddle.png");
		image = imageURL.getImage();
		
		width = image.getWidth(null);
		height = image.getHeight(null);
		
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
	
	public Image getImage() {
		return image;
	}
	
	public void move() { //moves the computer
		y += dy;
		
		if (y > screenHeight) { //this keeps the computer inside the game
			y = screenHeight;
		} else if (y < 0) {
			y = 0;
		}
		
	}
	
	private void initialAI() {
		dy = changeY;
		
		Random rng = new Random();
		
		int negY = rng.nextInt(1);
		
		if (negY == 1) {
			dy = -1 * dy;
		}
		
		
	}
	
	public void negdY() {
		
		if (dy > 0) {
			dy = -1 * dy;
		}
		
	}
	
	public void posdY() {
		
		if (dy < 0) {
			dy = -1 * dy;
		}
		
	}
	
	
	
	

}
