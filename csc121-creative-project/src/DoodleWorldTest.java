
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import processing.event.KeyEvent;

class DoodleWorldTest {
Jumper j1 = new Jumper(new Posn(50,50), new Posn(0,0), 40, 40);
Jumper j2 = new Jumper(new Posn(50,70), new Posn(0,10), 40, 40);
Jumper j3 = new Jumper(new Posn(100,100), new Posn(5,-10), 40, 40);
Platform p1 = new Platform(new Posn(50,50), 10, 4, "blue");
DoodleWorld w1 = new DoodleWorld(j1, p1);


@Test
	void testKeyPressed() {
		assertEquals( w1, w1.keyPressed(new KeyEvent(null, 0, 0, 0, 'c', 'c')));
		assertEquals( new DoodleWorld(j1.boost(),p1), w1.keyPressed(new KeyEvent(null, 0, 0, 0, ' ', ' ')));
}
}
