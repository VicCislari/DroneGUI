import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;


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
        //North: 50.3091 West: 8.1155 South: 49.9322 East: 9.0218

        DroneTypeManager.initializeDroneTypes();
        DroneManager.initializeDrones();
        Image mapImage = new Image(getClass().getResourceAsStream("/map.png"));
        mapView.setImage(mapImage);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        mapView.setFitWidth(screenBounds.getWidth() * 0.8); // Use 50% of screen width
        mapView.setFitHeight(screenBounds.getHeight() * 0.8); // Use 50% of screen height
        mapView.setPreserveRatio(true);
        Pane overlay = new Pane();
        overlay.getChildren().add(mapView);
        double mapWidthPixels = mapImage.getWidth(); // Adjust based on your actual image width
        double mapHeightPixels = mapImage.getHeight(); // Adjust based on your actual image height
        double mapNorthLat = 50.5047;
        double mapSouthLat = 49.7511;
        double mapEastLon = 9.6075;
        double mapWestLon = 7.7948;
        double targetLat = DroneDynamicManager.getMostRecentDroneDynamicsForAllDronesPage()[0].getLatitude();
        System.out.println(targetLat);
        double targetLon = DroneDynamicManager.getMostRecentDroneDynamicsForAllDronesPage()[0].getLongitude();
        System.out.println(targetLon);
        // Convert to pixel coordinates
        double x = ((targetLon - mapWestLon) / (mapEastLon - mapWestLon)) * mapWidthPixels;
        double y = ((mapNorthLat - targetLat) / (mapNorthLat - mapSouthLat)) * mapHeightPixels;
        Circle marker = new Circle(x, y, 10, Color.BLUE); // Adjust the circle size and color as needed
        overlay.getChildren().add(marker);
        mainBody.setCenter(overlay);
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
