import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.ImageIcon;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Title
{
    public int x;
    public int y;
    public int height;
    public int width;
    
    public Title(int xLoc, int yLoc, int width, int height)
    {
        this.x = xLoc;
        this.y = yLoc;
        this.width = width;
        this.height = height;
    
    }
    
    public void draw(Graphics2D g2)
    {
      g2.setColor(Color.WHITE);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
      Font font = new Font("MS Gothic", Font.PLAIN, 15);
      g2.setFont(font);
      g2.drawString("by Stephen Frees, Anthony Quesenberry, Michael Hellenga, Eric Ching", x, y);
    }
}