import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

/**
 * @class DroneDynamicsManager
 * @description Manages the retrieval and mapping of drone dynamics data from an
 *              API,
 *              providing functionality to initialize and access the drone
 *              dynamics list.
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

    //this is for a more seemless transition between windows. No more need to rerequest data
    private static Map<Integer, DroneDynamics[]> cache = new HashMap<>(); 

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
        //index https://dronesim.facets-labs.com/simulator/dronedynamics/?page=3603
        long droneDynamicsId = index; //@VicCislari: verstehe Index gerade nicht ganz
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
     * Maps individual drone dynamics data from the JSON object obtained from API
     * response.
     * Caching the DroneDynamics Objects.
     * 
     * @author Adizen, Victor
     * @param droneJsonObject JSON object containing drone dynamics data.
     * @return Array of DroneDynamics representing the mapped data.
     * @last_modified 2024.01.28
     *                //NOTE: Not sure if the best thing here is to put cache
     *                immediately. I wonder what @plotarmor27 thinks
     */
    private static DroneDynamics[] mapAndCacheDroneDynamics(JSONObject droneJsonObject) {
        JSONArray resultsArray = droneJsonObject.getJSONArray("results");
        DroneDynamics[] dynamicsArray = new DroneDynamics[resultsArray.length()];
        
        //die droneDynamicsId = offset also die 71 ist 36001, die 95 ist dann 36002.

        for (int i = 0; i < resultsArray.length(); i++) {
            JSONObject droneDynJson = resultsArray.getJSONObject(i);

            long droneDynamicsId = index; //ich sollte es eigentlich auf null setzen  //@VicCislari: verstehe Index gerade nicht ganz
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

            dynamicsArray[i] = new DroneDynamics(droneDynamicsId, droneId, timestamp, speed, alignRoll,
                    alignYaw, alignPitch, longitude, latitude, batteryStatus, lastSeen, isActive);
        }

        return dynamicsArray;
    }
    // this function right here. //private static DroneDynamics[]
    // mapDroneDynamics(JSONObject droneJsonObject){}

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
     * https://dronesim.facets-labs.com/api/dronedynamics/
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

    /**
     * https://dronesim.facets-labs.com/api/dronedynamics/
     * Gets the drone dynamics data for a specific page.
     * Fetches from the API and caches it if not already in cache.
     * 
     * @author Victor
     * @return Array of DroneDynamics of the most current 25 entries.
     * @Explanation_for maxAmountOfPages = 4
     *                  I think though that we need to request the last 4 pages,
     *                  because if
     *                  ther is a max of 10 entries per pagee and you have
     *                  25 drones
     *                  10 entries per page.
     *                  minimum is 2,5 ≈ 3 pages
     *                  maximum of 3,x pages ≈ 4 pages
     *                  because you have 10 in a row which fit perfectly on 2 pages
     *                  and then
     *                  the 5 (0 to 5 can be on 1 page behind and 0 to 5
     *                  can be in front). only in the case of 0 and 5 do you have
     *                  2,5 ≈ 3
     *                  pages. in most cases you have 3,x ≈ 4 pages spread
     **/
    public static DroneDynamics[] getDroneDashboardData() {
        int countDrones = DroneManager.getCount(); // for now it is basically just 25 and that is it.
        String myString = 
        "https://dronesim.facets-labs.com/api/dronedynamics/?format=json&limit=" + countDrones + "&offset=" + (count - countDrones);
        System.out.println(myString);

        JSONObject apiResponse = ApiAdapter.fetchApiRequest(myString);
        System.out.println(apiResponse);
        if (apiResponse != null) {
            System.out.println("hello 2 ");
            return mapAndCacheDroneDynamics(apiResponse);
        } else {
            return null;// new DroneDynamics[0]; // or handle the error as needed //or some warnnig of some sort.
        }
    }

    public static DroneDynamics[] getDroneDynamicsForAllDronesPage(int pageIndex){
        JSONArray droneDyns = ApiAdapter.fetchDataPageForAllDronesFromCategory(dataCategory,pageIndex);
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
