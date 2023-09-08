package application;
import java.util.*;
public class Student {
	public String firstName;
	public String lastName;
	public Student() {}
	public Student(String line) {
		List<String> names =  Arrays.asList(line.split(", "));
		firstName = names.get(1);
		lastName = names.get(0);
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String toString() {
		return lastName + ", " + firstName;
	}

}

