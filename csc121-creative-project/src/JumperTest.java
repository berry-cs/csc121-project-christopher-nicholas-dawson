import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JumperTest {
	
	// positive y velocity means moving down
	
	Jumper j1 = new Jumper(new Posn(50,50), new Posn(0,0), 40, 40);
	Jumper j2 = new Jumper(new Posn(50,70), new Posn(0,10), 40, 40);
	Jumper j3 = new Jumper(new Posn(100,100), new Posn(5,-10), 40, 40);
	Platform p1 = new Platform(new Posn(50,50), 10, 4, "blue");

	Jumper j4 = new Jumper(new Posn(100,400), new Posn(5,7), 40, 40);  // at bottom of the world

	
	@Test
	void moveTest() {
		assertEquals( new Jumper(new Posn(50,50), new Posn(0,1), 40, 40) ,  j1.move(p1)  );
		assertEquals(  new Jumper(new Posn(50,80), new Posn(0,11), 40, 40)   ,  j2.move(p1)  );
		assertEquals(  new Jumper(new Posn(105,90), new Posn(5,-9), 40, 40)   ,  j3.move(p1)  );
		
		
		assertEquals( new Jumper(new Posn(100,400), new Posn(0,0), 40, 40)  ,  j4.move(p1)  ); 
		
		assertEquals( new Jumper(new Posn(100,392), new Posn(0,-7), 40, 40),
					new Jumper(new Posn(100,400), new Posn(0,-8), 40, 40).move(p1));
		// move()  adds the velocity to the location to change the loction;  y velocity is affected by gravity
	}
	
	@Test
	void atBottomTest() {
	   assertEquals(  true,   j4.atBottom() );
	   assertEquals( false,  j1.atBottom() );
	   
	   assertEquals( false,  new Jumper(new Posn(50, 399), new Posn(5,7), 40, 40).atBottom() );
	   assertEquals( true,  new Jumper(new Posn(100,401), new Posn(5,7), 40, 40).atBottom() );
	   assertEquals( true,  new Jumper(new Posn(100,410), new Posn(5,7), 40, 40).atBottom() );
	
	}
	
	
	
	@Test
	void boostTest() {
		assertEquals( new Jumper(new Posn(50,50), new Posn(0,-8), 40, 40)  , j1.boost() );     
		assertEquals( new Jumper(new Posn(50, 80), new Posn(0, 2), 40, 40)  , j2.boost() );
		
		assertEquals( new Jumper(new Posn(100,400), new Posn(0,-8), 40, 40), 
				new Jumper(new Posn(100,400), new Posn(0,0), 40, 40).boost() );

		// i.e. boost() changes the velocity only  (adds 10 to the y part)
	}
	
	
	
	@Test
	void Jumptest() {
		//assertEquals(j1.loc.translate(j1.vel.translate(new Posn(0,10))), new Posn(50,60));
		//assertEquals(j3.loc.translate(j3.vel.translate(new Posn(0,10))), new Posn(100,100));
		//assertEquals(j2.loc.translate(j2.vel.translate(new Posn(0,10))), new Posn(50,90));
	}

}
