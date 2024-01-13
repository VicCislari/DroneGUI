import java.time.ZonedDateTime;
public class DroneDynamics {
    private int droneId;
    private ZonedDateTime timestamp;
    private int speed;
    private float alignRoll;
    private float alignYaw;
    private float alignPitch;
    private float longitude;
    private float latitude;
    private int batteryStatus;
    private ZonedDateTime lastSeen;
    private boolean isActive;

    public DroneDynamics(int droneId, String timestamp, int speed, float alignRoll, float alignYaw,
                         float alignPitch, float longitude, float latitude, int batteryStatus, String lastSeen,
                         boolean isActive){
        this.setDroneId(droneId);
        this.setTimestamp(timestamp);
        this.setSpeed(speed);
        this.setAlignRoll(alignRoll);
        this.setAlignYaw(alignYaw);
        this.setAlignPitch(alignPitch);
        this.setLongitude(longitude);
        this.setLatitude(latitude);
        this.setBatteryStatus(batteryStatus);
        this.setLastSeen(lastSeen);
        this.setIsActive(isActive);
    }

    public int getDroneId() {
        return droneId;
    }

    public void setDroneId(int droneId) {
        this.droneId = droneId;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = ZonedDateTime.parse(timestamp);
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getAlignRoll() {
        return alignRoll;
    }

    public void setAlignRoll(float alignRoll) {
        this.alignRoll = alignRoll;
    }

    public float getAlignYaw() {
        return alignYaw;
    }

    public void setAlignYaw(float alignYaw) {
        this.alignYaw = alignYaw;
    }

    public float getAlignPitch() {
        return alignPitch;
    }

    public void setAlignPitch(float alignPitch) {
        this.alignPitch = alignPitch;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(int batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public ZonedDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = ZonedDateTime.parse(lastSeen);
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
