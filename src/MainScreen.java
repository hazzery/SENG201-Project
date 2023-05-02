
import java.awt.EventQueue;
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

	private JFrame frmHello;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frmHello.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
        initialize();
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHello = new JFrame();
		frmHello.setTitle("THE GAME!!!!");
		frmHello.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        frmHello.setBounds(0, 0, 1920, 1080);
		frmHello.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHello.getContentPane().setLayout(null);
		
		JTextPane txtpnTheGameIs = new JTextPane();
		txtpnTheGameIs.setText("The Game Is HERE!!!");
		txtpnTheGameIs.setBounds(24, 112, 151, 20);
		frmHello.getContentPane().add(txtpnTheGameIs);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(229, 11, 422, 355);
		frmHello.getContentPane().add(panel);
		
		JButton btnNewButton = new JButton("Start Game");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Start Game Button Pressed", "InfoBox: Infomation", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(47, 158, 113, 23);
		frmHello.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Settings");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Settings Button Pressed", "InfoBox: Infomation", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton_1.setBounds(47, 194, 113, 23);
		frmHello.getContentPane().add(btnNewButton_1);
	}
}