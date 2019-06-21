import java.util.Random;

import processing.core.*;
//import java.util.Random;

public class PrApp extends PApplet {
	// references to PrObj object(s) should be declared here
	//any other custom "global" variables can be added here
	int pWidth;
	int pHeight;
	PrObj square;
	int objSize;
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
				if (square.mouseOver) {
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
		createObjects(); 
	}

	void createObjects() {
		Random random = new Random();
		int hue=(random.nextInt(101)/4) * 4;
		//square=new PrObj(this, width/2-objSize/2, height/2-objSize/2, objSize, color((float)hue,80,80));
		square=new PrObj(this, width/2-objSize/2, height/2-objSize/2, objSize, color((float)hue,30,90), "ship.png");
		square.setDeathAnimation("expl1/explosion0_", 100, "tga");
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
