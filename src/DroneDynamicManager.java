import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * @class DroneDynamicManager
 * @description Manages the retrieval and mapping of drone dynamics data from an
 *              API,
 *              providing functionality to initialize and access the drone
 *              dynamics list.
 * @author @plotarmor27
 * @version 1.0
 * @since 2024.01.26
 */
public class DroneDynamicManager {
    private static final String dataCategory = "dronedynamics";

    private static int index;
    private static int count;
    private int currentPageIndex = 0;
    private boolean previousPageExists = true;
    private boolean nextPageExists = false;
    private static Map<Integer, DroneDynamic> cache = new HashMap<>();

    public static void initialize() {
        count = ApiAdapter.getCountOfDataFromCategory(dataCategory);
    }

    /**
     * Formats the drone ID from the API response.
     *
     * @param droneId The raw drone ID from the API response.
     * @return The formatted drone ID.
     */
    private static Drone doDroneIdToDrone(String droneId) {
        int i = Integer.parseInt(droneId.substring(43, 45));
        return DroneManager.getDroneList()[i - 71];
    }

    /**
     * Formats the isActive status from the API response.
     *
     * @param isActiveStr The raw isActive status from the API response.
     * @return The formatted isActive status.
     */
    private static boolean isFormatActive(String isActiveStr) {
        boolean isActive;
        isActive = isActiveStr.equals("ON");
        return isActive;
    }

    /**
     * Maps individual drone dynamics data from the JSON object.
     *
     * @param droneDynJson JSON object containing drone dynamics data.
     * @return DroneDynamics object representing the mapped data.
     */
    private static void doMapDroneDynamic(JSONObject droneDynJson) {
        try {
            Drone drone = doDroneIdToDrone(droneDynJson.getString("drone"));
            String timestamp = droneDynJson.getString("timestamp");
            int speed = droneDynJson.getInt("speed");
            float alignRoll = droneDynJson.getFloat("align_roll");
            float alignYaw = droneDynJson.getFloat("align_yaw");
            float alignPitch = droneDynJson.getFloat("align_pitch");
            float longitude = droneDynJson.getFloat("longitude");
            float latitude = droneDynJson.getFloat("latitude");
            int batteryStatus = droneDynJson.getInt("battery_status");
            String lastSeen = droneDynJson.getString("last_seen");
            boolean isActive = isFormatActive(droneDynJson.getString("status"));
            DroneDynamic droneDyn = new DroneDynamic(drone, timestamp, speed, alignRoll,
                    alignYaw, alignPitch, longitude, latitude, batteryStatus, lastSeen, isActive);
            cache.put(index, droneDyn);
        } catch (Exception e) {
            System.err.println("Error mapping drone dynamic data: " + e.getMessage());
        }
    }

    // TODO: testing
    /**
     * Retrieves a page of DroneDynamic objects based on the specified amount and
     * page number.
     * 
     * @param amount The number of DroneDynamic objects to retrieve per page.
     * @param pageNr The page number of DroneDynamic objects to retrieve.
     * @return An array of DroneDynamic objects representing the requested page.
     * @since 1.0
     * @last_modified 2024.01.10
     * @author @plotarmor27
     */
    public static DroneDynamic[] doGetDroneDynamicsPage(int amount, int pageNr) {
        DroneDynamic[] result = new DroneDynamic[amount];
        int i, index;
        ArrayList<Integer> missingIds = new ArrayList<>();
        int[] tuple = new int[2];
        if (pageNr > 0) {
            pageNr -= 1;
        }
        index = (count + pageNr * amount) % count;
        for (i = index; i < index + amount; i++) {
            if (!cache.containsKey(i)) {
                missingIds.add(i);
            }
        }
        for (i = 0; i < missingIds.size(); i++) {
            int k;
            for (k = i; k + 1 < missingIds.size() && missingIds.get(k) == missingIds.get(k + 1) - 1; k++) {
            }
            tuple[0] = missingIds.get(i);
            tuple[1] = k + 1 - i;
            doLoadData(tuple[0], tuple[1]);
            i = k;
        }

        for (int l = 0; l < result.length; l++) {
            result[l] = cache.get(index + l);
        }
        Arrays.sort(result, Comparator.comparingInt(o -> o.getDrone().getId()));
        return result;

    }

    /**
     * Loads (maps) missing drone dynamics data into the cache. The cache private
     * variable in the class.
     * 
     * @param startIndex The starting index for loading data.
     * @param amount     The number of elements to load.
     */
    private static void doLoadData(int startIndex, int amount) {
        JSONArray apiResult = ApiAdapter.fetchDataFromCategoryOffsetwise(dataCategory, startIndex, amount);
        for (int i = 0; i < amount; i++) {
            index = startIndex + i;
            doMapDroneDynamic(apiResult.getJSONObject(i));
        }
    }

    public static int getCount() {
        return count;
    }
}
