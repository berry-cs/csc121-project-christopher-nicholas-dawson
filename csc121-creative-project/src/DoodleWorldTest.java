
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import processing.event.KeyEvent; 

class DoodleWorldTest {
	Jumper j1 = new Jumper(new Posn(50,50), new Posn(0,0), 40, 40);
	Jumper j2 = new Jumper(new Posn(50,70), new Posn(0,10), 40, 40);
	Jumper j3 = new Jumper(new Posn(100,100), new Posn(5,-10), 40, 40);
	Platform p1 = new Platform(new Posn(50,50), 10, 4);

	Platform p2 = new Platform(new Posn(100, 400), 100, 20);
	Platform p3 = new Platform(new Posn(400, 500), 80, 20);
	LoX<Platform> pl1 = new Cons<Platform>(p1, new Cons<Platform>(p2, new Cons<Platform>(p3, new MT<Platform>())));


	Obstacle ob1 = new Obstacle(new Posn(50,50), 10, 4);

	Obstacle ob2 = new Obstacle(new Posn(100, 400), 100, 20);
	Obstacle ob3 = new Obstacle(new Posn(400, 500), 80, 20);
	LoX<Obstacle> obl1 = new Cons<Obstacle>(ob1, new Cons<Obstacle>(ob2, new Cons<Obstacle>(ob3, new MT<Obstacle>())));



	DoodleWorld w1 = new DoodleWorld(j1, pl1, obl1, null, 0, new Score());
	DoodleWorld w2 = new DoodleWorld(j1, pl1, obl1, null, 500, new Score());


	@Test
	void testKeyPressed() {
	//	assertEquals( w1, w1.keyPressed(new KeyEvent(null, 0, 0, 0, 'c', 'c')));

		assertEquals( new DoodleWorld(j1.boost(), pl1, obl1, null, 0, new Score()), w1.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', ' ')));

		assertEquals( new DoodleWorld(j1.boost(), pl1, obl1, null, 0, new Score() ), w1.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', ' ')));

	}

	@Test
	void testScrollBottom() {
		assertEquals(600, w1.bottomY() );
		assertEquals(605, w2.bottomY() );
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
		assertEquals(605, w2.bottomY());
		assertEquals(600, w1.bottomY());
	}
}
