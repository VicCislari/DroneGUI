<<<<<<< HEAD
=======
package app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

	/**
	 * @functionality
     * Simple FXML Loader
     * 
	 * @author Wassabie
	 * @since 1.0
	 * @last_modified 2024.01.31
	 */

public class DroneGUIx extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("DroneGUI.fxml"));

        primaryStage.setTitle("Dronefleet-Viewer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
>>>>>>> a6c9d3b (@Wassabie)
