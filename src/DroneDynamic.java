import java.time.ZonedDateTime;

/**
 * @class DroneDynamic
 * @description Represents the dynamics and status of a drone, including its
 *              location,
 *              battery status, and activity status.
 * @author Atheesen
 * @version 1.0
 * @since 2024-01-26
 */
public class DroneDynamic {
    private Drone drone; // Unique identifier
    private ZonedDateTime timestamp; // Drone Data Timestamp
    private int speed; // Dronespeed (unit = second)
    private float alignRoll; // Roll alignment
    private float alignYaw; // Yaw alignment
    private float alignPitch; // Pitch alignment
    private float longitude; // Longitude coordinate of the drone's location
    private float latitude; // Latitude coordinate of the drone's location
    private int batteryStatus; // Battery status
    private ZonedDateTime lastSeen; // Timestamp of the last time the drone was seen/data was logged
    private boolean isActive; // Activity status of the drone //Victor:WHAT?

    public DroneDynamic(Drone drone, String timestamp, int speed, float alignRoll, float alignYaw,
            float alignPitch, float longitude, float latitude, int batteryStatus, String lastSeen,
            boolean isActive) {
        this.setDroneId(drone);
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

    public Drone getDrone() {
        return drone;
    }

    public void setDroneId(Drone drone) {
        this.drone = drone;
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
