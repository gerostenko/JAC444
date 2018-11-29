package jac444.wk5;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StudentApp extends Application {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		Application.launch(StudentApp.class, (java.lang.String[])null);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 * start JavaFx App
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        Scene scene = new Scene(root, 670, 500);
        
        stage.setTitle("Student Application");
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://ashmagautam.files.wordpress.com/2013/11/mcj038257400001.jpg?w=614&h=614"));
        stage.setScene(scene);
        stage.show();
	}
}
