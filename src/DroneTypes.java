public class DroneTypes {
    private static int id;
    private static String manufacturer;
    private static String typeName;
    private static int weight;
    private static int maxSpeed;
    private static int batteryCapacity;
    private static int controlRange;
    private static int maxCarriage;

    public DroneTypes(int id, String manufacturer, String typeName, int weight, int maxSpeed, int batteryCapacity, int controlRange, int maxCarriage) {
        setId(id);
        setManufacturer(manufacturer);
        setTypeName(typeName);
        setWeight(weight);
        setMaxSpeed(maxSpeed);
        setBatteryCapacity(batteryCapacity);
        setControlRange(controlRange);
        setMaxCarriage(maxCarriage);
    }

    public static int getId() {
        return id;
    }

    public static String getManufacturer() {
        return manufacturer;
    }

    public static String getTypeName() {
        return typeName;
    }

    public static int getWeight() {
        return weight;
    }

    public static int getMaxSpeed() {
        return maxSpeed;
    }

    public static int getBatteryCapacity() {
        return batteryCapacity;
    }

    public static int getControlRange() {
        return controlRange;
    }

    public static int getMaxCarriage() {
        return maxCarriage;
    }

    public static void setId(int id) {
        DroneTypes.id = id;
    }

    public static void setManufacturer(String manufacturer) {
        DroneTypes.manufacturer = manufacturer;
    }

    public static void setTypeName(String typeName) {
        DroneTypes.typeName = typeName;
    }

    public static void setWeight(int weight) {
        DroneTypes.weight = weight;
    }

    public static void setMaxSpeed(int maxSpeed) {
        DroneTypes.maxSpeed = maxSpeed;
    }

    public static void setBatteryCapacity(int batteryCapacity) {
        DroneTypes.batteryCapacity = batteryCapacity;
    }

    public static void setControlRange(int controlRange) {
        DroneTypes.controlRange = controlRange;
    }

    public static void setMaxCarriage(int maxCarriage) {
        DroneTypes.maxCarriage = maxCarriage;
    }
}
