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
  private static JLabel scoreLabel;
  
  private static JLayeredPane lp;
  
  private static Map map;
  private static Pacman pacman;
  private static int lives;
  private static int score;
  
  private static int clock = 0;
  

  public Room() {
    init();
    startGame();
  }
  
  // Called to initialize window framework.
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
    scoreLabel = new JLabel();
    scoreLabel.setSize(100,50);
    head.add(scoreLabel);
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
  }
  
  // Called to initialize game characters.
  private static void initCharacters() {
      pacman = new Pacman();
      chars.add(pacman);
      win.addKeyListener(pacman);
      
      //Fruit fruit = new Fruit();
  }
  
  // Used to determine if pacman is eligible to turn.
  // Eligibility is based on being in the middle of a tile.
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
  
  // Used to determine if pacman has run into a wall.
  // In other words, the tile immediately in front of him is a wall.
  private static boolean hasCollision() {
    if (centered()) {
      int i = (pacman.getXPos()+9)/18;
      int j = (pacman.getYPos()+9)/18;
      int dir = pacman.getDir();
      if (dir==0 && map.getTile(j-1,i).getContent()<=-1)
        return true;
      if (dir==1 && map.getTile(j,i+1).getContent()<=-1)
        return true;
      if (dir==2 && map.getTile(j+1,i).getContent()<=-1)
        return true;
      if (dir==3 && map.getTile(j,i-1).getContent()<=-1)
        return true;
    }
    return false;
  }
  
  // Used to determine if pacman is within a threshold of the center
  // of a tile.
  private static boolean centered() {
    if (pacman.getXPos()%18 <= 2 && pacman.getYPos()%18 <= 2)
      return true;
    else
      return false;
  }
  
  // Called to update components of Pacman.
  private static void updatePacman() {
    int speed = 1; // the magnitude of the position offset
    // update dir to reflect user requested direction
    if (canTurn()) {
      pacman.setDir(pacman.getDirReq());
      pacman.toggleIcon();
    }
    
    // teleport to opposite side if on edge
    if (pacman.getXPos() <= 18)
      pacman.setXPos(504);
    else if (pacman.getXPos() >= 504) 
      pacman.setXPos(18);
      
    if (!hasCollision()) {
      if (clock % 15 == 0) // toggle pacman icon when not stopped
        pacman.toggleIcon();
      
      // offset position to simulate movement in given direction
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
  
  // Called to update map components.
  public static void updateMap() {
    if (centered()) {
      int i = (pacman.getXPos()+9)/18;
      int j = (pacman.getYPos()+9)/18;
      Tile t = map.getTile(j,i);
      if (t.getContent() == 1)
        addScore(10);
      if (t.getContent() == 2)
        addScore(50);
      t.eatTile();
    }
  }
  
  public static void addScore(int n) {
      score += n;
      updateHUD();
  }
  
  public static void addLives(int n) {
      lives += n;
      updateHUD();
  }
  
  private static void updateHUD() {
      scoreLabel.setText("Score: " + score);
  }
  
  public static void gameOver() {
    //printScore();
    //if (exitGreeting())
  }
  
  // Called as the main loop of the program.
  public static void update() {
    clock++;
    if (clock >= 10000)
      clock = 0;
    
    updateMap();
    //map.repaint();
    pacman.repaint();
    updatePacman();
    pacman.repaint();
  }
  
  // Runs the main loop of the program.
  public static void startGame() {
    System.out.println("Start Game...");
    while(true) {
      update();
      try {
        Thread.sleep(7);
      } catch (Exception e) {}
    }
  }
  
}