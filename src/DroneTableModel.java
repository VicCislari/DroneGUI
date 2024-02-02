import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

//AI-generated
public class DroneTableModel {
    private final int id;
    private final String manufacturer;
    private final String typeName;
    private final int weight;
    private final int maxSpeed;
    private final int batteryCapacity;
    private final int controlRange;
    private final int maxCarriage;

    public DroneTableModel(DroneType droneType) {
        this.id = droneType.getId();
        this.manufacturer = droneType.getManufacturer();
        this.typeName = droneType.getTypeName();
        this.weight = droneType.getWeight();
        this.maxSpeed = droneType.getMaxSpeed();
        this.batteryCapacity = droneType.getBatteryCapacity();
        this.controlRange = droneType.getControlRange();
        this.maxCarriage = droneType.getMaxCarriage();
    }

    // Getters for each property

    public int getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getWeight() {
        return weight;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public int getControlRange() {
        return controlRange;
    }

    public int getMaxCarriage() {
        return maxCarriage;
    }

    /**
     * Create a list of DroneTableModel instances for all drone types.
     *
     * @return List of DroneTableModel instances.
     */
    public static List<DroneTableModel> createTableModelList() {
        List<DroneTableModel> modelList = new ArrayList<>();
        for (DroneType droneType : DroneTypeManager.getDroneTypeList()) {
            modelList.add(new DroneTableModel(droneType));
        }
        return modelList;
    }
}
