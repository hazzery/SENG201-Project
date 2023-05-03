//import javax.swing.*;
//import java.awt.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class InitScreen {
    public static final int NUM_ALL_ATHLETES = 8;
    private static final int BORDER_WIDTH = 0;
    private JFrame frame;

    private JPanel HeaderPanel;
        private JPanel teamNamePanel;
            private JLabel enterTeamNameLabel;
            private JTextField teamNameText;
        private JPanel seasonLengthPanel;
            private JLabel enterSeasonLengthLabel;
            private JSlider seasonLengthSlider;
    private JPanel athleteSelectionPanel;
        private JLabel selectAthletesLabel;
        private JPanel buttonsWrapperPanel;
            private JPanel allAthleteButtonsPanel;
                private JButton[] allAthleteButtons;
            private JPanel selectedAthleteButtonsPanel;
                private JButton[] selectedAthleteButtons;
    private JPanel FooterPanel;
        private JButton resetAthletesButton;
        private JButton acceptAthletesButton;


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
        frame.getContentPane().setLayout(new BorderLayout(100, 0));
        frame.getRootPane().setBorder(new EmptyBorder(75, 75, 75, 75));

        HeaderPanel = new JPanel();
        HeaderPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        HeaderPanel.setLayout(new GridLayout(1, 2, 0, 0));
        frame.add(HeaderPanel, BorderLayout.NORTH);

        teamNamePanel = new JPanel();
        teamNamePanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        teamNamePanel.setLayout(new BoxLayout(teamNamePanel, BoxLayout.X_AXIS));
        HeaderPanel.add(teamNamePanel);

        enterTeamNameLabel = new JLabel();
        enterTeamNameLabel.setText("Enter team name:");
        teamNamePanel.add(enterTeamNameLabel);

        teamNameText = new JTextField();
        teamNamePanel.add(teamNameText);

        seasonLengthPanel = new JPanel();
        seasonLengthPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        seasonLengthPanel.setLayout(new BoxLayout(seasonLengthPanel, BoxLayout.X_AXIS));
        HeaderPanel.add(seasonLengthPanel);

        enterSeasonLengthLabel = new JLabel();
        enterSeasonLengthLabel.setText("Enter season length in weeks:");
        seasonLengthPanel.add(enterSeasonLengthLabel);

        seasonLengthSlider = new JSlider();
        seasonLengthSlider.setMajorTickSpacing(1);
        seasonLengthSlider.setPaintTicks(true);
        seasonLengthSlider.setPaintLabels(true);
        seasonLengthSlider.setMinimum(5);
        seasonLengthSlider.setMaximum(15);
        seasonLengthSlider.setValue(10);
        seasonLengthPanel.add(seasonLengthSlider);

        athleteSelectionPanel = new JPanel();
        athleteSelectionPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        athleteSelectionPanel.setLayout(new BorderLayout(0, 0));
        frame.add(athleteSelectionPanel, BorderLayout.CENTER);

        selectAthletesLabel = new JLabel();
        selectAthletesLabel.setText("Select athletes from the below options:");
        athleteSelectionPanel.add(selectAthletesLabel, BorderLayout.NORTH);

        buttonsWrapperPanel = new JPanel();
        buttonsWrapperPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        buttonsWrapperPanel.setLayout(new BoxLayout(buttonsWrapperPanel, BoxLayout.Y_AXIS));
        athleteSelectionPanel.add(buttonsWrapperPanel, BorderLayout.CENTER);


        allAthleteButtonsPanel = new JPanel();
        allAthleteButtonsPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        allAthleteButtonsPanel.setLayout(new GridLayout(2, 4, 0, 0));
        buttonsWrapperPanel.add(allAthleteButtonsPanel);

        buttonsWrapperPanel.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 10000)));

        allAthleteButtons = new JButton[NUM_ALL_ATHLETES];
        for (int i = 0; i < allAthleteButtons.length; i++) {
            allAthleteButtons[i] = new JButton();
            allAthleteButtonsPanel.add(allAthleteButtons[i]);
        }

        selectedAthleteButtonsPanel = new JPanel();
        selectedAthleteButtonsPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        selectedAthleteButtonsPanel.setLayout(new GridLayout(1, 8, 0, 0));
        buttonsWrapperPanel.add(selectedAthleteButtonsPanel);

        selectedAthleteButtons = new JButton[NUM_ALL_ATHLETES];
        for (int i = 0; i < selectedAthleteButtons.length; i++) {
            selectedAthleteButtons[i] = new JButton();
            selectedAthleteButtonsPanel.add(selectedAthleteButtons[i]);
        }

        FooterPanel = new JPanel();
        FooterPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        FooterPanel.setLayout(new GridLayout(1, 2, 0, 0));
        frame.add(FooterPanel, BorderLayout.SOUTH);

        resetAthletesButton = new JButton();
        resetAthletesButton.setText("Reset team");
        FooterPanel.add(resetAthletesButton);

        acceptAthletesButton = new JButton();
        acceptAthletesButton.setText("Accept team and continue");
        FooterPanel.add(acceptAthletesButton);
    }

}
