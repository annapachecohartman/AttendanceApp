package application;
import java.util.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import java.io.*;
public class StudentRoster {
	protected String filename;
	protected List<Student> roster;
	
	public StudentRoster() {
		roster = new ArrayList<Student>();
	}
	public void load_roster(String file) {
		roster.clear();
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("File was not found.");
			System.exit(0);
		}
		while(fileIn.hasNextLine()) {
			String newLine = fileIn.nextLine();
			Student newStudent = new Student(newLine);
			roster.add(newStudent);
			}
		
		fileIn.close();
	}
	public String getFileName() {
		return filename;
	}
	public List<Student> getRoster() {
		return roster;
	}
	public void print_collection(TextArea out) {
		for(int i = 0; i < roster.size(); i++) {
			out.appendText(roster.get(i).toString());
			out.appendText("\n");
		}
	}
	public void print_count(TextArea out) {
		out.appendText("Size of roster: " + roster.size());
	}
	public Student findStudent(String last, String first) {
		for(int i = 0; i < roster.size(); i++) {
			if(roster.get(i).getFirstName().equals(first) && roster.get(i).getLastName().equals(last)) {
				return roster.get(i);
			}
		}
		Student l = new Student();
		return l;
	}

}
