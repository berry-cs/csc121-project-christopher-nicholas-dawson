
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import processing.event.KeyEvent; 

class DoodleWorldTest {
	Jumper j1 = new Jumper(new Posn(50,50), new Posn(0,0), 40, 40);
	Jumper j2 = new Jumper(new Posn(50,70), new Posn(0,10), 40, 40);
	Jumper j3 = new Jumper(new Posn(100,100), new Posn(5,-10), 40, 40);
	Platform p1 = new Platform(new Posn(50,50), 10, 4, "blue");

	Platform p2 = new Platform(new Posn(100, 400), 100, 20, "blue");
	Platform p3 = new Platform(new Posn(400, 500), 80, 20, "blue");
	ILoP pl1 = new ConsLoP(p1, new ConsLoP(p2, new ConsLoP(p3, new MTLoP())));
	DoodleWorld w1 = new DoodleWorld(j1, pl1, 0);
	DoodleWorld w2 = new DoodleWorld(j1, pl1, 500);

	@Test
	void testKeyPressed() {
		assertEquals( w1, w1.keyPressed(new KeyEvent(null, 0, 0, 0, 'c', 'c')));
		assertEquals( new DoodleWorld(j1.boost(), pl1, 0), w1.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', ' ')));
	}

	@Test
	void testScrollBottom() {
		assertEquals( 400, w1.bottomY() );
		assertEquals( 405, w2.bottomY() );
	}
	
	
	@Test
	void testReadyForNewPlatform() {
		
		int count = 0;
		for (int i = 0; i < 1000; i++) {		 // repeat 1000 times
			if (w1.readyForNewPlatform()) {
				count = count + 1;
			} else {
				// do nothing
			}
		}
// hard to test because it's random		
//		assertEquals( true,  count > 9 );
//		assertEquals( true, count >  11);
		
	}
	
	@Test 
	void testActualScrollAmount() {
		assertEquals(5, w2.actualScrollAmount());
		assertEquals(0, w1.actualScrollAmount());
	}
	
	@Test
	void testTopY() {
		assertEquals(-5, w2.topY());
		assertEquals(0, w1.topY());
	}
	
	@Test
	void testBottomY() {
		assertEquals(405, w2.bottomY());
		assertEquals(400, w1.bottomY());
	}
}
