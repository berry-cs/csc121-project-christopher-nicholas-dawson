import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JumperTest {
 
	Jumper j1 = new Jumper(new Posn(50,50), new Posn(0,0), 40, 40);
	Jumper j2 = new Jumper(new Posn(50,70), new Posn(0,10), 40, 40);
	Jumper j3 = new Jumper(new Posn(100,100), new Posn(5,-10), 40, 40);
	Jumper j4 = new Jumper(new Posn(100, 600), new Posn(5,7), 40, 40);  // at bottom of the world

	
	Platform p1 = new Platform(new Posn(50,50), 10, 4);
	Platform p2 = new Platform(new Posn(100, 100), 10, 4);
	LoX<Platform> pl1 = new Cons<Platform>(p1, 
			new Cons<Platform>(p2, new MT<Platform>()));
	
	LoX<Platform> mtpl = new MT<Platform>();
	
	Obstacle o1 = new Obstacle(new Posn(50,50), 10, 4);
	Obstacle o2 = new Obstacle(new Posn(100,100), 10, 4);
	LoX<Obstacle> obl1 = new Cons<Obstacle>(o1,
			new Cons<Obstacle>(o2, new MT<Obstacle>()));
	
	LoX<Obstacle> mtobl = new MT<Obstacle>();
	
	Star s1 = new Star(new Posn(50,50), 10, 4);
	Star s2 = new Star(new Posn(100, 100), 10, 4);
	LoX<Star> sl1 = new Cons<Star>(s1,
			new Cons<Star>(s2, new MT<Star>()));
	
	LoX<Star> sl2 = new Cons<Star>(s1, new MT<Star>());
	
	LoX<Star> mtsl = new MT<Star>();

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
		
		assertEquals(true, j1.withinXRangeOb(o1));
		assertEquals(false, j3.withinXRangeOb(o1));
		
		assertEquals(true, j1.withinXRangeSta(s1));
		assertEquals(false, j3.withinXRangeSta(s1));
	}

	@Test
	void testWithinYRange() {
		assertEquals(true, j1.withinYRangePla(p1));
		assertEquals(false, j3.withinYRangePla(p1));
		
		assertEquals(true, j1.withinYRangeOb(o1));
		assertEquals(false, j3.withinYRangeOb(o1));
		
		assertEquals(true, j1.withinYRangeSta(s1));
		assertEquals(false, j3.withinYRangeSta(s1));
	}

	@Test
	void testIsCollision() {
		assertEquals(true, j1.isCollisionPla(p1));
		assertEquals(false, j3.isCollisionPla(p1));
		
		assertEquals(true, j1.isCollisionOb(o1));
		assertEquals(false, j3.isCollisionOb(o1));
		
		assertEquals(true, j1.isCollisionSta(s1));
		assertEquals(false, j3.isCollisionSta(s1));
	}

	@Test
	void testIsCollisionLop() {
		assertEquals(true, j1.isCollisionLop(pl1));
		assertEquals(true, j3.isCollisionLop(pl1));
		assertEquals(false, j1.isCollisionLop(mtpl));
		assertEquals(false, j4.isCollisionLop(pl1));
		
		assertEquals(true, j1.isCollisionLoB(obl1));
		assertEquals(true, j3.isCollisionLoB(obl1));
		assertEquals(false, j1.isCollisionLoB(mtobl));
		assertEquals(false, j4.isCollisionLoB(obl1));
		
		assertEquals(true, j1.isCollisionLoS(sl1));
		assertEquals(true, j3.isCollisionLoS(sl1));
		assertEquals(false, j1.isCollisionLoS(mtsl));
		assertEquals(false, j4.isCollisionLoS(sl1));
	} 
	
	@Test
	void testRemoveStar() {
		assertEquals(sl2, j3.removeStar(sl1));
		assertEquals(mtsl, j3.removeStar(mtsl));
		assertEquals(sl1, j4.removeStar(sl1));
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
