import javax.swing.JFrame;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

public class MainScreen {

	private static JFrame frame;


	/**
	 * Create the application.
	 */
	public MainScreen() {
        initialize();
		frame.setVisible(true);
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("THE GAME!!!!");
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        frame.setBounds(0, 0, 1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnTheGameIs = new JTextPane();
		txtpnTheGameIs.setText("The Game Is HERE!!!");
		txtpnTheGameIs.setBounds(24, 112, 151, 20);
		frame.getContentPane().add(txtpnTheGameIs);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(229, 11, 422, 355);
		frame.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addActionListener(e -> GameManager.gameSetup());
		btnNewButton.setBounds(47, 158, 113, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Settings");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Settings Button Pressed", "InfoBox: Infomation", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1.setBounds(47, 194, 113, 23);
		frame.getContentPane().add(btnNewButton_1);
	}

	public static void closeWindow() {
		frame.dispose();
	}
}