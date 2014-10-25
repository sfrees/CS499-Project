import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
// I wasn't trying to go the whole 9 yards
// I just wanted to get something moving in a JFrame
// Here is the link to the video to do this
// https://www.youtube.com/watch?v=Km81XyczqC4&list=UUG6ZJvd_7ZlKJG_rXjvg4Eg&index=6
public class Room extends JPanel implements ActionListener, KeyListener{

	Timer t = new Timer(5, this); //Used to reprint the image after it starts moving
	int x = 0, y = 0, speedX = 0, speedY = 0; 
	// x and y are the position, speed is the speed of the object on the different axes

	public Room(){
		t.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false); //used to disable shift and tab
	}
	public void paintComponent(Graphics PacMan){
		super.paintComponent(PacMan);
		PacMan.setColor(Color.YELLOW);
		PacMan.fillOval(x, y, 50, 50);
	}
	public void actionPerformed(ActionEvent e) {
		if (x < 0){
			speedX = 0;
			x = 0;
		}
		if (x > 230){
			speedX = 0;
			x = 230;
		}
		if (y < 0){
			speedY = 0;
			y = 0;
		}
		if (y > 230){
			speedY = 0;
			y = 230;			
		}
		//above section is used to prevent dot from moving outside of the boundary	
		x = x + speedX;
		y = y + speedY;
		repaint();
	}
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode(); //gets the key commands codes
		if (c == KeyEvent.VK_UP){
			speedX = 0;
			speedY = -1;
		}
		if (c == KeyEvent.VK_DOWN){
			speedX = 0;
			speedY = 1;
		}
		if (c == KeyEvent.VK_LEFT){
			speedX = -1;
			speedY = 0;
		}
		if (c == KeyEvent.VK_RIGHT){
			speedX = 1;
			speedY = 0;
		}
	}
	public void keyTyped(KeyEvent e) {
		//just for filler
	}
	public void keyReleased(KeyEvent e) {
		speedX = 0;
		speedY = 0;
		//stops the dot from moving when arrow key release
	}

	public static void main(String args[]){
		Room q = new Room();
		JFrame room = new JFrame("Incomplete PacMan");
		room.setSize(300, 300);
		room.setVisible(true);
		room.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		room.add(q);
		}
}
