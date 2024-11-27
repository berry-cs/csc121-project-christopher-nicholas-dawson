import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StarTest {

	Star s1 = new Star(new Posn(50,50), 10, 4);
	Star s2 = new Star(new Posn(100, 100), 10, 4);
	LoX<Star> sl1 = new Cons<Star>(s1,
			new Cons<Star>(s2, new MT<Star>()));
	
	LoX<Star> sl2 = new Cons<Star>(s1, new MT<Star>());
	
	LoX<Star> mtsl = new MT<Star>();
	
	@Test
	void testGetFirst() { 
		assertEquals(s1, sl1.getFirst());
		assertEquals(null, mtsl.getFirst());
	}
 
	@Test
	void testGetRest() {
		assertEquals(new Cons<Star>(s2, new MT<Star>()), sl1.getRest());
		assertEquals(new MT<Star>(), mtsl.getRest());
	}

	@Test
	void testIsEmpty() {
		assertEquals(true, mtsl.isEmpty());
		assertEquals(false, sl1.isEmpty());
	}
	
	@Test
	void testGetPosn() {
		assertEquals(new Posn(50, 50), s1.getPosn());
		assertEquals(new Posn(100, 100), s2.getPosn());
	}
	
	@Test
	void testGetWidth() {
		assertEquals(10, s1.getWidth());
		assertEquals(10, s2.getWidth());
	}
	
	@Test
	void testGetHeight() {
		assertEquals(4, s1.getHeight());
		assertEquals(4, s2.getHeight());
	}

}
