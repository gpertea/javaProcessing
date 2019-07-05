
public class PrCircle extends PrObj implements PrShowable, PrClickable {

	PrCircle(PrApp parent, float ax, float ay, float asize, int acol) {
		super(parent, ax, ay, asize, acol);
	}
	
	public void show() {
	   //  Our custom method which draws this object 
	   //  on the parent application canvas,  every frame
	   // We will make use of pr. graphical methods here!
	   pr.noStroke();
	   pr.fill(col);
	   pr.circle(x+size/2, y+size/2, size);
	}
	
	public boolean contains(float ax, float ay) {
            return (PrApp.dist(x+size/2, y+size/2, ax, ay)<=size/2);		
	}
	
	public void mouseLClick() {
		shiftHue();
	}
	
	public void mouseRClick() { }


}
