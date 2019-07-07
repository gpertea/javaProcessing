import processing.core.*;

@SuppressWarnings("unused")
public class PrObj {
 PrApp pr; //reference to parent application!
 float x,y; //object location on the parent canvas
 float size; //size of the object (or of its largest dimension)
 int col; //object color
 String caption;
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
 
 public void showCaption() {
	 if (caption!=null && caption.length()>0) {
			pr.textSize(14);
			pr.fill(0,0,80);
			pr.noStroke();
			float tw=pr.textWidth(caption);
			float th=16; //text height
			pr.rect(x+(size-tw)/2-2, y+size+1, tw+4, th);
			pr.fill(0,80,70);
			pr.textAlign(PConstants.CENTER, PConstants.CENTER);
			pr.text(caption, x+size/2, y+size+th/2);

	 }
 }

 
}
