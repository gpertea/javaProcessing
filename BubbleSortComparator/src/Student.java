public class Student implements Comparable<Student> {
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
