//author: Adizen

import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.Math;

public class DroneMapper {
    private static final String drones_category = "drones";

    public static void main(String[] args) {
        map_drones(ApiAdapter.api_results(drones_category));
    }

    /**
     * Formats the date string by rounding the seconds to the nearest whole number.
     * @param date The original date in String type.
     * @return The formatted date string with rounded seconds.
     * @author Adizen
     * @since 1.0
     * @last_modified 2024.01.10
     */

    public static String format_date(String date){
        if (date.length() > 18) {
            String date_ending = String.valueOf(Math.round(Float.parseFloat(date.substring(18,20))));

            return date.substring(0,18) + date_ending;
        } else {
            return date;
        }
    }

    /**
     * Runs a test by fetching data from an API and processing the response.
     * @author AdiZen, Victor
     * @since 2.0
     * @last_modified 2023.01.25
     * Note:
     * - this function's code is extracted from HelloRest.java
     * - Link: https://campuas.frankfurt-university.de/pluginfile.php/422675/mod_resource/content/1/HelloRest.java
     * Note:
     * - during the merge superMerge <- wassabie, 
     * the functions had to be renamed because they just had different names
     * - TODO:
     */
    public static void map_drone(JSONObject drone_json){
        Drone drone = new Drone();
        drone.setId(drone_json.getInt("id"));
        //drone.setDroneType(drone_json.getString("dronetype"));
        drone.setCreated(format_date(drone_json.getString("created")));
        drone.setSerialNumber(drone_json.getString("serialnumber"));
        drone.setCarriageWeight(drone_json.getInt("carriage_weight"));
        drone.setCarriageType(drone_json.getString("carriage_type"));
    }
    public static void map_drones(JSONArray drones){
        int i;
        for (i = 0; i < drones.length(); i++){
            System.out.println(drones.getJSONObject(i).toString());
            map_drone(drones.getJSONObject(i));
        }
    }
}
