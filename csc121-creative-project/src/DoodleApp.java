import processing.core.*;
import processing.event.*;

/**
 * Provides the scaffolding to launch a Processing application
 */
public class DoodleApp extends PApplet {	// <----- 1. rename AppTemplate everywhere in this file

	private IWorld w;

	private static final int platwid = 75;
	private static final int plahgt = 10;
    private static final int ScreenWid = 400;
    private static final int ScreenHgt = 600;

	public void settings() { 
		this.size(ScreenWid, ScreenHgt);
	}

	public void setup() {		
		w = new DoodleWorld(new Jumper( new Posn(200, 300), new Posn(0,0), 17, 17),
				new Cons<Platform> 
		(new Platform(new Posn(200, 310), platwid, plahgt),
				new Cons<Platform> 
		(new Platform(new Posn(250, 250), platwid, plahgt), 
				new Cons<Platform>
		(new Platform(new Posn(300, 150), platwid, plahgt),
				new Cons<Platform>
		(new Platform(new Posn(200, 100), platwid, plahgt),
				new Cons<Platform>

	

		(new Platform(new Posn(200, 50), platwid, plahgt), new MT<Platform>()))))),
		new Cons<Obstacle>
		(new Obstacle(new Posn(300, 300), 35, 35), new MT<Obstacle>()),
		new Cons<Star>
		 (new Star(new Posn(350, 250), 35, 35), new MT<Star>()),

		0, new Score()); 
	}

	// draws the application
	public void draw() {
		w = w.update();
		w.draw(this);
	}

	@Override
	public void mousePressed(MouseEvent mev) {
		w = w.mousePressed(mev);
	}

	@Override
	public void mouseReleased(MouseEvent mev) {
		w = w.mouseReleased(mev);
	}

	@Override
	public void mouseMoved(MouseEvent mev) {
		w = w.mouseMoved(mev);
	}

	@Override
	public void mouseDragged(MouseEvent mev) {
		w = w.mouseDragged(mev);
	}

	@Override
	public void mouseClicked(MouseEvent mev) {
		w = w.mouseClicked(mev);
	}

	@Override
	public void mouseEntered(MouseEvent mev) {
		w = w.mouseEntered(mev);
	}

	@Override
	public void mouseExited(MouseEvent mev) {
		w = w.mouseExited(mev);
	}

	@Override
	public void mouseWheel(MouseEvent mev) {
		w = w.mouseWheel(mev);
	}

	@Override
	public void keyPressed(KeyEvent kev) {
		w = w.keyPressed(kev);
	}

	@Override
	public void keyReleased(KeyEvent kev) {
		w = w.keyReleased(kev);
	}

	@Override
	public void keyTyped(KeyEvent kev) {
		w = w.keyTyped(kev);
	}

	public static void main(String[] args) {
		PApplet.runSketch(new String[] { DoodleApp.class.getName() }, new DoodleApp());

	}
}
