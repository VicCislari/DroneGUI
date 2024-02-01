import org.json.JSONArray;
import org.json.JSONObject;

public class DroneTypeManager {
    private static final String dataCategory = "dronetypes";
    private static DroneType[] droneTypeList;
    private static int count;
    public static DroneType[] getDroneTypeList() {
        return droneTypeList;
    }

    private static DroneType mapDroneType(JSONObject droneTypeJson) {
        int id = droneTypeJson.getInt("id");
        String manufacturer = droneTypeJson.getString("manufacturer");
        String typeName = droneTypeJson.getString("typename");
        int weight = droneTypeJson.getInt("weight");
        int maxSpeed = droneTypeJson.getInt("max_speed");
        int batteryCapacity = droneTypeJson.getInt("battery_capacity");
        int controlRange = droneTypeJson.getInt("control_range");
        int maxCarriage = droneTypeJson.getInt("max_carriage");
        return new DroneType(id, manufacturer, typeName, weight, maxSpeed, batteryCapacity, controlRange, maxCarriage);
    }

    private static void mapDroneTypes(JSONArray droneTypes) {
        int i;
        droneTypeList = new DroneType[droneTypes.length()];
        for (i = 0; i < droneTypes.length(); i++) {
            droneTypeList[i] = mapDroneType(droneTypes.getJSONObject(i));
        }
    }
    public static void initializeDroneTypes() {
        mapDroneTypes(ApiAdapter.fetchDataFromCategory(dataCategory, 0, 0));
        count = ApiAdapter.getLastCount();
    }
    public static int getCount(){return count;}
}
