import processing.core.*;

public class PrApp extends PApplet {
     // references to PrObj object(s) should be declared here
     //any other custom "global" variables can be added here
 PrObj square;
 float objSize;
 int pWidth;
 int pHeight;
 public static void main(String[] args) {
   	 PApplet.main("PrApp");
 }
 public void settings() { 
    //--  this runs once before setup()
	pWidth=800;
	pHeight=600;
    size(pWidth, pHeight); //setup window/canvas size
    //other initialization of custom variables
    objSize=100;
 }
 
 public void setup() { 
  //--  this runs once after settings() and before draw()
  // Here we can set up other graphical properties, 
  // for example:
  colorMode(HSB, 100); //setting color mode to HSB
  createObjects(); //create all our objects 
  surface.setResizable(true);
 }

 public void draw() { 
   // this runs many times a second, must refresh the 
   // window (canvas) content
   if (width!=pWidth || height!=pHeight)
	   onResize();
   background(50);//clear the canvas
   drawObjects(); //draw all our objects
 }
 
 void onResize() {
   square.setPos(width/2-objSize/2, height/2-objSize/2);	 
 }
 
 void createObjects() {
   //  our custom method which creates and instantiates 
   //   properly all our custom PrObj objects declared; 
   //  Must use the constructor PrObj(this, ...)
   square=new PrObj(this, width/2-objSize/2, height/2-objSize/2, objSize, color(0,100,80));
   onResize();
 }

 void drawObjects() {
  // our custom method which calls the draw() method for 
  //  EACH of our custom objects (the PrObj references)
  // declared in the class and created by createObjects()
  square.draw();
 }
}
