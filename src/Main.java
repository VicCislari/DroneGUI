
/**
 * TODO:
 * 1. alle Dronen bekommen und in Dronen Objekte konvertieren
 * a) die Links erstellen für alle Dronen
 * Prio 1:
 * https://dronesim.facets-labs.com/api/drones/?format=json&limit=30&offset=10
 * Prio 3: additionally: zukunftsorienteirt machen. Was ist wenn mehr Dronen
 * hinzugefügt werden
 * -> extra Tools.java Datei, Funktion - String getAll() - gibt ein String
 * zurück in JSON Format
 * -> Json String muss bearbeitet werden, extra Funktion,
 * -> schauen ob ApiAdapter das eigentlich schon erfüllt.
 * -> es werden neue Werte kommen, deshalb das Programm zukunftsorientiert.
 * (Skalierbarer Code, Error)
 * 2. Globalising the main variables
 * 3. Frontend... you are welcome to add some comments
 * 4. Backend... same here
 * 5. Diagrams
 * a) Flowchart
 * b) Data Chart
 * c) Class diagram
 */

public class Main {
    public static void AdiZenTest() {
        DroneManager.initializeDrones();
        System.out.println(DroneManager.getDroneList()[23].getDroneType());
        System.out.println(DroneManager.getDroneList().length);
        System.out.println(DroneDynamicsManager.getDroneDynamicsPage(7)[0].getDroneId());
        //DroneDynamicsManager.initializeDroneDynamics();
        //System.out.println(DroneDynamicsManager.getDroneDynamicsList()[20].getTimestamp());
        //System.out.println((DroneDynamicsManager.getDroneDynamicsList().length));

    }

    public static void main(String[] args) {
        AdiZenTest();
        //ApiAdapter.api_results("drones");
    }
}