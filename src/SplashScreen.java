import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;


public class SplashScreen extends JPanel{
	/**
	 * Create the application.
	 */
	public SplashScreen() {
        initialize();
		this.setVisible(true);
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JTextPane txtpnTheGameIs = new JTextPane();
		txtpnTheGameIs.setText("The Game Is HERE!!!");
		txtpnTheGameIs.setBounds(24, 112, 151, 20);
		this.add(txtpnTheGameIs);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(229, 11, 422, 355);
		this.add(panel);
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addActionListener(e -> GameManager.initializeGame());
		btnNewButton.setBounds(47, 158, 113, 23);
		this.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Settings");
		btnNewButton_1.addActionListener(e -> 
				JOptionPane.showMessageDialog(null, "Settings Button Pressed",
						"InfoBox: Information", JOptionPane.INFORMATION_MESSAGE));
		btnNewButton_1.setBounds(47, 194, 113, 23);
		this.add(btnNewButton_1);
	}
}