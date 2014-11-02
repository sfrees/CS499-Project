// ========================================================
//  Room.java
//  Author: Stephen Frees
//  
//  The room is where the logic takes place. All swing
//  initialization will begin here as well as collision 
//  detection and ghost AI.
// ========================================================

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Room {
  
  private static JFrame win;
  private static JPanel head, main, foot, chars;
  
  private static JLayeredPane lp;
  
  private static Map map;
  private static Pacman pacman;
  private static int lives;
  private static int score;
  

  public Room() {
    init();
    startGame();
  }
  
  private static void init() {
    win = new JFrame("My Graphics Test");
    win.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    win.setSize(540,746);
    win.setResizable(false);
    win.setVisible(true);
    
    lp = win.getLayeredPane();
    
    main = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    main.setSize(540, 746);
    main.setBackground(Color.red);
    lp.add(main, 1);
    
    head = new JPanel();
    head.setPreferredSize(new Dimension(540,75));
    head.setBackground(Color.blue);
    main.add(head);
    
    map = new Map();
    map.init("map.txt");
    map.setPreferredSize(new Dimension(540,596));
    map.setBackground(Color.black);
    main.add(map);
    
    foot = new JPanel();
    foot.setPreferredSize(new Dimension(540,75));
    foot.setBackground(Color.blue);
    main.add(foot);
    
    chars = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
    chars.setSize(520,596);
    chars.setLocation(0,75);
    chars.setOpaque(false);
    lp.add(chars,0);
    
    initCharacters();
    map.updateUI();
    chars.updateUI();
  }
  
  private static void initCharacters() {
      pacman = new Pacman();
      pacman.setPreferredSize(new Dimension(18,18));
      pacman.setOpaque(true);
      pacman.setIcon(new ImageIcon("graphics/pacman.png"));
      chars.add(pacman);
      win.addKeyListener(pacman);
      
      //Fruit fruit = new Fruit();
  }
  
  private static void updatePacman() {
    int speed = 2;
    if (canTurn()) {
      pacman.setDir(pacman.getDirReq());
    }
    if (!hasCollision()) {
      if (pacman.getDir() == 0) {
        pacman.setYPos(pacman.getYPos()-speed);
      } else if (pacman.getDir() == 1) {
        pacman.setXPos(pacman.getXPos()+speed);
      } else if (pacman.getDir() == 2) {
        pacman.setYPos(pacman.getYPos()+speed);
      } else {
        pacman.setXPos(pacman.getXPos()-speed);
      }
    }
    pacman.setLocation(new Point(pacman.getXPos(), pacman.getYPos()));
  }
  
  private static boolean canTurn() {
    int dir = pacman.getDir();
    int req = pacman.getDirReq();
    
    if (dir == req) // same direction
      return false;
    if ((dir-req)%2==0) // opposite direction
      return true;
    
    int i = (pacman.getXPos()+9)/18;
    int j = (pacman.getYPos()+9)/18;
     
    
    if (centered()) {
    
      if (req==0 && map.getTile(j-1,i).getContent()!=-1)
        return true;
      if (req==1 && map.getTile(j,i+1).getContent()!=-1)
        return true;
      if (req==2 && map.getTile(j+1,i).getContent()!=-1)
        return true;
      if (req==3 && map.getTile(j,i-1).getContent()!=-1)
        return true;
      
    }
    return false;
  }
  
  private static boolean hasCollision() {
    if (centered()) {
      int i = (pacman.getXPos()+9)/18;
      int j = (pacman.getYPos()+9)/18;
      int dir = pacman.getDir();
      if (dir==0 && map.getTile(j-1,i).getContent()==-1)
        return true;
      if (dir==1 && map.getTile(j,i+1).getContent()==-1)
        return true;
      if (dir==2 && map.getTile(j+1,i).getContent()==-1)
        return true;
      if (dir==3 && map.getTile(j,i-1).getContent()==-1)
        return true;
    }
    return false;
  }
  
  private static boolean centered() {
    if (pacman.getXPos()%18 <= 2 && pacman.getYPos()%18 <= 2)
      return true;
    else
      return false;
  }
  
  public static void updateMap() {
    if (centered()) {
      int i = (pacman.getXPos()+9)/18;
      int j = (pacman.getYPos()+9)/18;
      Tile t = map.getTile(j,i);
      t.eatTile();
    }
  }
  
  public static void updateScore(int n) {
      score += n;
  }
  
  public static void updateLives(int n) {
      lives += n;
  }
  
  private static void updateScreen() {
      //drawLives();
  }
  
  public static void gameOver() {
    //printScore();
    //if (exitGreeting())
  }
  
  public static void update() {
    updateMap();
    map.repaint();
    
    updatePacman();
    pacman.repaint();
  }
  
  
  
  public static void startGame() {
    System.out.println("Start Game...");
    while(true) {
      update();
      try {
        Thread.sleep(10);
      } catch (Exception e) {}
    }
  }
  
  
  
}