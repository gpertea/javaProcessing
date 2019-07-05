import processing.core.*;

@SuppressWarnings("unused")
public class PrObj {
 PrApp pr; //reference to parent application!
 float x,y; //object location on the parent canvas
 float size; //size of the object (or of its largest dimension)
 int col; //object color
 PrObj(PrApp parent, float ax, float ay, float asize, int acol) { //constructor!
   pr=parent; // so we can use all PApplet methods/fields
   x=ax;
   y=ay;
   size=asize;
   col=acol;
 }
 
 void shiftHue() {
	float hue=pr.hue(col);
	hue+=4;
	if (hue>100) hue=0;
	col=pr.color(hue, pr.saturation(col), pr.brightness(col));
 }
 
}
