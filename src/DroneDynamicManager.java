import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * @class DroneDynamicManager
 * @description Manages the retrieval and mapping of drone dynamics data from an
 *              API,
 *              providing functionality to initialize and access the drone
 *              dynamics list.
 * @author Atheesan
 * @version 1.0
 * @since 2024-01-26
 */

public class DroneDynamicManager {
    private static final String dataCategory = "dronedynamics";

    private static int index;
    private static int count;
    private int currentPageIndex = 0;
    private boolean previousPageExists = true;
    private boolean nextPageExists = false;
    private static Map<Integer, DroneDynamic> cache = new HashMap<>();

    /**
     * Formats the drone ID from the API response.
     *
     * @param droneId The raw drone ID from the API response.
     * @return The formatted drone ID.
     */
    private static Drone droneIdToDrone(String droneId) {
        int i = Integer.parseInt(droneId.substring(43, 45));
        return  DroneManager.getDroneList()[i - 71];
    }

    /**
     * Formats the isActive status from the API response.
     *
     * @param isActiveStr The raw isActive status from the API response.
     * @return The formatted isActive status.
     */
    private static boolean formatIsActive(String isActiveStr) {
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
    private static void mapDroneDynamic(JSONObject droneDynJson) {
        Drone drone = droneIdToDrone(droneDynJson.getString("drone"));
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
        DroneDynamic droneDyn = new DroneDynamic(drone, timestamp, speed, alignRoll,
                alignYaw, alignPitch, longitude, latitude, batteryStatus, lastSeen, isActive);
        cache.put(index, droneDyn);
    }

    public static DroneDynamic[] getDroneDynamicsPage(int amount, int pageNr) {
        DroneDynamic[] result = new DroneDynamic[amount];
        int i, index;
        ArrayList<Integer> missingIds = new ArrayList<>();
        int[] tuple = new int[2];
        if(pageNr > 0){
            pageNr -= 1;
        }
        index = (count + pageNr*amount)%count;
        for (i = index; i < index+amount; i++) {
            if (!cache.containsKey(i)) {
                missingIds.add(i);
            }
        }
        for (i=0; i < missingIds.size(); i++){
            int k;
            for (k = i; k+1 < missingIds.size() && missingIds.get(k) == missingIds.get(k+1)-1; k++){
            }
            tuple[0] = missingIds.get(i);
            tuple[1] = k+1-i;
            loadData(tuple[0], tuple[1]);
            i = k;
        }

        for (int l = 0; l < result.length; l++){
            result[l] = cache.get(index + l);
        }
        Arrays.sort(result, Comparator.comparingInt(o -> o.getDrone().getId()));
        return result;
    }



    public static int getCount(){
        System.out.println(cache.keySet());
        return count;}

    private static void loadData(int startIndex, int amount){
        JSONArray apiResult = ApiAdapter.fetchDataFromCategoryOffsetwise(dataCategory, startIndex, amount);
        for(int i = 0; i < amount; i++){
            index = startIndex+i;
            mapDroneDynamic(apiResult.getJSONObject(i));
        }
    }

    public static void initialize(){
        count = ApiAdapter.getCountOfDataFromCategory(dataCategory);
    }
}
