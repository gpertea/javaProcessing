import processing.core.*;
import java.util.ArrayList;

public class PrApp extends PApplet {
	// references to PrObj object(s) should be declared here
	// any other custom "global" variables can be added here
	ArrayList<PrObj> objects;  
	int oSize;
	int numCircles;
	int numSquares;
	ArrayList<PrObj> destroyed; //hold objects to be destroyed between frames
	public static void main(String[] args) {
		PApplet.main("PrApp");
	}

	public void settings() {
		// -- this runs once before setup()
		size(1200, 720); // setup window/canvas size
		// -- setup the objects geometry;
		oSize = 100; // set the size of objects when created
		// squares=new PrSquare[10];
		// circles=new PrCircle[10];
		objects = new ArrayList<PrObj>();
		destroyed = new ArrayList<PrObj>();
	}

	public void setup() {
		// -- this runs once after settings() and before draw()
		// Here we can set up other graphical properties,
		colorMode(HSB, 100); // setting color mode to HSB
		// surface.setResizable(true);
		createObjects(); // create all our objects
	}

	public void draw() {
		// this runs many times a second, must refresh the
		// window (canvas) content
		background(50);// clear the canvas
		showObjects(); // draw all our objects
	}

	// mouseClicked() is an event-triggered method which is called once
	// when a mouse button is pressed and released 
	public void mouseClicked() {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof PrClickable) {
				PrClickable obj = (PrClickable)objects.get(i);
				if (obj.contains(mouseX, mouseY)) {
					if (mouseButton == LEFT)
						obj.mouseLClick();
					if (mouseButton == RIGHT)
						obj.mouseRClick();
				} // if mouse over
			} // if clickable
		} //for each object
	}

	// keyPressed() is an event-triggered method which is called once
	// when a key was pressed
	public void keyPressed() {
	  switch(key)  {
		case ' ': // clear the canvas
			destroyObjects();
			break;
		case 'c':
		case 'o':
			createCircle(mouseX, mouseY); 
			break;
		case 't':
			//createTriangle();
			break;
		case 's':
		case 'r':
			createSquare(mouseX, mouseY);
			break;
	  }
	}

	void createObjects() {
		// we don't create any objects here
		// objects will be created interactively, on user request
	}

	void destroyObjects() {
		objects.clear();
		numSquares=0;
		numCircles=0;
	}

	void destroy(PrObj t) {
		destroyed.add(t); //simply add it to the list, postpone actual destruction
	}
	
	void createSquare(float x, float y) {
		numSquares++;
		objects.add(new PrSquare(this, x - oSize / 2, y - oSize / 2, oSize,
				color((float) (Math.random() * 100.0), (float) 80, (float) 80)) );
		((PrObjCaptioned)objects.get(objects.size()-1)).setCaption("Square_"+numSquares);
	}

	void createCircle(float x, float y) {
		numCircles++;
		objects.add(new PrCircle(this, x - oSize / 2, y - oSize / 2, oSize,
				color((float) (Math.random() * 100.0), 80, 80)) );
		((PrObjCaptioned)objects.get(objects.size()-1)).setCaption("Circle_"+numCircles);
	}

	public void showObjects() { // simply call the show() method of *all* drawable objects
		//first, clean up any objects destroyed meanwhile
		if (destroyed.size()>0) {
		   destroyed.forEach( obj -> objects.remove(obj) );
		   destroyed.clear();
		}
		objects.forEach( obj -> {
			obj.show();
		} );
	}

}
