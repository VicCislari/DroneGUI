import javax.swing.*;
import java.awt.*;
import java.util.List;


public class DroneList extends JFrame 
{
	
	private final List<String> droneList; // list with drone fleet
	

	public DroneList(List<String> droneList)
	{
		this.droneList = droneList; // initialize drone list with the returned drone list
		initializeUI();
	}
	
	/**
    * @functionality
	* creates a new interface for the drone list.
	* as for now, it only presents a dummy list
	* used in GUIPT.java to list drones
	* 
	* @Layout FlowLayout
    * @author Wassabie
    * @version 1.0
    * @last_modified 2024.01.12
    */

	private void initializeUI()
	{
		JList<String> list = new JList<>(droneList.toArray(new String[0])); // creates JList to show drones, saves the new made array in a String
		JScrollPane scrollPane = new JScrollPane(list); // for scroll thing
		
		setLayout(new FlowLayout());
		add(scrollPane);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setSize(400,300);
		setTitle("Dronefleet");
		setVisible(true);
	}
	
	/**
    * @functionality
	* the mentioned dummy dronelist
	* returns the dummy drone list to DroneList function
	* 
    * @author Wassabie
    * @version 1.0
    * @last_modified 2024.01.12
	*/
	
	public static void rundummy()
	{
		SwingUtilities.invokeLater(() -> {
			List<String> dummydronelist = List.of("Drone 1", "Drone 2", "Drone 3");

			new DroneList(dummydronelist);
		});
	}

}
