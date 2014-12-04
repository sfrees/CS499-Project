// ========================================================
//  Map.java
//  Author: Stephen Frees
//  Date: 10/30/14
//
//  Map will handle the array of tiles that implement the 
//  form that the maze will take. It will read in a text
//  file that specifies the grid coordinates and build an
//  array of tiles that will be displayed in swing.
// ========================================================

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;
import java.io.*;

public class Map extends JPanel {
  private int sizex = 30;
  private int sizey = 33;
  private Tile[][] map = new Tile[sizey][sizex]; // array of tiles

  // begin build process
  public Map() {
    super(new GridLayout(33,30,0,0));
  }
  
  // begin initialization process
  public int[][] init(String filename) {
    int[][] a = readMapFile(filename);
    buildMap(a);
    paintMap();
    return a;
  }
  
  // read in the file and return array
  private int[][] readMapFile(String filename) {
    int [][] a = new int[sizey][sizex];
    try {
      Scanner scan = new Scanner(new File(filename)); // open file
      
      for (int j=0; j<sizey; j++) {
        for (int i=0; i<sizex; i++) {
          String s = scan.next();
          if (s.compareTo("-") == 0) // barrier
            a[j][i] = -2;
          else if (s.compareTo("=") == 0) // wall
            a[j][i] = -1;
          else if (s.compareTo("0") == 0) // blank
            a[j][i] = 0;
          else if (s.compareTo("1") == 0) // dot
            a[j][i] = 1;
          else if (s.compareTo("2") == 0) // energizer
            a[j][i] = 2;
          else if (s.compareTo("3") == 0) // fruit
            a[j][i] = 3;
          else if (s.compareTo("4") == 0) // ghost: Blinky
            a[j][i] = 4;
          else if (s.compareTo("5") == 0) // ghost: Pinky
            a[j][i] = 5;
          else if (s.compareTo("6") == 0) // ghost: Inky
            a[j][i] = 6;
          else if (s.compareTo("7") == 0) // ghost: Clyde
            a[j][i] = 7;
          else if (s.compareTo("8") == 0) // Pacman
            a[j][i] = 8;
          else
            System.err.println("Illegal character in map file.");
        }
      }
    
    } catch (Exception e) {
      System.err.println(e);
    }
    return a;
  }
  
  // Use the data in 'int[][] a' to determine the properties
  // of the tiles and build map. Search neighbours for wall type.
  private void buildMap(int[][] a) {
    for (int j=0; j<sizey; j++) {
      for (int i=0; i<sizex; i++) {
        map[j][i] = new Tile(9, 9);
        // characters
        if (a[j][i] >= 3)
          map[j][i] = new Tile(0,0); // blank
        
        // dots/spaces
        else if (a[j][i] == 0) 
          map[j][i] = new Tile(0,0);
        else if (a[j][i] == 1)
          map[j][i] = new Tile(1,0);
        else if (a[j][i] == 2)
          map[j][i] = new Tile(2,0);
        
        // barriers
        else if (a[j][i] == -2)
          map[j][i] = new Tile(-2,0);
          
        else { // walls
          // left
          if (a[j-1][i]==-1 && a[j+1][i]==-1 && a[j][i+1]>=0 && a[j][i-1]<=0)
            map[j][i] = new Tile(-1, 8);
          // right
          if (a[j-1][i]==-1 && a[j+1][i]==-1 && a[j][i-1]>=0 && a[j][i+1]<=0)
            map[j][i] = new Tile(-1, 4);
          // bottom
          if (a[j][i-1]<=-1 && a[j][i+1]<=-1 && a[j-1][i]>=0 && a[j+1][i]<=0)
            map[j][i] = new Tile(-1, 6);
          // top
          if (a[j][i-1]<=-1 && a[j][i+1]<=-1 && a[j+1][i]>=0 && a[j-1][i]<=0)
            map[j][i] = new Tile(-1, 2);
            
          // topleft
          if (a[j-1][i]==-1 && a[j][i-1]==-1 && a[j+1][i+1]>=0) {
            if (a[j+1][i]>=0 && a[j][i+1]>=0)
              map[j][i] = new Tile(-1, 1); // convex
            if (a[j+1][i]==-1 && a[j][i+1]==-1)
              map[j][i] = new Tile(-1, -1); // concave
          }
          // topright
          if (a[j-1][i]==-1 && a[j][i+1]==-1 && a[j+1][i-1]>=0) {
            if (a[j+1][i]>=0 && a[j][i-1]>=0)
              map[j][i] = new Tile(-1, 3); // convex
            if (a[j+1][i]==-1 && a[j][i-1]==-1)
              map[j][i] = new Tile(-1, -3); // concave
          }
          // bottomright
          if (a[j+1][i]==-1 && a[j][i+1]==-1 && a[j-1][i-1]>=0) {
            if (a[j-1][i]>=0 && a[j][i-1]>=0)
              map[j][i] = new Tile(-1, 5); // convex
            if (a[j-1][i]==-1 && a[j][i-1]==-1)
              map[j][i] = new Tile(-1, -5); // concave
          }
          // bottomleft
          if (a[j+1][i]==-1 && a[j][i-1]==-1 && a[j-1][i+1]>=0) {
            if (a[j-1][i]>=0 && a[j][i+1]>=0)
              map[j][i] = new Tile(-1, 7); // convex
            if (a[j-1][i]==-1 && a[j][i+1]==-1)
              map[j][i] = new Tile(-1, -7); // concave
          }
        }
      }
    }
  }
  
  // assign an icon based on the tile type
  public void paintMap() { 
    for (int j=0; j<sizey; j++) {
      for (int i=0; i<sizex; i++) {
        Tile t = map[j][i];
        ImageIcon icon = new ImageIcon("graphics/default.png");
        if (t.getContent() == -1) {
          if (t.getWallType() == -1)
            icon = new ImageIcon("graphics/topleft-.png");
          if (t.getWallType() == -3)
            icon = new ImageIcon("graphics/topright-.png");
          if (t.getWallType() == -5)
            icon = new ImageIcon("graphics/btmright-.png");
          if (t.getWallType() == -7)
            icon = new ImageIcon("graphics/btmleft-.png");
        
          if (t.getWallType() == 1)
            icon = new ImageIcon("graphics/topleft.png");
          if (t.getWallType() == 2)
            icon = new ImageIcon("graphics/top.png");
          if (t.getWallType() == 3)
            icon = new ImageIcon("graphics/topright.png");
          if (t.getWallType() == 4)
            icon = new ImageIcon("graphics/right.png");
          
          if (t.getWallType() == 5)
            icon = new ImageIcon("graphics/btmright.png");
          if (t.getWallType() == 6)
            icon = new ImageIcon("graphics/bottom.png");
          if (t.getWallType() == 7)
            icon = new ImageIcon("graphics/btmleft.png");
          if (t.getWallType() == 8)
            icon = new ImageIcon("graphics/left.png");
        } 
        else if (t.getContent() == -2)
          icon = new ImageIcon("graphics/barrier.png");
        else if (t.getContent() == 1)
          icon = new ImageIcon("graphics/dot.png");
        else if (t.getContent() == 2)
          icon = new ImageIcon("graphics/energizer.png");
        
        t.setIcon(icon);
        t.setOpaque(true);
        add(t);
      }
    }
    System.out.println(map[1][1].getWallType());
  }
  
  // Use x,y location to determine the tile in which that
  // coordinate resides.
  public Tile getTile(int x, int y) {
    //int i = (x+9)/18;
    //int j = (y+9)/18;
    
    //System.out.println(i + ", " + j);
    //System.out.println(map[j][i].getContent());
    
    return map[x][y];
  }
}