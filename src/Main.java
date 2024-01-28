public class Main {
    public static void AdiZenTest() {
        DroneManager.initializeDrones();
        System.out.println(DroneManager.getDroneList()[23].getDroneType());
        System.out.println(DroneManager.getDroneList().length);
        System.out.println(DroneDynamicManager.getDroneDynamicsPage(7)[0].getDrone().getId());
        System.out.println(DroneDynamicManager.getMostRecentDroneDynamicsForAllDronesPage()[3].getDrone().getId());
        //DroneDynamicsManager.initializeDroneDynamics();
        //System.out.println(DroneDynamicsManager.getDroneDynamicsList()[20].getTimestamp());
        //System.out.println((DroneDynamicsManager.getDroneDynamicsList().length));

    }

    public static void testDroneDashboard(){ 
        System.out.println(DroneDynamicManager.getDroneDashboardData().length + " hello");
    }

    public static void main(String[] args) {
        testDroneDashboard();
    
        //AdiZenTest();
        //ApiAdapter.api_results("drones");
    }
}