import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JumperTest {
	
	// positive y velocity means moving down
	
	Jumper j1 = new Jumper(new Posn(50,50), new Posn(0,0), 40, 40);
	Jumper j2 = new Jumper(new Posn(50,70), new Posn(0,10), 40, 40);
	Jumper j3 = new Jumper(new Posn(100,100), new Posn(5,-10), 40, 40);
	Platform p1 = new Platform(new Posn(50,50), 10, 4, "blue");
	ILoP pl1 = new ConsLoP(p1, new MTLoP());

	Jumper j4 = new Jumper(new Posn(100,400), new Posn(5,7), 40, 40);  // at bottom of the world

	
	@Test
	void moveTest() {
		assertEquals(new Jumper(new Posn(50,50), new Posn(0,.45), 40, 40),j1.move());
		assertEquals(new Jumper(new Posn(50,80), new Posn(0, 10.45), 40, 40),j2.move());
		assertEquals(new Jumper(new Posn(105,90), new Posn(5,-9.55), 40, 40), j3.move());
				
		assertEquals( new Jumper(new Posn(100,392), new Posn(0,-7.55), 40, 40),
					new Jumper(new Posn(100,400), new Posn(0,-8), 40, 40).move());
	}
	
	@Test
	void atBottomTest() {
		assertEquals(false, j1.atBottom(20));
		assertEquals(true, j4.atBottom(0));
	} 
	
	@Test
	void testGetPosition() {
		assertEquals(new Posn(50,50), j1.getPosition());
	}
	
	@Test
	void testTranslate() {
		assertEquals(new Jumper(new Posn(55, 55), new Posn(0,0), 40, 40), j1.translate(new Posn(5,5)));
	}
	
	@Test
	void testWithinXRange() {
		assertEquals(true, j1.withinXRange(p1));
		assertEquals(false, j3.withinXRange(p1));
	}
	
	@Test
	void testWithinYRange() {
		assertEquals(true, j1.withinYRange(p1));
		assertEquals(false, j3.withinYRange(p1));
	}
	
	@Test
	void testIsCollision() {
		assertEquals(true, j1.isCollision(p1));
		assertEquals(false, j3.isCollision(p1));
	}
	
	@Test
	void testIsCollisionLop() {
		assertEquals(true, j1.isCollisionlop(pl1));
		assertEquals(false, j3.isCollisionlop(pl1));
	}
	
	@Test
	void testCollider() {
		assertEquals(new Jumper(new Posn(50,50), new Posn(0,0), 40, 40), j1.Collider(p1));
		assertEquals(new Jumper(new Posn(105, 90), new Posn(5, -9.55), 40, 40), j3.Collider(p1));
	}
	
	
	//@Test
/*	void boostTest() {
		assertEquals( new Jumper(new Posn(50,50), new Posn(0,-8), 40, 40)  , j1.boost() );     
		assertEquals( new Jumper(new Posn(50, 80), new Posn(0, 2), 40, 40)  , j2.boost() );
		
		assertEquals( new Jumper(new Posn(100,400), new Posn(0,-8), 40, 40), 
				new Jumper(new Posn(100,400), new Posn(0,0), 40, 40).boost() );

		// i.e. boost() changes the velocity only  (adds 10 to the y part)
	}*/
	
	
	
//	@Test
//	void Jumptest() {
		//assertEquals(j1.loc.translate(j1.vel.translate(new Posn(0,10))), new Posn(50,60));
		//assertEquals(j3.loc.translate(j3.vel.translate(new Posn(0,10))), new Posn(100,100));
		//assertEquals(j2.loc.translate(j2.vel.translate(new Posn(0,10))), new Posn(50,90));
	

}
