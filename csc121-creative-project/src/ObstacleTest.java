import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class ObstacleTest { 

	Obstacle ob1 = new Obstacle(new Posn(50, 50), 10, 4);
	Obstacle ob2 = new Obstacle(new Posn(100, 100), 10, 4); 

	LoX<Obstacle> oblist1 = new Cons<Obstacle>(ob1, 
			new Cons<Obstacle>(ob2, 
					new MT<Obstacle>()));

	LoX<Obstacle> mtob = new MT<Obstacle>();

	@Test
	void testGetFirst() {
		assertEquals(ob1, oblist1.getFirst());
		assertEquals(null, mtob.getFirst());
	}

	@Test
	void testGetRest() {
		assertEquals(new Cons<Obstacle>(ob2, new MT<Obstacle>()), oblist1.getRest());
		assertEquals(new MT<Obstacle>(), mtob.getRest());
	}

	@Test
	void testIsEmpty() {
		assertEquals(true, mtob.isEmpty());
		assertEquals(false, oblist1.isEmpty());
	}
	
	@Test
	void testGetPosn() {
		assertEquals(new Posn(50, 50), ob1.getPosn());
		assertEquals(new Posn(100, 100), ob2.getPosn());
	}
	
	@Test
	void testGetWidth() {
		assertEquals(10, ob1.getWidth());
		assertEquals(10, ob2.getWidth());
	}
	
	@Test
	void testGetHeight() {
		assertEquals(4, ob1.getHeight());
		assertEquals(4, ob2.getHeight());
	}

}
