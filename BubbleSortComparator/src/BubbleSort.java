import java.util.*;

class Student implements Comparable<Student> {
	String firstName;
	String lastName;
	char grade;
	Student( String first, String last, char gr) {
		firstName=first;
		lastName=last;
		grade=gr;
	}
	public int compareTo(Student other) {
		return Character.compare(grade, other.grade);
	}
	public String toString() {
		return firstName + " "+lastName + " ["+ Character.toString(grade) +"]";
	}
}


class sortByName implements Comparator<Student> {
	public int compare(Student a, Student b) {
		return a.firstName.compareTo(b.firstName);
	}
}

class sortByLast implements Comparator<Student> {
	public int compare(Student a, Student b) {
		return a.lastName.compareTo(b.lastName);
	}
}

class sortByGrade implements Comparator<Student> {
	public int compare(Student a, Student b) {
		if (a.grade>b.grade) return -1;
		else if (a.grade<b.grade) return 1;
		else return 0;
	}
}

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

	public static <T> void genSwap(ArrayList<T> arr, int i, int j)  {
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
	public static <T> void genBubbleUpMax( ArrayList<T> arr, int upTo, Comparator cmp) {
		for (int i = 0; i < upTo; i++) 
			if (cmp.compare(arr.get(i), arr.get(i+1))>0) //comparison!
				genSwap(arr, i, i+1);
	}
	public static <T> void genBubbleSort(ArrayList<T> arr, Comparator cmp) {
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
		/*
		ArrayList<Integer> arr=new ArrayList<>(Arrays.asList(12, 7, 3, 1, -1, 2 , 4));
		System.out.println("Before sorting: "+arr);
		genBubbleSort(arr, new CmpIncreasing());
		System.out.println("After sorting increasingly: "+arr);
		genBubbleSort(arr, new CmpDecreasing());
		System.out.println("After sorting decreasingly: "+arr);
		*/
		ArrayList<Student> students=new ArrayList<Student>();
		students.add(new Student("Darth", "Vader", 'F'));
		students.add(new Student("Anne", "Frank", 'A'));
		students.add(new Student("Zoe", "Saldana", 'B'));
		students.add(new Student("Tom", "Cruise", 'D'));
		students.add(new Student("Tom", "Hanks", 'A'));
		students.add(new Student("Bobby", "Brown", 'C'));
		students.add(new Student("Krusty", "Klown", 'E'));
		System.out.println("Before sorting: "+students);
		
	}
}
