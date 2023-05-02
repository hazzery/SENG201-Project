//import javax.swing.*;
//import java.awt.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class InitScreen {
    public static final int NUM_ALL_ATHLETES = 8;
    private JFrame frame;

    private JPanel teamNameAndSeasonLengthPanel;
    private JLabel enterTeamNameLabel;
    private JTextField teamNameText;
    private JLabel enterSeasonLengthLabel;
    private JSlider seasonLengthSlider;
    private JPanel athleteSelectionPanel;
    private JPanel allAthleteButtonsPanel;
    private JLabel selectAthletes;
    private JButton[] allAthleteButtons;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InitScreen window = new InitScreen();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public InitScreen() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Ski team game");
        frame.setBounds(0, 0, 1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
        frame.getRootPane().setBorder(new EmptyBorder(75, 75, 75, 75));

        teamNameAndSeasonLengthPanel = new JPanel();
        teamNameAndSeasonLengthPanel.setLayout(new GridLayout(2, 2, 0, 0));
        frame.add(teamNameAndSeasonLengthPanel);

        enterTeamNameLabel = new JLabel();
        enterTeamNameLabel.setText("Enter team name:");
        teamNameAndSeasonLengthPanel.add(enterTeamNameLabel);

        teamNameText = new JTextField();
//        teamNameText.setMaximumSize(new Dimension(0, 100));
        teamNameAndSeasonLengthPanel.add(teamNameText);

        enterSeasonLengthLabel = new JLabel();
        enterSeasonLengthLabel.setText("Enter season length in weeks:");
        teamNameAndSeasonLengthPanel.add(enterSeasonLengthLabel);

        seasonLengthSlider = new JSlider();
        seasonLengthSlider.setMajorTickSpacing(1);
        seasonLengthSlider.setPaintTicks(true);
        seasonLengthSlider.setPaintLabels(true);
        seasonLengthSlider.setMinimum(5);
        seasonLengthSlider.setMaximum(15);
        seasonLengthSlider.setValue(10);
        teamNameAndSeasonLengthPanel.add(seasonLengthSlider);

        athleteSelectionPanel = new JPanel();
        athleteSelectionPanel.setLayout(new GridLayout(3, 1, 0, 0));
        frame.add(athleteSelectionPanel);

        selectAthletes = new JLabel();
        selectAthletes.setText("Select athletes from the below options:");
        athleteSelectionPanel.add(selectAthletes);

        allAthleteButtonsPanel = new JPanel();
        allAthleteButtonsPanel.setLayout(new GridLayout(2, 4, 0, 0));
        athleteSelectionPanel.add(allAthleteButtonsPanel);

        allAthleteButtons = new JButton[NUM_ALL_ATHLETES];
        for (int i = 0; i < allAthleteButtons.length; i++) {
            allAthleteButtons[i] = new JButton();
            allAthleteButtonsPanel.add(allAthleteButtons[i]);
        }
    }

}
