// ========================================================
//  Character.java
//  Author: Stephen Frees
//  Date: 11/04/14
//
//  Character will be the parent object for characters such
//  as the ghosts and pacman.
// ========================================================

import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class Character extends JLabel {
  protected int x;
  protected int y;
  protected int dir; // 0=up, 1=right, 2=down, 3=left
  protected int dirReq; // set to the direction that is being requested
  
  public Character(int startX, int startY) {
    super();
    // set default location
    x = startX;
    y = startY;
    dir = 3;
    dirReq = 3;
    setPreferredSize(new Dimension(18,18));
    setOpaque(true);
  }
  
  public int getXPos() {
    return x;
  }
  
  public int getYPos() {
    return y;
  }
  
  public int getDir() {
    return dir;
  }
  
  public int getDirReq() {
    return dirReq;
  }
  
  public void setDir(int d) {
    dir = d;
  }
  
  public void setXPos(int xPos) {
    x = xPos;
  }
  
  public void setYPos(int yPos) {
    y = yPos;
  }
  
}