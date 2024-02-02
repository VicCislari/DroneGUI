package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;


// NEEDS REVIEW AND ADJUSTMENT READ COMMENTS BELOW

public class StartGUI {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button continueButton;

    @FXML
    private Button exitButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    void doHandleContinueClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FlightDynamics.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage currentStage = (Stage) continueButton.getScene().getWindow();

            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void doHandleExitClick(ActionEvent event) {
        if (stage != null) {
            stage.close();
        } else {
            System.exit(0);
        }
    }
}

	/**
	 * @functionality
     * Controller for StartGUI.fxml
     * 
     * Continue -> loads next GUI
     * Exit -> closes program
     * 
     * 
	 * @author Wassabie
	 * @since 1.1
	 * @last_modified 2024.02.02
     * 
	 */
