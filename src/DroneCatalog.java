import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class DroneCatalog extends Application{

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drone Catalog");

        // Get the list of drones as an array
        Drone[] droneArray = DroneManager.getDroneList();

        // Get the list of drones as objects
        List<Drone> droneList = Arrays.asList(droneArray);

        // Create a TableView to display the drone list
        TableView<DroneTableModel> tableView = createDroneTableView(droneList);

        // Create a VBox layout and add the TableView to it
        VBox root = new VBox();
        root.getChildren().add(tableView);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public TableView<DroneTableModel> createDroneTableView(List<Drone> droneList) {
        TableView<DroneTableModel> tableView = new TableView<>();
        ObservableList<DroneTableModel> droneDataList = FXCollections.observableArrayList();

        // Convert Drone objects to DroneTableModel objects
        for (Drone drone : droneList) {
            droneDataList.add(new DroneTableModel(drone));
        }

        // Create columns for the TableView
        TableColumn<DroneTableModel, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<DroneTableModel, Integer> droneTypeColumn = new TableColumn<>("Drone Type");
        droneTypeColumn.setCellValueFactory(new PropertyValueFactory<>("droneType"));

        TableColumn<DroneTableModel, ZonedDateTime> createdColumn = new TableColumn<>("Created");
        createdColumn.setCellValueFactory(new PropertyValueFactory<>("created"));

        TableColumn<DroneTableModel, String> serialNumberColumn = new TableColumn<>("Serial Number");
        serialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));

        TableColumn<DroneTableModel, Integer> carriageWeightColumn = new TableColumn<>("Carriage Weight");
        carriageWeightColumn.setCellValueFactory(new PropertyValueFactory<>("carriageWeight"));

        TableColumn<DroneTableModel, String> carriageTypeColumn = new TableColumn<>("Carriage Type");
        carriageTypeColumn.setCellValueFactory(new PropertyValueFactory<>("carriageType"));

        // Add columns to the TableView
        tableView.getColumns().addAll(idColumn, droneTypeColumn, createdColumn, serialNumberColumn, carriageWeightColumn, carriageTypeColumn);

        // Set the data in the TableView
        tableView.setItems(droneDataList);

        return tableView;
    }



    public static void main(String[] args) {
        launch(args);
    }
}
