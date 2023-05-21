import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.*;


/**
 * InitScreen is the user interface that allows the user to select the initial game configuration
 * This consists of their team's name, the length of the season, and weather or not they play in hard mode
 *
 * @author Harrison Parkes
 */
public class InitScreen extends JPanel {
    private final MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);

    // Indentation of components below shows hierarchy of elements on the screen
    private JPanel headerPanel;
        private JLabel headerLabel;
    private JPanel mainPanel;
        private JPanel teamNamePanel;
            private JLabel enterTeamNameLabel;
            private JTextField teamNameText;
        private JPanel seasonLengthPanel;
            private JLabel enterSeasonLengthLabel;
            private JSlider seasonLengthSlider;
        private JPanel difficultyPanel;
            private JCheckBox difficultyButton;
    private JPanel footerPanel;
        private JButton confirmButton;
        private JButton cancelButton;


    /**
     * Create a new initialisation screen
     */
    public InitScreen() {
        initialize();
        setVisible(true);
    }

    /**
     * Confirms the user's selected configuration and moves onto the team selection screen
     */
    public void confirm() {
        String teamName = teamNameText.getText();

        try {
            Utilities.validateName(teamName, false);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }

        int seasonLength = seasonLengthSlider.getValue();

        boolean hardMode = difficultyButton.isSelected();

        GameManager.setConfiguration(teamName, seasonLength, hardMode);
        WindowManager.showTeamSelection();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.setLayout(new BorderLayout(100, 0));
        this.setBorder(new EmptyBorder(75, 75, 50, 75));

        headerPanel = new JPanel();
        headerPanel.setBorder(marginBorder);
        headerPanel.setLayout(new GridLayout(1, 2, 0, 0));
        this.add(headerPanel, BorderLayout.NORTH);

        headerLabel = new JLabel();
        headerLabel.setText("Welcome to Cool Ski Game!");
        headerPanel.add(headerLabel);

        mainPanel = new JPanel();
        mainPanel.setBorder(marginBorder);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
        this.add(mainPanel, BorderLayout.CENTER);

        teamNamePanel = new JPanel();
        teamNamePanel.setBorder(marginBorder);
        teamNamePanel.setLayout(new BoxLayout(teamNamePanel, BoxLayout.X_AXIS));
        mainPanel.add(teamNamePanel);

        enterTeamNameLabel = new JLabel();
        enterTeamNameLabel.setText("Enter team name:");
        teamNamePanel.add(enterTeamNameLabel);

        teamNameText = new JTextField();
        teamNameText.setText("ValidTeamName");
        teamNamePanel.add(teamNameText);

        seasonLengthPanel = new JPanel();
        seasonLengthPanel.setBorder(marginBorder);
        seasonLengthPanel.setLayout(new BoxLayout(seasonLengthPanel, BoxLayout.X_AXIS));
        mainPanel.add(seasonLengthPanel);

        enterSeasonLengthLabel = new JLabel();
        enterSeasonLengthLabel.setText("Choose season length in weeks:");
        seasonLengthPanel.add(enterSeasonLengthLabel);

        seasonLengthSlider = new JSlider();
        seasonLengthSlider.setMajorTickSpacing(1);
        seasonLengthSlider.setPaintTicks(true);
        seasonLengthSlider.setPaintLabels(true);
        seasonLengthSlider.setMinimum(5);
        seasonLengthSlider.setMaximum(15);
        seasonLengthSlider.setValue(10);
        seasonLengthPanel.add(seasonLengthSlider);

        difficultyButton = new JCheckBox();
        difficultyButton.setText("Hard Mode");
        mainPanel.add(difficultyButton);

        footerPanel = new JPanel();
        footerPanel.setBorder(marginBorder);
        footerPanel.setLayout(new GridLayout(1, 0, 0, 0));
        this.add(footerPanel, BorderLayout.SOUTH);

        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(e -> {});
        footerPanel.add(cancelButton);

        confirmButton = new JButton();
        confirmButton.setText("Confirm");
        confirmButton.addActionListener(e -> confirm());
        footerPanel.add(confirmButton);
    }
}
