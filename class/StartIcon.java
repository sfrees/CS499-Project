import java.awt.*;
import java.util.*;
import javax.swing.*;

public class StartIcon implements Icon
{
   private int width;
   private int height;
   private int x;
   private int y;
   private PacmanDisplay display;
   
   public StartIcon(PacmanDisplay display, int x, int y, int height, int width)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
      this.display = display;
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
      Title title = new Title(25, 500, 100, 30);
      title.draw(g2);
      display.draw(g2);
   }

}

