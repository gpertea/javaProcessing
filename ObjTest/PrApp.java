import processing.core.*;
//import java.util.Random;

public class PrApp extends PApplet {
	// references to PrObj object(s) should be declared here
	//any other custom "global" variables can be added here
	int pWidth;
	int pHeight;
	//PrObj[][] squares;
	PrObj square;
	int objSize;
	/*
	int minObjSpacing; //minimum required distance between squares
	int topDist;  //top padding
	int sideDist; //left and right padding 
	int baseDist;  //bottom padding
	int numRows; //number of rows
	int numCols; //number of columns
    float objSpacing; //adjusted final object spacing
    //keep track of mouse-pressed objects (for mouse dragging)
    PrObj mousePressObj; //there can be only one, for now - the last one checked
    //if some game session has started (and resizing is disabled):
	boolean actionStarted;
*/
	public static void main(String[] args) {
		PApplet.main("PrApp");
	}
	public void settings() { 
		//--  this runs once before setup()
		pWidth=800;
		pHeight=600;
		size(pWidth, pHeight); //setup window/canvas size
		//-- setup the objects geometry;
		objSize=200;    //set the object size in pixels
		/*
		minObjSpacing=objSize/2; //minimum spacing between squares
		sideDist=objSize/2;
		topDist=objSize/3;
		baseDist=objSize*3;
		*/
	}

	public void setup() { 
		//--  this runs once after settings() and before draw()
		// Here we can set up other graphical properties, 
		colorMode(HSB, 100); //setting color mode to HSB
		surface.setResizable(true);
		rectMode(CORNER);
		createObjects(); //create all our objects 
	}

	public void draw() { 
		// this runs many times a second, must refresh the 
		// window (canvas) content
		if (width!=pWidth || height!=pHeight) {
			onResize();
			pWidth=width;
			pHeight=height;
		}
		background(50);//clear the canvas
		drawObjects(); //draw all our objects
	}

	// mousePressed() is an event-triggered method which is called once 
	// when a mouse button is pressed
	public void mousePressed() {
		/* 
		if (!actionStarted) return;
		for(PrObj[] row: squares) 
			for(PrObj square: row) {
				if (square.dead) continue;
		*/
				if (square.mouseOver) {
					/*
				
					if (mousePressObj!=null) {
						if (mouseButton==LEFT) mousePressObj.mousePressedL=false;
						if (mouseButton==RIGHT) mousePressObj.mousePressedR=false;
					}
					mousePressObj=square;
					*/
					if (mouseButton==LEFT) square.mouseDownL();
					if (mouseButton==RIGHT) square.mouseDownR();
				}
	//	}
	}

	// mouseReleased() is an event-triggered method which is called once 
	// when a mouse button is released
	public void mouseReleased() {
		if (mouseButton==LEFT) {
			if (square.mouseOver) square.mouseUpL();
			square.mousePressedL=false;
		}
		if (mouseButton==RIGHT) {
			if (square.mouseOver) square.mouseUpR();
			else square.mousePressedR=false;
		}
	}

	// keyPressed() is an event-triggered method which is called once 
	// when a key was pressed
	/*
	public void keyPressed() {
		if (key==' ') {
			actionStarted=true;
			surface.setResizable(false);
		}
	}
	*/
	//---------- our new custom methods here ----
	void onResize() {
		System.out.println("Resize called!");
		createObjects(); 
	}

	void createObjects() {
		//Random random = new Random();
		//int hue=random.nextInt(101);
		//square=new PrObj(this, width/2-objSize/2, height/2-objSize/2, objSize, color((float)hue,80,80));
		square=new PrObj(this, width/2-objSize/2, height/2-objSize/2, objSize, "ship.png");
		square.setDeathAnimation("explosion0_", 100, "tga");
	}

	public void drawObjects() { //simply call the draw() method of *all* objects
		// -- draw the grid:
		/*
		for(PrObj[] row: squares)
			for(PrObj square: row)
				square.draw();
		// -- draw other objects
		if (!actionStarted) {
			textSize(24);
			fill(0,80,50);
			textAlign(CENTER, CENTER);
			text("Resize and press SPACE to start", width/2, height-baseDist/2);
		}
		*/
		square.draw();
	}



}
