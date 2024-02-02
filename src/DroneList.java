import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * represents a JFrame for displaying a list of drones.
 * It is used to present a drone fleet list.
 *
 * @author @WassabieX
 * @since 1.0
 * @last_modified 2024.01.12
 */
public class DroneList extends JFrame {

    private final List<String> droneList; // list with drone fleet

    /**
     * Constructs a new instance of the `DroneList` class.
     *  @author @WassabieX
     * @param droneList The list of drones to be displayed.
     */
    public DroneList(List<String> droneList) {
        this.droneList = droneList; // initialize drone list with the returned drone list
        initializeUI();
    }

    /**
     *
     * as for now, it only presents a dummy list
     * used in GUIPT.java to list drones
     *
     * @Layout FlowLayout
     * @author @WassabieX
     * @last_modified 2024.01.12
     * @since 1.0
     */
    private void initializeUI() {
        JList<String> list = new JList<>(droneList.toArray(new String[0])); // creates JList to show drones, saves the
                                                                            // new made array in a String
        JScrollPane scrollPane = new JScrollPane(list); // for scroll thing

        setLayout(new FlowLayout());
        add(scrollPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(400, 300);
        setTitle("Dronefleet");
        setVisible(true);
    }

    /**
     * the mentioned dummy dronelist
     * Note: TODO: returns the dummy drone list to DroneList function
     *
     * @author @WassabieX
     * @last_modified 2024.01.12
     * @since 1.0
     */
    public static void rundummy() {
        SwingUtilities.invokeLater(() -> {
            List<String> dummydronelist = List.of("Drone 1", "Drone 2", "Drone 3");

            new DroneList(dummydronelist);
        });
    }

}
