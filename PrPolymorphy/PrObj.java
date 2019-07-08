//import processing.core.*;

public abstract class PrObj { //abstract square placeholder object (bounding box)
 PrApp pr; //reference to parent application!
 float x,y; //top-left corner of object's bounding box location
 float size; //size of the object
 int col; //object color
 
 PrObj(PrApp parent, float ax, float ay, float asize, int acol) { //constructor!
   pr=parent; // so we can use all PApplet methods/fields
   x=ax;
   y=ay;
   size=asize;
   col=acol;
 }
 public abstract void show();

}
