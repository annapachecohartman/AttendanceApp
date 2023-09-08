package application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.event.EventHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class SampleController {
	@FXML FileChooser rosFile = new FileChooser();
	@FXML FileChooser logFile = new FileChooser();
	@FXML TextArea out = new TextArea();
	
	
	@FXML TextField firstName = new TextField();
	@FXML TextField lastName = new TextField();
	@FXML TextField month = new TextField();
	@FXML TextField time = new TextField();
	@FXML TextField days = new TextField();
	
	
	@FXML Button logload = new Button();
	@FXML Button rostload = new Button();
	@FXML Button printlog = new Button();
	@FXML Button printrost = new Button();
	@FXML Button printlogcount = new Button();
	@FXML Button printrostcount = new Button();
	
	@FXML Button G_button = new Button();
	@FXML Button H_Button = new Button();
	@FXML Button I_Button = new Button();
	@FXML Button J_button = new Button();
	@FXML Button KQ_Button = new Button();
	@FXML Button L_Button = new Button();
	@FXML Button M_button = new Button();
	@FXML Button N_Button = new Button();
	@FXML Button O_Button = new Button();
	@FXML Button P_button = new Button();
	@FXML Button R_Button = new Button();
	@FXML Button S_Button = new Button();
	
	@FXML Button submit = new Button();
	
	String first;
	String last;
	String month2;
	String days2;
	String time2;
	
	String title;
	
	public void submitText(ActionEvent e) {
		first = firstName.getText();
		last = lastName.getText();
		month2 = month.getText();
		time2 = time.getText();
		days2 = days.getText();
		if(month2.charAt(3) == '0') {
			month2 = month2.replace("/0", "/");
		}
	}
	
	boolean loadrost = false;
	boolean loadlog = false;

	AttendanceLog aLog = new AttendanceLog();
	StudentRoster sRost = new StudentRoster();
	AttendanceApp newApp = new AttendanceApp();
	String flogname;
	String frostname;
	boolean ready = loadrost && loadlog;
	boolean stop = false;
	List list = new ArrayList();
	
	@FXML Button exitButton = new Button();
		
	
	public void chooseRostFile(ActionEvent e) {
		File newFile = rosFile.showOpenDialog(null);
		out.clear();
		if(newFile != null) {
			try {
			String name = newFile.getName();
			newApp.setRoster(name);
			sRost.load_roster(name);
			loadrost = true;
			ready = loadrost && loadlog;
			} catch (RuntimeException l) {
				out.appendText("Error: cannot find file");
			}
		}
	}
	public void chooseLogFile(ActionEvent e) {
		File newFile = logFile.showOpenDialog(null);
		out.clear();
		if(newFile != null) {
			try {
			String name = newFile.getName();
			newApp.setLog(name);
			loadlog = true;
			aLog.load_log(name);
			ready = loadlog && loadrost;
			} catch (RuntimeException l) {
				out.appendText("Error: cannot find file");
			}
		}
	}
	
	public void printRost(ActionEvent e) {
		out.clear();
		if(loadrost) {
			newApp.stuRoster.print_collection(out);
		} else {
			out.appendText("No roster is loaded.");
		}
	}
	
	public void printLog(ActionEvent e) {
		out.clear();
		if(loadlog) {
			newApp.atLog.print_collection(out);
		} else {
			out.appendText("No attendance log is loaded.");
		}
	}
	
	public void printRostCount(ActionEvent e) {
		out.clear();
		if(loadrost) {
			newApp.stuRoster.print_count(out);
		} else {
			out.appendText("No roster is loaded.");
		}
	}
	
	public void printLogCount(ActionEvent e) {
		out.clear();
		if(loadlog) {
			newApp.atLog.print_count(out);
		} else {
			out.appendText("No attendance log is loaded.");
		}
	}
	
	public void G_Button(ActionEvent e) {
		out.clear();
		list.clear();
		title = "";
		if(ready) {
		    list = newApp.list_students_not_in_class();
		    title = "*****Students not in class*****";
		} else {
			out.appendText("Roster and/or log are not loaded.");
		}
	}
	
	public void H_Button(ActionEvent e) {
		out.clear();
		list.clear();
		title = "";
		if(ready) {
			out.clear();
			out.appendText("Must provide a first name and last name to perform function (BEFORE CLICKING ON LETTER). ");
			Student s = sRost.findStudent(last, first);
			if(s == null) {
				out.clear();
				out.appendText("Student not found.");
			} else {
			try {
			out.clear();
			newApp.list_all_times_checking_in_and_out(s, out);
			} catch (NullPointerException w) {
				out.clear();
				out.appendText("Must provide a first name and last name to perform function (BEFORE CLICKING ON LETTER). \n it is possible that you either did not provide a first and last name, or the student does not exist.");
			}
			}
        } else {
			out.appendText("Roster and/or log are not loaded.");
		}
	}
	
	public void I_Button(ActionEvent e) {
		out.clear();
		list.clear();
		title = "";
		if(ready) {
			list = newApp.list_all_times_checked_in();
			title = "***** Check in times for all students who attended ********";
			
		} else {
			out.appendText("Roster and/or log are not loaded.");
		}
	}
	
	public void J_Button(ActionEvent e) {
		out.clear();
		list.clear();
		title = "";
		if(ready) {
			out.clear();
			out.appendText("Please provide a timestamp with the time and month: \"hr:min:sec, MM/DD/YEAR\" .");
			String timestamp = time2 + ", " + month2;
			try {
			list = newApp.list_students_late_to_class(timestamp);
		    title = "****** Students that arrived late *******";
			} catch(NumberFormatException l) {
				out.clear();
				out.appendText("Please format your time and date correctly and provide a timestamp with the time and month: \\\"hr:min:sec, MM/DD/YEAR\\\" . (BEFORE CLICKING ON LETTER) .");
			} catch(NullPointerException w) {
				out.clear();
				out.appendText("Must provide a timestamp with the time and month: \"hr:min:sec, MM/DD/YEAR\" . (BEFORE CLICKING ON LETTER). \n It is possible that the date you entered is not in the logs.");
			}
		     
		     
		} else {
			out.appendText("Roster and/or log are not loaded.");
		}
	}
	
	public void KQ_Button(ActionEvent e) {
		out.clear();
		list.clear();
		title = "";
		if(ready && !month.equals("null")) {
			out.clear();
			out.appendText("Must provide a date in the form \"MM/DD/YEAR\" (BEFORE CLICKING ON LETTER).");
			try {
			out.clear();
			list.add(0, "****** First student to enter on " + month2 + " ******* \n");
			Student first = newApp.get_first_student_to_enter(month2);
			String name = first.toString();
			if (name.equals ("null, null")) {
				list.add("*******************");
			} else {
			list.add(name);
			}
			} catch(NumberFormatException l) {
				out.clear();
				out.appendText("Please format your date correctly.");
			}  catch(NullPointerException w) {
				out.clear();
				out.appendText("Must provide a a date:  \"MM/DD/YEAR\" . (BEFORE CLICKING ON LETTER).");
			}
			 
		} else {
			out.appendText("Roster and/or log are not loaded.");
		}
	}
	
	public void L_Button(ActionEvent e) {
		out.clear();
		list.clear();
		title = "";
		if(ready) {
			out.clear();
			out.appendText("Must provide a first name and last name.(BEFORE CLICKING ON LETTER)");
			try {
			Student s = sRost.findStudent(last, first);
			if(s == null) {
				out.clear();
				out.appendText("Student not found. \n");
				out.appendText("Must provide a first name and last name.(BEFORE CLICKING ON LETTER)");
			} else {
			out.clear();
			out.appendText("****** Looking up Student Attendance Data ******* \n");
			newApp.print_attendance_data_for_student(s, out);
			}
			} catch (NullPointerException l) {
				out.clear();
				out.appendText("Must provide a VALID first name and last name.(BEFORE CLICKING ON LETTER).\n (If you entered a student's name, it does not exist).");
			}
		} else {
			out.appendText("Roster and/or log are not loaded.");
		}
	}
	
	public void M_Button(ActionEvent e) {
		out.clear();
		list.clear();
		title = "";
		if(ready) {
			out.clear();
			out.appendText("Must provide a first name, last name, and month date. (BEFORE CLICKING ON LETTER) ");
			Student s = sRost.findStudent(last, first);
			if(s == null) {
				out.clear();
				out.appendText("The student does not exist.");
			} else {
			
			try {
			out.clear();
			list.add(0, "****** Looking to see if Student was present on date ******* \n");
			boolean present = newApp.is_present(s, month2);
			String p;
			if(present) {
				p = "True";
			} else {
				p = "False";
			}
			list.add(p);
			}
			catch(NumberFormatException l) {
				out.clear();
				out.appendText("Please format your date correctly if it is entered, or enter it.");
			} catch(NullPointerException w) {
				out.clear();
				out.appendText("Must provide a first name, last name, and month date. (BEFORE CLICKING ON LETTER). \n If you already entered the required fields, it is possible they are not valid.");
			}
			}
			 
			
		} else {
			out.clear();
			out.appendText("Roster and/or log are not loaded.");
		}
	}
	
	
	public void N_Button(ActionEvent e) {
		out.clear();
		list.clear();
		title = "";
		if(ready) {
			list.clear();
			try {
			list = newApp.list_all_students_checked_in(month2);
			title = "****** Students present on this date *******";
			} catch(NumberFormatException l) {
				out.clear();
				out.appendText("Please format your date correctly if it is entered, or enter it.");
			} catch(NullPointerException w) {
				out.clear();
				out.appendText("Must provide a date in the form \"MM/DD/YEAR\" (BEFORE CLICKING ON LETTER), it is possible the date you entered is not correctly formatted, or it is not valid for the data set.");
			}
		} else {
			out.clear();
			out.appendText("Roster and/or log are not loaded.");
		}
	}
	public void O_Button(ActionEvent e) {
		if(ready) {
			out.clear();
			list.clear();
			title = "";
			out.appendText("Must provide a date in the form \"MM/DD/YEAR\" and ");
			out.appendText("time in the form \"hr:mn:sc\" (BEFORE CLICKING ON LETTER).");
			try {
			out.clear();
			list = newApp.list_all_students_checked_in_before(month2, time2);
			title = "All students checked in before " + time2 + ", " + month2;
			} catch(NumberFormatException l) {
				out.clear();
				out.appendText("Please format your time and date correctly if it is entered, or enter a time and date.");
			}catch(NullPointerException w) {
				out.clear();
				out.appendText("Must provide a date in the form \"MM/DD/YEAR\" and ");
				out.appendText("time in the form \"hr:mn:sc\" (BEFORE CLICKING ON LETTER). \n If they are entered already, it is possible they are not valid for the data set or are not formatted correctly.");
			}
		} else {
			out.clear();
			out.appendText("Roster and/or log are not loaded.");
		}
	}
	
	public void P_Button(ActionEvent e) {
		if(ready) {
			out.clear();
			list.clear();
			title = "";
			
			out.appendText("Must provide an integer number for days of attendance (BEFORE CLICKING ON LETTER).");
			try {
			int days1 = Integer.parseInt(days2);
			list = newApp.list_students_attendance_count(days1);
			title = "****** Those who attended " + days2 + " classes *******";
			}catch(NumberFormatException w) {
				out.clear();
				out.appendText("Must provide VALID integer number for days of attendance (BEFORE CLICKING ON LETTER). Try again.");	
			}
			
		} else {
			out.clear();
			out.appendText("Roster and/or log are not loaded.");
		}
	}
	
	public void R_Button(ActionEvent e) {
		try {
		out.clear();
		out.appendText(title);
		out.appendText("\n");
			if(list.isEmpty() && title.equals("")) {
				out.appendText("Query is empty.");
			} else if (list.isEmpty()){
				out.appendText("*********************");
			} else if(title.equals( "All students checked in before " + time2 + ", " + month2) || title.equals("****** Students present on this date *******")) {
				newApp.print_query_list(list, out);
				out.appendText("\n");
				newApp.print_count(list, out);
			}else {
			newApp.print_query_list(list, out);
			}
		} catch (NullPointerException l) {
			out.clear();
			out.appendText("Query is empty.");
		}
	}
	public void S_Button(ActionEvent e) {
		out.clear();
		newApp.print_count(list, out);
	}
	
	public void exitButton(ActionEvent e) {
		Platform.exit();
	}
	
}
