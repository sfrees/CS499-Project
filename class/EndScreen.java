import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class EndScreen
{
    private static final Insets insets = new Insets(0, 0, 50, 0);
    public static boolean active, endGame, userWon;
    public static int score;
    
    public EndScreen(boolean usrWon, int score)
    {
        init();
        active = true;
        userWon = usrWon;
        this.score = score;
    }
    
    public static void init()
   {
      
      final JFrame frame = new JFrame("End Screen");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JPanel p = new JPanel();
      
      final PacmanDisplay display = new PacmanDisplay(30, 30, 540, 596);
      
      EndIcon title = new EndIcon(display, 0, 0, 540, 596, userWon, score);
      
      final JLabel label = new JLabel(title);
      final JButton start = new JButton("Restart");
      final JButton quit = new JButton("Quit");
      
      p.setLayout(new GridBagLayout());
      p.setBackground(Color.BLACK);
      frame.add(p);
      frame.setSize(540,596);
      frame.setTitle("PacMan");
      GridBagConstraints gbcS = new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0,
                     GridBagConstraints.CENTER, 0, insets, 0, 0);
      GridBagConstraints gbcE = new GridBagConstraints(0, 2, 2, 1, 1.0, 1.0,
                     GridBagConstraints.CENTER, 0, insets, 0, 0);
      
      p.add(quit, gbcE);
      p.add(start, gbcS);
      p.add(label);
      
      start.addActionListener(new ActionListener()
             {
                 public void actionPerformed(ActionEvent e)
                 {
                    if (e.getSource() == start) {
                      endGame = false;
                      active = false;
                      frame.dispose();
                      //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    } 
                 }    
              });
      
      quit.addActionListener(new ActionListener()
             {
                 public void actionPerformed(ActionEvent e)
                 {
                    if (e.getSource() == quit) {
                      endGame = true;
                      active = false;
                      frame.dispose();
                    }
                 }    
              });

      frame.pack();
      frame.setVisible(true);
      
   }
    
    public static void main(String[] args)
    {
        //StartScreen screen = new StartScreen();
    }
}