import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JumperTest {
	
	// positive y velocity means moving down
	
	Jumper j1 = new Jumper(new Posn(50,50), new Posn(0,0), 40, 40);
	Jumper j2 = new Jumper(new Posn(50,70), new Posn(0,10), 40, 40);
	Jumper j3 = new Jumper(new Posn(100,100), new Posn(5,-10), 40, 40);
	Platform p1 = new Platform(new Posn(50,50), 10, 4, "blue");
	
	
	@Test
	void moveTest() {
		assertEquals( new Jumper(new Posn(50,50), new Posn(0,1), 40, 40) ,  j1.move(p1)  );
		assertEquals(  new Jumper(new Posn(50,80), new Posn(0,11), 40, 40)   ,  j2.move(p1)  );
		assertEquals(  new Jumper(new Posn(105,90), new Posn(5,-9), 40, 40)   ,  j3.move(p1)  );
		// move()  adds the velocity to the location to change the loction;  y velocity is affected by gravity
	}
	
	
	
	@Test
	void boostTest() {
		assertEquals( new Jumper(new Posn(50,50), new Posn(0,-4), 40, 40)  , j1.boost() );     
		assertEquals( new Jumper(new Posn(50, 80), new Posn(0, 6), 40, 40)  , j2.boost() );
		// i.e. boost() changes the velocity only  (adds 10 to the y part)
	}
	
	
	
	@Test
	void Jumptest() {
		//assertEquals(j1.loc.translate(j1.vel.translate(new Posn(0,10))), new Posn(50,60));
		//assertEquals(j3.loc.translate(j3.vel.translate(new Posn(0,10))), new Posn(100,100));
		//assertEquals(j2.loc.translate(j2.vel.translate(new Posn(0,10))), new Posn(50,90));
	}

}
