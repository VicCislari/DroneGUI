import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @class DroneManager
 * @description Manages the retrieval and mapping of drone data from an API,
 *              providing functionality to initialize and access the drone list.
 * @author Adizen
 * @version 1.0
 * @since 2024-01-26
 */
public class DroneManager {
    private static final String dataCategory = "drones";
    private static Drone[] droneList;

    /**
     * Formats the drone type from the API response.
     *
     * @param droneType The raw drone type from the API response.
     * @return The formatted drone type.
     */
    private static int formatDroneType(String droneType) {
        return Integer.parseInt(droneType.substring(47, 49));
    }

    /**
     * Maps individual drone data from the JSON object.
     *
     * @param droneJson JSON object containing drone data.
     * @return Drone object representing the mapped data.
     */
    private static Drone mapDrone(JSONObject droneJson) {
        int id = droneJson.getInt("id");
        int droneType = formatDroneType(droneJson.getString("dronetype"));
        String created = droneJson.getString("created");
        String serialNumber = droneJson.getString("serialnumber");
        int carriageWeight = droneJson.getInt("carriage_weight");
        String carriageType = droneJson.getString("carriage_type");
        return new Drone(id, droneType, created, serialNumber, carriageWeight, carriageType);
    }

    /**
     * Maps all drone data from the JSON array and populates the droneList.
     *
     * @param drones JSON array containing multiple drone data.
     */
    private static void mapDrones(JSONArray drones) {
        int i;
        droneList = new Drone[drones.length()];
        for (i = 0; i < drones.length(); i++) {
            droneList[i] = mapDrone(drones.getJSONObject(i));
        }
    }

    /**
     * Gets the array of Drone objects.
     *
     * @return The array of Drone objects.
     */
    public static Drone[] getDroneList() {
        return droneList;
    }

    /**
     * Initializes the drone data by fetching and mapping from the API.
     */
    public static void initializeDrones() {
        mapDrones(ApiAdapter.fetchAllDataFromCategory(dataCategory));
    }
}
