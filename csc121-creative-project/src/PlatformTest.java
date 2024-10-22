import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlatformTest {

	Platform pl1 = new Platform(new Posn(50, 50), 10, 4, "blue");
	Platform pl2 = new Platform(new Posn(100, 100), 10, 4, "blue");
	ILoP lop1 = new ConsLoP(pl1, new ConsLoP( pl2, new MTLoP())); 
	ILoP MTlist = new MTLoP();
	
	@Test
	void testGetFirst() {
		assertEquals(pl1, lop1.getFirst());
		assertEquals(new Platform(new Posn(0,0), 0, 0, "blue"), MTlist.getFirst());
	}
	
	@Test
	void testGetRest() {
		assertEquals(new ConsLoP(pl2, new MTLoP()), lop1.getRest());
		assertEquals(new MTLoP(), MTlist.getRest());
	}
	
	@Test
	void testIsEmpty() {
		assertEquals(true, MTlist.isEmpty());
		assertEquals(false, lop1.isEmpty());
	}

}

