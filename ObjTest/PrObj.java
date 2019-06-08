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
 int deathFrame;
 static int DeathFrames=20; //total frame count for death animation
 PrObj(PrApp parent, float x, float y, float size, int col) { //constructor!
   pr=parent; // so we can use all PApplet methods/fields
   ox=x;
   oy=y;
   osize=size;
   mouseOver=false;
   ocol=col;
   mousePressedL=false;
   mousePressedR=false;
   //.. any other object fields initialization can be here
   dead=false;
   deathFrame=0;
 }
 /*
 void setPos(float x, float y) {
	ox=x;
	oy=y;
 }
 
 void setColor(int color) {
	ocol=color; 
 }
 */
 
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
   //ocol=pr.color(0,80,80);
   killOrResurect();
 }
 
 void killOrResurect() {
	 if (dead) {
		 dead=false;
		 deathFrame=0;
	 } else {
	   dead=true;
	 }
	 
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
 
 void killAnim() {
    deathFrame++;
	float shrinkDelta=(float)(deathFrame * osize)/DeathFrames;
	shrinkDelta/=2;
	pr.fill(ocol); 
	pr.rect(ox+shrinkDelta, oy+shrinkDelta, osize-shrinkDelta*2, osize-shrinkDelta*2);
 }
 
 void draw() {
   //  Our custom method which draws this object 
   //  on the parent application canvas,  every frame
   // We will make use of pr. graphical methods here!
	 
   //special case: object is dead/dying:
   if (dead) {
		if (deathFrame<DeathFrames) killAnim();
		return;
   }
   int col=ocol;
   mouseOver=contains(pr.mouseX, pr.mouseY); //test for every frame!
   if (mouseOver)
	   col=pr.color(pr.hue(col), pr.saturation(col), pr.brightness(col)+10);
   pr.noStroke();
   if (mousePressedL) pr.stroke(80);
   pr.fill(col);
   pr.rect(ox, oy, osize, osize);  // draw a square
 }
}
