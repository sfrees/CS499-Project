import static org.junit.Assert.*;

import org.junit.Test;
//Eric Ching

public class Boundary_Check {

	@Test
	public void Boarder_test() {
		//Just checking for the outer most boarders
		int PacMan_Position = PacMan.getPosition(); //Gets PacMan's x and y coordinate
		//Assuming that the boarder is of size 10
		int Wall1 = boundary.getBound1(); //The top wall, x = (0,9),y=9
		int Wall2 = boundary.getBound2(); //The bottom wall, x=(0,9), y=0
		int Wall3 = boundary.getBound3(); //The left wall, x=0, y=(0,9)
		int Wall4 = boundary.getBound4(); //The right wall, x=9, y=(0,9)
		
		//potentially, check which wall PacMan is closest to then only perform one of the if statements
		if(PacMan_Position == Wall1){
			//move PacMan down one space
		}
		if(PacMan_Position == Wall2){
			//move PacMan up one space
		}
		if(PacMan_Position == Wall3){
			//move PacMan right one space
		}
		if(PacMan_Position == Wall4){
			//move PacMan left one space
		}	
	}

}
