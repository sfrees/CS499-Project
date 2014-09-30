import static org.junit.Assert.*;

import org.junit.Test;
//Eric Ching

public class Check_Warp_Zone {

	@Test
	public void test() {
		int PacMan_Position = PacMan.getPosition(); //Gets PacMan's x and y coordinate
		//Assuming that the warping area is around (3,6)
		int WarpZone1 = boundary.getWarpZone1(); //Gets the left warp zone; x=0,y=(3,6)
		int WarpZone2 = boundary.getWarpZone2(); //Gets the right warp zone; x=9, y=(3,6)
		
		if(PacMan_Position == WarpZone1){
			//Move PacMan to WarpZone2, keep the y coordinate the same but change the x coordinate
		}
		if(PacMan_Position == WarpZone2){
			//Move PacMan to WarpZone1, keep the y coordinate the same but change the x coordinate
		}
		//Could potentially embed this test within the boundary checker
	}

}
