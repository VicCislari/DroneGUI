import org.json.JSONArray;
import org.json.JSONObject;

public class DroneTypesManager {
    private static final String dataCategory = "dronetypes";
    private static DroneTypes[] droneTypesList;
    public static DroneTypes[] getDroneTypesList() {
        return droneTypesList;
    }

    private static DroneTypes mapDroneTypes(JSONObject droneTypeJson) {
        int id = droneTypeJson.getInt("id");
        String manufacturer = droneTypeJson.getString("manufacturer");
        String typeName = droneTypeJson.getString("typename");
        int weight = droneTypeJson.getInt("weight");
        int maxSpeed = droneTypeJson.getInt("max_speed");
        int batteryCapacity = droneTypeJson.getInt("battery_capacity");
        int controlRange = droneTypeJson.getInt("control_range");
        int maxCarriage = droneTypeJson.getInt("max_carriage");
        return new DroneTypes(id, manufacturer, typeName, weight, maxSpeed, batteryCapacity, controlRange, maxCarriage);
    }

    private static void mapAllDroneTypes(JSONArray droneTypes) {
        int i;
        droneTypesList = new DroneTypes[droneTypes.length()];
        for (i = 0; i < droneTypes.length(); i++) {
            droneTypesList[i] = mapDroneTypes(droneTypes.getJSONObject(i));
        }
    }
    public static void initializeDroneTypes() {
        mapAllDroneTypes(ApiAdapter.fetchAllDataFromCategory(dataCategory));
    }
}
