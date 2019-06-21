import processing.core.*;
import java.util.Random;

public class PrApp extends PApplet {
	// references to PrObj object(s) should be declared here
	//any other custom "global" variables can be added here
	int pWidth;
	int pHeight;
	PrShooter shooter;
	PrObj[][] squares;
	PrSprite sqSprite;
	PrSprite sqExplosion;
	// -- bullets data
	PrBullet[] bullets;
	static final int MAX_BULLETS=10;
	static final float BULLET_SPEED=6; // pixels to advance per frame
	static final int BULLET_WIDTH=4; // projectile "thickness"
	static final int BULLET_LEN=22; // projectile "length" 
	int liveBullets; //keep track of the number of bullets
	// --
	int objSize;
	int minObjSpacing; //minimum required distance between squares
	int topDist;  //top padding
	int sideDist; //left and right padding 
	int baseDist;  //bottom padding
	int numRows; //number of rows
	int numCols; //number of columns
    float objSpacing; //adjusted final object spacing
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
		// -- create our objects
		// there is no need to load sprites more than once though
		sqExplosion=new PrSprite(this, "expl1/explosion0_", 100, "tga");
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
		//System.out.println("Live bullets: "+liveBullets);
		if (mouseButton == LEFT && liveBullets<MAX_BULLETS) {
			//fire a bullet!
			for (int i=0;i<bullets.length;i++) {
				if (bullets[i]==null) {
					bullets[i]=new PrBullet(this, shooter.ox+shooter.osize/2-BULLET_WIDTH/2, 
							shooter.oy, BULLET_LEN, BULLET_WIDTH, color(hue(shooter.ocol), 
									saturation(shooter.ocol)+10, brightness(shooter.ocol)+10));
					liveBullets++;
					break;
				}
			}
		}
	}

	// mouseReleased() is an event-triggered method which is called once 
	// when a mouse button is released
	public void mouseReleased() {
		if (!actionStarted) return;
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
			    hue=(hue/4)*4; // making sure it's a multiple of 4
				squares[i][j]=new PrObj(this, (float)sideDist + j*((float)objSize+objSpacing), 
						                      (float)topDist + i*((float)objSize+objSpacing), 
						                      objSize, color((float)hue,30, 90), "invader.png");
				squares[i][j].setDeathAnimation(sqExplosion);
			}
		}
		// -- create the shooter object
		int sHeight=objSize/2;
		shooter=new PrShooter(this, width/2-sHeight/2, height-sHeight-4, sHeight, color(0,80,80));
		// -- create bullet array
		bullets=new PrBullet[MAX_BULLETS];
	}

	public void drawObjects() { //simply call the draw() method of *all* objects
		// -- draw the grid:
		for(PrObj[] row: squares)
			for(PrObj square: row) {
				square.draw();
				//if actionStarted we could also 
			}
		//draw the bullets first, so if they overlap the shooter they should be hidden behind it
		for (int i=0;i<bullets.length;i++) {
			if (bullets[i]!=null) {
				bullets[i].oy-=BULLET_SPEED; //move the bullet
				//-- check for collision to any square in the new location
				for(PrObj[] row: squares) {
					for (int s=0;s<row.length;s++) {
						if (row[s]==null || row[s].dead) continue;
					    if (bullets[i].hit(row[s])) {
					    	row[s].dead=true;
					        bullets[i].dead=true;
					    }
					}
				}
				//-- check for bullets going outside the window (disappear)
				if (bullets[i].oy+BULLET_LEN<1) 
					bullets[i].dead=true;
				bullets[i].draw();
				if (bullets[i].dead) {
					bullets[i]=null;
					liveBullets--;
				}
			}
		}
		// -- draw the shooter
		shooter.draw();
		// -- not drawing anything else if the game hasn't started
		if (!actionStarted) {
			showText();
			return;
		}
		// -- draw any live projectiles, while checking if they hit any squares:
	}

	void showText() {
		textSize(24);
		fill(0,80,50);
		textAlign(CENTER, CENTER);
		text("Resize and press SPACE to start", width/2, height-baseDist/2);
	}


}
