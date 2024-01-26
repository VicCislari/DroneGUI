import javax.swing.*; // library for gui
import java.awt.BorderLayout;

public class GUIPT extends JFrame {
	/**
	 * @functionality
	 *                The following constructor creates a GUI with one button
	 *                After clicking on the button, it should present a list of all
	 *                drones to the user
	 *                As of for the first version it will only return a dummy drone
	 *                list as example
	 * @bug WORKS ON ECLIPSEIDE BUT WONT OPEN VIA STUDIO CODE --FIXED--
	 * 
	 * @Layout BorderLayout
	 * @author Wassabie
	 * @since 1.1
	 * @last_modified 2024.01.12
	 */

	public void createGUI() {
		JLabel label = new JLabel("Drone GUI ");
		JButton button = new JButton("Open Drone List");

		setLayout(new BorderLayout());

		add(label, BorderLayout.NORTH);
		add(button, BorderLayout.CENTER);

		button.addActionListener(e -> {

			DroneList.rundummy();

		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setVisible(true);

	}

	public static void test_GUIPT(String[] args) {
		SwingUtilities.invokeLater(() -> new GUIPT().createGUI()); // BUGFIX -> changed method name & added method in
	}
}
