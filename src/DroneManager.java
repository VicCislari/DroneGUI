//author: Adizen

import org.json.JSONArray;
import org.json.JSONObject;


public class DroneManager {
    private static final String dronesCategory = "drones";
    private static Drone[] droneList;

    public static void main(String[] args) {
        mapDrones(ApiAdapter.apiResults(dronesCategory));
        System.out.println(droneList[0].getCreated());
    }

    public static int formatDroneType(String droneType){
        return Integer.parseInt(droneType.substring(47,49));
    }
    public static Drone mapDrone(JSONObject droneJson){
        Drone drone = new Drone();
        drone.setId(droneJson.getInt("id"));
        drone.setDroneType(formatDroneType(droneJson.getString("dronetype")));
        drone.setCreated(droneJson.getString("created"));
        drone.setSerialNumber(droneJson.getString("serialnumber"));
        drone.setCarriageWeight(droneJson.getInt("carriage_weight"));
        drone.setCarriageType(droneJson.getString("carriage_type"));
        return drone;
    }
    public static void mapDrones(JSONArray drones){
        int i;
        droneList = new Drone[drones.length()];
        for (i = 0; i < drones.length(); i++){
            System.out.println(drones.getJSONObject(i).toString());
            droneList[i] = mapDrone(drones.getJSONObject(i));
        }
    }

    public static Drone[] getDroneList(){
        return droneList;
    }
}
