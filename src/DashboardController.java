import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class DashboardController {
    @FXML
    private VBox vBoxButtonList;
    @FXML
    private Button droneButton;
    @FXML
    private void handleButtonAction() {
        // Logic for handling button click
    }
    @FXML
    public void initialize(){
        DroneTypeManager.initializeDroneTypes();
        DroneManager.initializeDrones();
        for (int i = 0; i < DroneManager.getCount(); i++){
            String droneButtonText = String.valueOf(DroneManager.getDroneList()[i].getId());
            Button droneButton = new Button(droneButtonText);
            vBoxButtonList.getChildren().add(droneButton);
        }

    }
}
