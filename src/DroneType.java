public class DroneType {
    private int id;
    private String manufacturer;
    private String typeName;
    private int weight;
    private int maxSpeed;
    private int batteryCapacity;
    private int controlRange;
    private int maxCarriage;

    public DroneType(int id, String manufacturer, String typeName, int weight, int maxSpeed, int batteryCapacity,
            int controlRange, int maxCarriage) {
        setId(id);
        setManufacturer(manufacturer);
        setTypeName(typeName);
        setWeight(weight);
        setMaxSpeed(maxSpeed);
        setBatteryCapacity(batteryCapacity);
        setControlRange(controlRange);
        setMaxCarriage(maxCarriage);
    }

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

    public void setId(int id) {
        this.id = id;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public void setControlRange(int controlRange) {
        this.controlRange = controlRange;
    }

    public void setMaxCarriage(int maxCarriage) {
        this.maxCarriage = maxCarriage;
    }
}
