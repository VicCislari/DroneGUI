//author: Adizen

import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.Math;

public class DroneMapper {
    private static final String drones_category = "drones";

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
     * Maps individual drone JSON data to a Drone object.
     * @param drone_json The JSON data representing a single drone.
     * @author Adizen
     * @since 1.0
     * @last_modified 2024.01.10
     */
    public static void map_drone(JSONObject drone_json){
        Drone drone = new Drone();
        drone.set_id(drone_json.getInt("id"));
        drone.set_drone_type(drone_json.getString("dronetype"));
        drone.set_created(format_date(drone_json.getString("created")));
        drone.set_serial_number(drone_json.getString("serialnumber"));
        drone.set_carriage_weight(drone_json.getInt("carriage_weight"));
        drone.set_carriage_type(drone_json.getString("carriage_type"));
    }

    /**
     * Maps a JSON array of drone data to individual Drone objects.
     * @param drones The JSON array containing drone data.
     * @author Adizen
     * @since 1.0
     * @last_modified 2024.01.10
     */
    public static void map_drones(JSONArray drones){
        int i;
        for (i = 0; i < drones.length(); i++){
            System.out.println(drones.getJSONObject(i).toString());
            map_drone(drones.getJSONObject(i));
        }
    }
}
