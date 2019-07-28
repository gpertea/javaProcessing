import java.util.*;
@SuppressWarnings({ "rawtypes", "unchecked" })

public class Sorting {
	
    public static int bsearch(ArrayList<Comparable> data, Comparable findMe, int start, int end) throws NullPointerException {
         if (start >= end) {
        	 return -1;
         }
         int midpt;
         midpt = (start + end)/2;
         int compareTest=data.get(midpt).compareTo(findMe);
         if (compareTest == 0)
             return midpt;
         if (compareTest > 0)
             return bsearch(data, findMe, start, midpt);
         // compareTest < 0
         return bsearch(data, findMe, midpt+1, end);
    }
    
    public static int bsearch(ArrayList<Comparable> data, Comparable findMe) {
    	return bsearch(data, findMe, 0, data.size()-1);
    }
    
    public static void mergeSort(ArrayList<Comparable> data) throws NullPointerException {
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
        mergeSort(leftData);
        mergeSort(rightData);
        int l = 0, r = 0;
        /*** TODO: Write a while loop that repeats until either l is greater than or equal
                   to the size of leftData, or r is greater than or equal to the size of
                   rightData ***/
        //data.clear();
        while (l<leftData.size() && r<rightData.size())
        {
             /*** TODO: Write a selection statement that is true when the element in
                        leftData referenced by l is less than the element in rightData
                        referenced by r ***/
             if (leftData.get(l).compareTo(rightData.get(r))<0) {
            	  data.set(l+r, leftData.get(l));
                  l++;
             } else {
            	 data.set(l+r, rightData.get(r));
                 r++;
             }
        }
        /*** TODO: Write a loop that repeats until l is greater than or equal to
                   the size of leftData ***/
        for (;l<leftData.size();l++)
        {
             /*** TODO: Set the element of data referenced by l+r to the element
                        of leftData referenced by l, and increment l ***/
        	data.set(l+r, leftData.get(l));
        }

        /*** TODO: Write a loop that repeats until r is greater than or equal to
                   the size of rightData ***/
        for (;r<rightData.size();r++)
        {
             /*** TODO: Set the element of data referenced by l+r to the element
                        of rightData referenced by r, and increment r ***/
        	//data.set(l+r, rightData.get(r));
        	data.set(l+r, rightData.get(r));
        }
        return;
   }

    public static void swap(ArrayList<Comparable> data, int i, int j) {
    	Comparable temp=data.get(i);
    	data.set(i, data.get(j));
    	data.set(j, temp);
    }
    

    public static void quickSort(ArrayList<Comparable> data) {
    	quickSort(data, 0, data.size()-1);
    }
    
    public static void quickSort(ArrayList<Comparable> data, int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        Comparable pivot = data.get((lowerIndex+higherIndex)/2);
        // Partition into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which 
             * is greater then the pivot value, and also we will identify a number 
             * from right side which is less then the pivot value. Once the search 
             * is done, then we exchange both numbers.
             */
            while (data.get(i).compareTo(pivot) < 0) {
                i++;
            }
            while (data.get(j).compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                swap(data, i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively as needed
        if (lowerIndex < j)
            quickSort(data, lowerIndex, j);
        if (i < higherIndex)
            quickSort(data, i, higherIndex);
    }

    public static void quickSort2(ArrayList<Comparable> data) {
    	quickSort2(data, 0, data.size()-1);
    }
    
    
    public static int partition(ArrayList<Comparable> arr, int start, int end) {
        Comparable pivot = arr.get(end);
        for(int i=start; i<end; i++){
            if(arr.get(i).compareTo(pivot)<0) {
            	swap(arr, start, i);
                start++;
            }
        }
        Comparable temp = arr.get(start);
        arr.set(start, pivot);
        arr.set(end, temp);
        return start;
    }
    
    public static void quickSort2(ArrayList<Comparable> data, int start, int end) {
        int pivotIdx = partition(data, start, end);
        if(pivotIdx-1>start) {
            quickSort(data, start, pivotIdx - 1);
        }
        if(pivotIdx+1<end) {
            quickSort(data, pivotIdx + 1, end);
        }
    }
   
   public static void quickSort3(ArrayList<Comparable> arr) {
	   quickSort3(arr, 0, arr.size()-1);
   }
   
   public static void quickSort3(ArrayList<Comparable> arr, int l, int r) {
	   int i, j;
	   do {
	      i = l; j = r;
	      Comparable pivot = arr.get((l + r) >> 1);
	      do {
	        while (arr.get(i).compareTo(pivot) < 0) i++;
	        while (arr.get(j).compareTo(pivot) > 0) j--;
	        if (i <= j) {
	          swap(arr, i, j);
	          i++; j--;
	          }
	        } while (i <= j);
	      if (l < j) quickSort3(arr, l, j);
	      l = i;
	   } while (i < r);
   } 
   
   public static void searchReport(ArrayList<Comparable> data, Comparable toFind, int idx) {
	   if (idx<0)
		   System.out.println("Query '"+toFind+ "' NOT found!");
	   else
		   System.out.println("Query '"+toFind+ "' found at index "+idx + " ("+data.get(idx)+")");
   }
    
   public static void main(String[] args) {
        Random rnd = new Random(2019);
        ArrayList<Comparable> myList = new ArrayList<Comparable>();
        long startTime = 0;
        long endTime = 0;

        for (int i = 0; i < 250; i++) {
             myList.add(rnd.nextInt(100));
        }
        ArrayList<Comparable> myList2 = new ArrayList<Comparable>(myList);
        ArrayList<Comparable> myList3 = new ArrayList<Comparable>(myList);
        /*
		myList.add(new Student("Anne", "Jolie", 'B'));
		myList.add(new Student("Darth", "Vader", 'F'));
		myList.add(new Student("Anne", "Frank", 'A'));
		myList.add(new Student("Zoe", "Saldana", 'B'));
		myList.add(new Student("Tom", "Cruise", 'D'));
		myList.add(new Student("Tom", "Hanks", 'A'));
		myList.add(new Student("Colin", "Hanks", 'A'));
		myList.add(new Student("Bobby", "Brown", 'C'));
		myList.add(new Student("Krusty", "Krab", 'E'));
		myList.add(new Student("Donald", "Trump", 'G'));
        */
        System.out.println("Initial: " +myList);

        try {
            startTime = System.nanoTime();
             //mergeSort(myList);
             quickSort(myList);
             endTime = System.nanoTime();
        } catch (NullPointerException e) {
             e.printStackTrace();
        }
		System.out.println("QSorted1: "+myList);
        System.out.println("Quick sort 1 took " + (int)((endTime - startTime)/1000) + " milliseconds to complete.");
        startTime = System.nanoTime();
		quickSort2(myList2);
        endTime = System.nanoTime();
		System.out.println("QSorted2: " +myList2);
		if (!myList2.equals(myList)) {
		  System.out.println("Error: sorted myList2 not equal to myList !!!");
		  System.exit(2);
		}
        System.out.println("Quick sort 2 took " + (int)((endTime - startTime)/1000) + " milliseconds to complete.");

        
        startTime = System.nanoTime();
		quickSort3(myList3);
        endTime = System.nanoTime();
		System.out.println("QSorted3: " +myList3);
		if (!myList3.equals(myList)) {
		  System.out.println("Error: sorted myList3 not equal to myList !!!");
		  System.exit(2);
		}
        System.out.println("Quick sort 3 took " + (int)((endTime - startTime)/1000) + " milliseconds to complete.");

        /*
        ArrayList<Student> queries=new ArrayList<Student>();
        queries.add(new Student("Anne", "Jolie"));
        queries.add(new Student("Tom", "Cruise"));
        queries.add(new Student("Bob", "Hanks"));
        queries.add(new Student("Tom", "Hanks"));
        */
        for (int q=36;q<=46;q+=2) {
            Integer toFind=new Integer(q);
            int foundIdx=bsearch(myList2, toFind);
            searchReport(myList2, toFind, foundIdx);
       }

   }
	
}
