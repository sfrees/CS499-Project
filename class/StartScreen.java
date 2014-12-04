import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartScreen
{
    private static final Insets insets = new Insets(0, 0, 50, 0);
    public static boolean active;
    
    public StartScreen()
    {
        init();
        active = true;
    }
    
    public static void init()
   {
      
      final JFrame frame = new JFrame("Start Screen");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      JPanel p = new JPanel();
      
      final PacmanDisplay display = new PacmanDisplay(30, 30, 540, 596);
      
      StartIcon title = new StartIcon(display, 0, 0, 540, 596);
      
      final JLabel label = new JLabel(title);
      JButton start = new JButton("Start");
      
      p.setLayout(new GridBagLayout());
      p.setBackground(Color.black);
      frame.add(p);
      frame.setSize(540,596);
      frame.setTitle("PacMan");
      GridBagConstraints gbc = new GridBagConstraints(0, 1, 2, 1, 1.0, 1.0,
                     GridBagConstraints.CENTER, 0, insets, 0, 0);
      
      p.add(start, gbc);
      p.add(label);
      
      start.addActionListener(new ActionListener()
             {
                 public void actionPerformed(ActionEvent e)
                 {
                      //System.out.println("test");
                      active = false;
                      frame.dispose();
                      //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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