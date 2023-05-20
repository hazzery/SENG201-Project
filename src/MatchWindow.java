import java.awt.*;
import javax.swing.*;
import java.awt.Dialog.ModalExclusionType;

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
		WindowManager.showMatchScreen();
	}

	/**
	 * Create the application.
	 */
	public MatchWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setLayout(new BorderLayout(0, 0));

        //BOTTOM BUTTONS
		JButton btnNewButton = new JButton("Light Attack");
		btnNewButton.setBounds(150, 850, 200, 40);
		this.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Heavy Attack");
		btnNewButton_1.setBounds(450, 850, 200, 40);
		this.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Heal");
		btnNewButton_2.setBounds(750, 850, 200, 40);
		this.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Next Turn");
		btnNewButton_2_1.setBounds(1050, 850, 200, 40);
		this.add(btnNewButton_2_1);

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
