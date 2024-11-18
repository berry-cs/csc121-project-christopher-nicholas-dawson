import processing.core.*;
import processing.event.*;

/**
 * Provides the scaffolding to launch a Processing application
 */
public class DoodleApp extends PApplet {	// <----- 1. rename AppTemplate everywhere in this file
	private IWorld w;

	public void settings() { 
		this.size(400, 400);
	}

	
	   public void setup() {
        w = new DoodleWorld(new Jumper( new Posn(200, 300), new Posn(0,0), 15, 15),
        		 new ConsLoP 
        		 (new Platform(new Posn(200, 310), 75, 10, "blue"),
        				new ConsLoP 
        				(new Platform(new Posn(250, 250), 75, 10, "blue"), 
        				new ConsLoP
        		        		(new Platform(new Posn(300, 150), 75, 10, "blue"),
        		        				new ConsLoP
        		        				(new Platform(new Posn(200, 100), 75, 10, "blue"),
        		        						new ConsLoP
                		        				(new Platform(new Posn(200, 50), 75, 10, "blue"), new MTLoP()))))),
        		 0, new Score()); 
    } 

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
