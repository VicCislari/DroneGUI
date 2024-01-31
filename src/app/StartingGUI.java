package app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

	/**
	 * @functionality
     * uses StartGUI.fxml as GUI, needs the StartGUI.java in addition to launch the GUI.
     * to work in vscode, package app; must be implemented -> automatically created the "App" folder.
     * 
	 * @author Wassabie
	 * @since 1.0
	 * @last_modified 2024.01.30
	 */

public class StartingGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("StartGUI.fxml"));

        primaryStage.setTitle("Dronefleet Viewer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}