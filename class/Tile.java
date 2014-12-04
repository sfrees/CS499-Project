// ========================================================
//  Tile.java
//  Author: Stephen Frees
//  
//  A class to hold the state information necessary for the
//  tiles that will make up the playing field.
// ========================================================

import javax.swing.*;
import java.awt.*;

public class Tile extends JLabel {
  private int content; // -2=barrier -1=wall, 0=blank, 1=dot, 2=energizer
                       // 3-8=characters
  private int wallType; 
  // ======== wallType definitions ========
  // 0=none
  // negative=convex
  //    -1=topleft, -3=topright, -5=btmright, -7=btmleft
  // positive=concave
  //    1=topleft, 2=top, 3=topright, 4=right, 5=btmright
  //    6=btm, 7=btmleft, 8=left
  // ======================================
  
  public Tile(int content, int wallType) {
    super();
    this.content = content;
    this.wallType = wallType;
  }
  
  public int getContent() {
    return content;
  }
  
  public int getWallType() {
    return wallType;
  }
  
  public void eatTile() {
    if (content >= 1) {
      content = 0;
      ImageIcon icon = new ImageIcon("graphics/default.png");
      setIcon(icon);
      repaint();
    }
  }
}