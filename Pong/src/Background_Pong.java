import java.awt.Image;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;

public class Background_Pong {
	
	private double x;
	private double y;
	private int width;
	private int height;
	private Image image;
	private int current;
	
	public Background_Pong(double x, double y) {
		this.x = x;
		this.y = y;
		current = 0;
		initializeBackground();
	}
	
	private void initializeBackground() {
		ImageIcon imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0001.jpg");
		image = imageURL.getImage();
		width = image.getWidth(null) / 5;
		height = image.getHeight(null) / 5;
	}
	
	public void update() {
		
		current++;
		if (current > 13)
			current = 1;
		
		updateImage(current);
		
	}
	
	private void updateImage(int num) {
		
		ImageIcon imageURL;
		
		switch(num) {
		case 1:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0001.jpg");
			break;
		case 2:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0002.jpg");
			break;
		case 3:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0003.jpg");
			break;
		case 4:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0004.jpg");
			break;
		case 5:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0005.jpg");
			break;
		case 6:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0006.jpg");
			break;
		case 7:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0007.jpg");
			break;
		case 8:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0008.jpg");
			break;
		case 9:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0009.jpg");
			break;
		case 10:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0010.jpg");
			break;
		case 11:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0011.jpg");
			break;
		case 12:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0012.jpg");
			break;
		case 13:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0013.jpg");
			break;
		default:
			imageURL = new ImageIcon("src/art assets/Images of 3/stock-footage-number-rotating-on-white-background-d-animation - Image Sequence/IMG_0001.jpg");
		}
		
		image = imageURL.getImage();
		width = image.getWidth(null) / 5;
		height = image.getHeight(null) / 5;
		
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}
	
	

}
