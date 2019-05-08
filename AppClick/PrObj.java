import processing.core.*;

@SuppressWarnings("unused")
public class PrObj {
 PrApp pr; //reference to parent application!
 float ox,oy; //object location on the parent canvas
 float osize;
 int ocol; //object color
 boolean mouseOver;
 boolean mouseLpressed;
 boolean mouseRpressed;
 // other custom fields here, as needed

 PrObj(PrApp parent, float x, float y, float size, int col) { //constructor!
   pr=parent; // so we can use all PApplet methods/fields
   ox=x;
   oy=y;
   osize=size;
   mouseOver=false;
   mouseLpressed=false;
   mouseRpressed=false;
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
 
 void mouseLup() {
     if (mouseOver) shiftHue();	 
 }
 
 void mouseRup() {
	 
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
   if (contains(pr.mouseX, pr.mouseY)) {
	   float br=pr.brightness(col);
	   mouseOver=true;
	   if (pr.mousePressed) {
		   if (pr.mouseButton==PrApp.LEFT && !mouseLpressed) {
			   mouseLpressed=true;
		   }
		   if (pr.mouseButton==PrApp.RIGHT && !mouseRpressed) {
			   mouseRpressed=true;
		   }
	   }
	   col=pr.color(pr.hue(col), pr.saturation(col), br+10);
   } else {
	   mouseOver=false;
   }
   if (mouseLpressed) pr.stroke(80);
   pr.fill(col); // set the fill color to gray-80
   pr.rect(ox, oy, osize, osize);  // draw a square
 }
}
