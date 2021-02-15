import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Apple {
	
	private int xCoor, yCoor, width, height;
	private BufferedImage testImage;
	
	public Apple(int xCoor, int yCoor, int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = tileSize;
		height = tileSize;
	}
	public void tick() {
		
	}
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		//testImage = ImageLoader.loadImage("/textures/apple.png");
		//g.fillRect(xCoor * width , yCoor * height, width, height);
		g.fillOval(xCoor* width, yCoor* height+1, width+1, height+1);
	}
	
	public int getxCoor() {
		return xCoor;
	}
	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}
	public int getyCoor() {
		return yCoor;
	}
	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
	
}