import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Objects;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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
    private static int currentPageIndex = 0;
    private static boolean previousPageExists = true;
    private static boolean nextPageExists = false;

    //this is for a more seemless transition between windows. No more need to rerequest data
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
        isActive = Objects.equals(isActiveStr, "ON");
        return isActive;
    }

    /**
     * Maps individual drone dynamics data from the JSON object.
     *
     * @param droneDynJson JSON object containing drone dynamics data.
     * @return DroneDynamics object representing the mapped data.
     */
    private static void mapDroneDynamic(JSONObject droneDynJson) {
        //index https://dronesim.facets-labs.com/simulator/dronedynamics/?page=3603
        int droneDynamicId = index; //@VicCislari: verstehe Index gerade nicht ganz
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
        DroneDynamic droneDyn = new DroneDynamic(droneDynamicId, drone, timestamp, speed, alignRoll,
                alignYaw, alignPitch, longitude, latitude, batteryStatus, lastSeen, isActive);
        cache.put(index, droneDyn);
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

    /**
     * Fetches drone dynamics data for a specific page and caches it.
     *
     * @param pageIndex The page index to fetch.
     */
    /*
    private static void fetchAndCachePage(int pageIndex) {
        JSONArray droneDyns = ApiAdapter.fetchDataPageFromCategory(dataCategory, pageIndex);
        DroneDynamic[] dynamics = new DroneDynamic[droneDyns.length()];
        for (int i = 0; i < droneDyns.length(); i++) {
            index = 10 * pageIndex + i;
            dynamics[i] = mapDroneDynamic(droneDyns.getJSONObject(i));
        }
        cache.put(pageIndex, dynamics);
    }
    */

    /**
     * https://dronesim.facets-labs.com/api/dronedynamics/
     * Gets the drone dynamics data for a specific page.
     * Fetches from the API and caches it if not already in cache.
     *
     * @param pageIndex The page index to retrieve.
     * @return Array of DroneDynamics for the given page.
     */

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

    /*
    public static DroneDynamic[] getDroneDynamicsForAllDronesPage(int pageIndex){
        JSONArray droneDyns = ApiAdapter.fetchDataPageForAllDronesFromCategory(dataCategory,pageIndex);
        DroneDynamic[] dynamics = new DroneDynamic[droneDyns.length()];
        for (int i = 0; i < droneDyns.length(); i++) {
            dynamics[i] = mapDroneDynamic(droneDyns.getJSONObject(i));
        }
        return dynamics;
    }

    public static DroneDynamic[] getMostRecentDroneDynamicsForAllDronesPage(){
        return getDroneDynamicsForAllDronesPage((count/ApiAdapter.getCountOfDataFromCategory("drones"))-1);
    }
    */

    public static DroneDynamic[] getDroneDynamicsPage(int amount, int pageNr) {
        DroneDynamic[] result = new DroneDynamic[amount];
        int c = 0, i, index, j = 0;
        ArrayList<Integer> missingIds = new ArrayList<>();
        int[] tuple = new int[2];
        System.out.println("Step 1");
        if (pageNr < 0) {
            index = count + (amount * (pageNr + 1));
        } else {
            index = amount * pageNr;
        }
        System.out.println("Step 2");
        for (i = index - amount; i < index; i++) {

            if (!cache.containsKey(i)) {
                missingIds.add(i);

            }
        }
        System.out.println(index);
        System.out.println(missingIds.size());
        System.out.println("Step 3");
        for (i=0; i < missingIds.size(); i++){
            int k;
            for (k = i; k+1 < missingIds.size() && missingIds.get(k)+1 == missingIds.get(k+1); k++){
                tuple[0] = missingIds.get(i);
                tuple[1] = k+2-i;
            }
            i = k + 2;
            System.out.println(tuple[1]+ " " + tuple[0]+ " " + (Math.floor((tuple[0] / tuple[1]))+1));
            System.out.println((int) (double) (tuple[0] / tuple[1]) +1);
            loadData(tuple[1], (int) (double) (tuple[0] / tuple[1]) +1);
        }

        System.out.println("Step 4: " + cache.get(25).getLatitude());

        for (int l = 0; l < result.length; l++){
            result[l] = cache.get(index - amount + l);
        }
        return result;
    }

    public static int getCount(){return count;}

    private static void loadData(int amount, int pageNr){
        JSONArray apiResult = ApiAdapter.fetchDataFromCategory(dataCategory, amount, pageNr);
        for(int i = 0; i < amount; i++){
            index = (amount*pageNr)-amount+i;
            mapDroneDynamic(apiResult.getJSONObject(i));
            System.out.println(apiResult.getJSONObject(i).getInt("speed"));
        }
    }
}
