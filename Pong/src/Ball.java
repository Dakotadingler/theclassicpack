import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;

import javax.swing.JPanel;
import javax.swing.*;


public class Ball {
	
	//Screen width
	//FIGURE OUT HOW TO IMPORT THIS FROM EARLIER IN THE PROGRAM
	public int screenHeight = 760;
	public int screenWidth = 1024;
	
	//ball movement
	private double dx;
	private double dy;
	
	//how fast the ball will move
	private double change = 1;
	
	//Ball position
	private double x = 500;
	private double y = 500;
	
	//Image width and height
	private int width;
	private int height;
	
	private Image image;
	
	public Ball( ) {
		
		//screenWidth = sW;
		//screenHeight = sH;
		
		imageLoad();
		initialMove(); //initial random direction of movement
		
		
		
//		System.out.println(dx + "\t" + dy); //prints out the initial dx and dy for movement TESTING PURPOSES
	}
	
	private void imageLoad() { //loads the image of the ball into the terminal
		
		ImageIcon imageURL = new ImageIcon("src/art assets/Ball.png");
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
	
	public void move() { //changes the ball's x and y position
		
		//System.out.println(x + "\t" + y);
		
		if ( (y <= 0) || (y >= screenHeight) ){ //if the ball has hit the y parts of the window
			dy = -1 * dy;
		}
		
		if ( (x <= 0) || (x >= screenWidth) ) { //if the ball has hit the x parts of the window
			dx = -1 * dx;
		}
		
		//actually updates the x and y cordinates
		x += dx;
		y += dy;
		

		
	}
	
	private void initialMove() { //initial random direction of movement
		
		x = 500;
		y = 500;
		
		Random rng = new Random();
		
		//0 if the dx/dy should be positive
		//1 if the dx/dy should be negative
		int negX = rng.nextInt(2);
		int negY = rng.nextInt(2);
		
		dx = change;
		dy = change;
		
//		dx = rng.nextInt(4) + 1;
//		dy = rng.nextInt(4) + 1;
		
		if (negX == 1) {
			dx = -1 * dx;
		}
		if (negY == 1) {
			dy = -1 * dy;
		}
		
		
		
	}
	
	public void collision() { //if a collision happens, reverse X direction
		
		dx = -1 * dx;
		
		//There's a really dumb glitch I encountered while making this where the ball would stick to the com paddle
		//if the speeds were the same so this fixes that glitch (hopefully)
		if ( (x > 900) && (x <= 910) ) { 
			x = 880;
		}
		
		//System.out.println("Collision at:\t" + x +"\t" + y + "\t" + dx);
		
		
	}
	
	public double getdY() {
		return dy;
	}
	
	
	

}
