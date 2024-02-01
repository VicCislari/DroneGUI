import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));

        primaryStage.setTitle("My Application");

        primaryStage.setScene(new Scene(root));

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
    /*
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

    public static void main(String[] args) {
        AdiZenTest();
        //ApiAdapter.api_results("drones");
    }
}