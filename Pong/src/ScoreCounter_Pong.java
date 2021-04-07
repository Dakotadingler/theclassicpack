import java.awt.*;

import javax.swing.*;

public class ScoreCounter_Pong {
	
	
	//Position of counter
	private double x;
	private double y;
	
	private int width;
	private int height;
	
	private Image image;
	
	public ScoreCounter_Pong(double newX, double newY) {
		
		x = newX;
		y = newY;
		
		imageInitialization();
		
		
	}
	
	private void imageInitialization() {
		
		ImageIcon imageURL = new ImageIcon("src/art assets/scoreArt/0.png");
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
	
	public void counterUpdate(int score) {
		
		ImageIcon imageURL;
		
		switch (score) {
		
		case 0:
			imageURL = new ImageIcon("src/art assets/scoreArt/0.png");
			break;
		case 1:
			imageURL = new ImageIcon("src/art assets/scoreArt/1.png");
			break;
		case 2:
			imageURL = new ImageIcon("src/art assets/scoreArt/2.png");
			break;
		case 3:
			imageURL = new ImageIcon("src/art assets/scoreArt/3.png");
			break;
		case 4:
			imageURL = new ImageIcon("src/art assets/scoreArt/4.png");
			break;
		case 5:
			imageURL = new ImageIcon("src/art assets/scoreArt/5.png");
			break;
		case 6:
			imageURL = new ImageIcon("src/art assets/scoreArt/6.png");
			break;
		case 7:
			imageURL = new ImageIcon("src/art assets/scoreArt/7.png");
			break;
		case 8:
			imageURL = new ImageIcon("src/art assets/scoreArt/8.png");
			break;
		case 9:
			imageURL = new ImageIcon("src/art assets/scoreArt/9.png");
			break;
		case 10:
			imageURL = new ImageIcon("src/art assets/scoreArt/10.png");
			break;
		case 11:
			imageURL = new ImageIcon("src/art assets/scoreArt/11.png");
			break;
		case 12:
			imageURL = new ImageIcon("src/art assets/scoreArt/12.png");
			break;
		case 13:
			imageURL = new ImageIcon("src/art assets/scoreArt/13.png");
			break;
		case 14:
			imageURL = new ImageIcon("src/art assets/scoreArt/14.png");
			break;
		case 15:
			imageURL = new ImageIcon("src/art assets/scoreArt/15.png");
			break;
		case 16:
			imageURL = new ImageIcon("src/art assets/scoreArt/16.png");
			break;
		case 17:
			imageURL = new ImageIcon("src/art assets/scoreArt/17.png");
			break;
		case 18:
			imageURL = new ImageIcon("src/art assets/scoreArt/18.png");
			break;
		case 19:
			imageURL = new ImageIcon("src/art assets/scoreArt/19.png");
			break;
		case 20:
			imageURL = new ImageIcon("src/art assets/scoreArt/20.png");
			break;
		case 21:
			imageURL = new ImageIcon("src/art assets/scoreArt/21.png");
			break;
		case 22:
			imageURL = new ImageIcon("src/art assets/scoreArt/22.png");
			break;
		case 23:
			imageURL = new ImageIcon("src/art assets/scoreArt/23.png");
			break;
		case 24:
			imageURL = new ImageIcon("src/art assets/scoreArt/24.png");
			break;
		case 25:
			imageURL = new ImageIcon("src/art assets/scoreArt/25.png");
			break;
		default:
			imageURL = new ImageIcon("src/art assets/scoreArt/0.png");
		}
		
		image = imageURL.getImage();
		
		width = image.getWidth(null);
		height = image.getHeight(null);
			
			
				
		
	}
	
	

}
