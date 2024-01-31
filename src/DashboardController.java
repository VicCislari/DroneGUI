import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//50°39'35"N, 7°21'38"E → 49°32'34"N, 10°8'5"E
public class DashboardController{
    @FXML
    private ImageView mapView;
    @FXML
    private BorderPane mainBody;

    @FXML
    private VBox vBoxButtonList;

    @FXML
    private void handleButtonAction() {
        // Logic for handling button click
    }
    @FXML
    public void initialize(){
        DroneTypeManager.initializeDroneTypes();
        DroneManager.initializeDrones();
        Image mapImage = new Image("file:./map.png");
        mapView.setImage(mapImage);
        for (int i = 0; i < DroneManager.getCount(); i++){
            int id = DroneManager.getDroneList()[i].getId();
            String droneSerialNr = DroneManager.getDroneList()[i].getSerialNumber();
            String droneButtonText = "ID:" + (id + " ");
            Button droneButton = new Button(droneButtonText + droneSerialNr);
            droneButton.setMaxWidth(Double.MAX_VALUE);
            vBoxButtonList.getChildren().add(droneButton);
        }
    }

}
