import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlatformTest {
  
	Platform pl1 = new Platform(new Posn(50, 50), 10, 4);
	Platform pl2 = new Platform(new Posn(100, 100), 10, 4);
	LoX<Platform> lop1 = new Cons<Platform>(pl1, new Cons<Platform>( pl2, new MT<Platform>())); 
	LoX<Platform> MTlist = new MT<Platform>();

	@Test
	void testGetFirst() { 
		assertEquals(pl1, lop1.getFirst());
		assertEquals(null, MTlist.getFirst());
	}

	@Test
	void testGetRest() {
		assertEquals(new Cons<Platform>(pl2, new MT<Platform>()), lop1.getRest());
		assertEquals(new MT<Platform>(), MTlist.getRest());
	}
 
	@Test
	void testIsEmpty() {
		assertEquals(true, MTlist.isEmpty());
		assertEquals(false, lop1.isEmpty());
	}
	
	@Test
	void testGetPosn() {
		assertEquals(new Posn(50, 50), pl1.getPosn());
		assertEquals(new Posn(100, 100), pl2.getPosn());
	}
	
	@Test
	void testGetWidth() {
		assertEquals(10, pl1.getWidth());
		assertEquals(10, pl2.getWidth());
	}
	
	@Test
	void testGetHeight() {
		assertEquals(4, pl1.getHeight());
		assertEquals(4, pl2.getHeight());
	}

}

