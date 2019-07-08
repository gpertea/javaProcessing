import processing.core.*;
//import java.util.Random;

public class PrApp extends PApplet {
	// references to PrObj object(s) should be declared here
	// any other custom "global" variables can be added here
	// PrSquare[] squares;
	// PrCircle[] circles;
	PrObj[] objects;
	final static int MAX_OBJS = 20;
	int oSize;
	int numCircles;
	int numSquares;

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
		objects = new PrObj[MAX_OBJS];
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
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null)
				continue;
			if (objects[i] instanceof PrClickable) {
				PrClickable obj = (PrClickable)objects[i];
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
		objects = new PrObj[MAX_OBJS];
		numSquares=0;
		numCircles=0;
	}

	void destroy(PrObj t) {
		for (int i=0;i<objects.length;i++) {
			if (objects[i]==t) {
				objects[i]=null;
				break;
			}
		}
	}
	
	int findNullEntry() {
		int spot = -1;
		for (int i = 0; i < objects.length; i++) {
			if (objects[i] == null) {
				spot = i;
				break;
			}
		}
		return spot;
	}

	void createSquare(float x, float y) {
		int i = findNullEntry();
		if (i >= 0) {
			numSquares++;
			PrSquare obj = new PrSquare(this, x - oSize / 2, y - oSize / 2, oSize,
					color((float) (Math.random() * 100.0), (float) 80, (float) 80));
			obj.setCaption("Square_"+numSquares);
			objects[i]=obj;
		}
	}

	void createCircle(float x, float y) {
		int i = findNullEntry();
		if (i >= 0) {
			numCircles++;
			PrCircle obj = new PrCircle(this, x - oSize / 2, y - oSize / 2, oSize,
					color((float) (Math.random() * 100.0), 80, 80));
			obj.setCaption("Circle_"+numCircles);
			objects[i]=obj;
		}
	}

	public void showObjects() { // simply call the show() method of *all* drawable objects
		for (int i = 0; i < objects.length; i++) {
			PrObj obj = objects[i];
			if (obj == null)
				continue;
			obj.show();
			//if (obj instanceof PrShowable)
			//	((PrShowable) obj).show();
		}
	}

}
