package jac444.wk5;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class StudentController {
	@FXML Button FileOpenerButton;
	@FXML private Label actiontarget;
	@FXML private Label notes;
	@FXML private TableView<Student> table = new TableView<Student>();
	@FXML private TableColumn<Student, Integer> colId;
	@FXML private TableColumn<Student, String> colName;
	@FXML private TableColumn<Student, String> colCourse;
	@FXML private TableColumn<Student, Integer> colGrade;
	@FXML Button NewButton;
	@FXML Button DeleteButton;
	@FXML Button SaveButton;
	@FXML Button EditButton;
	
	private ObservableList<Student> tableContent = FXCollections.observableArrayList();
	private File file;
	
	/**
	 * code to run upon controller initialization
	 * Set column liasons and load tableContent to TableView component
	 */
	@FXML 
    void initialize() {
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
		colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
		table.setItems(tableContent);
	}
	
	/**
	 * @param event
	 * event handler for adding new student
	 */
	public void AddNewStudent(ActionEvent event) {
		//before creating new student, seed the isGenerator
		Student.seedIdGenerator(tableContent.stream()
				.max(Comparator.comparing(Student::getId))
				.get()
				.getId());
		Student mockStudent = new Student("Name", "Course", 0);
		table.getItems().add(mockStudent);
		notes.setText("Update student with " + mockStudent.getId() + " id");
		EditStudent(event);
	}

	/**
	 * @param event
	 * event handler for deleting a student
	 */
	public void DeleteStudent(ActionEvent event) {
		notes.setText("");
		Student selectedStudent = getSelectedStudent();
		if (selectedStudent != null) {
			table.getItems().remove(selectedStudent);
		}
		SaveButton.setDisable(false);
	}
	
	/**
	 * @param event
	 * event handler to edit a student
	 */
	public void EditStudent(ActionEvent event) {
		SaveButton.setDisable(false);
		table.setEditable(true);
		colName.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
		colName.setOnEditCommit(
		    new EventHandler<CellEditEvent<Student, String>>() {
		        @Override
		        public void handle(CellEditEvent<Student, String> t) {
		        	if (isEmpty(t.getNewValue(), "Name")) {
						((Student) t.getTableView().getItems()
		        				.get(t.getTablePosition()
		        				.getRow()))
		        				.setName(t.getOldValue());
						//a weird bug when the table does not update with the old content
						//below is the fix based on //https://community.oracle.com/thread/2362317
						table.setItems(null); 
				        table.layout(); 
				        table.setItems(FXCollections.observableList(tableContent));
		        	}
		        	else {
		        		((Student) t.getTableView().getItems().get(
				                t.getTablePosition().getRow())
				                ).setName(t.getNewValue());
		        	}
		        }
		    }
		);
		colCourse.setCellFactory(TextFieldTableCell.<Student>forTableColumn());
		colCourse.setOnEditCommit(
		    new EventHandler<CellEditEvent<Student, String>>() {
		        @Override
		        public void handle(CellEditEvent<Student, String> t) {
		        	if(isEmpty(t.getNewValue(), "Course")) {
						((Student) t.getTableView().getItems()
		        				.get(t.getTablePosition()
		        				.getRow()))
		        				.setCourse(t.getOldValue());
						table.setItems(null); 
				        table.layout(); 
				        table.setItems(FXCollections.observableList(tableContent));
		        	}
		        	else {
		        		((Student) t.getTableView().getItems().get(
				                t.getTablePosition().getRow())
				                ).setCourse(t.getNewValue());	
		        	}
		        }
		    }
		);
		colGrade.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<Integer>(){
			//since colGrade is an Integer, need to provide from/toString methods to be able to insert/load string data
			@Override
			public String toString(Integer object) {
				return object != null ? object.toString() : null;
			}

			@Override
			public Integer fromString(String string) {
				Integer grade = null;
				try {
					grade = Integer.parseInt(string);
				}
				catch(NumberFormatException ex) {
					return null;
				}
				return grade;
			}
        }));
		colGrade.setOnEditCommit(
		    new EventHandler<CellEditEvent<Student, Integer>>() {
		        @Override
		        public void handle(CellEditEvent<Student, Integer> t) {
		        	if (t.getNewValue() != null) {
		        		if (t.getNewValue() < 0 || t.getNewValue() > 100) {
							alert("Warning!", "Valid grade: 0 to 100", Alert.AlertType.WARNING);
							((Student) t.getTableView().getItems()
			        				.get(t.getTablePosition()
			        				.getRow()))
			        				.setGrade(t.getOldValue());
							table.setItems(null); 
					        table.layout(); 
					        table.setItems(FXCollections.observableList(tableContent));
						}
			        	else {
			        		((Student) t.getTableView().getItems()
			        				.get(t.getTablePosition()
			        				.getRow()))
			        				.setGrade(t.getNewValue());
			        	}
		        	}
		        	else {
		        		alert("Warning!", "Grade must be a numeric value and can not be empty", Alert.AlertType.WARNING);
		        		table.setItems(null); 
				        table.layout(); 
				        table.setItems(FXCollections.observableList(tableContent));
		        	}
		        }
		    }
		);
	}
	
	/**
	 * @param event
	 * event handler to save table content to the specified file
	 */
	public void SaveStudents(ActionEvent event) {
		notes.setText("");
		try (FileOutputStream out = new FileOutputStream(file)) {
			ObjectOutputStream outputStream = new ObjectOutputStream(out);
			for(Student s : tableContent) {
				outputStream.writeObject(s);
			}
			alert("Confirmation!", "Data Saved to the File", Alert.AlertType.CONFIRMATION);
		}
		catch(FileNotFoundException ex) {
			alert("Error!", "File not Found", Alert.AlertType.ERROR);
		}
		catch (IOException ex) {
			alert("Error!", "Something went wrong...", Alert.AlertType.ERROR);
		}	
	}
	
	/**
	 * @param event
	 * event handler for loading data from file
	 */
	public void fileOpen (ActionEvent event) {
		FileChooser chooser = new FileChooser();
	    chooser.setTitle("Open File");
	    file = chooser.showOpenDialog(new Stage());
	    actiontarget.setText(file.getAbsolutePath());
	    
	    try (FileInputStream in = new FileInputStream(file)){
	    	 ObjectInputStream inputStream = new ObjectInputStream(in);
	    	 while(true) {
	    		 tableContent.add((Student)inputStream.readObject());
	    	 }  
	     }
	    catch(Exception ex) {
	    	if (ex instanceof EOFException) {
		    	DeleteButton.setDisable(false);
		    	NewButton.setDisable(false);
		    	EditButton.setDisable(false);
	    	}
	    	else if (ex instanceof FileNotFoundException)
	    		alert("Error!", "File not Found", Alert.AlertType.ERROR);
	    	else if (ex instanceof ClassNotFoundException) 
				 alert("Error!", "Class not recorgnized", Alert.AlertType.ERROR);
	    	else 
	    		alert("Error!", "Something went wrong...", Alert.AlertType.ERROR);
	    }
	}
	 
	 /**
	 * @param field
	 * @param fieldName
	 * @return
	 * helper to check if provided field is empty
	 */
	private boolean isEmpty(String field, String fieldName) {
		 if (field.isEmpty()) {
			 alert("Warning!", "The field " + fieldName + " can not be empty", Alert.AlertType.WARNING);
			 return true;
		 }
		 return false;
	 }
	 
	 /**
	 * @param title
	 * @param message
	 * @param type
	 * create an alert when required
	 */
	private void alert(String title, String message, Alert.AlertType type) {
		 Alert alert = new Alert(type);
	     alert.setTitle(title);
	     alert.setContentText(message);
	     alert.setHeaderText(null);
	     alert.showAndWait();
	 }
	 
	 /**
	 * @return
	 * get the focused row
	 */
	private Student getSelectedStudent() {   
		 final ObservableList<Student> selectedStudent = table.getSelectionModel().getSelectedItems();
		 return selectedStudent.size() >= 1 ? selectedStudent.get(0) : null;

	    }
}