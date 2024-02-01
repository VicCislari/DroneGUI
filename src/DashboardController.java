import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;


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
        DroneDynamicManager.initialize();
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
        double mapNorthLat = 50.2428;
        double mapSouthLat = 49.7440;
        double mapEastLon = 8.9470;
        double mapWestLon = 8.1642;
        DroneDynamic[] droneDynamics = DroneDynamicManager.getDroneDynamicsPage(DroneManager.getCount(), -1);
        for (int i = 0; i < droneDynamics.length; i++){
            int id = DroneManager.getDroneList()[i].getId();
            String droneSerialNr = DroneManager.getDroneList()[i].getSerialNumber();
            String droneButtonText = "ID:" + (id + " ");
            Button droneButton = new Button(droneButtonText + droneSerialNr);
            droneButton.setMaxWidth(Double.MAX_VALUE);
            vBoxButtonList.getChildren().add(droneButton);
            double targetLat = droneDynamics[i].getLatitude();
            double targetLon = droneDynamics[i].getLongitude();
            double x = ((targetLon - mapWestLon) / (mapEastLon - mapWestLon)) * mapWidthPixels;
            double y = ((mapNorthLat - targetLat) / (mapNorthLat - mapSouthLat)) * mapHeightPixels;
            SVGPath svgPath = new SVGPath();
            svgPath.setContent("M 50,5 95,97.5 5,97.5 z");
            svgPath.setLayoutX(x-50);
            svgPath.setLayoutY(y-(5+(97.5-5)/2));
            svgPath.setScaleX(0.1);
            svgPath.setScaleY(0.1);
            double pivotX = x + svgPath.getBoundsInLocal().getWidth() / 2.0 * 0.1; // Adjust for scale
            double pivotY = y + svgPath.getBoundsInLocal().getHeight() / 2.0 * 0.1; // Adjust for scale
            Rotate rotate = new Rotate();
            rotate.setAngle(droneDynamics[i].getAlignYaw());
            rotate.setPivotX(pivotX);
            rotate.setPivotY(pivotY);
            svgPath.getTransforms().add(rotate);
            overlay.getChildren().add(svgPath);
            mainBody.setCenter(overlay);
            //Circle marker = new Circle(x, y, 3, Color.BLUE); // Adjust the circle size and color as needed
            //overlay.getChildren().add(marker);
        }
    }
}
