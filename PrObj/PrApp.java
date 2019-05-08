import processing.core.*;

public class PrApp extends PApplet {
    PrObj square;
    
	public static void main(String[] args) {
		PApplet.main("PrApp");
	}

	public void settings(){ //this method runs before setup()
		size(800,600); //setup window size
    }

    public void setup(){
		surface.setResizable(true);
    	colorMode(HSB, 100); //setting color mode to HSB, range max 100
    	//create objects vertically centered, spaced at objsize/2 horizontally
    	createObjects();
    }

    public void draw() { 
        background(50); //50% gray background
    	drawObjects();  //draw all objects
    }

    
    void createObjects() {
		square=new PrObj(this, width/2-20, height/2-20, color(80));
    }
   
    public void drawObjects() { //simply call the draw() method of *all* objects
    	square.setPos(width/2-20, height/2-20);
    	square.draw();
    }
}
