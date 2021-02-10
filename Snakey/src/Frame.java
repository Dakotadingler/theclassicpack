import javax.swing.JFrame;

public class Frame {
	
	public Frame() {
		JFrame frame = new JFrame();
		Screen screen = new Screen();
		
		frame.add(screen);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setTitle("SNAKE");
    	
    	frame.setResizable(false);
    	frame.pack();
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true); 

	}

	public static void main(String[] args) {
		new Frame();

	}

}
