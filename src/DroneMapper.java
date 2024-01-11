//author: Adizen

import org.json.JSONArray;
import org.json.JSONObject;
import java.lang.Math;

public class DroneMapper {
    private static final String drones_category = "drones";

    public static void main(String[] args) {
        map_drones(ApiAdapter.api_results(drones_category));
    }

    public static String format_date(String date){
        if (date.length() > 18) {
            String date_ending = String.valueOf(Math.round(Float.parseFloat(date.substring(18,20))));

            return date.substring(0,18) + date_ending;
        } else {
            return date;
        }
    }
    public static void map_drone(JSONObject drone_json){
        Drone drone = new Drone();
        drone.set_id(drone_json.getInt("id"));
        drone.set_drone_type(drone_json.getString("dronetype"));
        drone.set_created(format_date(drone_json.getString("created")));
        drone.set_serial_number(drone_json.getString("serialnumber"));
        drone.set_carriage_weight(drone_json.getInt("carriage_weight"));
        drone.set_carriage_type(drone_json.getString("carriage_type"));
    }
    public static void map_drones(JSONArray drones){
        int i;
        for (i = 0; i < drones.length(); i++){
            System.out.println(drones.getJSONObject(i).toString());
            map_drone(drones.getJSONObject(i));
        }
    }
}
