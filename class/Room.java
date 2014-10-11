public class Room {
    
    public int lives;
    public int score;
    public static int SCORE = 10;
    

    public Room() {
        
    }
    
    private static void initMap() {
    
    }
    
    private static void initCharacters() {
        
        Fruit fruit = new Fruit();
        Pacman man = new Pacman();
    }
    
    public static void updateScore(int n) {
        this.score += SCORE;
    }
    
    public static void updateLives(int n) {
        this.lives += 1;
    }
    
    private static void updateScreen() {
        drawLives()
    }
    
    public static void gameOver() {
        printScore();
        if (exitGreeting())
            
    
    }
}