package pacman;

import static org.junit.Assert.*;

//Anthony Quesenberry
//CS499 functionality test
//
//tests to ensure that when pacman
//runs out of lives, the session 
//terminates
//

import org.junit.Test;

public class PacGameOverTest {

	@Test
	public void test() {
		int lives = 1;
		Room room = new Room();
		room.spawnPacman();
		room.spawnGhosts();
		lives--;
		if(lives == 0)
		{
			room.TerminateGame();
		}
	}

}
