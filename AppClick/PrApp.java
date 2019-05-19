import processing.core.*;

public class PrApp extends PApplet {
   // references to PrObj object(s) should be declared here
   //any other custom "global" variables can be added here
  int pWidth;
  int pHeight;
  PrObj[][] squares;
  int objSize;
  float objSpacing; //min distance between squares 
  float edgeSpacing; //min distance between squares and window edge
  int numRows; //number of rows
  int numCols; //number of columns
    
  public static void main(String[] args) {
	  PApplet.main("PrApp");
  }

  public void settings() { 
     //--  this runs once before setup()
	pWidth=800;
	pHeight=600;
    size(pWidth, pHeight); //setup window/canvas size
    //other initialization of custom variables
	objSize=60;    //set the object size in pixels
    objSpacing=(float)objSize/3;
	edgeSpacing=(float)objSize/4;
  }
	
 void createObjects() {
    	//-- determine number of rows and columns based on height, width and objsize
    	//How many rows and columns we can fit, assuming objsize/3 spacing between squares
    	//   and edgeSpacing between the grid of squares and the window edges? 
    	//We're trying to think of a grid with cell size of objsize+objSpacing
    	// which should fit in a rectangle width-edgeSpacing wide, height-objSpacing tall?
    	float gsize=(float)objSize+objSpacing; //grid cell size
    	//gsize is also the horizontal and/or vertical increment of the top-left corner of each square
    	float gw=(float)width-2*edgeSpacing; //usable grid width
    	float gh=(float)height-2*edgeSpacing; //usable  grid height
    	numCols=(int)(gw/gsize);
    	numRows=(int)(gh/gsize);
    	//-- What is the location of the top-left corner of the top left square?
    	float x0=((float)width-gsize*numCols)/2;
    	float y0=((float)height-gsize*numRows)/2;
    	squares=new PrObj[numRows][]; //allocate an array of rows (a row is an array of numcols squares)
    	float hue=0; //starting hue
    	for (int i=0;i<numRows;i++) {
    		squares[i]= new PrObj[numCols]; //allocate the row array which has numcols objects
    		//now we can properly create each PrSquare object in this row 
    		for (int j=0;j<numCols;j++) {
    			if ((hue+=2)>100) hue=0; //cycle the hue
    			squares[i][j]=new PrObj(this, x0+gsize*j, y0+gsize*i, objSize, color(hue,80,80));
    		}
    	}
    }
    
  public void drawObjects() { //simply call the draw() method of *all* objects
	for(PrObj[] row: squares)
	  for(PrObj square: row)
    	square.draw();
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

 public void mouseReleased() {
  for(PrObj[] row: squares) {
   for(PrObj square: row) {
	 if (mouseButton==LEFT) {
		 if (square.mouseOver && square.mouseLpressed) {
				 square.mouseLup();
		 }
		 square.mouseLpressed=false;
	 }
	 if (mouseButton==RIGHT) {
		 if (square.mouseOver && square.mouseRpressed) {
			 square.mouseRup();
		 }
		 square.mouseRpressed=false;
	 }
	}
  }
 }

 
 void onResize() {
	createObjects(); 
 }
 
}
