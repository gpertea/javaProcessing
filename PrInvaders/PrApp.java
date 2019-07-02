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
	PrSprite bulletSprite;
	PrSprite sqExplosion;
	PrSprite shipSprite;
	// -- bullets data
	PrBullet[] bullets;
	static final int MAX_BULLETS=20;
	static final float BULLET_SPEED=6; // pixels to advance per frame
	static final int BULLET_WIDTH=5; // projectile "thickness"
	static final int BULLET_LEN=22; // projectile "length" 
	int liveBullets; //keep track of the number of bullets
	// --
	int objSize;
	int minObjSpacing; //minimum required distance between squares
	int topDist;  //top padding
	int sideDist; //left and right padding 
	int baseDist;  //bottom padding
	//-- calculated from the above and window size:
	int numRows; //number of rows
	int numCols; //number of columns
	
    float objSpacing; //adjusted final object spacing
    // -- initial invaders movement speed
    float invSpeedX;
    float invDirection; //flipping X-move direction when any invader gets too close to left or right side of the screen   
    float invSpeedY;
    
    //if some game session has started (and resizing is disabled):
	boolean actionStarted;
	boolean gameOver;
	boolean gameLost;
	int liveInvaders; //keep track of the remaining invaders
	int winFrame; //frame when winning condition was achieved (liveInvaders got to 0)
	public static void main(String[] args) {
		PApplet.main("PrApp");
	}
	public void settings() { 
		//--  this runs once before setup()
		pWidth=800;
		pHeight=600;
		//size(pWidth, pHeight); //setup window/canvas size
		size(pWidth, pHeight, "processing.opengl.PGraphics2D");

		//-- setup the objects geometry;
		objSize=50;    //set the object size in pixels
		minObjSpacing=objSize/2; //minimum spacing between squares
		sideDist=objSize*2;
		topDist=objSize/3;
		baseDist=objSize*5;
		//--
		invSpeedX=(float)1.5;
		invSpeedY=(float)1.7;
		invDirection=1;
	}

	public void setup() { 
		//--  this runs once after settings() and before draw()
		// Here we can set up other graphical properties,

		colorMode(HSB, 100); //setting color mode to HSB
		
		surface.setResizable(true);
		rectMode(CORNER);
		// -- create our objects
		// there is no need to load some often-used sprites more than once though
		sqExplosion=new PrSprite(this, "expl1/explosion0_", 100, "tga");
		sqSprite=new PrSprite(this, "invader.png");
		bulletSprite=new PrSprite(this, "bullet.png");
		shipSprite=new PrSprite(this, "ship.png");
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
		surface.setTitle("Invaders ("+Math.round(frameRate) + " fps)");
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
							shooter.oy, BULLET_LEN, BULLET_WIDTH, color(0,60,100));
					bullets[i].sprite=bulletSprite;
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
						                      objSize, color((float)hue,30, 90));
				squares[i][j].sprite=sqSprite;
				squares[i][j].setDeathAnimation(sqExplosion);
			}
		}
		liveInvaders=numCols*numRows;
		// -- create the shooter object
		int shipSize=objSize+objSize/2;
		shooter=new PrShooter(this, width/2-shipSize/2, height-shipSize-4, shipSize, color(0,80,80));
		shooter.sprite = shipSprite;
		// -- create bullet array
		bullets=new PrBullet[MAX_BULLETS];
	}

	public void drawObjects() { //simply call the draw() method of *all* objects
		// -- draw the grid:
		boolean sideHit=false; //if the invaders group hit left or right side of the window
		float advanceY=0;
		if (actionStarted) {
			for (int r=0;r<numRows; r++) {
				for (int c=0;c<numCols; c++) {
					if (!squares[r][c].dead) {
						float newX=squares[r][c].ox+invSpeedX*invDirection;
						//did we hit right side?
						if (newX+objSize >= width) {
							sideHit=true;
							invDirection=-1;
							break;
						}
						//did we hit left side?
						if (newX <= 0) {
							sideHit=true;
							invDirection=1;
							break;
						}
					} //alive invader
				} //for each column
				if (sideHit) break;
			} //for each row
			// we checked for moving direction changes, now advance the invaders accordingly
			if (sideHit) {
				advanceY = invSpeedY;
				invSpeedX += 0.08;
			}
		} //if actionStarted
		for (int r=0;r<numRows; r++) {
			for (int c=0;c<numCols; c++) {
				if (actionStarted) {
				    squares[r][c].advance(invSpeedX*invDirection, advanceY);
				    if (squares[r][c].oy+objSize>=shooter.oy) 
				    	gameLost=true;
				}
			    squares[r][c].draw();
			} //for each column
		} //for each row
	    if (liveInvaders==0 && winFrame > 0 && frameCount > winFrame + 120) {
	    	gameOver=true;
	    	actionStarted=false;
	    }

		//draw the bullets first, so if they overlap the shooter they should be hidden behind it
		for (int i=0;i<bullets.length;i++) {
			if (bullets[i]!=null) {
				bullets[i].oy-=BULLET_SPEED; //move the bullet
				//-- check for collision to any square in the new location
				for(PrObj[] row: squares) {
					if (row[0].oy>bullets[i].oy+BULLET_LEN) break; //bullet above this row, no chance to hit any more squares 
					if (bullets[i].oy>row[0].oy+objSize) continue; //bullet below this row, go to next row; 
					for (int s=0;s<row.length;s++) {
						if (row[s].ox>bullets[i].ox+BULLET_WIDTH) break; //bullet to the left of this square, no chance to hit any more in this row 
						if (bullets[i].ox>row[s].ox+objSize) continue; //bullet to the right of this square
						if (row[s].dead) continue;
					    if (bullets[i].hit(row[s])) {
					    	row[s].kill();
					    	liveInvaders--;
					    	if (liveInvaders==0) winFrame = frameCount;
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
		if (gameLost) {
			actionStarted=false;
			gameOver=true;
		}
		// -- not drawing anything else if the game hasn't started
		if (!actionStarted) {
			if (gameOver) { 
				showText(gameLost ? "GAME OVER - You lost!" : "YOU WIN!");
			}
			else 
				showText("Resize, then press SPACE to start");
			return;
		}
	}

	void showText(String s) {
		textSize(24);
		fill(0,0,80);
		noStroke();
		int twidth=(int)textWidth(s)+40;
		rect((float)(0.5*(float)(width-twidth)), height - baseDist + (float)objSize*(float)0.75, 
				(float)twidth, (float)baseDist * (float)0.5-(float)objSize);
		fill(0,80,70);
		textAlign(CENTER, CENTER);
		text(s, width/2, (float)height-(float)baseDist*(float)0.5-(float)objSize);
	}


}
