import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Start_Screen extends JFrame implements ActionListener{
	
	private JFrame Start;
	private JButton begin;
	private JButton end;
  
  public boolean active;
	
	public Start_Screen(){
    active = true;
  
		//Creates the frame
	    Start = new JFrame("Simple Start");
	    Start.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
	    Start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Start.setSize(400,400);
	    Start.setResizable(false);
	    Start.setVisible(true);	
			
	    //Create the title
	    JLabel label = new JLabel("PacMan");
	    label.setForeground(Color.yellow);
	    label.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	    label.setFont(new Font(null, Font.BOLD, 75));
	    label.setBounds(60, 50, 290, 80);
	   
	    //Simple display of lives
	    JLabel lives = new JLabel("Lives: 3");
	    lives.setForeground(Color.black);
	    lives.setFont(new Font(null, Font.BOLD, 20));
	    lives.setBounds(170, 275, 100, 100);
	    
		//creates the start button
	    begin = new JButton("Start");
	    begin.setBackground(Color.yellow);
	    begin.setBounds(130,200,150,50);
	    begin.setFont(new Font(null, Font.BOLD, 40));    
	    begin.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	    begin.addActionListener(this);
	    add(begin);
	    
	    //creates the quit button
	    end = new JButton("Quit");
	    end.setBackground(Color.yellow);
	    end.setBounds(180,250,50,25);
	    end.setFont(new Font(null, Font.BOLD, 20));
	    end.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	    end.addActionListener(this);
	    add(end);
	    
	    JLayeredPane start = Start.getLayeredPane();
	    JPanel StartButtons = new JPanel();
	    //Sets the position by hand --> setlayout(null)
	    StartButtons.setLayout(null);
	    StartButtons.setSize(400, 400);
	    StartButtons.setBackground(Color.blue);
	    StartButtons.add(begin);  
	    StartButtons.add(end);
	    StartButtons.add(label);
	    StartButtons.add(lives);
	    start.add(StartButtons);	
	}
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == begin){
			//Can't figure out how to make the game to start by calling the room
			//using what is below the program will lock up.
			//Room r = new Room();//Will lock up on my system
			Start.dispose();
      active = false;
		}else if(e.getSource() == end){
			System.exit(0);
			//Start.dispose();
		}
	}
}
