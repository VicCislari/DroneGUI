//test

public class Main {
    public static void main(String[] args) {
        DroneManager.initializeDrones();
        DroneDynamicsManager.initializeDroneDynamics();
        System.out.println(DroneDynamicsManager.getDroneDynamicsList()[0].getTimestamp());
        System.out.println((DroneDynamicsManager.getDroneDynamicsList().length));
    }
}