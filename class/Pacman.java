// ========================================================
//  Pacman.java
//  Author: Stephen Frees
//  Date: 10/30/14
//
//  Pacman will be a class used to contain state
//  information specific to the Pacman character.
// ========================================================

import javax.swing.*;
import java.awt.event.*;

public class Pacman extends JLabel implements KeyListener {
  private static int x;
  private static int y;
  private static int dir; // 0=up, 1=right, 2=down, 3=left
  private static int dirReq; // set to the direction that is being requested
  
  public Pacman() {
    super();
    // set default location
    x = 252;
    y = 432;
    dir = 3;
    dirReq = 3;
  }
  
  public static int getXPos() {
    return x;
  }
  
  public static int getYPos() {
    return y;
  }
  
  public static int getDir() {
    return dir;
  }
  
  public static int getDirReq() {
    return dirReq;
  }
  
  public static void setDir(int d) {
    dir = d;
  }
  
  public static void setXPos(int xPos) {
    x = xPos;
  }
  
  public static void setYPos(int yPos) {
    y = yPos;
  }

  
    @Override
  public void keyTyped(KeyEvent e) {
  
  }

  @Override
  public void keyPressed(KeyEvent e) {

      if (e.getKeyCode() == KeyEvent.VK_UP) {
          //System.out.println("Up key pressed");
          dirReq = 0;
      }
      else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
          //System.out.println("Right key pressed");
          dirReq = 1;
      }
      else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          //System.out.println("Down key pressed");
          dirReq = 2;
      }
      else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
          //System.out.println("Left key pressed");
          dirReq = 3;
      }

  }

  @Override
  public void keyReleased(KeyEvent e) {

      if (e.getKeyCode() == KeyEvent.VK_UP && dirReq == 0) {
          //System.out.println("Up key pressed");
          dirReq = dir;
      }
      else if (e.getKeyCode() == KeyEvent.VK_RIGHT && dirReq == 1) {
          //System.out.println("Right key pressed");
          dirReq = dir;
      }
      else if (e.getKeyCode() == KeyEvent.VK_DOWN && dirReq == 2) {
          //System.out.println("Down key pressed");
          dirReq = dir;
      }
      else if (e.getKeyCode() == KeyEvent.VK_LEFT && dirReq == 3) {
          //System.out.println("Left key pressed");
          dirReq = dir;
      }
  }
}