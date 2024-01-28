/*
 * Represents the Drone class for use in JavaFX GUI. In JavaFX, the Properties API is used for e.g. binding. 
 * If the value of a property changes, it is shown in the JavaFX GUI accordingly.
 * 
 * @author bahadir
 * @last_modified 28.01.2024
 */

import javafx.beans.property.*;

import java.time.ZonedDateTime;

public class DroneTableModel {
    private final IntegerProperty id;
    private final SimpleIntegerProperty droneType;
    private final ObjectProperty<ZonedDateTime> created;
    private final StringProperty serialNumber;
    private final IntegerProperty carriageWeight;
    private final StringProperty carriageType;

    public DroneTableModel(Drone drone) {
        this.id = new SimpleIntegerProperty(drone.getId());
        this.droneType = new SimpleIntegerProperty(drone.getDroneType());
        this.created = new SimpleObjectProperty<>(drone.getCreated());
        this.serialNumber = new SimpleStringProperty(drone.getSerialNumber());
        this.carriageWeight = new SimpleIntegerProperty(drone.getCarriageWeight());
        this.carriageType = new SimpleStringProperty(drone.getCarriageType());
    }

    // Getters for each property

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getDroneType() {
        return droneType.get();
    }

    public IntegerProperty droneTypeProperty() {
        return droneType;
    }

    public ZonedDateTime getCreated() {
        return created.get();
    }

    public ObjectProperty<ZonedDateTime> createdProperty() {
        return created;
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public StringProperty serialNumberProperty() {
        return serialNumber;
    }

    public int getCarriageWeight() {
        return carriageWeight.get();
    }

    public IntegerProperty carriageWeightProperty() {
        return carriageWeight;
    }

    public String getCarriageType() {
        return carriageType.get();
    }

    public StringProperty carriageTypeProperty() {
        return carriageType;
    }
}
