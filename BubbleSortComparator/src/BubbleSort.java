import java.util.*;

class CmpIncreasing implements Comparator<Integer> {
	public int compare(Integer a, Integer b) {
		return a.compareTo(b);
	}
}

class CmpDecreasing implements Comparator<Integer> {
	public int compare(Integer a, Integer b) {
		return -a.compareTo(b);
	}
}

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BubbleSort {

	public static void genSwap(ArrayList<Comparable> arr, int i, int j)  {
        Comparable temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
	public static void genBubbleUpMax( ArrayList<Comparable> arr, int upTo, Comparator cmp) {
		for (int i = 0; i < upTo; i++) 
			if (cmp.compare(arr.get(i), arr.get(i+1))>0) //comparison!
				genSwap(arr, i, i+1);
	}
	public static void genBubbleSort(ArrayList<Comparable> arr, Comparator cmp) {
		for (int o = 0; o < arr.size(); o++) { //outer loop
			int maxIdx = arr.size() - o - 1;
			genBubbleUpMax(arr, maxIdx, cmp);
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
	
	public static void main(String[] args) {
		//int[] myData= {12, 7, 3, 1, -1, 2 , 4};
		ArrayList<Comparable> arr=new ArrayList<>(Arrays.asList(12, 7, 3, 1, -1, 2 , 4));
		System.out.println("Before sorting: "+arr);
		genBubbleSort(arr, new CmpIncreasing());
		System.out.println("After sorting increasingly: "+arr);
		genBubbleSort(arr, new CmpDecreasing());
		System.out.println("After sorting decreasingly: "+arr);
		//myData = {12, 7, 3, 1, -1, 2 , 4};
		//int[] myDataAgain= {12, 7, 3, 1, -1, 2 , 4};
		//bubbleSortRecursive(myDataAgain, myDataAgain.length-1);
	}

	
}
