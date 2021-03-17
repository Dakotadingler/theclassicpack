
import java.awt.*;
import java.awt.event.*;


import java.awt.Image;

import javax.swing.*;
import java.util.Random;

public class Panel extends JPanel implements MouseListener, KeyListener, ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	static final int HEIGHT = 400;
	static final int WIDTH = 400;
	static final int unitSize = WIDTH / 30;
	static final int gameUnits = (WIDTH * HEIGHT) / unitSize;
	
	final int x[] = new int[gameUnits];
	final int y[] = new int[gameUnits];
	
	int BodySize = 10;
	int points;
	int appleXPOS;
	int appleYPOS;
	int lives;
	
	//rotten apple positions
	int rotAppleXPOS1;
	int rotAppleYPOS1;
	int rotAppleXPOS2;
	int rotAppleYPOS2;
	
	//starts the snake going in direction R
	char direction = 'R';
	
	boolean running = false;
	Random random;
	Timer timer;
	
	private Image apple;
	private Image mine;
	
	public Panel() {
		random = new Random();
		setFocusable(true);	
    	addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        loadImages();
        startGame();

	}
	
	private void loadImages() {

        ImageIcon iia = new ImageIcon("src/res/apple.png");
        apple = iia.getImage();
        
        ImageIcon iia1 = new ImageIcon("src/res/mine.png");
        mine = iia1.getImage();
        
    }
	
	public void startGame() {
		
		LocatenewApple();
		LocateNewMine1();
		LocateNewMine2();
		running = true;
		lives = 3;
		//difficulty is extracted from start screen input
		timer = new Timer(StartScreen.Difficulty,this);
		
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}
	//called in paint, draw function contains all in game graphics 
	public void draw(Graphics g) {
		
		if(running && lives !=0) {
			
			g.setColor(Color.RED);
			Font font = new Font("Arial", Font.BOLD, 15);
	    	g.setFont(font);
	    	g.drawString("Score: " + points , 170, HEIGHT/16);
	    	g.drawString("Lives: " + lives , 300, HEIGHT/16);
	    	
	    	
	    	g.drawImage(apple, appleXPOS, appleYPOS, this);
	    	g.drawImage(mine, rotAppleXPOS1, rotAppleYPOS1, this);
	    	g.drawImage(mine, rotAppleXPOS2, rotAppleYPOS2, this);
	    	
			
			for(int i = 0; i < BodySize; i++) {
				if(i == 0 ) {
					g.setColor(Color.BLACK);
					g.fillRect(x[i], y[i], unitSize , unitSize );
					g.setColor((StartScreen.headColor));
					g.fillRect(x[i], y[i], unitSize - 1, unitSize - 1);
					
				
		}else {
				g.setColor(Color.BLACK);
				g.fillRect(x[i], y[i], unitSize, unitSize);
				g.setColor(StartScreen.snakeColor);
				//g.setColor(Color.GREEN);
				g.fillRect(x[i], y[i], unitSize - 1, unitSize - 1);
		}
		}
		}else {
			
			youLose(g);
			
			
		}
	}
	//ensures the snake moves in set units instead of pixels
	public void move() {
		for(int i = BodySize; i> 0 ; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		//keeps the snake moving in the same direction thats pressed
		//as well as ensuring the snake directions don't compound 
		switch(direction) {
		case 'U' :
				y[0] = y[0] - unitSize;
				break;
		case 'D' :
				y[0] = y[0] + unitSize;
				break;
		case 'L' :
				x[0] = x[0] - unitSize;
				break;
		case 'R' :
				x[0] = x[0] + unitSize;	
				break;
		}
	}
	//finds random numbers within height and width for the new apple location
	public void LocatenewApple() {
		appleXPOS = random.nextInt((int)(WIDTH / unitSize)) * unitSize;
		appleYPOS = random.nextInt((int)(WIDTH / unitSize)) * unitSize;
	}
	
	//finds random numbers within height and width for the new rotten apple location
	public void LocateNewMine1() {
		rotAppleXPOS1 = random.nextInt((int)(WIDTH / unitSize)) * unitSize;
		rotAppleYPOS1 = random.nextInt((int)(WIDTH / unitSize)) * unitSize;

	}
	
	//finds random numbers within height and width for the new 2nd rotten apple location
	public void LocateNewMine2() {

		rotAppleXPOS2 = random.nextInt((int)(WIDTH / unitSize)) * unitSize;
		rotAppleYPOS2 = random.nextInt((int)(WIDTH / unitSize)) * unitSize;

	}
	//if apple collision happens then we add a body part, increase points,
	//and sets a new apple location
	public void checkApple() {
		if((x[0] == appleXPOS) && (y[0] == appleYPOS)) {
			BodySize ++;
			points ++;
			LocatenewApple();
		}
	}
	// if the rotten apple collision happens, then we decrease bodySize,
	//points, and lives by 1. Also finds new rotten apple location.
	public void checkrotApple() {
		if(((x[0] == rotAppleXPOS1) && (y[0] == rotAppleYPOS1))){
			BodySize --;
			points --;
			lives --;
			LocateNewMine1();
		}
		if(((x[0] == rotAppleXPOS2) && (y[0] == rotAppleYPOS2))){
			BodySize --;
			points --;
			lives--;
			LocateNewMine2();
		}
	}
	 
	public void Collisions() {
		//checks if the head of the snake collides with its body
		for(int i = BodySize; i > 0; i--) {
			if((x[0] == x[i]) && (y[0] == y[i])) {
				
				running = false;
			}
		}
		//check if the head of the snake touches borders
		if(x[0] < 0) {
			running = false;
		}
		if(x[0] > WIDTH) {
			running = false;
		}
		if(y[0] < 0) {
			running = false;
		}
		if(y[0] > HEIGHT) {
			running = false;
		}
		if(running == false) {
			timer.stop();
		}
		
	}
	//end screen graphics
	public void youLose(Graphics g) {
		
		g.setColor(Color.red);
    	Font font = new Font("Arial", Font.BOLD, 15);
    	g.setFont(font);
    	g.drawString("GAME OVER! Your score was " + points + " ", WIDTH/2 - 115, HEIGHT/2 - 50);
    	g.drawString("Play again? Press Spacebar", WIDTH/2 - 100, HEIGHT / 2);
    	g.setColor(Color.WHITE);
    	g.drawRect(WIDTH/2 - 108, HEIGHT/2 - 31, 210, 50);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkrotApple();
			Collisions();
		}
			repaint();
	}
	

	//if space bar is pressed then game restarts
	//if wasd is pressed the snake moves in that direction
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			for(int i = 0; i < BodySize; i++) {
				if(i == 0 ) {
				x[i] = 0;
				y[i] = 0;
				direction = 'R';
				move();
				}
			}
			startGame();
			//StartScreen.main(null);

			points = 0;
			BodySize = 5;
			repaint();
		}
		switch(e.getKeyCode()) {
		case KeyEvent.VK_A:
			if(direction != 'R') {
				direction = 'L';
			}
			break;
		case KeyEvent.VK_D:
			if(direction != 'L') {
				direction = 'R';
			}
			break;
		case KeyEvent.VK_W:
			if(direction != 'D') {
				direction = 'U';
			}
			break;
		case KeyEvent.VK_S:
			if(direction != 'U') {
				direction = 'D';
			}
			break;
			}
		}
		

	//these have to be here idk why
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	


	
}
