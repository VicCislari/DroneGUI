import javax.swing.*; // library for gui
import java.awt.BorderLayout;


public class GUIPT extends JFrame
{
	
	/**
     * @functionality
     * The following constructor creates a GUI with a single button
	 * After clicking on the button, it should present a list of all drones to the user
	 * v1.0 -> presents just a example of drones (dummyDroneList)
	 * @BUG: WORKS ON ECLIPSEIDE BUT WONT OPEN VIA STUDIO CODE
	 * 
	 * @Layout Borderlayout
     * @author Wassabie
     * @version 1.0
     * @last_modified 2024.01.11
     */

	public void GUIPT()
	{
		JLabel label = new JLabel("Drone GUI ");
		JButton button = new JButton("Open Drone List");
		
		setLayout(new BorderLayout());
		
		add(label, BorderLayout.NORTH);
		add(button, BorderLayout.CENTER);
		
		button.addActionListener( e-> {

			DroneList.rundummy();

		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);
		
	}
	
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater( () -> new GUIPT() );
	}

}
