import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DroneController {
    public Label title;
    public Label serialNr;
    public Label model;
    public Label batteryPercentage;
    public Label manufacturer;
    public Label status;
    public Label speed;
    public Label lastSeen;
    public Label flightTime;
    public Label batteryLifeEstimation;
    public Label avgSpeed;
    private DroneDynamic droneDynamic;
    private Drone drone;
    private DroneType droneType;
    private int currentPage;

    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            title.setText(title.getText() + drone.getId());
            serialNr.setText(drone.getSerialNumber());
            model.setText(droneType.getTypeName());
            manufacturer.setText(droneType.getManufacturer());
            batteryPercentage
                    .setText(getBatteryPercentage(droneDynamic.getBatteryStatus(), droneType.getBatteryCapacity()));
            if (droneDynamic.getIsActive()) {
                status.setText("ON");
            } else {
                status.setText("OFF");
            }
            speed.setText(droneDynamic.getSpeed() + "");
            lastSeen.setText("" + droneDynamic.getLastSeen().toLocalDateTime());
            flightTime.setText(getFlightTime(drone) + " min");
            batteryLifeEstimation.setText(getBatteryLifeEstimation());
            avgSpeed.setText(getAverageSpeed());
        });
    }

    public void setDroneDyn(DroneDynamic droneDyn) {

        this.droneDynamic = droneDyn;
        this.drone = droneDynamic.getDrone();
        this.droneType = drone.getDroneType();

    }

    private String getBatteryPercentage(int batteryStatus, int maxCapacity) {
        String result;
        if (batteryStatus == 0) {
            result = "0";
        } else {
            result = String.valueOf((maxCapacity / batteryStatus) * 100);
        }
        result += "%";
        return result;
    }

    private int getFlightTime(Drone drone) {
        int id = drone.getId() - 71;
        int j = 0;
        for (int i = currentPage - 1; Math.abs(i) > 0
                && Math.abs(i) <= Math.ceil(DroneDynamicManager.getCount() / DroneManager.getCount()); i--) {
            if (DroneDynamicManager.getDroneDynamicsPage(DroneManager.getCount(), i)[id].getIsActive()) {
                j++;
            } else {
                return j;
            }
        }
        return j;
    }

    private String getBatteryLifeEstimation() {
        return droneDynamic.getBatteryStatus() / 125 + " min left";
    }

    private String getAverageSpeed() {
        int id = drone.getId() - 71;
        int j = droneDynamic.getSpeed(), k = 1;
        for (int i = currentPage - 1; Math.abs(i) > 0
                && Math.abs(i) < Math.ceil(DroneDynamicManager.getCount() / DroneManager.getCount()); i--) {
            if (DroneDynamicManager.getDroneDynamicsPage(DroneManager.getCount(), i)[id].getIsActive()) {
                j += DroneDynamicManager.getDroneDynamicsPage(DroneManager.getCount(), i)[id].getSpeed();
                k++;
            } else {
                return j / k + "km/h";
            }
        }
        return j / k + "km/h";
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
