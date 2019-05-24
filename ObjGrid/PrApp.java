import processing.core.*;
import java.util.Random;

public class PrApp extends PApplet {
	// references to PrObj object(s) should be declared here
	//any other custom "global" variables can be added here
	int pWidth;
	int pHeight;
	PrObj[][] squares;
	int objSize;
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

	public static void main(String[] args) {
		PApplet.main("PrApp");
	}
	public void settings() { 
		//--  this runs once before setup()
		pWidth=800;
		pHeight=600;
		size(pWidth, pHeight); //setup window/canvas size
		//-- setup the objects geometry;
		objSize=60;    //set the object size in pixels
		minObjSpacing=objSize/2; //minimum spacing between squares
		sideDist=objSize/2;
		topDist=objSize/3;
		baseDist=objSize*3;
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
		if (!actionStarted) return;
		for(PrObj[] row: squares) 
			for(PrObj square: row) {
				if (square.dead) continue;
				if (square.mouseOver) {
					if (mousePressObj!=null) {
						if (mouseButton==LEFT) mousePressObj.mousePressedL=false;
						if (mouseButton==RIGHT) mousePressObj.mousePressedR=false;
					}
					mousePressObj=square;
					if (mouseButton==LEFT) square.mouseDownL();
					if (mouseButton==RIGHT) square.mouseDownR();
				}
			}
	}

	// mouseReleased() is an event-triggered method which is called once 
	// when a mouse button is released
	public void mouseReleased() {
		if (!actionStarted) return;
		if (mousePressObj!=null) { 
				if (mouseButton==LEFT) {
					if (mousePressObj.mouseOver) 
						mousePressObj.mouseUpL();
					mousePressObj.mousePressedL=false;
				}
				if (mouseButton==RIGHT) {
					if (mousePressObj.mouseOver)
						mousePressObj.mouseUpR();
					mousePressObj.mousePressedR=false;
				}
		}
		
		for(PrObj[] row: squares)
			for(PrObj square: row) {
				if (square.dead || square==mousePressObj) continue;
				if (square.mouseOver) {
					if (mouseButton==LEFT) {
						square.mouseUpL();
						if (mousePressObj!=null) {
							System.out.printf("comparing hue %d to %d\n", Math.round(hue(mousePressObj.ocol)), Math.round(hue(square.ocol)));
							if (Math.round(hue(mousePressObj.ocol))==Math.round(hue(square.ocol))) {
							  square.kill();
							  mousePressObj.kill();
							}
						}
					}
					if (mouseButton==RIGHT) {
						square.mouseUpR();
					}
				}
			}
			
	}

	// keyPressed() is an event-triggered method which is called once 
	// when a key was pressed
	public void keyPressed() {
		if (key==' ') {
			actionStarted=true;
			surface.setResizable(false);
		}
	}
	
	//---------- our new custom methods here ----
	void onResize() {
		createObjects(); 
	}

	void createObjects() {
		//-- determine numRows and numCols based on fitting obj
		//How many rows and columns we can fit in the rectangular area padded 
		//by topDist, baseDist and sideDist, such that the spacing between the squares
		//is at least minObjSpacing ? 
		numRows=(int)((height - (baseDist - minObjSpacing) - topDist ) / (objSize + minObjSpacing));
		numCols=(int)((width - (sideDist - minObjSpacing) - sideDist) / (objSize + minObjSpacing));
		if (sideDist<minObjSpacing) numCols--;
		//recompute horizontal spacing to make a row fit&fill the width-sideDist*2 space 
		objSpacing = (numCols>1) ? (float)(width - sideDist*2 - numCols*objSize)/(numCols-1) : 0;
		squares=new PrObj[numRows][numCols]; //allocate an array of rows (a row is an array of numcols squares)
		//float hue=0; //starting hue
		Random random = new Random();
		for (int i=0;i<numRows;i++) {
			//now we can properly create each PrSquare object in this row
			for (int j=0;j<numCols;j++) {
				// -- generate a random hue in range 0..100 which is a multiple of 4
			    int hue=random.nextInt(101);
			    hue=(hue/4)*4; //making sure it's a multiple of 4
				squares[i][j]=new PrObj(this, (float)sideDist + j*((float)objSize+objSpacing), 
						                      (float)topDist + i*((float)objSize+objSpacing), objSize, color((float)hue,80,80));
				//hue+=4;
				//if (hue>100) hue=0; //cycle the hue
				
				
			}
		}
		mousePressObj=null;
	}

	public void drawObjects() { //simply call the draw() method of *all* objects
		// -- draw the grid:
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
	}



}
