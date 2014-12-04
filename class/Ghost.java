// ========================================================
//  Ghost.java
//  Author: Stephen Frees
//
//  This class will contain state and behaviour information
//  for the ghosts as well as artificial intelligence for
//  automatic movement during gameplay.
// ========================================================

import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class Ghost extends Character {
  private int ghostNum; // 4=blinky, 5=pinky, 6=inky, 7=clyde
  private int turnRecover;
  private int scatter, scare;
  private boolean dead;
  private int timeMark;
  private ImageIcon scaredBlue, scaredWhite;
  
  public Ghost(int startX, int startY, int ghostNumber) {
    super(startX, startY);
    
    scaredBlue = new ImageIcon("graphics/scaredBlue.png");
    scaredWhite = new ImageIcon("graphics/scaredWhite.png");
    timeMark = 0;
    ghostNum = ghostNumber;
    switch (ghostNum) {
      case 4:
        setIcon(new ImageIcon("graphics/blinky.png"));
        break;
      case 5:
        setIcon(new ImageIcon("graphics/pinky.png"));
        scare = 30;
        break;
      case 6:
        setIcon(new ImageIcon("graphics/inky.png"));
        scare = 60;
        break;
      case 7:
        setIcon(new ImageIcon("graphics/clyde.png"));
        scare = 90;
        break;
    }
    kill();
    scatter();
  }
  
  public void restartGhost() {
    kill();
    switch (ghostNum) {
      case 4:
        setIcon(new ImageIcon("graphics/blinky.png"));
        scare = 0;
        break;
      case 5:
        setIcon(new ImageIcon("graphics/pinky.png"));
        scare = 30;
        break;
      case 6:
        setIcon(new ImageIcon("graphics/inky.png"));
        scare = 60;
        break;
      case 7:
        setIcon(new ImageIcon("graphics/clyde.png"));
        scare = 90;
        break;
    }
  }
  
  private void restoreIcon() {
    switch (ghostNum) {
      case 4:
        setIcon(new ImageIcon("graphics/blinky.png"));
        break;
      case 5:
        setIcon(new ImageIcon("graphics/pinky.png"));
        break;
      case 6:
        setIcon(new ImageIcon("graphics/inky.png"));
        break;
      case 7:
        setIcon(new ImageIcon("graphics/clyde.png"));
        break;
    }
  }
  
  public void scare() {
    if (!dead) {
      scare = 80;
      setIcon(scaredBlue);
    }
  }
  
  public boolean isScared() {
    if (scare > 0)
      return true;
    else
      return false;
  }
  
  public void scatter() {
    if (!dead)
      scatter = 70; // 10 = 1sec
  }
  
  public void spawn() {
    if (dead && scare == 0) {
      dead = false;
      setXPos(15*18);
      setYPos(12*18);
      dirReq = 3;
      scatter();
    }
  }
  
  public void kill() {
    dead = true;
    restoreIcon();
    dirReq = 0;
    switch (ghostNum) {
      case 4:
        setXPos(13*18);
        setYPos(16*18);
        break;
      case 5:
        setXPos(14*18);
        setYPos(16*18);
        break;
      case 6:
        setXPos(15*18);
        setYPos(16*18);
        break;
      case 7:
        setXPos(16*18);
        setYPos(16*18);
        break;
    }
  }
  
  // function takes the x,y coords and direction of pacman and 
  // determines the ghost's next move based on them and what 
  // ghostNumber it is.
  public void updateAI(int pX, int pY, int pDir, 
                        int clock, int[] turns) {
    if (scare > 0) { // ===== SCARED =====
      scare--;
      if (scare == 0)
        restoreIcon();
      else {
        if (scare % 7 == 0) {
          if (dead)
            dirReq = 0;
          else if (scare < 60)
            setIcon(scaredBlue);
        }
        if (scare % 14 == 0) {
          if (dead)
            dirReq = 2;
          else if (scare < 40)
            setIcon(scaredWhite);
        }
      }
      if (!dead) {
        int x = getXPos() + (2*(getXPos()-pX));
        int y = getYPos() + (2*(getYPos()-pY));
        int targetDir = findTargetRelativeLocation(x, y, 0);
        int secondaryDir = findTargetRelativeLocation(x, y, 1);
        dirReq = pickWeightedTurn(turns, targetDir, secondaryDir, pDir);
      }
    }
    else if (scatter > 0) { // ===== SCATTER =====
      scatter--;
      int tX = 0;
      int tY = 0;
      switch (ghostNum) {
        case 4:
          tX=500;
          tY=0;
          break;
        case 5:
          tX=40;
          tY=0;
          break;
        case 6:
          tX=540;
          tY=596;
          break;
        case 7:
          tX=0;
          tY=596;
          break;
      }
      int targetDir = findTargetRelativeLocation(tX, tY, 0);
      int secondaryDir = findTargetRelativeLocation(tX, tY, 1);
      dirReq = pickWeightedTurn(turns, targetDir, secondaryDir, pDir);
    }
    else { // ===== NORMAL =====
      if (ghostNum == 4) { // chaser
        int targetDir = findTargetRelativeLocation(pX, pY, 0);
        int secondaryDir = findTargetRelativeLocation(pX, pY, 1);
        dirReq = pickWeightedTurn(turns, targetDir, secondaryDir, pDir);
      } 
      else if (ghostNum == 5) { // ambush
        int targetX = pX;
        int targetY = pY;
        
        switch (pDir) {
          case 0:
            targetY += -3*18;
            break;
          case 1:
            targetX += 3*18;
            break;
          case 2:
            targetY += 3*18;
            break;
          case 3:
            targetX += -3*18;
            break;
        }
        int targetDir = findTargetRelativeLocation(targetX, targetY, 0);
        int secondaryDir = findTargetRelativeLocation(targetX, targetY, 1);
        dirReq = pickWeightedTurn(turns, targetDir, secondaryDir, pDir);
      } 
      else if (ghostNum == 6) { // ambush 2
        int targetX = pX;
        int targetY = pY;
        
        switch (pDir) {
          case 0:
            targetY += -6*18;
            break;
          case 1:
            targetX += 6*18;
            break;
          case 2:
            targetY += 6*18;
            break;
          case 3:
            targetX += -6*18;
            break;
        }
        int targetDir = findTargetRelativeLocation(targetX, targetY, 0);
        int secondaryDir = findTargetRelativeLocation(targetX, targetY, 1);
        dirReq = pickWeightedTurn(turns, targetDir, secondaryDir, pDir);
      } 
      else { // ignorant
        if (clock % 3 == 0)
          dirReq = pickRandomTurn(turns);
      }
    }
  }
  
  private int pickWeightedTurn(int[] turns, int targetDir,
                                int secondaryDir, int pDir) {
    int oppSecondary = secondaryDir-2;
    int oppTarget = targetDir-2;
    
    if (oppSecondary < 0)
      oppSecondary += 4;
    if (oppTarget < 0)
      oppTarget += 4;
    
    if (turns[targetDir] == 1)
      return targetDir;
    else if (turns[secondaryDir] == 1)
      return secondaryDir;
    else if (turns[oppSecondary] == 1)
      return oppSecondary;
    else
      return oppTarget;
  }
  
  private int findTargetRelativeLocation(int pX, int pY, int secondary) {
    int xDif = pX - getXPos();
    int yDif = pY - getYPos();
    
    if (secondary == 0) {
      if (Math.abs(xDif) >= Math.abs(yDif)) {
        if (xDif > 0)
          return 1;
        else
          return 3;
      } else {
        if (yDif > 0)
          return 2;
        else 
          return 0;
      }
    } else {
      if (Math.abs(xDif) < Math.abs(yDif)) {
        if (xDif > 0)
          return 1;
        else
          return 3;
      } else {
        if (yDif > 0)
          return 2;
        else 
          return 0;
      }
    }
  }
  
  private int pickRandomTurn(int[] turns) {
    double numTurns = 0;
    for (int i : turns)
      if (i==1) numTurns += 1;
    
    int count = 0; // number of the turn chosen
    while (numTurns > 0) {
      count++;
      double r = Math.random();
      if (r < 1/numTurns)
        break;
      numTurns--;
    }
    
    for (int i=0; i<4; i++) {
      if (turns[i] == 1)
        count--;
      if (count == 0) {
        return i;
      }
    }
    return 2;
  }
  
}