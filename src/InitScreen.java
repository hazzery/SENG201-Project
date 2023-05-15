import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class InitScreen extends JPanel {
    private static final int BORDER_WIDTH = 0;

    private static final ArrayList<Athlete> selectedAthletes = new ArrayList<>(Team.MIN_SIZE);


    // Indentation of components below shows hierarchy of elements on the screen
    private JPanel headerPanel;
        private JPanel teamNamePanel;
            private JLabel enterTeamNameLabel;
            private JTextField teamNameText;
        private JPanel seasonLengthPanel;
            private JLabel enterSeasonLengthLabel;
            private JSlider seasonLengthSlider;
        private JCheckBox difficultyButton;
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



    /**
     * Create the application.
     */
    public InitScreen() {
        initialize();
        setVisible(true);
    }

    /**
     * Add an athlete to the selected athletes list and update the GUI
     * @param athlete the athlete to add to the selected athletes list
     */
    private void selectAthlete(Athlete athlete) {
        if (!selectedAthletes.contains(athlete)) {
            final int athleteIndex = selectedAthletes.size();

            String nickName = JOptionPane.showInputDialog("Choose a nickname for " + athlete.getName() + ":");
            try {
                GameManager.validateName(nickName, true);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                selectAthlete(athlete);
                return;
            }

            athlete.setNickname(nickName);
            selectedAthletes.add(athlete);

            selectedAthleteButtons[athleteIndex] = new JButton();
            String athleteButtonText = HTMLString.multiLine(athlete.getName(), athlete.getNickname());
            selectedAthleteButtons[athleteIndex].setText(athleteButtonText);
            selectedAthleteButtonsPanel.add(selectedAthleteButtons[athleteIndex]);

            selectedAthleteButtonsPanel.revalidate();
            selectedAthleteButtonsPanel.repaint();

            selectedAthleteButtons[athleteIndex].addActionListener(e -> unselectAthlete(athlete, athleteIndex));
        }
    }

    /**
     * Remove an athlete from the selected athletes list and update the GUI
     * @param athlete the athlete to remove from the selected athletes list
     * @param athleteIndex the index of the athlete in the selected athletes list
     */
    private void unselectAthlete(Athlete athlete, int athleteIndex) {
        if (selectedAthletes.contains(athlete)) {
            selectedAthletes.remove(athlete);
            selectedAthleteButtonsPanel.remove(selectedAthleteButtons[athleteIndex]);
            selectedAthleteButtonsPanel.revalidate();
            selectedAthleteButtonsPanel.repaint();
        }
    }

    /**
     * Remove all athletes from the selected athletes list and update the GUI
     */
    private void resetAthletes() {
        selectedAthletes.clear();
        selectedAthleteButtonsPanel.removeAll();
        selectedAthleteButtonsPanel.revalidate();
        selectedAthleteButtonsPanel.repaint();
    }

    /**
     * Confirm the selected athletes and move to the next screen
     */
    private void acceptTeam() {
        String teamName = teamNameText.getText();

        try {
            GameManager.validateName(teamName, false);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return;
        }

        int seasonLength = seasonLengthSlider.getValue();

        if (selectedAthletes.size() < Team.MIN_SIZE) {
            JOptionPane.showMessageDialog(this, "You must select at least " + Team.MIN_SIZE + " athletes");
            return;
        }

        boolean hardMode = difficultyButton.isSelected();

        GameManager.startGame(teamName, seasonLength, selectedAthletes, hardMode);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.setLayout(new BorderLayout(100, 0));
        this.setBorder(new EmptyBorder(75, 75, 75, 75));

        headerPanel = new JPanel();
        headerPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        headerPanel.setLayout(new GridLayout(1, 2, 0, 0));
        this.add(headerPanel, BorderLayout.NORTH);

        teamNamePanel = new JPanel();
        teamNamePanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        teamNamePanel.setLayout(new BoxLayout(teamNamePanel, BoxLayout.X_AXIS));
        headerPanel.add(teamNamePanel);

        enterTeamNameLabel = new JLabel();
        enterTeamNameLabel.setText("Enter team name:");
        teamNamePanel.add(enterTeamNameLabel);

        teamNameText = new JTextField();
        teamNameText.setText("ValidTeamName");
        teamNamePanel.add(teamNameText);

        seasonLengthPanel = new JPanel();
        seasonLengthPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        seasonLengthPanel.setLayout(new BoxLayout(seasonLengthPanel, BoxLayout.X_AXIS));
        headerPanel.add(seasonLengthPanel);

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
        seasonLengthPanel.add(difficultyButton);


        athleteSelectionPanel = new JPanel();
        athleteSelectionPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        athleteSelectionPanel.setLayout(new BorderLayout(0, 0));
        this.add(athleteSelectionPanel, BorderLayout.CENTER);

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

        allAthleteButtons = new JButton[GameManager.NUM_ALL_ATHLETES];
        for (int i = 0; i < allAthleteButtons.length; i++) {
            allAthleteButtons[i] = new JButton();

            String text = HTMLString.multiLine(GameManager.athletes.get(i).getName(),
                    GameManager.athletes.get(i).getDescription());
            allAthleteButtons[i].setText(text);

            final int finalI = i;
            allAthleteButtons[i].addActionListener(e -> this.selectAthlete(GameManager.athletes.get(finalI)));
            allAthleteButtonsPanel.add(allAthleteButtons[i]);
        }

        selectedAthleteButtonsPanel = new JPanel();
        selectedAthleteButtonsPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        selectedAthleteButtonsPanel.setLayout(new GridLayout(1, 8, 0, 0));
        buttonsWrapperPanel.add(selectedAthleteButtonsPanel);

        selectedAthleteButtons = new JButton[GameManager.NUM_ALL_ATHLETES];

        FooterPanel = new JPanel();
        FooterPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        FooterPanel.setLayout(new GridLayout(1, 2, 0, 0));
        this.add(FooterPanel, BorderLayout.SOUTH);

        resetAthletesButton = new JButton();
        resetAthletesButton.setText("Reset team");
        resetAthletesButton.addActionListener(e -> resetAthletes());
        FooterPanel.add(resetAthletesButton);

        acceptAthletesButton = new JButton();
        acceptAthletesButton.setText("Accept team and continue");
        acceptAthletesButton.addActionListener(e -> acceptTeam());
        FooterPanel.add(acceptAthletesButton);
    }

//    teamNameText.getText()
//    seasonLengthSlider.getValue()
//    JOptionPane.showMessageDialog(frame, output)
}
