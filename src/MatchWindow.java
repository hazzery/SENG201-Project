import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JButton;

public class MatchWindow {

	JFrame jFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatchWindow window = new MatchWindow();
					window.jFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MatchWindow() {
		initialize();
	}

	static boolean canNextTurn = false;

	private void playGUI(int result) {
		GameMechanics.inputFromGUI(result);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		jFrame = new JFrame();
		jFrame.setTitle("THE GAME!!!!");
		jFrame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		jFrame.setBounds(0, 0, 1920, 1080);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.getContentPane().setLayout(null);

        //BOTTOM BUTTONS
		
		JButton btnNewButton = new JButton("Light Attack");
		btnNewButton.setBounds(150, 850, 200, 40);
		jFrame.getContentPane().add(btnNewButton);
		//Action which when the button is pressed the play GUI function is called
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playGUI(0);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Heavy Attack");
		btnNewButton_1.setBounds(450, 850, 200, 40);
		jFrame.getContentPane().add(btnNewButton_1);

		btnNewButton_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                playGUI(1);
            }
        });
		
		JButton btnNewButton_2 = new JButton("Heal");
		btnNewButton_2.setBounds(750, 850, 200, 40);
		jFrame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playGUI(2);
			}
		});
		
		JButton btnNewButton_2_1 = new JButton("Next Turn");
		btnNewButton_2_1.setBounds(1050, 850, 200, 40);
		jFrame.getContentPane().add(btnNewButton_2_1);
		
		
		btnNewButton_2_1.setEnabled(canNextTurn);

		btnNewButton_2_1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				playGUI(3);
			}
		});

		// mainPanel = new JPanel();
        // mainPanel.setBorder(marginBorder);
        // mainPanel.setLayout(new GridLayout(0, 1, 0, 0));

        // oppositionWrapperPanel = new JPanel();
        // oppositionWrapperPanel.setBorder(marginBorder);
        // oppositionWrapperPanel.setLayout(new BoxLayout(oppositionWrapperPanel, BoxLayout.X_AXIS));
        // mainPanel.add(oppositionWrapperPanel);

        // //TODO Active Athletes in Game are displayed here

        // athletesWrapperPanel = new JPanel();
        // athletesWrapperPanel.setBorder(marginBorder);
        // athletesWrapperPanel.setLayout(new BoxLayout(athletesWrapperPanel, BoxLayout.X_AXIS));
        // mainPanel.add(athletesWrapperPanel);

        // //TODO Opposition athletes in Game are displayed here

        // FooterPanel = new JPanel();
        // FooterPanel.setBorder(marginBorder);
        // FooterPanel.setLayout(new BoxLayout(FooterPanel, BoxLayout.X_AXIS));
        // mainPanel.add(FooterPanel);





	}
}
