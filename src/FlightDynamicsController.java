
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Arrays;

// NEEDS REVIEW AND ADJUSTMENT READ COMMENTS BELOW

public class FlightDynamicsController {

    public Button jumpButton;
    public Label currentPage;
    public Label totalPages;
    public TableView dynamicsTable;
    public TableColumn droneIdColumn;
    public TableColumn statusColumn;
    public TableColumn timestampColumn;
    public TableColumn speedColumn;
    public TableColumn batteryColumn;
    public TableColumn longitudeColumn;
    public TableColumn latitudeColumn;
    public TableColumn lastSeenColumn;
    public TableColumn rollColumn;
    public TableColumn pitchColumn;
    public TableColumn yawColumn;
    public TextField pageJumper;
    public Label wrongInput;

    private int currentPageNr = 1;

    @FXML
    private Button buttonNext;

    @FXML
    private Button buttonPrevious;


    public void initialize() {
        updateTable();
        jumpButton.setOnAction(event -> {
            try {
                int totalPagesNr = Integer.parseInt(totalPages.getText());
                int inputNumber = Integer.parseInt(pageJumper.getText());
                if ((inputNumber <= totalPagesNr ) && (inputNumber > 0)){
                    wrongInput.setVisible(false);
                    // Update currentPageNr based on the input from pageInput
                    currentPageNr = Integer.parseInt(pageJumper.getText());
                    // Update the page content based on the new currentPageNr
                    updateTable();
                } else {
                    wrongInput.setVisible(true);
                }

            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid integer
                System.out.println("Invalid page number");
            }
        });

    }

    private void updateTable(){
        // Assuming DroneDynamicManager.getDroneDynamicsPage returns an array of DroneDynamic objects
        ObservableList<DroneDynamic> droneDynamicsList = FXCollections.observableArrayList();
        DroneDynamic[] droneDynamics = DroneDynamicManager.getDroneDynamicsPage(DroneManager.getCount(), currentPageNr);
        droneDynamicsList.addAll(Arrays.asList(droneDynamics));

        // Make sure the TableView is clear before adding new items
        dynamicsTable.getItems().clear();

        dynamicsTable.setItems(droneDynamicsList);

        // Setting up the cell value factories for each column
        droneIdColumn.setCellValueFactory(new PropertyValueFactory<DroneDynamic, Integer>("droneId"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<DroneDynamic, String>("status"));
        timestampColumn.setCellValueFactory(new PropertyValueFactory<DroneDynamic, String>("timestamp"));
        speedColumn.setCellValueFactory(new PropertyValueFactory<DroneDynamic, Double>("speed"));
        batteryColumn.setCellValueFactory(new PropertyValueFactory<DroneDynamic, Integer>("batteryStatus"));
        longitudeColumn.setCellValueFactory(new PropertyValueFactory<DroneDynamic, Double>("longitude"));
        latitudeColumn.setCellValueFactory(new PropertyValueFactory<DroneDynamic, Double>("latitude"));
        lastSeenColumn.setCellValueFactory(new PropertyValueFactory<DroneDynamic, String>("lastSeen"));
        rollColumn.setCellValueFactory(new PropertyValueFactory<DroneDynamic, Double>("alignRoll"));
        pitchColumn.setCellValueFactory(new PropertyValueFactory<DroneDynamic, Double>("alignPitch"));
        yawColumn.setCellValueFactory(new PropertyValueFactory<DroneDynamic, Double>("alignYaw"));

        // Update current page label
        currentPage.setText(String.valueOf(currentPageNr));
        // Assuming you have a method to calculate total pages
        totalPages.setText(String.valueOf((int)Math.ceil(DroneDynamicManager.getCount() / DroneManager.getCount())));

        System.out.println(currentPageNr);

        if(currentPageNr == 1){
            buttonPrevious.setVisible(false);
        } else if(currentPageNr > 1) {
            buttonPrevious.setVisible(true);
        }

        if(currentPageNr == Integer.parseInt(totalPages.getText())){
            buttonNext.setVisible(false);
        } else if (currentPageNr < Integer.parseInt(totalPages.getText())) {
            buttonNext.setVisible(true);
        }


        buttonPrevious.setOnAction(event -> {
            try {
                currentPageNr--;
                updateTable();
            } catch (NumberFormatException e) {
                System.out.println("Invalid page number");
            }
        });

        buttonNext.setOnAction(event -> {
            try {
                currentPageNr++;
                updateTable();
            } catch (NumberFormatException e) {
                System.out.println("Invalid page number");
            }
        });
    }



    // function for page & totalPage missing -> setter getter
    // setter and getter methods for data missing

}
