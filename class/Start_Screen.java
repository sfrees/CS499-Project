import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Start_Screen extends JFrame implements ActionListener{
	
	private JFrame Start;
	private JButton begin;
	private JButton end;
	
	public Start_Screen(){
		//Creates the frame
	    Start = new JFrame("PacMan Start");
	    Start.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
	    Start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Start.setSize(400,400);
	    Start.setResizable(false);
	    Start.setVisible(true);	
			
		//creates the start button
	    begin = new JButton("Start");
	    begin.setBackground(Color.yellow);
	    begin.setBounds(130,130,150,50);
	    begin.setFont(new Font(null, Font.BOLD, 50));    
	    begin.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	    begin.addActionListener(this);
	    add(begin);
	    
	    //creates the quit button
	    end = new JButton("Quit");
	    end.setBackground(Color.yellow);
	    end.setBounds(180,180,50,25);
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
	    start.add(StartButtons, 1);	
	}
	public void actionPerformed(ActionEvent e) {	
		if(e.getSource() == begin){
			//Can't figure out how to make the game to start by calling the room
			//using what is below the program will lock up.
			//Room r = new Room();
			System.exit(0);
		}else if(e.getSource() == end){
			Start.dispose();
		}
	}
}
