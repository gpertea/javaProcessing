import processing.core.*;
//import java.util.Random;

public class PrApp extends PApplet {
	// references to PrObj object(s) should be declared here
	//any other custom "global" variables can be added here
	//PrSquare[] squares;
	//PrCircle[] circles;
	PrObj[] objects;
	int objSize;
	public static void main(String[] args) {
		PApplet.main("PrApp");
	}
	public void settings() { 
		//--  this runs once before setup()
		size(1200, 720); //setup window/canvas size
		//-- setup the objects geometry;
		objSize=100;   //set the size of objects when created
		//squares=new PrSquare[10];
		//circles=new PrCircle[10];
		objects=new PrObj[20];
		
	}

	public void setup() { 
		//--  this runs once after settings() and before draw()
		// Here we can set up other graphical properties, 
		colorMode(HSB, 100); //setting color mode to HSB
		//surface.setResizable(true);
		createObjects(); //create all our objects 
	}

	public void draw() { 
		// this runs many times a second, must refresh the 
		// window (canvas) content
		background(50);//clear the canvas
		showObjects(); //draw all our objects
	}

	// mousePressed() is an event-triggered method which is called once 
	// when a mouse button is pressed
	public void mouseClicked() {
		/*
		for (int i=0;i<squares.length;i++) {
			PrSquare obj=squares[i];
			if (obj==null) continue;
		    if (obj.contains(mouseX, mouseY)) {
			    if (mouseButton==LEFT) obj.mouseLClick();
				if (mouseButton==RIGHT) obj.mouseRClick();
		    }
		}
		for (int i=0;i<circles.length;i++) {
			PrCircle obj=circles[i];
			if (obj==null) continue;
		    if (obj.contains(mouseX, mouseY)) {
			    if (mouseButton==LEFT) obj.mouseLClick();
				if (mouseButton==RIGHT) obj.mouseRClick();
			}
		}
		*/
		for (int i=0;i<objects.length;i++) {
			PrObj obj=objects[i];
			if (obj==null) continue;
			if (! (obj instanceof PrClickable)) continue; 
		    if (((PrClickable)obj).contains(mouseX, mouseY)) {
			    if (mouseButton==LEFT) ((PrClickable)obj).mouseLClick();
				if (mouseButton==RIGHT) ((PrClickable)obj).mouseRClick();
		    }
		}
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
			//createCircle(new PrCircle(this, mouseX-objSize/2, mouseY-objSize/2, objSize,
			createObject(new PrCircle(this, mouseX-objSize/2, mouseY-objSize/2, objSize,
					  color((float)(Math.random()*100.0), (float)80, (float)80)));
			break;
		case 't':
			//createTriangle();
			break;
		case 's':
		case 'r':
			//createSquare(new PrSquare(this, mouseX-objSize/2, mouseY-objSize/2, objSize,
			createObject(new PrSquare(this, mouseX-objSize/2, mouseY-objSize/2, objSize,
					  color((float)(Math.random()*100.0), (float)80, (float)80)));
			break;
	  }
	}
	
	//---------- our new custom methods here ----
	void onResize() {
		//createObjects(); 
	}

	void createObjects() {
		//we don't create any objects here
		//objects will be created interactively, on user request
	}
	
    void destroyObjects() {
    	/*
    	for (int i=0;i<squares.length;i++)
    		squares[i]=null;
    	for (int i=0;i<circles.length;i++)
    		circles[i]=null;
    	*/
    	for (int i=0;i<objects.length;i++)
    		objects[i]=null;
    }

    void createObject(PrObj newobj) {
    	//create a PrSquare object with its geometric center at the mouse coordinates
    	//-- find the first null element in the squares array
    	int created=-1;
    	for (int i=0;i<objects.length;i++) {
    		if (objects[i]==null) {
    			objects[i]=newobj;
    			created=i;
    			break;
    		}
    	}
    	if (created>=0)
    		System.out.println("Created objects["+created+"] object.");
    	else
    		System.out.println("Sorry, object limit ("+objects.length+") reached! ");
    }

    /*
    void createSquare(PrObj newobj) {
    	//create a PrSquare object with its geometric center at the mouse coordinates
    	//-- find the first null element in the squares array
    	int created=-1;
    	for (int i=0;i<squares.length;i++) {
    		if (squares[i]==null) {
    			squares[i]=(PrSquare)newobj;
    			created=i;
    			break;
    		}
    	}
    	if (created>=0)
    		System.out.println("Created squares["+created+"] object.");
    	else
    		System.out.println("Sorry, square limit ("+squares.length+") reached! ");
    }
    
    void createCircle(PrObj p) {
    	//create a PrSquare object with its geometric center at the mouse coordinates
    	//-- find the first null element in the squares array
    	int created=-1;
    	for (int i=0;i<circles.length;i++) {
    		if (circles[i]==null) {
    			circles[i]=(PrCircle) p;
    			created=i;
    			break; // important! 
    		}
    	}
    	if (created>=0)
    		System.out.println("Created circles["+created+"] object.");
    	else
    		System.out.println("Sorry, object limit ("+circles.length+") reached! ");
    }
	*/
    
	public void showObjects() { //simply call the show() method of *all* drawable objects
		/*
		for (int i=0;i<squares.length;i++) {
			PrSquare obj=squares[i];
			if (obj!=null) obj.show();
		}
		for (int i=0;i<circles.length;i++) {
			PrCircle obj=circles[i];
			if (obj!=null) obj.show();
		}
		*/
		for (int i=0;i<objects.length;i++) {
			PrObj obj=objects[i];
			if (obj==null) continue;
			if (obj instanceof PrShowable) 
				((PrShowable)obj).show();
		}
	}



}
