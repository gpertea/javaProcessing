import java.util.*;

class NameComparator implements Comparator<Student> {
	public int compare(Student a, Student b) {
		return b.firstName.compareTo(a.firstName);
	}
}

class LastComparator implements Comparator<Student> {
	public int compare(Student a, Student b) {
		return b.lastName.compareTo(a.lastName);
	}
}

class GradeComparator implements Comparator<Student> {
	public int compare(Student a, Student b) {
		if (a.grade>b.grade) return -1;
		if (a.grade<b.grade) return 1;
		return 0;
	}
}

@SuppressWarnings({ "rawtypes", "unchecked" })

public class SelectionSort {

	public static void selectionSort(ArrayList arr,  Comparator cmp) {
		for (int o = 0; o < arr.size(); o++) { //outer loop
			int minIdx = o;
			for (int j=o+1;j<arr.size();j++) {
				if (cmp.compare(arr.get(j), arr.get(minIdx)) > 0) {
					minIdx=j;
				}
			}
			if (minIdx!=o) {
				Object temp=arr.get(minIdx);
				arr.set(minIdx, arr.get(o));
				arr.set(o, temp);
			}
		}
	}

	
	public static void main(String[] args) {
		ArrayList students=new ArrayList();
		students.add(new Student("Darth", "Vader", 'F'));
		students.add(new Student("Anne", "Frank", 'A'));
		students.add(new Student("Anne", "Jolie", 'B'));
		students.add(new Student("Zoe", "Saldana", 'B'));
		students.add(new Student("Tom", "Cruise", 'D'));
		students.add(new Student("Tom", "Hanks", 'A'));
		students.add(new Student("Colin", "Hanks", 'A'));
		students.add(new Student("Bobby", "Brown", 'C'));
		students.add(new Student("Krusty", "Krab", 'E'));
		students.add(new Student("Donald", "Trump", 'G'));
		System.out.println("Before sorting: "+students);
		selectionSort(students, new GradeComparator());
		System.out.println("After sorting by grade: "+students);
		selectionSort(students, new NameComparator());
		System.out.println("After sorting by first name: "+students);
		selectionSort(students, new LastComparator());
		System.out.println("After sorting by last name: "+students);
	}
}
