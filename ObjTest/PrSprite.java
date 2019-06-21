import processing.core.*;

public class PrSprite {
	 PApplet pr;
	 PImage[] images;
	 int numFrames;
	 int width;
	 int height;
	 //int frame;
	 PrSprite(PApplet app, String imagePrefix, int count, String format) {
		pr=app;
	    numFrames = count;
	    images = new PImage[numFrames];
        //image numeric suffix MUST start at 1, not 0 !
	    for (int i = 1; i <= numFrames; i++) {
	      // Use nf() to number format 'i' into four digits
	      String filename = imagePrefix + String.format("%03d", i) + "."+format;
	      images[i-1] = pr.loadImage(filename);
	    }
	    width=images[0].width;
	    height=images[0].height;
	  }
	 
	 PrSprite(PApplet app, String imagePath) {
		 pr=app;
		 numFrames=1;
		 images=new PImage[numFrames];
		 images[0]=pr.loadImage(imagePath);
	     width=images[0].width;
	     height=images[0].height;
	 }
	 /*
	  void display(float xpos, float ypos, float width, float height) {
	    frame = (frame+1) % imageCount;
	    pr.image(images[frame], xpos, ypos, width, height);
	  }
     */
	 void show(float xpos, float ypos, float imgWidth, float imgHeight) {
		 pr.image(images[0], xpos, ypos, imgWidth, imgHeight);
	 }
	 //Warning: frame value is 1 based, not 0 based!
	 void show(int frame, float xpos, float ypos, float imgWidth, float imgHeight) {
		pr.image(images[frame-1], xpos, ypos, imgWidth, imgHeight);
	 }  
}
