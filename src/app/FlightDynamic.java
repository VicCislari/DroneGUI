package app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

	/**
	 * @functionality
<<<<<<< HEAD
     * Simple FXML Loader for FlightDynamics -> FlightDynamics.fxml ; FlightDynamics.java
=======
     * same as StartingGUI -> this time it needs file FlightDynamics.fxml & FlightDynamics.java to load
>>>>>>> 7ffef53 (FlightDynamics GUI)
     * 
	 * @author Wassabie
	 * @since 1.0
	 * @last_modified 2024.01.31
	 */

public class FlightDynamic extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("FlightDynamics.fxml"));

        primaryStage.setTitle("Dronefleet-Viewer");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}