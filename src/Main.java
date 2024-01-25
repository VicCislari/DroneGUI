public class Main {
    public static void AdiZenTest() {
        DroneManager.initializeDrones();
        DroneDynamicsManager.initializeDroneDynamics();
        System.out.println(DroneDynamicsManager.getDroneDynamicsList()[0].getTimestamp());
        System.out.println((DroneDynamicsManager.getDroneDynamicsList().length));
    }
}