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
import java.util.*;
import java.io.*;
public class AttendanceLog {

	public String filename;
	public List<Log> logs;
	public AttendanceLog() {
		logs = new ArrayList<Log>();
	}
	public List<Log> getLogs(){
		return logs;
	}
	public String getFileName() {
		return filename;
	}

	public void load_log(String file) {
		Scanner fileIn = null;
		logs.clear();
		try {
			fileIn = new Scanner(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.");
			System.exit(0);
		}
		while(fileIn.hasNextLine()) {
			String newLine = fileIn.nextLine();
			Log newLog = new Log(newLine);
			if(!containsName(newLog.getLastName(), newLog.getFirstName())) {
				logs.add(newLog);
			} else {
				if(findNames(newLog.getLastName(), newLog.getFirstName()) == -1 ) {
					System.out.println("Not found");
				} else {
				logs.get(findNames(newLog.getLastName(), newLog.getFirstName())).setCheckOut(newLine);
				}
			}
		}
		fileIn.close();
	}
	public boolean containsName(String last, String first) {
		boolean has = false;
		for(int i = 0; i < logs.size(); i++) {
			//System.out.println(first  + " " + logs.get(i).getFirstName());
			if(last.equals(logs.get(i).getLastName()) && first.equals(logs.get(i).getFirstName())) {
				has = true;
			}
		}
		return has;
	}
	public int findNames(String last, String first) {
		int index = -1;
		for(int i = 0; i < logs.size(); i++) {
			//System.out.println(first  + " " + logs.get(i).getFirstName());
			if(last.equals(logs.get(i).getLastName()) && first.equals(logs.get(i).getFirstName())) {
				index = i;
			}
		}
		return index;
	}
	
	public int daysAttended(String last, String first) {
		List<String> dates = new ArrayList<String>();
		List<String> day = new ArrayList<String>();
		for(int i = 0; i < logs.size(); i++) {
			if(last.equals(logs.get(i).getLastName()) && first.equals(logs.get(i).getFirstName())) {
				dates = logs.get(i).getCheckInOut();
			}
		}
		for(int i = 0; i < dates.size(); i++) {
			if(!day.contains(dates.get(i).substring(10, 19))) {
				day.add(dates.get(i).substring(10, 19));
			}
		}
		
		return day.size();
	}
	public String return_first_timestamp_from_day(String last, String first, String date) {
		List<String> dates = new ArrayList<String>();
		String d = "";
		for(int i = 0; i < logs.size(); i++) {
			if(last.equals(logs.get(i).getLastName()) && first.equals(logs.get(i).getFirstName())) {
				dates = logs.get(i).getCheckInOut();
			}
		}
		for(int i = 0; i < dates.size(); i++) {
			if(dates.get(i).substring(10, 19).equals(date)) {
				d = dates.get(i);
				return d;
			}
		}
		return d;
		
	}
	public void print_collection(TextArea out) {
		for(int i = 0; i < logs.size(); i++) {
			out.appendText(logs.get(i).toString());
			out.appendText("\n");
		}
	}
	public void print_count(TextArea out) {
		out.appendText("Size of attendance log: " + logs.size());
	}
	
}

