package jac444.wk4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class StudentController {
	@FXML private Label actiontarget;
	@FXML TextField name;
	@FXML TextField course;
	@FXML TextField grade;
	@FXML TextField file;
	@FXML Button NewButton;
	@FXML Button DeleteButton;
	@FXML Button EditButton;
	@FXML Button SaveButton;
	@FXML Button LoadButton;
	
	 @FXML protected void handleNew(ActionEvent event) {
	        name.clear();
	        grade.clear();
	        course.clear();
	        file.clear();
	        name.setEditable(true);
	        course.setEditable(true);
	        grade.setEditable(true);
	        file.setEditable(true);
	        SaveButton.setDisable(false);
	 }
	 
	 @FXML protected void handleEdit(ActionEvent event) {
	       name.setEditable(true);
	       course.setEditable(true);
	       grade.setEditable(true);
	       SaveButton.setDisable(false);
	 }
	 
	 @FXML protected void handleDelete(ActionEvent event) {
	     String _file;
	     if (isEmpty(file, "file")) {return; } else _file = file.getText().trim();
	     File fileToDelete = new File(_file);
	     if (fileToDelete.exists()) {
	    	 fileToDelete.delete();
	    	 actiontarget.setText("Record deleted!");
	     }
	     else {
	    	 actiontarget.setText("File to delete not found");
	    	 return;
	     }
	     SaveButton.setDisable(true);
		 EditButton.setDisable(true);
		 name.setEditable(false);
	     course.setEditable(false);
	     grade.setEditable(false);
	     name.clear();
	     course.clear();
	     grade.clear();
	     file.clear();
	 }
	 
	 @FXML protected void handleSave(ActionEvent event) {
		 String _name; String _course; String _file;
		 if (isEmpty(name, "name")) {return; } else _name = name.getText().trim();
		 if (isEmpty(course, "course")) {return; } else _course = course.getText().trim();
		 if (isEmpty(grade, "grade")) return;
		 int _grade;
		 try {
			 _grade = Integer.parseInt(grade.getText());
			 if (_grade <= 0 || _grade >= 100) {
				 actiontarget.setText("Valid grade: 0 to 100");
				 grade.clear();
				 return;
			 }
		 }
		 catch(NumberFormatException ex) {
			 actiontarget.setText("Grade must be a numeric value");
			 grade.clear();
			 return;
		 }
		if (isEmpty(file, "file")) {return; } else _file = file.getText().trim();
		
		try (FileOutputStream out = new FileOutputStream(_file)) {
			ObjectOutputStream outputStream = new ObjectOutputStream(out);
			outputStream.writeObject(new Student(_name, _course, _grade));
			actiontarget.setText("Student " + name.getText() + " saved!");
			SaveButton.setDisable(true);
			EditButton.setDisable(false);
			DeleteButton.setDisable(false);
			name.setEditable(false);
			course.setEditable(false);
			grade.setEditable(false);
		}
		catch (Exception ex) {
			handleException(ex);
		}
	 }
	 
	 @FXML protected void handleLoad(ActionEvent event) {
		 String _file;
		 if (isEmpty(file, "file")) {return; } else _file = file.getText().trim();
	     try (FileInputStream in = new FileInputStream(_file)){
	    	 ObjectInputStream inputStream = new ObjectInputStream(in);
	    	 Student s = (Student)inputStream.readObject();
	    	 actiontarget.setText("Student " + s.getName() + " loaded");
	    	 name.setText(s.getName());
	    	 course.setText(s.getCourse());
	    	 name.setEditable(false);
	    	 course.setEditable(false);
	    	 grade.setEditable(false);
	    	 grade.setText(Integer.toString(s.getGrade()));
	    	 EditButton.setDisable(false);
	    	 DeleteButton.setDisable(false);
	    	 SaveButton.setDisable(true);
	     }
	     catch (Exception ex) {
	    	 handleException(ex);
	     }
	 }
	 
	 private <T extends Exception> void handleException (T ex) {
		 if (ex instanceof IOException) {
			 if (ex instanceof FileNotFoundException)
				 actiontarget.setText("Couldn't find the file. Please check "
						 + "if the provided path is correct");
			 else {
				 actiontarget.setText("Something went wrong, please try again later...");
			 }
		 }	 
		 else if (ex instanceof ClassNotFoundException) 
			 actiontarget.setText("The class in the file isn't Student class");
		 else {
			 actiontarget.setText("Unchecked exception! Debug is your friend!");
		 }
	 }
	 
	 private boolean isEmpty(TextField field, String fieldName) {
		 if (field.getText().isEmpty()) {
			 actiontarget.setText("The field " + fieldName + " can not be empty!");
			 return true;
		 }
		 return false;
	 }
}
