import java.awt.Color;

import javax.swing.*;


/**
 * DANIEL THIS ONE IS ON YOU TOO XOXOX
 *
 * @author Daniel Smith
 */
public class MatchWindow extends JPanel {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GameManager.initializeAthletes();
		GameManager.initializeItems();
		WindowManager.initializeMainWindow();
		GameManager.setConfiguration("DanielsTeam", 12, false);
		GameManager.startGame(GameManager.athletes, 10000);

        OppositionTeam oppositionTeam = new OppositionTeam();
        Athlete[] oppositionAthletes = oppositionTeam.getAthletes();
		GameMechanics.playGame(1, GameManager.athletes, oppositionAthletes);

		WindowManager.showMatchScreen();
	}

	/**
	 * Create the application.
	 */
	public MatchWindow() {
		initialize();
	}

	public void turnAction(int index){
		GameMechanics.guiButtonPress(index);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(null);
        //BUTTONS
		JButton btnNewButton = new JButton("Light Attack (90%)");
		btnNewButton.setBounds(100, 950, 200, 40);
		this.add(btnNewButton);
		btnNewButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("Light Attack");
				turnAction(0);
			}
		});

		JButton btnNewButton_1 = new JButton("Heavy Attack (40%)");
		btnNewButton_1.setBounds(400, 950, 200, 40);
		this.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("Heavy Attack");
				turnAction(1);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Heal");
		btnNewButton_2.setBounds(700, 950, 200, 40);
		this.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("Heal");
				turnAction(2);

			}
		});

		JButton btnNewButton_2_2 = new JButton("Exit Match");
		btnNewButton_2_2.setBounds(1300, 950, 200, 40);
		this.add(btnNewButton_2_2);
		btnNewButton_2_2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.out.println("Exit Game");
				turnAction(3);
			}
		});

		JPanel header = new JPanel();
		JLabel headerJLabel = new JLabel("Match Screen");
		headerJLabel.setText("Match Screen");
		header.setBackground(new Color(192, 192, 192));
		header.setBounds(10, 5, 1620, 100);
		add(header);
		add(headerJLabel, header, UNDEFINED_CONDITION);

		

		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(5, 150, 1550, 200);
		add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(5, 400, 1550, 200);
		add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(5, 650, 1550, 200);
		add(panel_2);

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
