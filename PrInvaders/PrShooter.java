
public class PrShooter extends PrObj {
    
	PrShooter(PrApp parent, float x, float y, float size, int col) {
		super(parent, x, y, size, col);
		// TODO Auto-generated constructor stub
	}

	void draw() {
		//if game has started, follow the mouse on the x axis:
		if (pr.actionStarted) {
			ox=pr.mouseX;
			if (ox>pr.width-osize) ox=pr.width-osize;
		}
		//draw a triangle instead!
		pr.stroke(4);
		pr.strokeWeight(1);
		pr.fill(ocol);
		pr.triangle(ox, oy+osize, ox+osize, oy+osize, ox+osize/2, oy);
	}
}
