/**
 * Represents a Drone with various attributes such as ID, type, creation date, serial number, carriage weight, and carriage type.
 * @author: Adizen
 * @version 1.0
 * @last_modified 2024.01.10
 */

/* 
TODO:
   - add setID(String)
 * 
 */
import java.time.ZonedDateTime;

public class Drone {
    private int id;
    private DroneType droneType;
    private ZonedDateTime created;
    private String serialNumber;
    private int carriageWeight;
    private String carriageType;

    public Drone(int id, DroneType droneType, String created, String serialNumber, int carriageWeight,
            String carriageType) {
        setId(id);
        setDroneType(droneType);
        setCreated(created);
        setSerialNumber(serialNumber);
        setCarriageWeight(carriageWeight);
        setCarriageType(carriageType);
    }

    /*
     * return all data related to Drone object as String
     * 
     * @author bahadir
     */
    public String toString() {
        return "Drone{" +
                "id=" + id +
                ", droneType=" + droneType +
                ", created='" + created + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", carriageWeight=" + carriageWeight +
                ", carriageType='" + carriageType + '\'' +
                '}';
    }

    // TODO:
    public Drone() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DroneType getDroneType() {
        return droneType;
    }

    public void setDroneType(DroneType droneType) {
        this.droneType = droneType;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = ZonedDateTime.parse(created);
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getCarriageWeight() {
        return carriageWeight;
    }

    public void setCarriageWeight(int carriageWeight) {
        this.carriageWeight = carriageWeight;
    }

    public String getCarriageType() {
        return carriageType;
    }

    public void setCarriageType(String carriageType) {
        this.carriageType = carriageType;
    }
}