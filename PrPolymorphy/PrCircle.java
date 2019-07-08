
public class PrCircle extends PrObjCaptioned implements PrHilightable, PrClickable, PrCaptionable {

	PrCircle(PrApp parent, float ax, float ay, float asize, int acol) {
		super(parent, ax, ay, asize, acol);
	}
	
	public boolean contains(float ax, float ay) {
        return (PrApp.dist(x+size/2, y+size/2, ax, ay)<=size/2);		
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

	public void show() {
	   //  Our custom method which draws this object 
	   //  on the parent application canvas,  every frame
	   // We will make use of pr. graphical methods here!
	   if (!hilight()) {
	      pr.fill(col);
	      pr.noStroke();
	   }
	   pr.circle(x+size/2, y+size/2, size);
	   showCaption();
	}
	
	public void mouseLClick() {
		shiftHue();
	}
	
	public void mouseRClick() { 
		pr.destroy(this);
	}


}
