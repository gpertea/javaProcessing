
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Practice {
    
    public static long selection(ArrayList<Comparable> data) throws NullPointerException {
        Comparable temp;
        int minimum;
        long startTime, endTime;
        startTime = System.nanoTime();

        for (int x = 0; x < data.size(); x++) {
            minimum = x;
             /*** TODO: Set minimum equal to the current value of the outer for-loop ***/
             for (int y = x; y < data.size(); y++) {
                 if (data.get(y).compareTo(data.get(minimum)) <0)
                    minimum = y;
              
             } 
               temp = data.get(minimum);
               data.set(minimum, data.get(x));
               data.set(x, temp);
               
               
             /*** TODO: Swap the value referenced by minimum with the value referenced by
                        the outer for-loop ***/
        }

        endTime = System.nanoTime();
        return endTime - startTime;
        
   }
    
    
     public static int binary(ArrayList<Comparable> data, Comparable findMe, int start, int end) throws NullPointerException {
         
         /*** TODO: Write a selection statement that is true when start is greater than or
                     equal to end ***/
    	  System.out.printf(">> entering binary() with start=%d, end=%d\n",start, end);
          if (start >= end){
        	  //int v=data.get(end).compareTo(findMe);
        	  //System.out.println(" ### start>=end, comparison of "+data.get(end)+" vs "+findMe+" result is "+v); 
        	  //if (v == 0)
              //    return end;
              return data.get(end).compareTo(findMe)==0 ? end : -1;
          }
          int midpt;
          /*** TODO: Calculate the midpoint between start and end and store in midpt (NOTE: 
                     Don't forget to account for where you started in your calculation) ***/
          midpt = (start + end)/2;
          /***TODO: Write a selection statement with the following conditions and outcome
                      If the element referenced by midpt is findMe, return midpt
                      If the element referenced by midpt is greater than findMe, return
                       the result of a recursive call to binary with midpt passed as the
                       new end and start the same
                      If the element referenced by midpt is less than findMe, return the
                       result of a recursive call the binary with midpt passed as the new
                       start and end the same ***/ 
          if (data.get(midpt).compareTo(findMe) == 0)
              return midpt;
          if (data.get(midpt).compareTo(findMe) > 0)
              return binary(data, findMe, start, midpt-1);
          if (data.get(midpt).compareTo(findMe) < 0)
              return binary(data, findMe, midpt+1, end);
          
            return 0;
     }

     public static void main(String[] args) {
          int index = 0;
          Random rnd = new Random(2019);
          ArrayList<Comparable> myList = new ArrayList<Comparable>();
          long startTime = 0;
          long endTime = 0;

          for (int i = 0; i < 50; i++) {
               myList.add(rnd.nextInt(100));
          }
          /*** TODO: Sort the data using any method you wish ***/
          //selection(myList);
          Collections.sort(myList);
          System.out.println(myList);

          try {
               startTime = System.nanoTime();
               index = binary(myList,new Integer(42),0,myList.size()-1);
               endTime = System.nanoTime();
          } catch (NullPointerException e) {
               e.printStackTrace();
          }

          System.out.print("The binary search took ");
          System.out.print((int)((endTime-startTime)/1000000) + " seconds to complete ");
          if (index > -1) {
               System.out.println("and found the element at position " + index + " of our list.");
          } else {
               System.out.println("and did not find the element.");
          }
     }
}
