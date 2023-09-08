package application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.*;
import java.util.*;

import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.*;
public class AttendanceApp {
	protected AttendanceLog atLog;
	protected StudentRoster stuRoster;
	
	public AttendanceApp() {
		atLog = new AttendanceLog();
		stuRoster = new StudentRoster();
		
	}
	public AttendanceApp(String rosterFile, String logFile) {
		atLog = new AttendanceLog();
		atLog.load_log(logFile);
		stuRoster = new StudentRoster();
		stuRoster.load_roster(rosterFile);
		
	}
	public void setRoster(String rostfile) {
		stuRoster.load_roster(rostfile);
	}
	public void setLog(String logfile) {
		atLog.load_log(logfile);
	}
	public List<Student> list_students_not_in_class(){
		List<Student> students = stuRoster.getRoster();
		List<Log> swipeData = atLog.getLogs();
		List<Student> missing = new ArrayList<Student>();
		for(int i = 0; i < students.size(); i++) {
			boolean inClass = false;
			for(int j = 0; j < swipeData.size(); j++) {
				if(students.get(i).getFirstName().equals(swipeData.get(j).getFirstName()) && students.get(i).getLastName().equals(swipeData.get(j).getLastName())){
					inClass = true;
					
				}
			}
			if (!inClass) {
				missing.add(students.get(i));
			}
		}
		return missing;
	}
	
	public void list_all_times_checking_in_and_out(Student given, TextArea out) {
		List<Log> swipeData = atLog.getLogs();
		boolean foundstudent = false;
		out.clear();
		out.appendText("****** List all swipe in and out for a student ******* \n");
		for(int i = 0; i < swipeData.size(); i++) {
			if(given.getFirstName().equals(swipeData.get(i).getFirstName()) && given.getLastName().equals(swipeData.get(i).getLastName())) {
				List<String> checks = swipeData.get(i).getCheckInOut();
				foundstudent = true;
				for(int j = 0; j < checks.size(); j++) {
					out.appendText(given + ", " + checks.get(j));
					out.appendText("\n");
				}
			}
		}
		if(!foundstudent) {
			out.appendText("************************");
		}
	}
	
	public List<String> list_all_times_checked_in(){
		List<Log> swipeData = atLog.getLogs();
		List<String> firstSwipe = new ArrayList();
		for(int i = 0; i < swipeData.size(); i++) {
			List<String> checks = swipeData.get(i).getCheckInOut();
			String name = swipeData.get(i).getLastName() + ", " + swipeData.get(i).getFirstName();
			if(atLog.daysAttended(swipeData.get(i).getLastName(), swipeData.get(i).getFirstName()) == checks.size()) {
				for(int j = 0; j < checks.size(); j++) {
					String firstSwipeIn = name + ", " + checks.get(j);
					firstSwipe.add(firstSwipeIn);
				}
			} else {
				for(int j = 0; j < checks.size(); j+=2) {
					String firstSwipeIn = name + ", " + checks.get(j);
					firstSwipe.add(firstSwipeIn);
				}
			}
		}
		return firstSwipe;
	}
	
	public List<String> list_students_late_to_class(String timestamp){
		List<String> late = new ArrayList<String>();

		int tshrs = Integer.parseInt(timestamp.substring(0,2)) * 3600;
		int tsmins = Integer.parseInt(timestamp.substring(3,5)) * 60;
		int tssecs = Integer.parseInt(timestamp.substring(6, 8));
		boolean same = false;
		String date = timestamp.substring(10, 19);
		int stTot = tshrs + tsmins + tssecs;
		List<Log> swipeData = atLog.getLogs();
		for(int i = 0; i < swipeData.size(); i++) {
			int currhrs = Integer.parseInt(swipeData.get(i).getCheckInOut().get(0).substring(0,2)) * 3600;
			int currmins = Integer.parseInt(swipeData.get(i).getCheckInOut().get(0).substring(3,5)) * 60;
			int currsecs = Integer.parseInt(swipeData.get(i).getCheckInOut().get(0).substring(6,8));
			int tot = currhrs + currmins + currsecs;
			String currDate = swipeData.get(i).getCheckInOut().get(0).substring(10,19);
			same = currDate.equals(date);	
			
			if(tot > stTot && same) {
				String name = swipeData.get(i).getLastName() + ", " + swipeData.get(i).getFirstName();
				String lateStu = name + ", " + swipeData.get(i).getCheckInOut().get(0);
				late.add(lateStu);
			}
		}
		return late;
		
	}
	
	public void print_attendance_data_for_student(Student s, TextArea out) {
		String last = s.getLastName();
		String first = s.getFirstName();
		List<Log> swipeData = atLog.getLogs();
		boolean found = false;
		for(int i = 0; i < swipeData.size(); i++) {
			if(last.equals(swipeData.get(i).getLastName()) && first.equals(swipeData.get(i).getFirstName())) {
				out.appendText(swipeData.get(i).toString());
				out.appendText("\n");
				found = true;
				break;
			}
		}
		if(!found) {
			System.out.println("No student of this name in the attendance log.");
		}
	}
	public boolean equalDate(String date, Student s) {
		String last = s.getLastName();
		String first = s.getFirstName();
		List<Log> swipeData = atLog.getLogs();
		Log curr = new Log();
		for(int i = 0; i < swipeData.size(); i++) {
			if(last.equals(swipeData.get(i).getLastName()) && first.equals(swipeData.get(i).getFirstName())) {
				curr = swipeData.get(i);
				break;
			}
		}
		List<String> checks =  new ArrayList<String>();
        checks = curr.getCheckInOut();
        for(int i = 0; i < checks.size(); i++) {
        	if(date.equals(checks.get(i).substring(10, 19))) {
        		return true;
        	}
        }
		return false;
		
	}
	public boolean is_present(Student s, String date) {
		String last = s.getLastName();
		String first = s.getFirstName();
		List<Log> swipeData = atLog.getLogs();
		boolean found = false;
		for(int i = 0; i < swipeData.size(); i++) {
			if(last.equals(swipeData.get(i).getLastName()) && first.equals(swipeData.get(i).getFirstName()) && equalDate(date, s)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	public List<Student> list_all_students_checked_in(String date){
		List<Student> rost = stuRoster.getRoster();
		List<Student> checkedIn = new ArrayList<Student>();
		for(int i = 0; i < rost.size(); i++) {
			if(is_present(rost.get(i), date)) {
				checkedIn.add(rost.get(i));
			}
		}
		return checkedIn;
	}
	public List<Student> list_all_students_checked_in_before(String date, String time){
		List<Student> rost = stuRoster.getRoster();
		List<Student> checkedIn = new ArrayList<Student>();
		
		
		for(int i = 0; i < rost.size(); i++) {
			if(is_present(rost.get(i), date)) {
				checkedIn.add(rost.get(i));
			}
		}
		String timedate = time + ", " + date;
		List<String> latePeople = this.list_students_late_to_class(timedate);
		for(int i = 0; i < latePeople.size(); i++) {
			List<String> parts = Arrays.asList(latePeople.get(i).split(", "));
			String name = parts.get(0) + ", " + parts.get(1);
			for(int j = 0; j < checkedIn.size(); j++) {
				String name2 = checkedIn.get(j).getLastName() + ", " + checkedIn.get(j).getFirstName();
				if(name2.equals(name)) {
					checkedIn.remove(j);
				}
			}
	
		}
		return checkedIn;
		
	}
	
	public List<Student> list_students_attendance_count(int days){
		boolean inandout = false;
	
		List<Log> swipeData = atLog.getLogs();
		List<Student> listcount = new ArrayList<Student>();
		if(days <= 0) {
			for(int i = 0; i < stuRoster.getRoster().size(); i++) {
				if(!atLog.containsName(stuRoster.getRoster().get(i).getLastName(), stuRoster.getRoster().get(i).getFirstName())) {
					listcount.add(stuRoster.getRoster().get(i));
				}
			}
		} else {
			for(int i = 0; i < swipeData.size(); i++) {
				if(atLog.daysAttended(swipeData.get(i).getLastName(), swipeData.get(i).getFirstName()) == days) {
					listcount.add(stuRoster.findStudent(swipeData.get(i).getLastName(), swipeData.get(i).getFirstName()));
				}
			}
		}
		return listcount;
	}
	
	public Student get_first_student_to_enter(String date) {
		int fastest = 2147483647;
		Student fastStu = new Student();
		List<Log> swipeData = atLog.getLogs();
		for(int i = 0; i < swipeData.size(); i++) {
			String curr = atLog.return_first_timestamp_from_day(swipeData.get(i).getLastName(), swipeData.get(i).getFirstName(), date);
			if(!curr.equals("")) {
				
				int currhrs = Integer.parseInt(curr.substring(0,2)) * 3600;
				int currmins = Integer.parseInt(curr.substring(3,5)) * 60;
				int currsecs = Integer.parseInt(curr.substring(6,8));
				int totsecs = currhrs + currmins + currsecs;
				if(totsecs < fastest) {
					fastest = totsecs;
					fastStu = stuRoster.findStudent(swipeData.get(i).getLastName(), swipeData.get(i).getFirstName());
				}
			}
		}
		return fastStu;
	}
	
	public void print_query_list(List lis, TextArea out) {
		for (int i = 0; i < lis.size(); i++) {
			if(lis.get(i) instanceof String) {
				out.appendText((String) lis.get(i));
				out.appendText("\n");
			} else {
				out.appendText((String) lis.get(i).toString());
				out.appendText("\n");
			}
			
		}
	}
	
	public void print_count(List lis, TextArea out) {
		out.appendText("There were " + lis.size() + " records for this query");
	}
}