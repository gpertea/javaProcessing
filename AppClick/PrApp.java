import processing.core.*;

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
		for(PrObj[] row: squares) 
			for(PrObj square: row) 
				if (square.mouseOver) {
					if (mouseButton==LEFT) square.mouseDownL();
					if (mouseButton==RIGHT) square.mouseDownR();
				}
	}

	// mouseReleased() is an event-triggered method which is called once 
	// when a mouse button is released
	public void mouseReleased() {
		for(PrObj[] row: squares) {
			for(PrObj square: row) {
				if (mouseButton==LEFT) {
					if (square.mouseOver && square.mousePressedL)
						square.mouseUpL();
					square.mousePressedL=false;
				}
				if (mouseButton==RIGHT) {
					if (square.mouseOver && square.mousePressedR) 
						square.mouseUpR();
					square.mousePressedR=false;
				}
			}
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
        //float objSpacingY = (numRows>1) ? (float)(height - baseDist - topDist - numRows*objSize)/(numRows-1) : 0;
		//objSpacingX is what matters
		objSpacing = (numCols>1) ? (float)(width - sideDist*2 - numCols*objSize)/(numCols-1) : 0;
		//if (objSpacingX >= 1 && objSpacingY>objSpacingX) objSpacingY=objSpacingX;
		squares=new PrObj[numRows][numCols]; //allocate an array of rows (a row is an array of numcols squares)
		float hue=0; //starting hue
		for (int i=0;i<numRows;i++) {
			//now we can properly create each PrSquare object in this row
			for (int j=0;j<numCols;j++) {
				hue+=2;
				if (hue>100) hue=0; //cycle the hue
				squares[i][j]=new PrObj(this, (float)sideDist + j*((float)objSize+objSpacing), 
						                      (float)topDist + i*((float)objSize+objSpacing), objSize, color(hue,80,80));
			}
		}
	}

	public void drawObjects() { //simply call the draw() method of *all* objects
		for(PrObj[] row: squares)
			for(PrObj square: row)
				square.draw();
	}



}
