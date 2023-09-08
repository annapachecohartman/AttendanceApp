package application;
	
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.AttendanceApp;
import application.AttendanceLog;
import application.Log;
import application.Student;
import application.StudentRoster;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root,620,565);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
//		System.out.println("Hello! Welcome to the Attendance App. Please enter the choice that you would like as follows: ");
//		System.out.println("0 - Exit \n A - load a log \n B- print the log collection \n C - print the number of items in the log collection");
//		System.out.println("D - load a roster \n E - print the roster collection \n F - print the number of items in the roster collection");
//		System.out.println("After a roster and log are loaded, you may use the attendance app: ");
//		System.out.println("G - list all students not in class \n H - list all times checking in and out for a particular student \n I - list all times checked in for a student and date");
//		System.out.println("J - list all students that are late to a class at a time \n K - get the first student to swipe in to class \n L - print attendance data for a particular student");
//		System.out.println("M - whether a given student is present \n N - a list of all students who checked in on a date \n O - a list of all students who checked in before a particular time");
//		System.out.println("P - list of students who have attended a given number of days \n Q - get the first student who entered on a particular day");
//		System.out.println("R - print a particular query list, S - print the number of items in the query list.");
//		System.out.println("\n");
//		Scanner in = new Scanner(System.in);
//		AttendanceLog aLog = new AttendanceLog();
//		StudentRoster sRost = new StudentRoster();
//		AttendanceApp newApp = new AttendanceApp();
//		String flogname;
//		String frostname;
//		boolean loadlog = false;
//		boolean loadrost = false;
//		boolean ready = false;
//		boolean stop = false;
//		List list = new ArrayList();
//		while(!stop){
//			System.out.println("Enter your command here: ");
//			String input = in.next();	
//			if(loadlog && loadrost) {
//				ready = true;
//			}
//			if(input.equals("0")) {
//				System.out.println("Exiting program. Thanks! ");
//				System.exit(1);
//			} else if (input.equals("A")) {
//				System.out.println("Please enter your filename: ");
//				flogname = in.next();
//				aLog.load_log(flogname);
//				newApp.setLog(flogname);
//				System.out.println("... Loaded successfully.");
//				loadlog = true;
//			} else if (input.equals("B")) {
//				System.out.println("Printing collection: ");
//				aLog.print_collection();
//			} else if (input.equals("C")) {
//				System.out.println("Printing log count: ");
//				aLog.print_count();
//			} else if(input.equals("D")) {
//				System.out.println("Please enter your filename: ");
//				frostname = in.next();
//				sRost.load_roster(frostname);
//				newApp.setRoster(frostname);
//				System.out.println("... Loaded successfully.");
//				loadrost = true;
//			} else if(input.equals("E")) {
//				System.out.println("Printing collection: ");
//				sRost.print_collection();
//			} else if (input.equals("F")) {
//				System.out.println("Printing roster count: ");
//				sRost.print_count();
//			} else if (input.equals("G")) {
//				if(ready) {
//					System.out.println("****** Students missing in class *******");
//				    list = newApp.list_students_not_in_class();
//				} else {
//					System.out.println("Please load a roster and log first.");
//				}
//			} else if (input.equals("I")) {
//				if(ready) {
//					System.out.println("****** Check in times for all students who attended *******");
//					list = newApp.list_all_times_checked_in();
//				} else {
//					System.out.println("Please load a roster and log first.");
//				}
//			} else if (input.equals("H")) {
//				if(ready) {
//					System.out.println("Please provide a first name: ");
//					String first = in.next();
//					System.out.println("Please provide a last name: ");
//					String last = in.next();
//					System.out.println("****** List all swipe in and out for a student *******");
//					Student s = sRost.findStudent(last, first);
//					newApp.list_all_times_checking_in_and_out(s);
//				} else {
//					System.out.println("Please load a roster and log first.");
//				}
//			} 
//			else if (input.equals("J")) {
//				if(ready) {
//					System.out.println("Please provide a timestamp string in the form \"hr:min:sec, MM/D/YEAR\": ");
//					in.nextLine();
//					String time = in.nextLine();
//					System.out.println("****** Students that arrived late *******");
//					list = newApp.list_students_late_to_class(time);
//				} else {
//					System.out.println("Please load a roster and log first.");
//				}
//			} else if(input.equals("L")) {
//
//				if(ready) {
//					System.out.println("Please provide a first name: ");
//					String first = in.next();
//					System.out.println("Please provide a last name; ");
//					String last = in.next();
//					Student s = new Student();
//					s = sRost.findStudent(last, first);
//					System.out.println("****** Looking up Student Attendance Data *******");
//					newApp.print_attendance_data_for_student(s);
//				} else {
//					System.out.println("Please load a roster and log first.");
//				}
//			} else if(input.equals("M")) {
//				if(ready) {
//					System.out.println("Please provide a first name: ");
//					String first = in.next();
//					System.out.println("Please provide a last name; ");
//					String last = in.next();
//					System.out.println("Please provide a date  in the form \"MM/D/YEAR\":");
//					String date = in.next();
//					System.out.println("****** Looking to see if Student was present on date *******");
//					Student s = new Student();
//					s = sRost.findStudent(last, first);
//					boolean present = newApp.is_present(s, date);
//					System.out.println(present);
//				} else {
//					System.out.println("Please load a roster and log first.");
//				}
//		
//			} else if (input.equals("N")) {
//				if(ready) {
//					System.out.println("Please provide a date in the form \"MM/D/YEAR\" : ");
//					String d = in.next();
//					System.out.println("****** Students present on this date *******");
//					list = newApp.list_all_students_checked_in(d);
//				} else {
//					System.out.println("Please load a roster and log first.");
//				}
//			} else if (input.equals("O")) {
//				if(ready) {
//					System.out.println("Please provide a date in the form \"MM/D/YEAR\" : ");
//					String d = in.next();
//					System.out.println("Please provide a time in the form \"hr:mn:sc\" :");
//					String t = in.next();
//					System.out.println("****** Students present on date and before a time assigned *******");
//					list = newApp.list_all_students_checked_in_before(d, t);
//				} else {
//					System.out.println("Please load a roster and log first.");
//				}
//			} else if (input.equals("P")) {
//				if(ready) {
//					System.out.println("Please provide an integer number for days of attendance: ");
//					int days = Integer.parseInt(in.next());
//					System.out.println("****** Those who attended " + days + " classes *******");
//					list = newApp.list_students_attendance_count(days);
//				} else {
//					System.out.println("Please load a roster and log first.");
//				}
//			} else if (input.equals("Q") || input.equals("K")) {
//				if(ready) {
//					System.out.println("Please provide a date in the form \"MM/D/YEAR\" : ");
//					String d = in.next();
//					System.out.println("****** First student to enter on " + d + " *******");
//					System.out.println(newApp.get_first_student_to_enter(d));
//				} else {
//					System.out.println("Please load a roster and log first.");
//				}
//			} else if (input.equals("R")) {
//				newApp.print_query_list(list);
//			} else if (input.equals("S")) {
//				newApp.print_count(list);
//			} else {
//				System.out.println("Invalid command: try again.");
//			}
//	}
	}
}
