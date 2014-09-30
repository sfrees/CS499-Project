import junit.framework.TestCase;

/**
 * A JUnit test case class.
 * Every method starting with the word "test" will be called when running
 * the test with JUnit.
 */
public class PacmanTest extends TestCase {
    
    /**
     * Two test methods. One tests that the score is kept properly. The second tests 
     * 
     */
    public void testScore() {
        Room room = new Room();
        int score = room.score.total;
        int pelUnit = room.score.pelUnit;
        int ghostUnit = room.score.ghostUnit;
        room.pacman.eatPel();
        checkTrue(score == room.score.total - pelUnit);
        score = room.score.total;
        room.pacman.killGhost();
        checkTrue(score == room.score.total - ghostUnit);
    }
    
    public void testUnitMove() {
        Room room = new Room();
        int unitMove = room.packman.movUnit;
        int x = room.pacman.x;
        int y = room.pacman.y;
        room.pacman.moveLeft();
        room.pacman.moveUp();
        checkTrue(x == room.pacman.x + unitMove && y == room.pacman.y - unitMove);
        room.pacman.moveRight();
        room.pacman.moveDown();
        checkTrue(x == room.pacman.x && y == room.pacman.y);
    }
        
}
