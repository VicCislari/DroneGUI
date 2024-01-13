//author: Adizen

import org.json.JSONArray;
import org.json.JSONObject;


public class DroneManager {
    private static final String category = "drones";
    private static Drone[] droneList;
    private static int formatDroneType(String droneType){
        return Integer.parseInt(droneType.substring(47,49));
    }
    private static Drone mapDrone(JSONObject droneJson){
        int id = droneJson.getInt("id");
        int droneType = formatDroneType(droneJson.getString("dronetype"));
        String created = droneJson.getString("created");
        String serialNumber = droneJson.getString("serialnumber");
        int carriageWeight = droneJson.getInt("carriage_weight");
        String carriageType = droneJson.getString("carriage_type");
        return new Drone(id, droneType, created, serialNumber, carriageWeight, carriageType);
    }
    private static void mapDrones(JSONArray drones){
        int i;
        droneList = new Drone[drones.length()];
        for (i = 0; i < drones.length(); i++){
            //System.out.println(drones.getJSONObject(i).toString());
            droneList[i] = mapDrone(drones.getJSONObject(i));
        }
    }

    public static Drone[] getDroneList(){
        return droneList;
    }
    public static void initializeDrones(){
        mapDrones(ApiAdapter.apiResults(category));
    }
}
