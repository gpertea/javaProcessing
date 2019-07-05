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
 float deathRotation;
 int deathFrame;
 int totalDeathFrames; //total frame count for death animation
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
   deathRotation=0;
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
 
 void kill() {
	 dead=true;
	 deathRotation = (float) (Math.random()*2*PApplet.PI); 
 }
 
 
 void killOrResurect() {
	 if (dead) {
		 dead=false;
		 deathFrame=0;
	 } else {
	   kill();
	 }
	 
 }
 
 void shiftHue() {
	float hue=pr.hue(ocol);
	hue+=4;
	if (hue>100) hue=0;
	ocol=pr.color(hue, 30, 90);
 }
 
 boolean contains(float ax, float ay) {
	 return (ax>=ox && ax<=ox+osize && ay>=oy && ay<=oy+osize);
 }
 /*
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
 */
 
 void killAnimation() {
	   deathFrame++;
	   if (deathAnimation != null) {
	     float fadingShipFrames = (float) (((float) totalDeathFrames) * (0.25));
	     //for the first fadingShipFrames frames, show the invader ship fading away
	     float explOpacity = 100;
	     float fadingExplFrames = (float) (((float) totalDeathFrames) * (0.75));
	     //for the last fadingExplFrames frames make the explosion sprite fade away
	     if (deathFrame >= fadingExplFrames) {
	         explOpacity = ((float)(totalDeathFrames-deathFrame)*100)/((float)totalDeathFrames/4);
	     }
	     //deathAnimation.show(deathFrame, ox-osize/2, oy-osize/2-osize/8, 
	     //                                2*osize, 2*osize);
	     //the explosion should be shown rotated at deathRotation angle
	     // while it keeps rotating in rotateAnim increments!
	     float rotateAnim = (float)(PApplet.PI/400);
	     if (deathRotation>PApplet.PI) rotateAnim = -rotateAnim;
	     pr.pushMatrix();
	     pr.translate(ox + osize/2, oy + osize/2);
	     pr.rotate(rotateAnim * (float) deathFrame);
	     //for the first fadingShipFrames frames, show the invader ship fading away
	     if (deathFrame <= fadingShipFrames) {
	         pr.tint(ocol, 100 - ((float) deathFrame * 100 / fadingShipFrames));
	         //sprite.show(ox, oy, osize, osize);
	         sprite.show(-osize/2, -osize/2, osize, osize);
	     }

	     pr.rotate(deathRotation);
	     pr.tint(ocol, explOpacity);
	     deathAnimation.show(deathFrame, -osize, -osize, 2*osize, 2*osize);
	     pr.popMatrix();
	     
	     return;
	   }
	   //plain death animation: drawing a shrinking square:
	   float deathSpeed = osize * deathFrame / totalDeathFrames;
	   pr.fill(ocol);
	   pr.rect(ox + deathSpeed/2, oy + deathSpeed/2, 
	           osize - deathSpeed, osize - deathSpeed);
 }
 
 void setDeathAnimation(String imgprefix, int count, String format) {
	 deathAnimation=new PrSprite(pr, imgprefix, count, format);
	 totalDeathFrames=deathAnimation.numFrames;
	 System.out.println("Total death frames: "+totalDeathFrames);
 }
 
 void draw() {
   //  Our custom method which draws this object 
   //  on the parent application canvas,  every frame
   // We will make use of pr. graphical methods here!
   mouseOver=contains(pr.mouseX, pr.mouseY); //test for every frame!
   //special case: object is dead/dying:
   if (dead) {
		if (deathFrame<totalDeathFrames) killAnimation();
		return;
   }
   if (sprite!=null)  {
	   //pr.fill(pr.color(pr.hue(ocol),50, 100));
	   //pr.noStroke();
	   //pr.rect(ox, oy, osize, osize);
	   if (mouseOver) pr.tint(pr.hue(ocol),30, 90,100);
	   else pr.tint(pr.hue(ocol),30, 90, 100); //set total opacity but with ocol hue
	   //System.out.printf("HSB = (%.1f, %.1f, %.1f)\n", pr.hue(ocol), pr.saturation(ocol), pr.brightness(ocol));
	   sprite.show(ox, oy, osize, osize);
	   return;
   }
   int col=ocol;
   if (mouseOver)
	   col=pr.color(pr.hue(col), pr.saturation(col), pr.brightness(col)+10);
   pr.noStroke();
   if (mousePressedL) pr.stroke(80);
   pr.fill(col);
   pr.rect(ox, oy, osize, osize);  // draw a square
 }
}
