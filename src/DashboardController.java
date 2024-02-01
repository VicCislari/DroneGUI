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
        System.out.println("hello");
        //ApiAdapter.fetchDataPageForAllDronesFromCategory("dronedynamics",0);
        Image mapImage = new Image(getClass().getResourceAsStream("/resources/map.png"));
        mapView.setImage(mapImage);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        mapView.setFitWidth(screenBounds.getWidth() * 0.8); // Use 50% of screen width
        mapView.setFitHeight(screenBounds.getHeight() * 0.8); // Use 50% of screen height
        mapView.setPreserveRatio(true);
        Pane overlay = new Pane();
        overlay.getChildren().add(mapView);
        double mapWidthPixels = mapView.getBoundsInLocal().getWidth(); // Adjust based on your actual image width
        double mapHeightPixels = mapView.getBoundsInLocal().getHeight();// Adjust based on your actual image height
        double mapNorthLat = 50.4225;
        double mapSouthLat = 49.7777;
        double mapEastLon = 9.2697;
        double mapWestLon = 7.9596;
        double targetLat = DroneDynamicManager.getDroneDynamicsPage(DroneManager.getCount(), 2)[1].getLatitude();
        double targetLon = DroneDynamicManager.getDroneDynamicsPage(DroneManager.getCount(), 2)[1].getLongitude();
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
/*
        for (int i = 0; i < DroneDynamicManager.getMostRecentDroneDynamicsForAllDronesPage().length; i++){
            int id = DroneDynamicManager.getMostRecentDroneDynamicsForAllDronesPage()[i].getDrone().getId();
            String droneSerialNr = DroneDynamicManager.getMostRecentDroneDynamicsForAllDronesPage()[i].getDrone().getSerialNumber();
            String droneButtonText = "ID:" + (id + " ");
            Button droneButton = new Button(droneButtonText + droneSerialNr);
            droneButton.setMaxWidth(Double.MAX_VALUE);
            vBoxButtonList.getChildren().add(droneButton);
        }*/
    }
}
