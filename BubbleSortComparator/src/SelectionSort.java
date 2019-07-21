import java.util.*;

class sortByName implements Comparator<Student> {
	public int compare(Student a, Student b) {
		return b.firstName.compareTo(a.firstName);
	}
}

class sortByLast implements Comparator<Student> {
	public int compare(Student a, Student b) {
		return b.lastName.compareTo(a.lastName);
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

public class SelectionSort {

	public static <T> void genSelSort(ArrayList<T> arr,  Comparator cmp) {
		for (int o = 0; o < arr.size(); o++) { //outer loop
			int minIdx = o;
			for (int j=o+1;j<arr.size();j++) {
				if (cmp.compare(arr.get(j), arr.get(minIdx)) > 0) {
					minIdx=j;
				}
			}
			if (minIdx!=o) {
				T temp=arr.get(minIdx);
				arr.set(minIdx, arr.get(o));
				arr.set(o, temp);
			}
		}
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
		students.add(new Student("Krusty", "Krab", 'E'));
		students.add(new Student("Donald", "Trump", 'G'));
		System.out.println("Before sorting: "+students);
		genSelSort(students, new sortByGrade());
		System.out.println("After sorting by grade: "+students);
		genSelSort(students, new sortByName());
		System.out.println("After sorting by first name: "+students);
		genSelSort(students, new sortByLast());
		System.out.println("After sorting by last name: "+students);
	}
}
