public class Student {
	String firstName;
	String lastName;
	char grade;
	Student( String first, String last, char gr) {
		firstName=first;
		lastName=last;
		grade=gr;
	}
	public String toString() {
		return firstName + " "+lastName + " ["+ Character.toString(grade) +"]";
	}
}
