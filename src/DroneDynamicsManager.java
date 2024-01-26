import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Objects;
/**
 * @class DroneDynamicsManager
 * @description Manages the retrieval and mapping of drone dynamics data from an API,
 *              providing functionality to initialize and access the drone dynamics list.
 * @author Atheesan
 * @version 1.0
 * @since 2024-01-26
 */

public class DroneDynamicsManager {
    private static final String category = "dronedynamics";
    private static DroneDynamics[] droneDynamicsList;

    /**
     * Initializes the drone dynamics data by fetching and mapping from the API.
     */
    public static void initializeDroneDynamics() {
        mapAllDroneDynamics(ApiAdapter.api_results(category));
    }

     /**
     * Gets the array of DroneDynamics objects.
     *
     * @return The array of DroneDynamics objects.
     */
    public static DroneDynamics[] getDroneDynamicsList() {
        return droneDynamicsList;
    }

    /**
     * Formats the drone ID from the API response.
     *
     * @param droneId The raw drone ID from the API response.
     * @return The formatted drone ID.
     */
    private static int formatDroneId(String droneId) {
        return Integer.parseInt(droneId.substring(43, 45));
    }

    /**
     * Formats the isActive status from the API response.
     *
     * @param isActiveStr The raw isActive status from the API response.
     * @return The formatted isActive status.
     */
    private static boolean formatIsActive(String isActiveStr) {
        boolean isActive;
        isActive = Objects.equals(isActiveStr, "ON");
        return isActive;
    }

    /**
     * Maps individual drone dynamics data from the JSON object.
     *
     * @param droneDynJson JSON object containing drone dynamics data.
     * @return DroneDynamics object representing the mapped data.
     */
    private static DroneDynamics mapDroneDynamics(JSONObject droneDynJson) {
        int droneId = formatDroneId(droneDynJson.getString("drone"));
        String timestamp = droneDynJson.getString("timestamp");
        int speed = droneDynJson.getInt("speed");
        float alignRoll = droneDynJson.getFloat("align_roll");
        float alignYaw = droneDynJson.getFloat("align_yaw");
        float alignPitch = droneDynJson.getFloat("align_pitch");
        float longitude = droneDynJson.getFloat("longitude");
        float latitude = droneDynJson.getFloat("latitude");
        int batteryStatus = droneDynJson.getInt("battery_status");
        String lastSeen = droneDynJson.getString("last_seen");
        boolean isActive = formatIsActive(droneDynJson.getString("status"));
        return new DroneDynamics(droneId, timestamp, speed, alignRoll,
                alignYaw, alignPitch, longitude, latitude, batteryStatus, lastSeen, isActive);
    }

    /**
     * Maps all drone dynamics data from the JSON array and populates the droneDynamicsList.
     *
     * @param droneDyns JSON array containing multiple drone dynamics data.
     */
    private static void mapAllDroneDynamics(JSONArray droneDyns) {
        int i;
        droneDynamicsList = new DroneDynamics[droneDyns.length()];
        for (i = 0; i < droneDyns.length(); i++) {
            // System.out.println(droneDyns.getJSONObject(i).toString());
            droneDynamicsList[i] = mapDroneDynamics(droneDyns.getJSONObject(i));
        }
    }
}
