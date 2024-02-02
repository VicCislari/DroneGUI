import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Comparator;

public class DroneTypeManager {
    private static final String dataCategory = "dronetypes";
    private static DroneType[] droneTypeList;
    private static int count;

    /**
     * Maps a JSONObject representing a drone type to a DroneType object.
     * 
     * @author @plotarmor27
     * @param droneTypeJson The JSONObject representing a drone type.
     * @return A DroneType object mapped from the JSON data.
     */
    private static DroneType doMapDroneType(JSONObject droneTypeJson) {
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

    /**
     * Maps an array of drone types from a JSONArray.
     * Sorts the drone types based on their IDs.
     * 
     * @author @plotarmor27
     * @since 1.1
     * @param droneTypes The JSONArray containing drone type data.
     */
    private static void doMapDroneTypes(JSONArray droneTypes) {
        int i;
        droneTypeList = new DroneType[droneTypes.length()];
        for (i = 0; i < droneTypes.length(); i++) {
            droneTypeList[i] = doMapDroneType(droneTypes.getJSONObject(i));
        }
        Arrays.sort(droneTypeList, Comparator.comparingInt(o -> o.getId()));
    }

    /**
     * @author @plotarmor27
     * @since 1.0
     *        Initializes the drone types by fetching data from the API and mapping
     *        it.
     */
    public static void doInitializeDroneTypes() {
        doMapDroneTypes(ApiAdapter.fetchDataFromCategory(dataCategory, 0, 0));
        count = ApiAdapter.getLastCount();
    }

    /**
     * Gets the count of drone types.
     *
     * @return The count of drone types.
     */
    public static int getCount() {
        return count;
    }

    /**
     * Gets the array of DroneType objects.
     * 
     * @return An array of DroneType objects.
     *         -> private static DroneType[] droneTypeList;
     */
    public static DroneType[] getDroneTypeList() {
        return droneTypeList;
    }

}
