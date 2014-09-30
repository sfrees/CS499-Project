package pacman;

import static org.junit.Assert.*;
import org.junit.Test;
//Anthony Quesenberry
//CS499 functionality test
//
//tests to see if when pacman touches
//a ghost that he dies as expected.
//
public class pacghosttest {
	

	@Test
	public void test() {
		Room room = new Room();
		room.spawnPacman();
		room.spawnGhosts();
		if(room.getPacPosition() == room.getGhostPosition())
			room.killPacman();
		
	}

}
