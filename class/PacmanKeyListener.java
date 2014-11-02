import java.awt.event.*;

public class PacmanKeyListener implements KeyListener {
  public PacmanKeyListener() {
    super();
  }

    @Override
  public void keyTyped(KeyEvent e) {
      
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          System.out.println("Right key typed");
      }
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          System.out.println("Left key typed");
      }

  }

  @Override
  public void keyPressed(KeyEvent e) {

      System.out.println("HEY");
      System.out.println(e.getKeyCode());
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          System.out.println("Right key pressed");
      }
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          System.out.println("Left key pressed");
      }

  }

  @Override
  public void keyReleased(KeyEvent e) {
      if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          System.out.println("Right key Released");
      }
      if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          System.out.println("Left key Released");
      }
  }
}