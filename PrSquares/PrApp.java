import processing.core.*;

public class PrApp extends PApplet {
    PrSquare[][] squares;
    int objSize;
    float objSpacing; //min distance between squares 
    float edgeSpacing; //min distance between squares and window edge
    int numRows; //number of rows
    int numCols; //number of columns
    
	public static void main(String[] args) {
		PApplet.main("PrApp");
	}

	public void settings(){ //this method runs before setup()
		size(800,600); //setup window size
		objSize=40;    //set the object size in pixels
		objSpacing=objSize/3;
		edgeSpacing=(float)objSize/4;
    }

    public void setup(){
    	colorMode(HSB, 100); //setting color mode to HSB, range max 100
    	//create objects vertically centered, spaced at objsize/2 horizontally
    	createObjects();
    }

    public void draw() { 
        background(50); //50% gray background
    	drawObjects();  //draw all objects
    }

    
    void createObjects() {
    	//-- determine number of rows and columns based on height, width and objsize
    	//How many rows and columns we can fit, assuming objsize/3 spacing between squares
    	//   and a spacing of objsize/4 between any square and the window edges? 
    	//We're trying to think of a grid with cell size of objsize+objsize/3
    	// which should fit in a rectangle width-objsize/2 wide, height-objsize/2 tall?
    	float gsize=(float)objSize+objSpacing; //grid cell size
    	//gsize is also the horizontal and/or vertical increment of the top-left corner of each square
    	float gw=(float)width-2*edgeSpacing; //usable grid width
    	float gh=(float)height-2*edgeSpacing; //usable  grid height
    	numCols=(int)(gw/gsize);
    	numRows=(int)(gh/gsize);
    	System.out.println("numRows=" + numRows + ", numCols="+numCols);
    	//-- What is the location of the top-left corner of the top left square?
    	float x0=((float)width-gsize*numCols)/2;
    	float y0=((float)height-gsize*numRows)/2;
    	squares=new PrSquare[numRows][]; //allocate an array of rows (a row is an array of numcols squares)
    	float hue=0; //starting hue
    	for (int i=0;i<numRows;i++) {
    		squares[i]= new PrSquare[numCols]; //allocate the row array which has numcols objects
    		//now we can properly create each PrSquare object in this row 
    		for (int j=0;j<numCols;j++) {
    			if ((hue+=2)>100) hue=0; //cycle the hue
    			squares[i][j]=new PrSquare(this, x0+gsize*j, y0+gsize*i, objSize, color(hue,80,80));
    		}
    	}
    }
    
   
    public void drawObjects() { //simply call the draw() method of *all* objects
    	for (int i=0;i<numRows;i++)
    	  for (int j=0;j<numCols;j++)
    		squares[i][j].draw();
    }
	  
}
