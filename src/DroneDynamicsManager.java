import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

/**
 * @class DroneDynamicsManager
 * @description Manages the retrieval and mapping of drone dynamics data from an API,
 *              providing functionality to initialize and access the drone dynamics list.
 * @author Atheesan
 * @version 1.0
 * @since 2024-01-26
 */

public class DroneDynamicsManager {
    private static final String dataCategory = "dronedynamics";

    private static int index;
    private static int count = ApiAdapter.getCountOfDataFromCategory(dataCategory);
    private static int currentPageIndex = 0;
    private static boolean previousPageExists = true;
    private static boolean nextPageExists = false;
    private static Map<Integer, DroneDynamics[]> cache = new HashMap<>();
    /**
     * Gets the array of DroneDynamics objects.
     *
     * @return The array of DroneDynamics objects.
     */


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
        long droneDynamicsId = index;
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
        return new DroneDynamics(droneDynamicsId, droneId, timestamp, speed, alignRoll,
                alignYaw, alignPitch, longitude, latitude, batteryStatus, lastSeen, isActive);
    }

    /**
     * Fetches drone dynamics data for a specific page and caches it.
     *
     * @param pageIndex The page index to fetch.
     */
    private static void fetchAndCachePage(int pageIndex) {
        JSONArray droneDyns = ApiAdapter.fetchDataPageFromCategory(dataCategory, pageIndex);
        DroneDynamics[] dynamics = new DroneDynamics[droneDyns.length()];
        for (int i = 0; i < droneDyns.length(); i++) {
            index = 10 * pageIndex + i;
            dynamics[i] = mapDroneDynamics(droneDyns.getJSONObject(i));
        }
        cache.put(pageIndex, dynamics);
    }

    /**
     * Gets the drone dynamics data for a specific page.
     * Fetches from the API and caches it if not already in cache.
     *
     * @param pageIndex The page index to retrieve.
     * @return Array of DroneDynamics for the given page.
     */
    public static DroneDynamics[] getDroneDynamicsPage(int pageIndex) {
        if (!cache.containsKey(pageIndex)) {
            fetchAndCachePage(pageIndex);
        }
        return cache.get(pageIndex);
    }

    public static DroneDynamics[] getDroneDynamicsForAllDronesPage(int pageIndex){
        JSONArray droneDyns = ApiAdapter.fetchDataPageForALlDronesFromCategory(dataCategory,pageIndex);
        DroneDynamics[] dynamics = new DroneDynamics[droneDyns.length()];
        for (int i = 0; i < droneDyns.length(); i++) {
            dynamics[i] = mapDroneDynamics(droneDyns.getJSONObject(i));
        }
        return dynamics;
    }

    public static DroneDynamics[] getMostRecentDroneDynamicsForAllDronesPage(){
        return getDroneDynamicsForAllDronesPage(count/ApiAdapter.getCountOfDataFromCategory("drones")-1);
    }
}
