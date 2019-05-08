import processing.core.*;

@SuppressWarnings("unused")
public class PrObj {
 PrApp pr; //reference to parent application!
 float ox,oy; //object location on the parent canvas
 float osize;
 int ocol; //object color
 // other custom fields here, as needed

 PrObj(PrApp parent, float x, float y, float size, int col) { //constructor!
   pr=parent; // so we can use all PApplet methods/fields
   ox=x;
   oy=y;
   osize=size;
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
 
 boolean contains(float x, float y) {
	 return (x>=ox && x<=ox+osize && y>=oy && y>=oy+osize);
 }
 
 void draw() {
   //  Our custom method which draws this object 
   //  on the parent application canvas,  every frame
   // We will make use of pr. graphical methods here!
   int col=ocol;
   pr.fill(col); // set the fill color to gray-80
   pr.rect(ox, oy, osize, osize);  // draw a square
 }
}
