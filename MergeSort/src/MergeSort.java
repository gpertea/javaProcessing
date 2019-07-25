import java.util.*;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MergeSort {
	
    public static int bsearch(ArrayList<Comparable> data, Comparable findMe, int start, int end) throws NullPointerException {
         if (start >= end) {
             return data.get(end).compareTo(findMe)==0 ? end : -1;
         }
         int midpt;
         midpt = (start + end)/2;
         int compareTest=data.get(midpt).compareTo(findMe);
         if (compareTest == 0)
             return midpt;
         if (compareTest > 0)
             return bsearch(data, findMe, start, midpt-1);
         // compareTest < 0
         return bsearch(data, findMe, midpt+1, end);
    }
    
    public static int bsearch(ArrayList<Comparable> data, Comparable findMe) {
    	return bsearch(data, findMe, 0, data.size()-1);
    }
    
    public static void merge(ArrayList<Comparable> data) throws NullPointerException {
        int dataSize=data.size();
        /*** TODO: Write a selection statement that is true when the size of data is 1 ***/
        if (dataSize==1)
               return;
        ArrayList<Comparable> leftData = new ArrayList<Comparable>();
        ArrayList<Comparable> rightData = new ArrayList<Comparable>();
        /*** TODO: Write loops that add elements 0 to dataSize/2  to leftData, and 
                   dataSize/2 + 1 to dataSize to rightData ***/
        for (int i=0;i<dataSize/2;i++)
        	leftData.add(data.get(i));
        for (int i=dataSize/2;i<dataSize;i++)
        	rightData.add(data.get(i));
        /*** TODO: Make two recursive calls to merge, one passing leftData and the
                   other passing rightData ***/
        merge(leftData);
        merge(rightData);
        int l = 0, r = 0;
        /*** TODO: Write a while loop that repeats until either l is greater than or equal
                   to the size of leftData, or r is greater than or equal to the size of
                   rightData ***/
        data.clear();
        while (l<leftData.size() && r<rightData.size())
        {
             /*** TODO: Write a selection statement that is true when the element in
                        leftData referenced by l is less than the element in rightData
                        referenced by r ***/
             if (leftData.get(l).compareTo(rightData.get(r))<0) {
            	  data.add(leftData.get(l));
                  l++;
             } else {
            	 data.add(rightData.get(r));
                 r++;
             }
        }
        /*** TODO: Write a loop that repeats until l is greater than or equal to
                   the size of leftData ***/
        for (;l<leftData.size();l++)
        {
             /*** TODO: Set the element of data referenced by l+r to the element
                        of leftData referenced by l, and increment l ***/
        	data.add(leftData.get(l));
        }

        /*** TODO: Write a loop that repeats until r is greater than or equal to
                   the size of rightData ***/
        for (;r<rightData.size();r++)
        {
             /*** TODO: Set the element of data referenced by l+r to the element
                        of rightData referenced by r, and increment r ***/
        	//data.set(l+r, rightData.get(r));
        	data.add(rightData.get(r));
        }
        return;
   }

   public static void searchReport(ArrayList<Comparable> data, Comparable toFind, int idx) {
	   if (idx<0)
		   System.out.println("Query "+toFind+ " NOT found!");
	   else
		   System.out.println("Query "+toFind+ " found at index "+idx + " ("+data.get(idx)+")");
   }
    
   public static void main(String[] args) {
        Random rnd = new Random(2019);
        ArrayList<Comparable> myList = new ArrayList<Comparable>();
        long startTime = 0;
        long endTime = 0;

        for (int i = 0; i < 50; i++) {
             myList.add(rnd.nextInt(100));
        }
        System.out.println("Initial: " +myList);

        try {
             startTime = System.nanoTime();
             merge(myList);
             endTime = System.nanoTime();
        } catch (NullPointerException e) {
             e.printStackTrace();
        }
        System.out.println(" Sorted: "+ myList);
        System.out.println("The merge sort took " + (int)((endTime - startTime)/1000) + " milliseconds to complete.");
        for (int q=36;q<=46;q+=2) {
            Integer toFind=new Integer(q);
            int foundIdx=bsearch(myList, toFind);
            searchReport(myList, toFind, foundIdx);
        }
   }
	
}
