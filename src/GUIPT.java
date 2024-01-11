import javax.swing.*; // library for gui
import java.awt.BorderLayout;


public class GUIPT extends JFrame
{
	
	/**
     * @functionality
     * The following constructor creates a GUI with a single button
	 * After clicking on the button, it should present a list of all drones to the user
	 * 
	 * @Layout Borderlayout
     * @author Wassabie
     * @version 1.0
     * @last_modified 2024.01.11
     */

	public void GUI() //constructor for gui class
	{
		JLabel label = new JLabel("Drone GUI ");
		JButton button = new JButton("Open Drone List");
		
		setLayout(new BorderLayout());
		
		add(label, BorderLayout.NORTH);
		add(button, BorderLayout.CENTER);
		
		button.addActionListener(new ActionListener()  {
			
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(() -> {
					List<String> dummyDroneList = List.of("Drone 1", "Drohne32", "Drohne 3");
					new DroneList(dummyDroneList);
				});
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);
		
	}
	
	
	
	public static void testGUI(String[] args) 
	{
		SwingUtilities.invokeLater(() -> new GUI());
	}

}
