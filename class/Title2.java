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

public class Title2
{
    public int x;
    public int y;
    public int height;
    public int width;
    public boolean win;
    public int score;
    
    public Title2(int xLoc, int yLoc, int width, int height, boolean win, int score)
    {
        this.x = xLoc;
        this.y = yLoc;
        this.width = width;
        this.height = height;
        this.win = win;
        this.score = score;
    
    }
    
    public void draw(Graphics2D g2)
    {
      g2.setColor(Color.WHITE);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
      Font font = new Font("MS Gothic", Font.PLAIN, 25);
      g2.setFont(font);
      if (win) {
        g2.drawString("You Win!", x, y);
      }
      else {
        g2.drawString("You Lose.", x, y);
      }
    }
}