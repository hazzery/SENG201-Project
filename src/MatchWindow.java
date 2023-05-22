import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.*;


/**
 * Match Window, the screen that displays the match and allows the user to play the match
 *
 * @author Daniel Smith
 * @author Harrison Parkes
 */
public class MatchWindow extends JPanel {
	MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);
	GameMechanics gameMechanics = new GameMechanics();

	public String OppText;
	public String TeamText;
	public String gameText;

	private JPanel headerPanel;
		private JLabel matchLabel;
	private JPanel mainPanel;
		private PurchasablesShelf playerTeamPanel;
		private JPanel gameOutputPanel;
			private JLabel ouputLabel;
			static JLabel gameOutput;
			static JLabel teamOutput;
			static JLabel oppositionOutput;
		private PurchasablesShelf oppositionTeamPanel;
	private JPanel footerPanel;
		private JButton lightAttackButton;
		private JButton heavyAttackButton;
		private JButton healButton;
		private JButton exitMatchButton;

	/**
	 * Create the application.
	 */
	public MatchWindow() {
		initialize();
		setVisible(true);
	}


	/**
	 * Calls the {@link GameMechanics#playTurn(int)} method to play the turn
	 *
	 * @param index the index parameter is used to determine which button was pressed
	 *
	 */
	public void turnAction(int index){
		GameMechanics.guiButtonPress(index);
		update();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setLayout(new BorderLayout(0, 0));
		this.setBorder(marginBorder);

		headerPanel = new JPanel();
		headerPanel.setBorder(marginBorder);
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
		this.add(headerPanel, BorderLayout.NORTH);

		matchLabel = new JLabel("Match");
		headerPanel.add(matchLabel);

		mainPanel = new JPanel();
		mainPanel.setBorder(marginBorder);
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		this.add(mainPanel, BorderLayout.CENTER);

		playerTeamPanel = new PurchasablesShelf(GameManager.team.getActives(), "Your Team");
		mainPanel.add(playerTeamPanel);

		gameOutputPanel = new JPanel();
		gameOutputPanel.setBorder(marginBorder);
		gameOutputPanel.setLayout(new GridLayout(0, 1, 0, 0));
		mainPanel.add(gameOutputPanel);

		ouputLabel = new JLabel("Game Output:");
		gameOutputPanel.add(ouputLabel);

		gameOutput = new JLabel(gameText);
		gameOutputPanel.add(gameOutput);

		teamOutput = new JLabel(TeamText);
		gameOutputPanel.add(teamOutput);

		oppositionOutput = new JLabel(OppText);
		gameOutputPanel.add(oppositionOutput);

		oppositionTeamPanel = new PurchasablesShelf(GameManager.oppositionTeam.getAthletes(), "Opposition Team");
		mainPanel.add(oppositionTeamPanel);

		footerPanel = new JPanel();
		footerPanel.setBorder(marginBorder);
		footerPanel.setLayout(new GridLayout(1, 4, 0, 0));
		this.add(footerPanel, BorderLayout.SOUTH);

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


	/**
	 * Updates the {@link PurchasablesShelf} panels
	 */
	private void update(){
		playerTeamPanel.reload(GameMechanics.athleteList);
		oppositionTeamPanel.reload(GameMechanics.oppositionAthletes);
	}

	/**
	 * Calls the {@link GameMechanics#playTurn(int)} method to play the turn with the parameter 3 representing the exit condition
	 * @param actionEvent the actionEvent parameter is used to determine which button was pressed
	 */

	private void exitMatch(ActionEvent actionEvent) {
		System.out.println("Exit Game");
		turnAction(3);
	}

	/**
	 * Calls the {@link GameMechanics#playTurn(int)} method to play the turn with the parameter 2 representing the heal condition
	 * @param actionEvent the actionEvent parameter is used to determine which button was pressed
	 */
	private void heal(ActionEvent actionEvent) {
		System.out.println("Heal");
		turnAction(2);
	}

	/**
	 * Calls the {@link GameMechanics#playTurn(int)} method to play the turn with the parameter 1 representing the heavy attack condition
	 * @param actionEvent the actionEvent parameter is used to determine which button was pressed
	 */
	private void heavyAttack(ActionEvent actionEvent) {
		System.out.println("Heavy Attack");
		turnAction(1);
	}

	/**
	 * Calls the {@link GameMechanics#playTurn(int)} method to play the turn with the parameter 0 representing the light attack condition
	 * @param actionEvent the actionEvent parameter is used to determine which button was pressed
	 */
	private void lightAttack(ActionEvent actionEvent) {
		System.out.println("Light Attack");
		turnAction(0);
	}
}
