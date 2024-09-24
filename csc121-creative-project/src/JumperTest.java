import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JumperTest {
	
	// positive y velocity means moving down
	
	Jumper j1 = new Jumper(new Posn(50,50), new Posn(0,0));
	Jumper j2 = new Jumper(new Posn(50,70), new Posn(0,10));
	Jumper j3 = new Jumper(new Posn(100,100), new Posn(5,-10));

	
	
	@Test
	void moveTest() {
		assertEquals( new Jumper(new Posn(50,50), new Posn(0,1)) ,  j1.move()  );
		assertEquals(  new Jumper(new Posn(50,80), new Posn(0,11))   ,  j2.move()  );
		assertEquals(  new Jumper(new Posn(105,90), new Posn(5,-9))   ,  j3.move()  );
		// move()  adds the velocity to the location to change the loction;  y velocity is affected by gravity
	}
	
	
	
	@Test
	void boostTest() {
		assertEquals( new Jumper(new Posn(50,50), new Posn(0,-9))  , j1.boost() );     
		assertEquals( new Jumper(new Posn(50, 80), new Posn(0, 1))  , j2.boost() );
		// i.e. boost() changes the velocity only  (adds 10 to the y part)
	}
	
	
	
	@Test
	void Jumptest() {
		//assertEquals(j1.loc.translate(j1.vel.translate(new Posn(0,10))), new Posn(50,60));
		//assertEquals(j3.loc.translate(j3.vel.translate(new Posn(0,10))), new Posn(100,100));
		//assertEquals(j2.loc.translate(j2.vel.translate(new Posn(0,10))), new Posn(50,90));
	}

}
