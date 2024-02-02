import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * Controller class for managing the dashboard UI and interaction.
 *
 * @version 1.0
 * @since 2024.01.26
 * @author @aftermathlan
 */
public class DashboardController {

    public Button buttonHistory;
    public Button buttonFlightDynamics;
    public Button buttonDrones;

    @FXML
    private ImageView mapView;
    @FXML
    private BorderPane mainBody;

    @FXML
    private VBox vBoxButtonList;
    private int currentPage = -1;

    @FXML
    public void initialize() {
        // Initialize drone types, drones, and drone dynamics
        DroneTypeManager.doInitializeDroneTypes();
        DroneManager.initializeDrones();
        DroneDynamicManager.initialize();

        // Load and set the background map image
        Image mapImage = new Image(getClass().getResourceAsStream("/resources/map.png"));
        mapView.setImage(mapImage);
        mapView.setFitWidth(700);
        mapView.setFitHeight(700);
        mapView.setPreserveRatio(true);

        // Create an overlay pane to display drone icons on the map
        Pane overlay = new Pane();
        overlay.getChildren().add(mapView);

        // Define geographical boundaries of the map
        double mapWidthPixels = mapView.getBoundsInLocal().getWidth();
        double mapHeightPixels = mapView.getBoundsInLocal().getHeight();
        double mapNorthLat = 50.2428;
        double mapSouthLat = 49.7440;
        double mapEastLon = 8.9470;
        double mapWestLon = 8.1642;

        // Retrieve drone dynamics for the current page
        DroneDynamic[] droneDynamics = DroneDynamicManager.doGetDroneDynamicsPage(DroneManager.getCount(), currentPage);

        for (int i = 0; i < droneDynamics.length; i++) {
            int id = DroneManager.getDroneList()[i].getId();
            String droneSerialNr = DroneManager.getDroneList()[i].getSerialNumber();
            String droneButtonText = "ID:" + (id + " ");
            Button droneButton = new Button(droneButtonText + droneSerialNr);
            droneButton.setMaxWidth(Double.MAX_VALUE);
            int finalI = i;

            // Set action for drone button click
            droneButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        openDrone(droneDynamics[finalI]);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            // Calculate the position of the drone icon on the map
            double targetLat = droneDynamics[i].getLatitude();
            double targetLon = droneDynamics[i].getLongitude();
            double x = ((targetLon - mapWestLon) / (mapEastLon - mapWestLon)) * mapWidthPixels;
            double y = ((mapNorthLat - targetLat) / (mapNorthLat - mapSouthLat)) * mapHeightPixels;

            // Create an SVGPath representing the drone icon
            SVGPath svgPath = new SVGPath();
            svgPath.setContent("M 50,5 95,97.5 5,97.5 z");
            svgPath.setLayoutX(x - 50);
            svgPath.setLayoutY(y - (5 + (97.5 - 5) / 2));
            svgPath.setScaleX(0.1);
            svgPath.setScaleY(0.1);

            // Set color and rotation for the drone icon
            double hueStep = 360.0 / droneDynamics.length;
            double hue = i * hueStep;
            Color color = Color.hsb(hue, 1.0, 1.0);
            svgPath.setFill(color);
            svgPath.setStroke(Color.BLACK);
            svgPath.setStrokeWidth(10);

            // Set rotation for the drone icon
            double pivotX = x + svgPath.getBoundsInLocal().getWidth() / 2.0 * 0.1;
            double pivotY = y + svgPath.getBoundsInLocal().getHeight() / 2.0 * 0.1;
            Rotate rotate = new Rotate();
            rotate.setAngle(droneDynamics[i].getAlignYaw());
            rotate.setPivotX(pivotX);
            rotate.setPivotY(pivotY);
            svgPath.getTransforms().add(rotate);

            // Add the drone icon to the overlay
            overlay.getChildren().add(svgPath);

            // Create a colored rectangle next to the drone button
            Rectangle rectangle = new Rectangle(10, 10);
            rectangle.setFill(color);
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(2);

            // Create an HBox to hold the drone button and rectangle
            HBox hbox = new HBox(5);
            hbox.getChildren().addAll(droneButton, rectangle);
            hbox.setAlignment(Pos.BASELINE_RIGHT);
            droneButton.setAlignment(Pos.BASELINE_LEFT);

            // Add the HBox to the VBox (vertical box) for displaying drone buttons
            vBoxButtonList.getChildren().add(hbox);

            // Set the center of the main body to the overlay with drone icons
            mainBody.setCenter(overlay);

            // Set actions for history, flight dynamics, and drones buttons
            buttonHistory.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        openHistory();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            buttonFlightDynamics.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        openFlightDynamics();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            buttonDrones.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        openDroneCatalog();

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }

    /**
     * Opens a new window displaying details of a selected drone.
     *
     * @param droneDynamics The DroneDynamic object representing the drone's
     *                      dynamics.
     * @throws Exception if an error occurs while opening the drone details window.
     */
    public void openDrone(DroneDynamic droneDynamics) throws Exception {
        FXMLLoader loader = new FXMLLoader(DroneController.class.getResource("Drone.fxml"));
        Parent root = loader.load(); // Load the FXML and get the root

        DroneController droneController = loader.getController(); // Get the controller instance
        droneController.setDroneDyn(droneDynamics); // Call the instance method on the controller
        droneController.setCurrentPage(currentPage);
        Stage droneDetails = new Stage();
        droneDetails.setTitle("Drone Details");
        droneDetails.setScene(new Scene(root));
        droneDetails.show();

    }

    /**
     * Opens a new window displaying drone history.
     *
     * @throws Exception if an error occurs while opening the history window.
     */
    public void openHistory() throws Exception {
        FXMLLoader loader = new FXMLLoader(DroneController.class.getResource("History.fxml"));
        Parent root = loader.load(); // Load the FXML and get the root
        HistoryController historyController = loader.getController(); // Get the controller instance
        Stage history = new Stage();
        history.setTitle("Drone History");
        history.setScene(new Scene(root));
        history.show();
    }

    /*
     * Loads Drone Catalog via "Drones" button using FXML in a new window
     * 
     * @author @aftermathlan
     */
    public void openDroneCatalog() throws Exception {
        FXMLLoader loader = new FXMLLoader(DroneController.class.getResource("DroneCatalog.fxml"));
        Parent root = loader.load(); // Load the FXML and get the root
        DroneCatalogController newDroneCatalog = loader.getController(); // Get the controller instance
        Stage droneCatalogStage = new Stage();
        droneCatalogStage.setTitle("Drone Catalog");
        droneCatalogStage.setScene(new Scene(root));
        droneCatalogStage.show();
    }

    /**
     * Opens a new window displaying flight dynamics.
     *
     * @throws Exception if an error occurs while opening the flight dynamics
     *                   window.
     */
    public void openFlightDynamics() throws Exception {
        FXMLLoader loader = new FXMLLoader(DroneController.class.getResource("FlightDynamics.fxml"));
        Parent root = loader.load(); // Load the FXML and get the root
        FlightDynamicsController flightDynamicsController = loader.getController(); // Get the controller instance
        Stage flightDynamics = new Stage();
        flightDynamics.setTitle("Drone History");
        flightDynamics.setScene(new Scene(root));
        flightDynamics.show();
    }

}
