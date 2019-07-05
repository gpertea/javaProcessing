
public class PrSquare extends PrObj implements PrShowable, PrClickable {

	PrSquare(PrApp parent, float ax, float ay, float asize, int acol) {
		super(parent, ax, ay, asize, acol);
	}
	
	public void show() {
		   //  Our custom method which draws this object 
		   //  on the parent application canvas,  every frame
		   // We will make use of pr. graphical methods here!
		   //mouseOver=contains(pr.mouseX, pr.mouseY); //test for every frame!
		   //int mcol=col;
		   //if (mouseOver)
			//   mcol=pr.color(pr.hue(col), pr.saturation(col), pr.brightness(col)+10);
		   pr.noStroke();
		   //if (mousePressedL) pr.stroke(80);
		   pr.fill(col);
		   pr.rect(x, y, size, size);  // draw a square
		 }

	public boolean contains(float ax, float ay) {
		  return (ax>=x && ax<=x+size && ay>=y && ay<=y+size);
	}
	
	public void mouseLClick() {
		shiftHue();
	}
	public void mouseRClick() { }

}
