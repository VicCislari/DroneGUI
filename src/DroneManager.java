import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @class DroneManager
 * @description Manages the retrieval and mapping of drone data from an API,
 *              providing functionality to initialize and access the drone list.
 * @author Adizen
 * @version 1.0
 * @since 2024.01.26
 */
public class DroneManager {
    private static final String dataCategory = "drones";
    private static Drone[] droneList;
    private static int count;

    /**
     * Formats the drone type from the API response.
     *
     * @param droneTypeId The raw drone type from the API response.
     * @return The formatted drone type.
     */
    private static DroneType doDroneTypeIdToDroneType(String droneTypeId) {
        int i = Integer.parseInt(droneTypeId.substring(47, 49));
        return DroneTypeManager.getDroneTypeList()[i - 71];
    }

    /**
     * Maps individual drone data from the JSON object.
     *
     * @param droneJson JSON object containing drone data.
     * @return Drone object representing the mapped data.
     */
    private static Drone doMapDrone(JSONObject droneJson) {
        int id = droneJson.getInt("id");
        DroneType droneType = doDroneTypeIdToDroneType(droneJson.getString("dronetype"));
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
    private static void doMapDrones(JSONArray drones) {
        int i;
        droneList = new Drone[drones.length()];
        for (i = 0; i < drones.length(); i++) {
            droneList[i] = doMapDrone(drones.getJSONObject(i));
        }
        Arrays.sort(droneList, Comparator.comparingInt(o -> o.getId()));
    }

    /**
     * This Data can be found under the following link:
     * https://dronesim.facets-labs.com/api/drones/?limit=10&offset=0
     * This function maps the first 10 drones to Dronelist 
     * @author: @VicCislari
     */
    public static void initializeDrones() {
        doMapDrones(ApiAdapter.fetchDataFromCategory(dataCategory, 0, 0));
        count = ApiAdapter.getLastCount();
    }

    public static int getCount() {
        return count;
    }

    public static Drone[] getDroneList() {
        return droneList;
    }

}
