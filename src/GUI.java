import javax.swing.*; // library for gui
import java.awt.BorderLayout;

public class GUI extends JFrame // class GUi extended by JFrame which is in lib
{
	/**
	 * @functionality
	 *                The following constructor creates a GUI with one button
	 *                After clicking on the button, it should present a list of all
	 *                drones to the user
	 *                As of for the first version it will only return a dummy drone
	 *                list as example
	 * @bug WORKS ON ECLIPSEIDE BUT WONT OPEN VIA STUDIO CODE --FIXED--
	 * @change old prototype, refer to App folder for the real GUIs
	 * 
	 * @Layout BorderLayout
	 * @author @WassabieX
	 * @since 1.1
	 * @last_modified 2024.01.12
	 */

	public GUI() // constructor for gui class
	{
		JLabel label = new JLabel("Dronedata: "); // jlabel is only a text field
		JButton button = new JButton("Fetch Drone Data"); // button for fetching data

		setLayout(new BorderLayout()); // setss Layoutmanager to Borderlayout

		add(label, BorderLayout.NORTH); // position of label is top of window
		add(button, BorderLayout.CENTER); // position of label is center window

		button.addActionListener(e -> {
			DroneList.rundummy();
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // standard for closing app -> closes window
		setSize(400, 300); // 400x300 window
		setVisible(true); // make window visible

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GUI());
	}

}
