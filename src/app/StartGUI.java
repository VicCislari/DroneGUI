package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
    void handleContinueClick(ActionEvent event) {
        
        // HIER NÄCHSTE GUI LADNE @AdiZen

    }

    @FXML
    void handleExitClick(ActionEvent event) {
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
     * 
	 * @author Wassabie
	 * @since 1.0
	 * @last_modified 2024.02.01
     * 
	 */