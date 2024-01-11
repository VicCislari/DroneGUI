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
	
	private void initializeUI()
	{
		JList<String> list = new JList<>(droneList.toArray(new String[0])); // creates JList to show drones, saves the new made array in a String
		JScrollPane scrollPane = new JScrollPane(list); // for scroll thing
		
		setLayout(new GridLayout());
		
		add(scrollPane);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setSize(400,200);
		setTitle("Dronefleet");
		setVisible(true);
	}
	
	public static void rundummy()
	{
		SwingUtilities.invokeLater(() -> {
			List<String> dummydronelist = List.of("Drone 1", "Drone 2", "Drone 3");
			new DroneList(dummydronelist);
		});
	}

}
