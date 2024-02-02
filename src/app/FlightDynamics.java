package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FlightDynamics {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField ID;

    @FXML
    private TextField battery;

    @FXML
    private Button buttonNext;

    @FXML
    private Button buttonPrevious;

    @FXML
    private TextField latitude;

    @FXML
    private TextField longitude;

    @FXML
    private TextField manufacturer;

    @FXML
    private TextField model;

    @FXML
    private Label page;

    @FXML
    private TextField pagejumper;

    @FXML
    private TextField pitch;

    @FXML
    private TextField roll;

    @FXML
    private TextField serialnumber;

    @FXML
    private TextField status;

    @FXML
    private Label totalPage;

    @FXML
    private TextField yaw;

    @FXML
    void handleNextClick(ActionEvent event) {

        // NEXT PAGE

    }

    @FXML
    void handlePrevClick(ActionEvent event) {

        // PREV PAGE

    }

    @FXML
    void jumptoPage(ActionEvent event) {

        // JUMPING TO PAGE FUNCTION

    }

    void setID(String id) {

        // set ID
    }

    public String getID() {
        
        // return ID
    }
    
    // function for page & totalPage missing -> setter getter
    // setter and getter methods for data missing

}
