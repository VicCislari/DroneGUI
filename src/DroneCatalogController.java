
/**
 * Makes a table and shows all the Drone Types as a list.
 * @author: Bahadir
 * @version 1.0
 * @last_modified 2024.02.01
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DroneCatalogController {

    @FXML
    private VBox root;

    @FXML
    private TableView<DroneTableModel> tableView;

    public void initialize() {
        // Initialize DroneTypeManager to fetch drone types
        DroneTypeManager.doInitializeDroneTypes();

        // Get the list of drone types as objects
        DroneType[] droneTypeArray = DroneTypeManager.getDroneTypeList();

        // Create a TableView to display the drone type list
        TableView<DroneTableModel> tableView = createDroneTypeTableView(droneTypeArray);

        root.getChildren().add(tableView);

        VBox.setVgrow(tableView, javafx.scene.layout.Priority.ALWAYS);

    }

    /**
     * @param droneTypeArray
     * @return tableView
     */
    public TableView<DroneTableModel> createDroneTypeTableView(DroneType[] droneTypeArray) {
        TableView<DroneTableModel> tableView = new TableView<>();
        ObservableList<DroneTableModel> droneTypeDataList = FXCollections.observableArrayList();

        // Convert DroneType objects to DroneTableModel objects
        for (DroneType droneType : droneTypeArray) {
            droneTypeDataList.add(new DroneTableModel(droneType));
        }

        // Create columns for TableView
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

        // Adjust table to size of columns
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        // Set the data in the TableView
        tableView.setItems(droneTypeDataList);

        return tableView;
    }

    //FXML loader class
public static Parent loadFXML() throws IOException {
        FXMLLoader loader = new FXMLLoader(DroneCatalogController.class.getResource("DroneCatalog.fxml"));
        loader.load();
        return loader.getRoot();
    }

}
