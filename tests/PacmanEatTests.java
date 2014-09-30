import static org.junit.Assert.*;
import org.junit.Test;

public class PacmanTest {
    
    @Test
    public void testEatDot() {
        Room room = new Room();
        int x = room.pacman.getX();
        int y = room.pacman.getY();
        if (room.hasDot(x,y))
            room.eatDot(x,y);
        assertFalse(room.hasDot(x,y))
    }
    
    @Test
    public void testEatFruit() {
        Room room = new Room();
        int x = room.pacman.getX();
        int y = room.pacman.getY();
        if (room.fruit.isActive()) {
            fruitX = room.fruit.getX();
            fruitY = room.fruit.getY();
            if (fruitX == x && fruitY == Y)
                room.eatFruit();
            assertFalse(room.fruit.isActive());
        }
    }
        
}
