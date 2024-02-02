import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
//erstmal zuende schreiben bitte
public class HistoryController {

    public BorderPane mainBody;
    public BorderPane upperBody;
    public Label title;
    public ScrollPane scrollPane;
    public VBox vBoxButtonList;
    public ImageView mapView;
    public HBox upperHbox;
    public Label currentPage;
    public TextField pageInput;
    public Button changePageButton;
    public HBox lowerHbox;
    public Label firstPage;
    public Slider pageSlider;
    public Label lastPage;
    private int currentPageNr = 1;
    private double mapNorthLat = 50.2428;
    private double mapSouthLat = 49.7440;
    private double mapEastLon = 8.9470;
    private double mapWestLon = 8.1642;

    public void initialize() {

        Image mapImage = new Image(getClass().getResourceAsStream("/resources/map.png"));
        mapView.setImage(mapImage);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        mapView.setFitWidth(792); // Use 50% of screen width
        mapView.setFitHeight(792); // Use 50% of screen height
        mapView.setPreserveRatio(true);
        Pane overlay = new Pane();
        mainBody.setCenter(overlay);
        overlay.getChildren().add(mapView);
        firstPage.setText("1");
        int lastPageNr = (int) Math.ceil(DroneDynamicManager.getCount() / DroneManager.getCount());
        lastPage.setText(String.valueOf(lastPageNr));
        pageSlider.setMin(1);
        pageSlider.setMax(lastPageNr);
        pageSlider.setSnapToTicks(true);
        pageSlider.setMajorTickUnit(1);
        pageSlider.setMinorTickCount(0);
        pageSlider.setBlockIncrement(1);
        pageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            pageInput.setText(String.valueOf(newValue.intValue()));
        });
        updatePage(overlay);
        changePageButton.setOnAction(event -> {
            try {
                // Update currentPageNr based on the input from pageInput
                currentPageNr = Integer.parseInt(pageInput.getText());
                // Update the page content based on the new currentPageNr
                updatePage(overlay);
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                System.out.println("Invalid page number");
            }
        });
    }

    public void openDroneHistory(DroneDynamic droneDynamics) throws Exception {
        FXMLLoader loader = new FXMLLoader(DroneController.class.getResource("Drone.fxml"));
        Parent root = loader.load(); // Load the FXML and get the root

        DroneController droneController = loader.getController(); // Get the controller instance
        droneController.setDroneDyn(droneDynamics); // Call the instance method on the controller
        droneController.setCurrentPage(currentPageNr);
        Stage droneHistory = new Stage();
        droneHistory.setTitle("Drone Details");
        droneHistory.setScene(new Scene(root));
        droneHistory.show();
    }

    private void updatePage(Pane overlay) {
        vBoxButtonList.getChildren().clear();
        overlay.getChildren().clear();
        overlay.getChildren().add(mapView);
        DroneDynamic[] droneDynamics = DroneDynamicManager.doGetDroneDynamicsPage(DroneManager.getCount(), currentPageNr);
        for (int i = 0; i < droneDynamics.length; i++) {
            int id = droneDynamics[i].getDrone().getId();
            String droneSerialNr = droneDynamics[i].getDrone().getSerialNumber();
            String droneButtonText = "ID:" + (id + " ");
            Button droneButton = new Button(droneButtonText + droneSerialNr);
            droneButton.setMaxWidth(Double.MAX_VALUE);
            int finalI = i;
            droneButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        openDroneHistory(droneDynamics[finalI]);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            double mapWidthPixels = mapView.getBoundsInLocal().getWidth(); // Adjust based on your actual image width
            double mapHeightPixels = mapView.getBoundsInLocal().getHeight();// Adjust based on your actual image height
            double targetLat = droneDynamics[i].getLatitude();
            double targetLon = droneDynamics[i].getLongitude();
            double x = ((targetLon - mapWestLon) / (mapEastLon - mapWestLon)) * mapWidthPixels;
            double y = ((mapNorthLat - targetLat) / (mapNorthLat - mapSouthLat)) * mapHeightPixels;
            SVGPath svgPath = new SVGPath();
            svgPath.setContent("M 50,5 95,97.5 5,97.5 z");
            svgPath.setScaleX(0.1);
            svgPath.setScaleY(0.1);
            svgPath.setLayoutX(x - 50);
            svgPath.setLayoutY(y - (5 + (97.5 - 5) / 2));
            double hueStep = 360.0 / droneDynamics.length;
            double hue = i * hueStep;
            Color color = Color.hsb(hue, 1.0, 1.0); // Full saturation and brightness for vivid colors
            svgPath.setFill(color);
            svgPath.setStroke(Color.BLACK); // Outline color
            svgPath.setStrokeWidth(10);
            double pivotX = x + svgPath.getBoundsInLocal().getWidth() / 2.0 * 0.1; // Adjust for scale
            double pivotY = y + svgPath.getBoundsInLocal().getHeight() / 2.0 * 0.1; // Adjust for scale
            Rotate rotate = new Rotate();
            rotate.setAngle(droneDynamics[i].getAlignYaw());
            rotate.setPivotX(pivotX);
            rotate.setPivotY(pivotY);
            svgPath.getTransforms().add(rotate);
            overlay.getChildren().add(svgPath);
            Rectangle rectangle = new Rectangle(10, 10);
            rectangle.setFill(color);
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(2);
            HBox hbox = new HBox(5); // 5 is the spacing between elements
            hbox.getChildren().addAll(droneButton, rectangle);
            hbox.setAlignment(Pos.BASELINE_RIGHT);
            droneButton.setAlignment(Pos.BASELINE_LEFT);
            vBoxButtonList.getChildren().add(hbox);
        }
    }
}
