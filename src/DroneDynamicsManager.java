import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Objects;

public class DroneDynamicsManager {
    private static final String category = "dronedynamics";
    private static DroneDynamics[] droneDynamicsList;

    private static int formatDroneId(String droneId) {
        return Integer.parseInt(droneId.substring(43, 45));
    }

    private static boolean formatIsActive(String isActiveStr) {
        boolean isActive;
        isActive = Objects.equals(isActiveStr, "ON");
        return isActive;
    }

    public static void initializeDroneDynamics() {
        mapAllDroneDynamics(ApiAdapter.apiResults(category));
    }

    private static DroneDynamics mapDroneDynamics(JSONObject droneDynJson) {
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
        return new DroneDynamics(droneId, timestamp, speed, alignRoll,
                alignYaw, alignPitch, longitude, latitude, batteryStatus, lastSeen, isActive);
    }

    public static DroneDynamics[] getDroneDynamicsList() {
        return droneDynamicsList;
    }

    private static void mapAllDroneDynamics(JSONArray droneDyns) {
        int i;
        droneDynamicsList = new DroneDynamics[droneDyns.length()];
        for (i = 0; i < droneDyns.length(); i++) {
            // System.out.println(droneDyns.getJSONObject(i).toString());
            droneDynamicsList[i] = mapDroneDynamics(droneDyns.getJSONObject(i));
        }
    }
}
