
public class PrSquare extends PrObj implements PrShowable, PrHilightable {

	PrSquare(PrApp parent, float ax, float ay, float asize, int acol) {
		super(parent, ax, ay, asize, acol);
	}
	
	public boolean hilight() { //return the "brightened" version of the given color
   	    if (contains(pr.mouseX, pr.mouseY)) {
			   pr.strokeWeight((float)1.5);
			   pr.stroke(0,0,100);
			   pr.fill(pr.color(pr.hue(col), pr.saturation(col), pr.brightness(col)+10));
			   return true;
		   }
		return false;
	}

	public boolean contains(float ax, float ay) {
		  return (ax>=x && ax<=x+size && ay>=y && ay<=y+size);
	}
	
	public void show() {
		   //  Our custom method which draws this object 
		   //  on the parent application canvas,  every frame
		   // We will make use of pr. graphical methods here!
		   if (!hilight()) {
			      pr.fill(col);
			      pr.noStroke();
		   }
		   pr.rect(x, y, size, size);  // draw a square
		 }

	/*
	public void mouseLClick() {
		shiftHue();
	}
	public void mouseRClick() { }
    */
}
