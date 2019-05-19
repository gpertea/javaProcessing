import processing.core.*;

@SuppressWarnings("unused")
public class PrObj {
 PrApp pr; //reference to parent application!
 float ox,oy; //object location on the parent canvas
 float osize;
 int ocol; //object color
 boolean mouseOver;
 boolean mousePressedL;
 boolean mousePressedR;
 // other custom fields here, as needed

 PrObj(PrApp parent, float x, float y, float size, int col) { //constructor!
   pr=parent; // so we can use all PApplet methods/fields
   ox=x;
   oy=y;
   osize=size;
   mouseOver=false;
   mousePressedL=false;
   mousePressedR=false;
   //.. any other object fields initialization can be here
   ocol=col;
 }
 
 void setPos(float x, float y) {
	ox=x;
	oy=y;
 }
 
 void setColor(int color) {
	ocol=color; 
 }
 
 void mouseUpL() {
     if (mouseOver && mousePressedL) mouseClickL();
     mousePressedL=false;
 }
 
 
  void mouseUpR() {
	 if (mouseOver && mousePressedR) mouseClickR();
	 mousePressedR=false;
 }

 void mouseDownL() {
     if (mouseOver) mousePressedL=true;	 
 }
 
 void mouseDownR() {
     if (mouseOver) mousePressedR=true;	  
 }

 void mouseClickL() {
	 shiftHue();
 }
 
 void mouseClickR() {
 }
 
 void shiftHue() {
	float hue=pr.hue(ocol);
	hue+=2;
	if (hue>100) hue=0;
	ocol=pr.color(hue, pr.saturation(ocol), pr.brightness(ocol));
 }
 
 boolean contains(float x, float y) {
	 return (x>=ox && x<=ox+osize && y>=oy && y<=oy+osize);
 }
 
 void draw() {
   //  Our custom method which draws this object 
   //  on the parent application canvas,  every frame
   // We will make use of pr. graphical methods here!
   int col=ocol;
   pr.noStroke();
   mouseOver=contains(pr.mouseX, pr.mouseY); //test for every frame!
   if (mouseOver)
	   col=pr.color(pr.hue(col), pr.saturation(col), pr.brightness(col)+10);
   if (mousePressedL) pr.stroke(80);
   pr.fill(col); // set the fill color to gray-80
   pr.rect(ox, oy, osize, osize);  // draw a square
 }
}
