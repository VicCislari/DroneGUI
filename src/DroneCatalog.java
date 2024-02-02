
/**
 * Makes a table and shows all the Drone Types as a list.
 * @author: Bahadir
 * @version 1.0
 * @last_modified 2024.02.01
 */

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DroneCatalog extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drone Catalog");

        // Initialize DroneTypeManager to fetch drone types
        DroneTypeManager.initializeDroneTypes();

        // Get the list of drone types as objects
        DroneType[] droneTypeArray = DroneTypeManager.getDroneTypeList();

        // Create a TableView to display the drone type list
        TableView<DroneTableModel> tableView = createDroneTypeTableView(droneTypeArray);

        // Create a VBox layout and add the TableView to it
        VBox root = new VBox();
        root.getChildren().add(tableView);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root);
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            tableView.setPrefWidth(newValue.doubleValue());
        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            tableView.setPrefHeight(newValue.doubleValue());
        });

        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }

    public TableView<DroneTableModel> createDroneTypeTableView(DroneType[] droneTypeArray) {
        TableView<DroneTableModel> tableView = new TableView<>();
        ObservableList<DroneTableModel> droneTypeDataList = FXCollections.observableArrayList();

        // Convert DroneType objects to DroneTableModel objects
        for (DroneType droneType : droneTypeArray) {
            droneTypeDataList.add(new DroneTableModel(droneType));
        }

        // Create columns for the TableView
        TableColumn<DroneTableModel, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<DroneTableModel, String> manufacturerColumn = new TableColumn<>("Manufacturer");
        manufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

        TableColumn<DroneTableModel, String> typeNameColumn = new TableColumn<>("Type Name");
        typeNameColumn.setCellValueFactory(new PropertyValueFactory<>("typeName"));

        TableColumn<DroneTableModel, Integer> weightColumn = new TableColumn<>("Weight");
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<DroneTableModel, Integer> maxSpeedColumn = new TableColumn<>("Max Speed");
        maxSpeedColumn.setCellValueFactory(new PropertyValueFactory<>("maxSpeed"));

        TableColumn<DroneTableModel, Integer> batteryCapacityColumn = new TableColumn<>("Battery Capacity");
        batteryCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("batteryCapacity"));

        TableColumn<DroneTableModel, Integer> controlRangeColumn = new TableColumn<>("Control Range");
        controlRangeColumn.setCellValueFactory(new PropertyValueFactory<>("controlRange"));

        TableColumn<DroneTableModel, Integer> maxCarriageColumn = new TableColumn<>("Max Carriage");
        maxCarriageColumn.setCellValueFactory(new PropertyValueFactory<>("maxCarriage"));

        // Add columns to the TableView
        tableView.getColumns().addAll(idColumn, manufacturerColumn, typeNameColumn, weightColumn, maxSpeedColumn,
                batteryCapacityColumn, controlRangeColumn, maxCarriageColumn);

        // Adjust table to the size of the columns
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        // Set the data in the TableView
        tableView.setItems(droneTypeDataList);

        return tableView;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
