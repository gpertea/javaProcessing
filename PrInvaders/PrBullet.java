
public class PrBullet extends PrObj {
    int owidth; //

	PrBullet(PrApp parent, float x, float y, float size, int thickness, int col) {
		super(parent, x, y, size, col);
		// TODO Auto-generated constructor stub
		owidth=thickness;
	}
	
	boolean hit(PrObj target) {
		float leftX   = Math.max( ox, target.ox );
		float rightX  = Math.min( ox + owidth, target.ox + target.osize );
		float topY    = Math.max( oy, target.oy );
		float bottomY = Math.min( oy + osize, target.oy + target.osize );
		return ( leftX < rightX && topY < bottomY ) ;
	}
	
	void draw() {
		pr.noStroke();
		pr.fill(ocol);
		pr.rect(ox, oy, owidth, osize);  // draw a square
	}

}
