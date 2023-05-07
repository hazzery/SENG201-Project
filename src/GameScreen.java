import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private static final int BORDER_WIDTH = 2;



    // Indentation of components below shows hierarchy of elements on the screen
    private JPanel headerPanel;
        private JPanel teamInfoPanel;
            private JLabel teamNameLabel;
            private JLabel bankBalanceLabel;
        private JPanel seasonPanel;
            private JLabel currentWeekLabel;
            private JLabel remainingWeeksLabel;
    private JPanel FooterPanel;
        private JButton nextWeekButton;


    /**
     * Set up the JPanel
     */
    public GameScreen() {
        initialize();
        setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.setLayout(new BorderLayout(100, 0));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        headerPanel = new JPanel();
        headerPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        headerPanel.setLayout(new GridLayout(1, 2, 0, 0));
        this.add(headerPanel, BorderLayout.NORTH);

        teamInfoPanel = new JPanel();
        teamInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        teamInfoPanel.setLayout(new BoxLayout(teamInfoPanel, BoxLayout.X_AXIS));
        headerPanel.add(teamInfoPanel);

        teamNameLabel = new JLabel();
        teamNameLabel.setText(GameManager.getTeamName());
        teamInfoPanel.add(teamNameLabel);

        teamInfoPanel.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(20, 0)));

        bankBalanceLabel = new JLabel();
        bankBalanceLabel.setText("$" + GameManager.getBankBalance());
        teamInfoPanel.add(bankBalanceLabel);

        seasonPanel = new JPanel();
        seasonPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        seasonPanel.setLayout(new BoxLayout(seasonPanel, BoxLayout.X_AXIS));
        headerPanel.add(seasonPanel);

        currentWeekLabel = new JLabel();
        currentWeekLabel.setText("Week " + GameManager.currentWeek() + " of " + GameManager.getSeasonLength());
        seasonPanel.add(currentWeekLabel);

        seasonPanel.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(20, 0)));

        remainingWeeksLabel = new JLabel();
        remainingWeeksLabel.setText((GameManager.getSeasonLength() - GameManager.currentWeek()) + " weeks remaining");
        seasonPanel.add(remainingWeeksLabel);

        FooterPanel = new JPanel();
        FooterPanel.setBorder(new LineBorder(new Color(0, 0, 0), BORDER_WIDTH));
        FooterPanel.setLayout(new GridLayout(1, 2, 0, 0));
        this.add(FooterPanel, BorderLayout.SOUTH);

        nextWeekButton = new JButton();
        nextWeekButton.setText("Next Week");
        FooterPanel.add(nextWeekButton);
    }

    public static void main(String[] args) {
        GameManager.initializeMainWindow();
        GameManager.startGame("HarryTeam", 10, GameManager.athletes, false);
    }
}
