import java.awt.*;
import javax.swing.*;

public class GraphicsTest {
  
  public static void main(String[] args) {
    JFrame win = new JFrame("My Graphics Test");
    win.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    win.setSize(630, 720);
    win.setVisible(true);
    
    JLayeredPane lp = win.getLayeredPane();
    
    JPanel head = new JPanel();
    head.setSize(400,100);
    head.setBackground(Color.blue);
    lp.add(head, 0);
    
    JPanel body = new JPanel();
    body.setSize(500,500);
    body.setBackground(Color.black);
    lp.add(body, 0);
    
  }
}