import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.ImageIcon;
import java.io.*;
import javax.imageio.*;

public class PacmanDisplay
{
   public int x;
   public int y;
   public int width;
   public int height;
   
   public PacmanDisplay(int x, int y, int width, int height)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      this.height = height;
   }

   public void draw(Graphics2D g2)
   {
       BufferedImage img = null; try { img = ImageIO.read(new File("graphics/pacman.jpg")); }
       catch (IOException e) { }
       g2.drawImage(img, x, y, null);
   }
   
   
}
