//package gui;
//import java.awt.event.ActionListener;
//import javax.swing.*;
//
//import management.GameManager;
//import utility.HTMLString;
//import data.Team;
//
//import java.awt.*;
//
//
///**
// * TeamPanel is a display panel used to create a visual representation of a {@link Team}
// *
// * @author Harrison Parkes
// */
//public class TeamPanel extends JPanel {
//    final MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);
//
//    JLabel nameLabel;
//    private JPanel statsPanel;
//    private JLabel offenceLabel;
//    private JLabel defenceLabel;
//    private JLabel rankingLabel;
//
//    private final Team team;
//
//    /**
//     * Instantiates a new TeamPanel for the provided team
//     * @param team The team to visualise
//     */
//    public TeamPanel(Team team) {
//        this.team = team;
//        initialize();
//        setVisible(true);
//    }
//
//    /**
//     * Initialize the contents of the frame.
//     */
//    private void initialize() {
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//        this.setBorder(marginBorder);
//
//        nameLabel = new JLabel();
//        nameLabel.setText(HTMLString.subHeading(team.getName()));
//        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        nameLabel.setBackground(Color.LIGHT_GRAY);
//        nameLabel.setOpaque(true);
//        this.add(nameLabel);
//
//        statsPanel = new JPanel();
//        statsPanel.setLayout(new GridLayout(0, 1, 0, 0));
//        this.add(statsPanel);
//
//        offenceLabel = new JLabel();
//        offenceLabel.setText("Offence: " + String.format("%.1f", team.getAverageOffence()));
//        statsPanel.add(offenceLabel);
//
//        defenceLabel = new JLabel();
//        defenceLabel.setText("Defence: " + String.format("%.1f", team.getAverageDefence()));
//        statsPanel.add(defenceLabel);
//
//        rankingLabel = new JLabel();
//        rankingLabel.setText("Difficulty: " + String.format("%.1f", team.getDifficulty()));
//        statsPanel.add(rankingLabel);
//    }
//
//    /**
//     * Add a button to the TeamPanel with the provided text and {@link ActionListener}
//     * @param buttonText The text to show on the button
//     * @param actionListener The action listener to handle to {@link java.awt.event.ActionEvent}
//     *                       created when the button is clicked
//     */
//    public void addButton(String buttonText, ActionListener actionListener) {
//        JLabel rewardLabel = new JLabel();
//        rewardLabel.setText("Reward: $" + (int) team.getDifficulty() * 10 * GameManager.isGameHard() * (0.15 * GameManager.currentWeek()));
//        statsPanel.add(rewardLabel);
//
//        JButton button = new JButton(buttonText);
//        button.addActionListener(actionListener);
//        button.setAlignmentX(Component.CENTER_ALIGNMENT);
//        this.add(button);
//    }
//
//    /**
//     * Gets the team associated with this panel
//     * @return The team this panel represents
//     */
//    public Team getTeam() {
//        return team;
//    }
//
//    /**
//     * Reload the team panel to reflect any changes made to the team
//     */
//    public void reload() {
//        offenceLabel.setText("Offence: " + String.format("%.1f", team.getAverageOffence()));
//        defenceLabel.setText("Defence: " + String.format("%.1f", team.getAverageDefence()));
//        rankingLabel.setText("Difficulty: " + String.format("%.1f", team.getDifficulty()));
//    }
//}
