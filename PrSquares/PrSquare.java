import processing.core.*;

public class PrSquare { //a simple rectangle with 
	PrApp pr; //holds a reference to the parent window
	float ox,oy;  //top left corner coordinates
	float osize;  //width and height
	int color;  //fill color

	PrSquare(PrApp parent, float topLeftX, float topLeftY, float width, int _color) {
		  pr=parent; //link the parent window so we can use its methods
		  ox=topLeftX;
		  oy=topLeftY;
		  osize=width;
		  color=_color;
	}

	boolean contains(float x, float y) {
		//true if it contains the point at coordinates x,y)
		return (x>=ox && x<=ox+osize && y>=oy && y<=oy+osize);
	}

	void draw() {
		int cl=color;
		boolean mouseOver=contains(pr.mouseX, pr.mouseY);
		if (mouseOver)
			 cl=pr.color(pr.hue(color), pr.saturation(color), pr.brightness(color)+10);
		pr.fill(cl);
	    pr.rect(ox,oy,osize,osize);
	}


}
