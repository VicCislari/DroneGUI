import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.commons.math3.util.FastMath;

import java.time.ZonedDateTime;

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


    @FXML
    public void initialize() {
        Platform.runLater(() -> {
            title.setText(title.getText() + drone.getId());
            serialNr.setText(drone.getSerialNumber());
            model.setText(droneType.getTypeName());
            manufacturer.setText(droneType.getManufacturer());
            batteryPercentage.setText(getBatteryPercentage(droneDynamic.getBatteryStatus(), droneType.getBatteryCapacity()));
            if (droneDynamic.getIsActive()) {
                status.setText("ON");
            } else {
                status.setText("OFF");
            }
            System.out.println("dronedyn id" + droneDynamic.getSpeed());
            speed.setText(droneDynamic.getSpeed()+"");
            lastSeen.setText(""+droneDynamic.getLastSeen().toLocalDateTime());
            flightTime.setText(getFlightTime(drone) +" min");
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
        int i;
        for (i = 0; DroneDynamicManager.getLastDroneDynamicsForDrone(drone.getId(), i+1)[i].getIsActive();i++) {
            System.out.println(DroneDynamicManager.getLastDroneDynamicsForDrone(drone.getId(), i+1)[i].getDrone().getId());
            System.out.println(DroneDynamicManager.getLastDroneDynamicsForDrone(drone.getId(), i+1)[i].getTimestamp());
            System.out.println(DroneDynamicManager.getLastDroneDynamicsForDrone(drone.getId(), i+1)[i].getIsActive());
        }
        return i;
    }

    private String getBatteryLifeEstimation(){
        return droneDynamic.getBatteryStatus()/125 + " min left";
    }

    private String getAverageSpeed(){
        int i, j=0;
        for (i = 0; DroneDynamicManager.getLastDroneDynamicsForDrone(drone.getId(), i+1)[i].getIsActive();i++){
            j+= DroneDynamicManager.getLastDroneDynamicsForDrone(drone.getId(), i+1)[i].getSpeed();
        }
        if(i > 0){
            return j/(i+1) + " km/h";
        } else {
            return 0 + " km/h";
        }
    }
}
