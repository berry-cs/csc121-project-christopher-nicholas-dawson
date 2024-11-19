import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JumperTest {
	
	// positive y velocity means moving down
	
	Jumper j1 = new Jumper(new Posn(50,50), new Posn(0,0), 40, 40);
	Jumper j2 = new Jumper(new Posn(50,70), new Posn(0,10), 40, 40);
	Jumper j3 = new Jumper(new Posn(100,100), new Posn(5,-10), 40, 40);
	Platform p1 = new Platform(new Posn(50,50), 10, 4, "blue");
	LoX<Platform> pl1 = new Cons<Platform>(p1, new MT<Platform>());

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
		assertEquals(true, j1.withinXRangePla(p1));
		assertEquals(false, j3.withinXRangePla(p1));
	}
	
	@Test
	void testWithinYRange() {
		assertEquals(true, j1.withinYRangePla(p1));
		assertEquals(false, j3.withinYRangePla(p1));
	}
	
	@Test
	void testIsCollision() {
		assertEquals(true, j1.isCollisionPla(p1));
		assertEquals(false, j3.isCollisionPla(p1));
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
	
	@Test
	void testBoost() {
		assertEquals(new Jumper(new Posn(50, 50), new Posn(0, -8.55), 40, 40), j1.boost());
	}
	
	@Test
	void testTranslateVel() {
		assertEquals(new Jumper(new Posn(60, 60), new Posn(0, 0), 40, 40), j1.translate(new Posn(10, 10)));
	}

}
