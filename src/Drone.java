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
    private int droneType;
    private ZonedDateTime created;
    private String serialNumber;
    private int carriageWeight;
    private String carriageType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    ///polymorphism
    //this needs some attentipon
    public void setId(String id) {
        //this.id = id;
    }

    public int getDroneType() {
        return droneType;
    }

    public void setDroneType(int droneType) {
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