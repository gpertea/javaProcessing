import processing.core.*;

@SuppressWarnings("unused")
public class PrObj {
 PrApp pr; //reference to parent application!
 float ox,oy; //object location on the parent canvas
 float osize; //size of the object
 int ocol; //object color
 boolean mouseOver;
 boolean mousePressedL;
 boolean mousePressedR;
 boolean dead; //if true, it's no longer drawn or interacting
 PrSprite sprite;
 PrSprite deathAnimation;
 int deathFrame;
 int totalDeathFrames; //total frame count for death animation
 
 PrObj(PrApp parent, float x, float y, float size, int col) { //constructor!
   pr=parent; // so we can use all PApplet methods/fields
   ox=x;
   oy=y;
   osize=size;
   mouseOver=false;
   mousePressedL=false;
   mousePressedR=false;
   ocol=col;
   //.. any other object fields initialization can be here
   dead=false;
   deathFrame=0;
   sprite=null;
   deathAnimation=null;
   totalDeathFrames=20;
 }
 
 PrObj(PrApp parent, float x, float y, float size, int col, String imgPath) {
	   pr=parent; // so we can use all PApplet methods/fields
	   ox=x;
	   oy=y;
	   osize=size;
	   mouseOver=false;
	   ocol=col;
	   mousePressedL=false;
	   mousePressedR=false;
	   //.. any other object fields initialization can be here
	   sprite=new PrSprite(pr, imgPath);
	   totalDeathFrames=20;
	   dead=false;
	   deathFrame=0;
 }

 PrObj(PrApp parent, float x, float y, float size, int col, PrSprite asprite) {
	   pr=parent; // so we can use all PApplet methods/fields
	   ox=x;
	   oy=y;
	   osize=size;
	   mouseOver=false;
	   ocol=col;
	   mousePressedL=false;
	   mousePressedR=false;
	   //.. any other object fields initialization can be here
	   sprite=asprite;
	   totalDeathFrames=20;
	   dead=false;
	   deathFrame=0;
 }

 
 void setPos(float x, float y) {
	ox=x;
	oy=y;
 }
 
 void setColor(int color) {
	ocol=color; 
 }
 
 void mouseUpL() {
     if (mousePressedL) mouseClickL();
     mousePressedL=false;
 }
 
 
  void mouseUpR() {
	 if (mousePressedR) mouseClickR();
	 mousePressedR=false;
 }

 void mouseDownL() {
     mousePressedL=true;	 
 }
 
 void mouseDownR() {
     mousePressedR=true;	  
 }

 void mouseClickL() {
	 shiftHue();
 }
 
 void mouseClickR() {
	 
 }
 
 void kill() {
	 dead=true;
 }
 
 
 void shiftHue() {
	float hue=pr.hue(ocol);
	hue+=4;
	if (hue>100) hue=0;
	ocol=pr.color(hue, pr.saturation(ocol), pr.brightness(ocol));
 }
 
 boolean contains(float x, float y) {
	 return (x>=ox && x<=ox+osize && y>=oy && y<=oy+osize);
 }
 
 void killAnimation() {
	 deathFrame++;
	 if (deathAnimation!=null) {
		 float tadj=(float)100.0/(totalDeathFrames/2);
		 if (deathFrame<=totalDeathFrames/2) {
			 pr.tint(pr.hue(ocol),pr.saturation(ocol), 90, 100-tadj*deathFrame);
			 sprite.show(ox, oy, osize, osize);
		 }
		 if (deathFrame<=totalDeathFrames/2)
			 pr.tint(pr.hue(ocol), pr.saturation(ocol),90, 100); //set total opacity
		 else   pr.tint(pr.hue(ocol), pr.saturation(ocol),90, 100-tadj*(deathFrame-totalDeathFrames/2));   
		 deathAnimation.show(deathFrame, ox-osize/2, oy-osize/2-osize/8, osize+osize, osize+osize);
		 return;
	 }
	 float shrinkDelta=(float)(deathFrame * osize)/totalDeathFrames;
	 shrinkDelta/=2;
	 pr.fill(ocol); 
	 pr.rect(ox+shrinkDelta, oy+shrinkDelta, osize-shrinkDelta*2, osize-shrinkDelta*2);
 }

 void setDeathAnimation(String imgprefix, int count, String format) {
	 deathAnimation=new PrSprite(pr, imgprefix, count, format);
	 totalDeathFrames=deathAnimation.numFrames;
	 System.out.println("Total death frames: "+totalDeathFrames);
 }

 void setDeathAnimation(PrSprite asprite) {
	 deathAnimation=asprite;
	 if (asprite!=null) totalDeathFrames=deathAnimation.numFrames;
	 					else totalDeathFrames=20;
	 //System.out.println("Total death frames: "+totalDeathFrames);
 }

 
 void draw() {
   //  Our custom method which draws this object 
   //  on the parent application canvas,  every frame
   // We will make use of pr. graphical methods here!
   //mouseOver=contains(pr.mouseX, pr.mouseY); //test for every frame!
   //special case: object is dead/dying:
   if (dead) {
		if (deathFrame<totalDeathFrames) killAnimation();
		return;
   }
   if (sprite!=null)  {
	   //if (mouseOver) pr.tint(pr.hue(ocol),30, 100,100);
	   //else
		   pr.tint(pr.hue(ocol),30, 90, 100); //set total opacity but with ocol hue
	   sprite.show(ox, oy, osize, osize);
	   return;
   }
   //-- plain squares if no sprite is loaded:
   int col=ocol;
   pr.noStroke();
   //if (mouseOver)
   //   col=pr.color(pr.hue(col), pr.saturation(col), pr.brightness(col)+10);
   if (mousePressedL) {
	   pr.strokeWeight(1);
	   pr.stroke(80);
   }
   pr.fill(col);
   pr.rect(ox, oy, osize, osize);  // draw a square
 }
}
