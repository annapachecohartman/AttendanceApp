package application;
import java.util.*;

public class Log {

	public String lastName;
	public String firstName;
	public List<String> checkInandOut = new ArrayList<String>();
	
	public Log() {
	}
	
	public Log(String in) {
		List<String> parts = Arrays.asList(in.split(", "));
		lastName = parts.get(0);
		firstName = parts.get(1);
		String checkIn = parts.get(2) + ", " + parts.get(3);
		checkInandOut.add(checkIn);
		
	}
	public String getLastName() {
		return lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public List<String> getCheckInOut(){
		return checkInandOut;
	}
	public void setCheckOut(String input) {
		List<String> parts = Arrays.asList(input.split(", "));
		String checkOut = parts.get(2) + ", " + parts.get(3);
		checkInandOut.add(checkOut);
	}
	
	public String toString() {
		return lastName + ", " + firstName + " " + checkInandOut;
	}
}