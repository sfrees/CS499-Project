import java.awt.*;
import java.util.*;
import javax.swing.*;

public class EndIcon implements Icon
{
   private int width;
   private int height;
   private int x;
   private int y;
   private PacmanDisplay display;
   private boolean win;
   private int score;
   
   public EndIcon(PacmanDisplay display, int x, int y, int height, int width,
                    boolean win, int score)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.display = display;
      this.win = win;
      this.score = score;
   }
   
   public int getIconWidth()
   {
      return width;
   }

   public int getIconHeight()
   {
      return height;
   }

   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D) g;
      Title2 title;
      if (win) 
        title = new Title2(25, 500, 100, 30, win, score);
      else
        title = new Title2(25, 500, 100, 30, win, score);
      title.draw(g2);
      display.draw(g2);
   }

}

