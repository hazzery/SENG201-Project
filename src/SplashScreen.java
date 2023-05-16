import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.*;


public class SplashScreen extends JPanel {

	private static final int BORDER_WIDTH = 2;

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
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new EmptyBorder(150, 350, 350, 350));

		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
		this.add(titlePanel);

		JLabel gameTitle = new JLabel("Cool Ski Game");
		gameTitle.setFont(new Font(null, Font.PLAIN, 75));
		titlePanel.add(gameTitle);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2, 0, 0));
		buttonPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
		this.add(buttonPanel);
		
		JButton startGameButton = new JButton("Start Game");
		startGameButton.addActionListener(e -> WindowManager.showInitScreen());
		buttonPanel.add(startGameButton);
		
		JButton settingsButton = new JButton("Settings");
		settingsButton.addActionListener(e ->
				JOptionPane.showMessageDialog(null, "Settings Button Pressed",
				"InfoBox: Information", JOptionPane.INFORMATION_MESSAGE));
		buttonPanel.add(settingsButton);
	}
}