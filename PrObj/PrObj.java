import processing.core.*;
@SuppressWarnings("unused")

public class PrObj { //a simple rectangle with 
	PrApp pr; //holds a reference to the parent window
	float ox,oy;  //top left corner coordinates
	int clr;
	PrObj(PrApp parent, float x, float y, int aColor) {
		  pr=parent; //link the parent window so we can use its methods
		  ox=x;
		  oy=y;
		  clr=aColor;
	}
	
	void setPos(float x, float y) {
	  ox=x;
	  oy=y;
	}
	
	boolean contains(float x, float y) {
		//true if it contains the point at coordinates x,y)
		return (x>=ox && x<=ox+40 && y>=oy && y<=oy+40);
	}

	void draw() {
		int cl=clr;
		if (contains(pr.mouseX, pr.mouseY))
			 cl=pr.color(pr.hue(cl), pr.saturation(cl), pr.brightness(cl)+10);
		pr.fill(cl);
	    pr.rect(ox,oy,40, 40);
	}


}
