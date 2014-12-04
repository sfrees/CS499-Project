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
import java.awt.Dimension;

public class Pacman extends Character implements KeyListener {
  private boolean closed = true;
  private int lives;
  private int sX, sY;
  
  private ImageIcon p = new ImageIcon("graphics/pacman.png");
  private ImageIcon p0 = new ImageIcon("graphics/pacman0.png");
  private ImageIcon p1 = new ImageIcon("graphics/pacman1.png");
  private ImageIcon p2 = new ImageIcon("graphics/pacman2.png");
  private ImageIcon p3 = new ImageIcon("graphics/pacman3.png");
  
  public Pacman(int startX, int startY) {
    super(startX, startY);
    sX = startX;
    sY = startY;
    setIcon(p3);
    lives = 3;
  }
  
  public void kill() {
    lives--;
    setXPos(sX);
    setYPos(sY);
    setDir(3);
    dirReq = 3;
    setIcon(p3);
  }
  
  public int getLives() {
    return lives;
  }
  
  public void toggleIcon() {
    if (closed){
      closed = false;
      switch (dir) {
        case 0:
          setIcon(p0);
          break;
        case 1:
          setIcon(p1);
          break;
        case 2:
          setIcon(p2);
          break;
        case 3:
          setIcon(p3);
          break;
      }
    }else {
      setIcon(p);
      closed = true;
    }
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