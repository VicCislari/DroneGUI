import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Objects;

/**
 * @author @plotarmor27
 * @since 2024.01.26
 *        The DroneDynamicsManager class manages the retrieval and
 *        mapping of drone dynamics data from an API.
 *        It provides functionality to initialize and access the drone
 *        dynamics list.
 */

public class DroneDynamicsManager {
    private static final String category = "dronedynamics";
    private static DroneDynamics[] droneDynamicsList;

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
     * Formats the "isActive" status from the API response.
     *
     * @param isActiveStr The string representation of the "isActive" status.
     * @return True if the drone is active, false otherwise.
     */
    private static boolean formatIsActive(String isActiveStr) {
        boolean isActive;
        isActive = Objects.equals(isActiveStr, "ON");
        return isActive;
    }

    /**
     * Initializes the drone dynamics data by fetching and mapping from the API.
     */
    public static void initializeDroneDynamics() {
        mapAllDroneDynamics(ApiAdapter.apiResults(category));
    }

    /**
     * Maps individual drone dynamics data from the JSON object.
     *
     * @param droneDynamicsJSON JSON object containing drone dynamics data.
     * @return DroneDynamics object representing the mapped data.
     */
    private static DroneDynamics mapDroneDynamics(JSONObject droneDynamicsJSON) {
        int droneId = formatDroneId(droneDynamicsJSON.getString("drone"));
        String timestamp = droneDynamicsJSON.getString("timestamp");
        int speed = droneDynamicsJSON.getInt("speed");
        float alignRoll = droneDynamicsJSON.getFloat("align_roll");
        float alignYaw = droneDynamicsJSON.getFloat("align_yaw");
        float alignPitch = droneDynamicsJSON.getFloat("align_pitch");
        float longitude = droneDynamicsJSON.getFloat("longitude");
        float latitude = droneDynamicsJSON.getFloat("latitude");
        int batteryStatus = droneDynamicsJSON.getInt("battery_status");
        String lastSeen = droneDynamicsJSON.getString("last_seen");
        boolean isActive = formatIsActive(droneDynamicsJSON.getString("status"));
        return new DroneDynamics(droneId, timestamp, speed, alignRoll,
                alignYaw, alignPitch, longitude, latitude, batteryStatus, lastSeen, isActive);
    }

    /**
     * Maps all drone dynamics data from the JSON array and populates the
     * droneDynamicsList.
     *
     * @param droneDynamics JSON array containing multiple drone dynamics data.
     */
    private static void mapAllDroneDynamics(JSONArray droneDynamics) {
        int i;
        droneDynamicsList = new DroneDynamics[droneDynamics.length()];
        for (i = 0; i < droneDynamics.length(); i++) {
            // System.out.println(droneDyns.getJSONObject(i).toString());
            droneDynamicsList[i] = mapDroneDynamics(droneDynamics.getJSONObject(i));
        }
    }

    /**
     * Gets the array of DroneDynamics objects.
     *
     * @return The array of DroneDynamics objects.
     */
    public static DroneDynamics[] getDroneDynamicsList() {
        return droneDynamicsList;
    }
}
