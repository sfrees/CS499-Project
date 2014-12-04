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
  private static JLabel scoreLabel, livesLabel, readyLabel;
  
  private static JLayeredPane lp;
  
  private static Map map;
  private static Pacman pacman;
  private static Ghost blinky, pinky, inky, clyde;
  private static int score;
  
  private static int clock = 0;
  private static int eventCount = 0;
  
  private static boolean scatter, scare, start;
  

  public Room() {
    init();
    startGame();
  }
  
  // Called to initialize window framework.
  private static void init() {
    win = new JFrame("Pacman In Swing");
    win.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    win.setSize(540,746);
    win.setResizable(false);
    win.setVisible(true);
    
    lp = win.getLayeredPane();
    
    main = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    main.setSize(540, 746);
    lp.add(main, 1);
    
    head = new JPanel();
    head.setPreferredSize(new Dimension(540,75));
    head.setBackground(Color.blue);
    scoreLabel = new JLabel();
    scoreLabel.setPreferredSize(new Dimension(200,50));
    scoreLabel.setFont(new Font(scoreLabel.getFont().getName(), Font.BOLD, 20));
    scoreLabel.setForeground(Color.white);
    scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
    livesLabel = new JLabel();
    livesLabel.setPreferredSize(new Dimension(200,50));
    livesLabel.setFont(new Font(livesLabel.getFont().getName(), Font.BOLD, 20));
    livesLabel.setForeground(Color.white);
    livesLabel.setHorizontalAlignment(SwingConstants.CENTER);
    head.add(scoreLabel);
    head.add(livesLabel);
    main.add(head);
    
    map = new Map();
    int[][] a = map.init("map.txt");
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
    
    readyLabel = new JLabel("READY!");
    readyLabel.setPreferredSize(new Dimension(200,21));
    readyLabel.setFont(new Font(readyLabel.getFont().getName(), Font.BOLD, 20));
    readyLabel.setForeground(Color.white);
    readyLabel.setHorizontalAlignment(SwingConstants.CENTER);
    readyLabel.setVisible(true);
    chars.add(readyLabel,0);
    
    initCharacters(a);
  }
  
  // Called to initialize game characters.
  private static void initCharacters(int[][] a) {
    for (int j=0; j<33; j++) {
      for (int i=0; i<30; i++) {
        if (a[j][i] == 4) { // blinky
          blinky = new Ghost(i*18, j*18, 4);
          chars.add(blinky);
          a[j][i] = 0;
        }
        else if (a[j][i] == 5) { // pinky
          pinky = new Ghost(i*18, j*18, 5);
          chars.add(pinky);
          a[j][i] = 0;
        }
        else if (a[j][i] == 6) { // inky
          inky = new Ghost(i*18, j*18, 6);
          chars.add(inky);
          a[j][i] = 0;
        }
        else if (a[j][i] == 7) { // clyde
          clyde = new Ghost(i*18, j*18, 7);
          chars.add(clyde);
          a[j][i] = 0;
        }
        else if (a[j][i] == 8) { // pacman
          pacman = new Pacman(i*18, j*18);
          chars.add(pacman);
          win.addKeyListener(pacman);
          a[j][i] = 0;
        }
      }
    }
      //Fruit fruit = new Fruit();
  }
  
  // Used to determine if Character is eligible to turn.
  // Eligibility is based on being in the middle of a tile.
  private static boolean canTurn(Character c) {
    int dir = c.getDir();
    int req = c.getDirReq();
    
    if (dir == req) // same direction
      return false;
    if ((dir-req)%2==0) // opposite direction
      return true;
    
    int i = (c.getXPos()+9)/18;
    int j = (c.getYPos()+9)/18;
     
    
    if (centered(c)) {
    
      if (req==0 && map.getTile(j-1,i).getContent()>=0)
        return true;
      if (req==1 && map.getTile(j,i+1).getContent()>=0)
        return true;
      if (req==2 && map.getTile(j+1,i).getContent()>=0)
        return true;
      if (req==3 && map.getTile(j,i-1).getContent()>=0)
        return true;
      
    }
    return false;
  }
  
  // Used to determine if Character has run into a wall.
  // In other words, the tile immediately in front of him is a wall.
  private static boolean hasCollision(Character c) {
    if (centered(c)) {
      int i = (c.getXPos()+9)/18;
      int j = (c.getYPos()+9)/18;
      int dir = c.getDir();
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
  
  // Used to determine if Character is within a threshold of the center
  // of a tile.
  private static boolean centered(Character c) {
    if (c.getXPos()%18 <= 2 && c.getYPos()%18 <= 2)
      return true;
    else
      return false;
  }
  
  private static int[] possibleTurns(Ghost g, int includeOpposite) {
    int dir = g.getDir();
    int[] turns = {0,0,0,0};
    
    int i = (g.getXPos()+9)/18;
    int j = (g.getYPos()+9)/18;
    
    if (map.getTile(j-1,i).getContent()>=0)
      turns[0] = 1;
    if (map.getTile(j,i+1).getContent()>=0)
      turns[1] = 1;
    if (map.getTile(j+1,i).getContent()>=0)
      turns[2] = 1;
    if (map.getTile(j,i-1).getContent()>=0)
      turns[3] = 1;
    
    //turns[dir] = 0; // don't count straight forward as a turn
    if (includeOpposite == 0)
      turns[(dir+2)%4] = 0; // don't count opposite direction as a turn
    
    return turns;
  }
  
  private static void updateGhosts() {
    Ghost[] ghosts = {blinky, pinky, inky, clyde};
    for (Ghost g : ghosts) {
    
      if (scatter) {
        g.scatter();
      }
      if (scare) {
        g.scare();
        eventCount = 0;
      }
      
      if (g.isScared()) {
        int difX = g.getXPos() - pacman.getXPos();
        int difY = g.getYPos() - pacman.getYPos();
        if ((difX*difX) + (difY*difY) <= 9) {
          g.kill();
          addScore((int) (200*Math.pow(2, eventCount)));
          eventCount++;
        }
      }
      else {
        int difX = g.getXPos() - pacman.getXPos();
        int difY = g.getYPos() - pacman.getYPos();
        if ((difX*difX) + (difY*difY) <= 324)
          killPacman();
      }
        
      if (centered(g))
        g.updateAI(pacman.getXPos(), pacman.getYPos(), 
                    pacman.getDir(), clock, possibleTurns(g, 0));
      int speed = 2; // the magnitude of the position offset
      // update dir to reflect ghost AI requested direction
      if (canTurn(g)) {
        g.setDir(g.getDirReq());
      }
      
      // teleport to opposite side if on edge
      if (g.getXPos() <= 18)
        g.setXPos(504);
      else if (g.getXPos() >= 504) 
        g.setXPos(18);
        
      if (!hasCollision(g)) {
        // offset position to simulate movement in given direction
        if (g.getDir() == 0) {
          g.setYPos(g.getYPos()-speed);
        } else if (g.getDir() == 1) {
          g.setXPos(g.getXPos()+speed);
        } else if (g.getDir() == 2) {
          g.setYPos(g.getYPos()+speed);
        } else {
          g.setXPos(g.getXPos()-speed);
        }
      }
      g.spawn();
      g.setLocation(new Point(g.getXPos(), g.getYPos()));
    }
    scatter = false;
    scare = false;
  }
  
  // Called to update components of Pacman.
  private static void updatePacman() {
    int speed = 3; // the magnitude of the position offset
    // update dir to reflect user requested direction
    if (canTurn(pacman)) {
      pacman.setDir(pacman.getDirReq());
      pacman.toggleIcon();
    }
    
    // teleport to opposite side if on edge
    if (pacman.getXPos() <= 18)
      pacman.setXPos(504);
    else if (pacman.getXPos() >= 504) 
      pacman.setXPos(18);
      
    if (!hasCollision(pacman)) {
      if (clock % 5 == 0) // toggle pacman icon when not stopped
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
    if (centered(pacman)) {
      int i = (pacman.getXPos()+9)/18;
      int j = (pacman.getYPos()+9)/18;
      Tile t = map.getTile(j,i);
      if (t.getContent() == 1)
        addScore(10);
      if (t.getContent() == 2) {
        addScore(50);
        scare = true;
      }
      t.eatTile();
      winCondition();
    }
  }
  
  public static void addScore(int n) {
    System.out.println(n + " points");
    score += n;
    updateHUD();
  }
  
  /*public static void addLives(int n) {
    lives += n;
    updateHUD();
  }*/
  
  private static void updateHUD() {
    scoreLabel.setText("Score: " + score);
    livesLabel.setText("Lives: " + pacman.getLives());
  }
  
  public static void killPacman() {
    try {
      refreshPositions();
      Thread.sleep(2000);
    } catch (Exception e) {}
    pacman.kill();
    updateHUD();
    System.out.println("Lost a life!");
    if (pacman.getLives() == 0)
      gameOver();
    
    restart();
  }
  
  public static void restart() {
    Ghost[] ghosts = {blinky, pinky, inky, clyde};
    for (Ghost g : ghosts) {
      g.restartGhost();
    }
    start = true;
  }
  
  public static void gameOver() {
    //printScore();
    //if (exitGreeting())
    System.out.println("Game Over.");
  }
  
  public static boolean winCondition() {
    for (int j=0; j<33; j++) {
      for (int i=0; i<30; i++) {
        Tile t = map.getTile(j,i);
        if (t.getContent() > 0)
          return false;
      }
    }
    System.out.println("You Win!");
    System.exit(1);
    return true;
  }
  
  // Called as the main loop of the program.
  public static void update() {
    clock++; // 50 = 1sec
    if (clock >= 1000000)
      clock = 0;
      
    if (clock % 1400 == 0 && clock < 6000)
      scatter = true;
    
    updateMap();
    updatePacman();
    updateGhosts();
    pacman.repaint();
  }
  
  private static void refreshPositions() {
    pacman.setLocation(new Point(pacman.getXPos(), pacman.getYPos()));
    Ghost[] ghosts = {blinky, pinky, inky, clyde};
    for (Ghost g : ghosts) {
      g.setLocation(new Point(g.getXPos(), g.getYPos()));
    }
  }
  
  // Runs the main loop of the program.
  public static void startGame() {
    System.out.println("Start Game...");
    int x = 0;
    start = true;
    try {
    
      while(true) {
        
        refreshPositions();
        x++;
        
        if (x >= 1000000)
          x = 0;
        if (x % 10 == 0) 
          update();
          
        Thread.sleep(2);
        if (start){
          start = false;
          refreshPositions();
          readyLabel.setLocation(new Point(9*18+9, 18*18));
          readyLabel.setVisible(true);
          Thread.sleep(2000);
          readyLabel.setVisible(false);
        }
      }
    } catch (Exception e) {}
  }
  
}