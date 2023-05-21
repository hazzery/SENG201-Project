import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.*;


/**
 * DANIEL THIS ONE IS ON YOU TOO XOXOX
 *
 * @author Daniel Smith
 */
public class MatchWindow extends JPanel {
	MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);


	private JPanel headerPanel;
		private JLabel matchLabel;
	private JPanel mainPanel;
		private PurchasablesShelf playerTeamPanel;
		private JPanel gameOutputPanel;
			private JLabel idkLabel;
			private JLabel gameOutputLabel;
		private PurchasablesShelf oppositionTeamPanel;
	private JPanel footerPanel;
		private JButton lightAttackButton;
		private JButton heavyAttackButton;
		private JButton healButton;
		private JButton exitMatchButton;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	// 	GameManager.initializeAthletes();
	// 	GameManager.initializeItems();
	// 	WindowManager.initializeMainWindow();
	// 	GameManager.setConfiguration("DanielsTeam", 12, false);
	// 	GameManager.startGame(GameManager.athletes, 10000);

    //     OppositionTeam oppositionTeam = new OppositionTeam();
	// 	GameManager.oppositionTeam = oppositionTeam;
    //     Athlete[] oppositionAthletes = oppositionTeam.getAthletes();
	// 	GameMechanics.playGame(1, GameManager.athletes, oppositionAthletes);

	// 	WindowManager.showMatchScreen();
	}

	/**
	 * Create the application.
	 */
	public MatchWindow() {
		initialize();
		setVisible(true);
	}

	public void turnAction(int index){
		GameMechanics.guiButtonPress(index);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setLayout(new BorderLayout(0, 0));
		this.setBorder(marginBorder);

		//HEADER
		headerPanel = new JPanel();
		headerPanel.setBorder(marginBorder);
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
		this.add(headerPanel, BorderLayout.NORTH);

		matchLabel = new JLabel("Match");
		headerPanel.add(matchLabel);

		//MAIN
		mainPanel = new JPanel();
		mainPanel.setBorder(marginBorder);
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		this.add(mainPanel, BorderLayout.CENTER);

		playerTeamPanel = new PurchasablesShelf(GameManager.team.getActives(), "Your Team");
		mainPanel.add(playerTeamPanel);

		gameOutputPanel = new JPanel();
		gameOutputPanel.setBorder(marginBorder);
		gameOutputPanel.setLayout(new BoxLayout(gameOutputPanel, BoxLayout.X_AXIS));
		mainPanel.add(gameOutputPanel);

		idkLabel = new JLabel("Game Output:    ");
		gameOutputPanel.add(idkLabel);

		gameOutputLabel = new JLabel("SOME OUPUT LOL");
		gameOutputPanel.add(gameOutputLabel);

		oppositionTeamPanel = new PurchasablesShelf(GameManager.oppositionTeam.getAthletes(), "Opposition Team");
		mainPanel.add(oppositionTeamPanel);

		//FOOTER
		footerPanel = new JPanel();
		footerPanel.setBorder(marginBorder);
		footerPanel.setLayout(new GridLayout(1, 4, 0, 0));
		this.add(footerPanel, BorderLayout.SOUTH);

        //BUTTONS
		lightAttackButton = new JButton("Light Attack");
		lightAttackButton.addActionListener(this::lightAttack);
		footerPanel.add(lightAttackButton);

		heavyAttackButton = new JButton("Heavy Attack");
		heavyAttackButton.addActionListener(this::heavyAttack);
		footerPanel.add(heavyAttackButton);

		healButton = new JButton("Heal");
		healButton.addActionListener(this::heal);
		footerPanel.add(healButton);

		exitMatchButton = new JButton("Exit Match");
		exitMatchButton.addActionListener(this::exitMatch);
		footerPanel.add(exitMatchButton);
	}

	private void update(){
		playerTeamPanel.reload(GameMechanics.athleteList);
		oppositionTeamPanel.reload(GameMechanics.oppositionAthletes);
	}

	private void exitMatch(ActionEvent actionEvent) {
		System.out.println("Exit Game");
		turnAction(3);
		update();
	}

	private void heal(ActionEvent actionEvent) {
		System.out.println("Heal");
		turnAction(2);
		update();
	}

	private void heavyAttack(ActionEvent actionEvent) {
		System.out.println("Heavy Attack");
		turnAction(1);
		update();
	}

	private void lightAttack(ActionEvent actionEvent) {
		System.out.println("Light Attack");
		turnAction(0);
		update();
	}
}
