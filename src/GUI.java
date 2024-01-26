import javax.swing.*; // library for gui
import java.awt.BorderLayout;

public class GUI extends JFrame // class GUi extended by JFrame which is in lib
{

	public GUI() // constructor for gui class
	{
		JLabel label = new JLabel("Dronedata: "); // jlabel is only a text field
		JButton button = new JButton("Fetch Drone Data"); // button for fetching data

		setLayout(new BorderLayout()); // setss Layoutmanager to Borderlayout

		add(label, BorderLayout.NORTH); // position of label is top of window
		add(button, BorderLayout.CENTER); // position of label is center window

		button.addActionListener(e -> { // e -> for compact little function without creating new method or class

			// Action code here

			JOptionPane.showMessageDialog(this, "still need some wokring bruv"); // JOption for Pop-Up
		});

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // standard for closing app -> closes window
		setSize(400, 300); // 400x300 window
		setVisible(true); // make window visible

	}

	public static void runGUI() {
		SwingUtilities.invokeLater(() -> new GUI());
	}

}
