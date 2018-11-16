package jac444.wk4;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StudentApp extends Application {

	public static void main(String[] args) {
		Application.launch(StudentApp.class, (java.lang.String[])null);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        Scene scene = new Scene(root, 670, 325);
    
        stage.setTitle("Student Application");
        stage.setResizable(false);
        stage.getIcons().add(new Image("https://ashmagautam.files.wordpress.com/2013/11/mcj038257400001.jpg?w=614&h=614"));
        stage.setScene(scene);
        stage.show();
	}
}
