import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TeamPanel extends JPanel {
    MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);

    JLabel nameLabel;
    private JPanel statsPanel;
        private JLabel offenceLabel;
        private JLabel defenceLabel;
        private JLabel rankingLabel;
        private JLabel rewardLabel;

    private final Team team;
    public TeamPanel (Team team) {
        this.team = team;
        initialize();
        setVisible(true);
    }

    private void initialize() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(marginBorder);

        nameLabel = new JLabel();
        nameLabel.setText(HTMLString.header(team.getName()));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setBackground(Color.LIGHT_GRAY);
        nameLabel.setOpaque(true);
        this.add(nameLabel);

        statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(0, 1, 0, 0));
        this.add(statsPanel);

        offenceLabel = new JLabel();
        offenceLabel.setText("Offence: " + String.format("%.1f", team.getAverageOffence()));
        statsPanel.add(offenceLabel);

        defenceLabel = new JLabel();
        defenceLabel.setText("Defence: " + String.format("%.1f", team.getAverageDefence()));
        statsPanel.add(defenceLabel);

        rankingLabel = new JLabel();
        rankingLabel.setText("Difficulty: " + String.format("%.1f", team.getDifficulty()));
        statsPanel.add(rankingLabel);
    }

    public void addButton(String buttonText, ActionListener actionListener) {
        rewardLabel = new JLabel();
        rewardLabel.setText("Reward: $" + (int) team.getDifficulty() * 10);
        statsPanel.add(rewardLabel);

        JButton button = new JButton(buttonText);
        button.addActionListener(actionListener);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(button);
    }

    public Team getTeam() {
        return team;
    }

}
