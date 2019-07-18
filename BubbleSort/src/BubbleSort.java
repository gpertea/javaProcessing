import java.util.*;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BubbleSort {
    static int recursionDepth=0;

	public static void genSwap(ArrayList<Comparable> arr, int i, int j)  {
        Comparable temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
	public static void genBubbleUpMax( ArrayList<Comparable> arr, int upTo) {
		for (int i = 0; i < upTo; i++) 
			if (arr.get(i).compareTo(arr.get(i+1))>0) //comparison!
				genSwap(arr, i, i+1);
	}
	public static void genBubbleSort(ArrayList<Comparable> arr) {
		for (int o = 0; o < arr.size(); o++) { //outer loop
			int maxIdx = arr.size() - o - 1;
			genBubbleUpMax(arr, maxIdx);
		}
	}

    public static void swap(int arr[], int i, int j)  {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

	
	public static void bubbleUpMax(int arr[], int upTo) {
		for (int i = 0; i < upTo; i++) 
			if (arr[i] > arr[i+1]) //comparison!
				swap(arr, i, i+1);
	}
	
	public static void bubbleSort(int arr[]) {
		System.out.println("Before sorting: "+ Arrays.toString(arr));
		for (int o = 0; o < arr.length; o++) { //outer loop
			int maxIdx = arr.length - o - 1;
			bubbleUpMax(arr, maxIdx);
			System.out.printf("outer iteration %d (inner loop up to %d): ", o+1, maxIdx);
			System.out.println(Arrays.toString(arr));
		}
		System.out.println(" After sorting: "+ Arrays.toString(arr));
	}
	
	// Recursive function to perform bubble sort on sub-array arr[0..upTo]
    public static void bubbleSortRecursive(int arr[], int upTo)  {
		if (recursionDepth==0) 
			System.out.println("Before sorting: "+ Arrays.toString(arr));
    	bubbleUpMax(arr, upTo);
		System.out.printf("Recursion depth %d (inner loop up to %d): ", recursionDepth, upTo);
		System.out.println(Arrays.toString(arr));
		recursionDepth++;
        if (upTo > 0) {
            bubbleSortRecursive(arr, upTo-1);
        }
        else {
        	System.out.println(" Recursion ended: "+ Arrays.toString(arr));
        }
    }
	
	public static void main(String[] args) {
		//int[] myData= {12, 7, 3, 1, -1, 2 , 4};
		ArrayList<Comparable> arr=new ArrayList<>(Arrays.asList(12, 7, 3, 1, -1, 2 , 4));
		System.out.println("Before sorting: "+arr);
		genBubbleSort(arr);
		System.out.println("After sorting: "+arr);
		//myData = {12, 7, 3, 1, -1, 2 , 4};
		//int[] myDataAgain= {12, 7, 3, 1, -1, 2 , 4};
		//bubbleSortRecursive(myDataAgain, myDataAgain.length-1);
	}

	
}
