import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class ObstacleTest {

	Platform pl1 = new Platform(new Posn(50, 50), 10, 4, "blue");
	Platform pl2 = new Platform(new Posn(100, 100), 10, 4, "blue");

	Obstacle ob1 = new Obstacle(new Posn(50, 50), 10, 4, "red");
	Obstacle ob2 = new Obstacle(new Posn(100, 100), 10, 4, "red");

	LoX<Platform> plist1 = new Cons<Platform>(pl1,
			new Cons<Platform>(pl2,
					new MT<Platform>()));

	LoX<Obstacle> oblist1 = new Cons<Obstacle>(ob1, 
			new Cons<Obstacle>(ob2, 
					new MT<Obstacle>()));

	LoX<Platform> mtpl = new MT<Platform>();

	LoX<Obstacle> mtob = new MT<Obstacle>();

	@Test
	void testGetFirst() {
		assertEquals(pl1, plist1.getFirst());
		assertEquals(ob1, oblist1.getFirst());
	}

	@Test
	void testGetRest() {
		assertEquals(new Cons<Platform>(pl2, new MT<Platform>()), plist1.getRest());
		assertEquals(new Cons<Obstacle>(ob2, new MT<Obstacle>()), oblist1.getRest());
	}

	@Test
	void testIsEmpty() {
		assertEquals(true, mtpl.isEmpty());
		assertEquals(true, mtob.isEmpty());
	}

}
