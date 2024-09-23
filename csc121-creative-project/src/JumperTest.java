import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JumperTest {
	
	Jumper j1 = new Jumper(new Posn(50,50), new Posn(0,10));
	Jumper j2 = new Jumper(new Posn(50,70), new Posn(0,10));
	Jumper j3 = new Jumper(new Posn(100,100), new Posn(0,-10));

	@Test
	void Jumptest() {
		assertEquals(j1.loc.translate(j1.vel), new Posn(50,60));
		assertEquals(j3.loc.translate(j3.vel), new Posn(100,90));
		assertEquals(j2.loc.translate(j2.vel), new Posn(50,80));
	}

}
