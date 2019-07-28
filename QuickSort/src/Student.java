public class Student implements Comparable<Student> {
	String firstName;
	String lastName;
	char grade;
	Student( String first, String last, char gr) {
		firstName=first;
		lastName=last;
		grade=gr;
	}
	Student( String first, String last) {
		firstName=first;
		lastName=last;
	}

	public int compareTo(Student o) {
	  int cmp=lastName.compareTo(o.lastName);
	  if (cmp==0) return firstName.compareTo(o.firstName);
	  else return cmp;
	}
	
	public String toString() {
		String r=firstName + " "+lastName ;
		if (grade>0) r+=" ["+ Character.toString(grade) +"]";
		return r;
	}
}
