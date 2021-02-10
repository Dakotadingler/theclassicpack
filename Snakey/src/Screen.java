
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;
 
public class Screen extends JPanel implements Runnable, KeyListener {
 
    private static final long serialVersionUID = 1L;
    public static final int WIDTH = 300, HEIGHT = 300;
    private Thread thread;
    private boolean running = false;
 
    private BodyPart b;
    private ArrayList<BodyPart> snake;
    private Apple apple;
    private ArrayList<Apple> apples;
    
    private int points = 0;
    private int xCoor = 10, yCoor = 10;
    private int size = 7;
    
    private int HARD = 450000;
    private int MEDIUM = 650000;
    private int EASY = 850000;
    private boolean rightkey = true, leftkey = false, upkey = false, downkey =false;
    
    
    private enum STATE{
    	MENU,
    	GAME,
    	SETTINGS
    };
    private STATE State = STATE.MENU;

    
    private int ticks = 0;
    
    public Screen() {
    	setFocusable(true);	
    	addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();
        start();
    }

    public void tick() {
    	
        if (snake.size() == 0) {
            b = new BodyPart(xCoor , yCoor, 10);
            snake.add(b);
        }
        if(apples.size() == 0) {
        	int xCoor = ThreadLocalRandom.current().nextInt(3, (WIDTH/10) - 3);
        	int yCoor = ThreadLocalRandom.current().nextInt(3, (HEIGHT/10) - 3);
        	
        	apple = new Apple(xCoor, yCoor, 10);
        	apples.add(apple);
        }
        for(int i = 0; i < apples.size(); i++) {
        	if(xCoor == apples.get(i).getxCoor() && 
        			yCoor == apples.get(i).getyCoor()) {
        		size++;
        		points++;
        		apples.remove(i);
        		i++;
        	}
        }
        for(int i =0; i < snake.size(); i++) {
        	if(xCoor == snake.get(i).getxCoor() && 
        			yCoor == snake.get(i).getyCoor()) {
        		if(i != snake.size() - 1) {
        			
        			stop();	
        		}
        	}
        }
        if(xCoor < 2 || xCoor > (WIDTH/10) - 3 || yCoor < 2 || yCoor > (WIDTH/10) - 3) {
        	stop();
        }
        ticks++;
        
        if(ticks > EASY) {
        	if(rightkey) xCoor++;
        	if(leftkey) xCoor--;
        	if(upkey) yCoor--;
        	if(downkey) yCoor++;
        	
        	ticks = 0;
        	
        	b = new BodyPart(xCoor, yCoor, 10);
        	snake.add(b);
        	
        	if(snake.size() > size) {
        		snake.remove(0);
        	}
        }
    }
    
    public void Lose(Graphics g) {
    	g.setColor(Color.red);
    	Font font = new Font("Arial", Font.BOLD, 30);
    	g.setFont(font);
    	g.drawString("YOU LOSE TRY AGAIN", WIDTH/2, HEIGHT/2);
    }
 
    public void paint(Graphics g) {
    	
    	//g.clearRect(0, 0, WIDTH, HEIGHT);
    	//g.setColor(Color.GREEN);
    	//g.clearRect(0, 0, WIDTH, HEIGHT);
    	g.setColor(Color.GREEN);
    	g.fillRect(15, 15, WIDTH - 30, HEIGHT - 30);
    	g.setColor(Color.BLACK);
    	g.fillRect(20, 20, WIDTH-40, HEIGHT-40);
    	g.setColor(Color.red);
    	
    	Font font = new Font("Arial", Font.BOLD, 16);
    	g.setFont(font);
    	g.drawString("Score: " + points, WIDTH/2 - 36, 17);
 
        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }
        for(int i = 0; i < apples.size(); i++) {
        	apples.get(i).draw(g);
        }
    }
 
    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
 
    public void stop() {
    	
    	running = false;
    	try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    

    
	
    public void run() {
        while (running) {
        		tick();
        		repaint();
		}	
    }
    

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_D && !leftkey) {
			upkey = false;
			downkey = false;
			rightkey = true;
		}
		if(key == KeyEvent.VK_A && !rightkey) {
			upkey = false;
			downkey = false;
			leftkey = true;
		}
		if(key == KeyEvent.VK_W && !downkey) {
			leftkey = false;
			rightkey = false;
			upkey = true;
		}
		if(key == KeyEvent.VK_S && !upkey) {
			leftkey = false;
			rightkey = false;
			downkey = true;
		}
	}

	public void keyReleased(KeyEvent arg0) {	
	}
	public void keyTyped(KeyEvent arg0) {	
	}    
}
