import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);


    // Indentation of components below shows hierarchy of elements on the screen
    private JPanel headerPanel;
        private JPanel teamInfoPanel;
            private JLabel teamNameLabel;
            private JLabel bankBalanceLabel;
        private JPanel seasonPanel;
            private JLabel currentWeekLabel;
            private JLabel remainingWeeksLabel;
    private JPanel centrePanel;
        private ClubScreen clubScreen;
        private MarketScreen marketScreen;
        private StadiumScreen stadiumScreen;
    private JPanel FooterPanel;
        private JButton nextWeekButton;


    enum Screen {
        CLUB, MARKET, STADIUM;

        public Screen next() {
            return values()[(ordinal() + 1) % values().length];
        }

        public Screen previous() {
            int index = (((ordinal() - 1) % values().length + values().length) % values().length);
            return values()[index];
        }
    }


    /**
     * Set up the JPanel
     */
    public GameScreen() {
        initialize();
        setVisible(true);
    }

    void updateTeamInfo() {
        teamNameLabel.setText(GameManager.team.getName());
        bankBalanceLabel.setText("$" + GameManager.getBankBalance());
        teamInfoPanel.revalidate();
        teamInfoPanel.repaint();
    }

    void updateSeasonInfo() {
        GameManager.nextWeek();
        currentWeekLabel.setText("Week " + GameManager.currentWeek() + " of " + GameManager.getSeasonLength());
        remainingWeeksLabel.setText((GameManager.getSeasonLength() - GameManager.currentWeek()) + " weeks remaining");
        seasonPanel.revalidate();
        seasonPanel.repaint();

        marketScreen.updateWeeklyPool();
        marketScreen.reload();
        stadiumScreen.reload();
    }

    /**
     * Changes the current screen to the specified screen
     * @param screen the screen type to display
     */
    void setScreen(Screen screen) {
        centrePanel.removeAll();

        switch (screen) {
            case CLUB -> {
                clubScreen.reload();
                centrePanel.add(clubScreen);
            }
            case MARKET -> {
                marketScreen.reload();
                centrePanel.add(marketScreen);
            }
            case STADIUM -> {
                stadiumScreen.reload();
                centrePanel.add(stadiumScreen);
            }
        }

        centrePanel.revalidate();
        centrePanel.repaint();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        this.setLayout(new BorderLayout(100, 0));
        this.setBorder(marginBorder);

        headerPanel = new JPanel();
        headerPanel.setBorder(marginBorder);
        headerPanel.setLayout(new GridLayout(1, 0, 0, 0));
        this.add(headerPanel, BorderLayout.NORTH);

        teamInfoPanel = new JPanel();
        teamInfoPanel.setBorder(marginBorder);
        teamInfoPanel.setLayout(new BoxLayout(teamInfoPanel, BoxLayout.X_AXIS));
        headerPanel.add(teamInfoPanel);

        teamNameLabel = new JLabel();
        teamNameLabel.setText(GameManager.team.getName());
        teamInfoPanel.add(teamNameLabel);

        teamInfoPanel.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(20, 0)));

        bankBalanceLabel = new JLabel();
        bankBalanceLabel.setText("$" + GameManager.getBankBalance());
        teamInfoPanel.add(bankBalanceLabel);

        seasonPanel = new JPanel();
        seasonPanel.setBorder(marginBorder);
        seasonPanel.setLayout(new BoxLayout(seasonPanel, BoxLayout.X_AXIS));
        headerPanel.add(seasonPanel);

        currentWeekLabel = new JLabel();
        currentWeekLabel.setText("Week " + GameManager.currentWeek() + " of " + GameManager.getSeasonLength());
        seasonPanel.add(currentWeekLabel);

        seasonPanel.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(20, 0)));

        remainingWeeksLabel = new JLabel();
        remainingWeeksLabel.setText((GameManager.getSeasonLength() - GameManager.currentWeek()) + " weeks remaining");
        seasonPanel.add(remainingWeeksLabel);

        centrePanel = new JPanel();
        centrePanel.setBorder(marginBorder);
        centrePanel.setLayout(new CardLayout(0, 0));
        this.add(centrePanel, BorderLayout.CENTER);

        clubScreen = new ClubScreen(this);
        centrePanel.add(clubScreen);
        stadiumScreen = new StadiumScreen(this);
        marketScreen = new MarketScreen(this);

        FooterPanel = new JPanel();
        FooterPanel.setBorder(marginBorder);
        FooterPanel.setLayout(new GridLayout(1, 0, 0, 0));
        this.add(FooterPanel, BorderLayout.SOUTH);

        nextWeekButton = new JButton();
        nextWeekButton.setText("Next Week");
        nextWeekButton.addActionListener(e -> updateSeasonInfo());
        FooterPanel.add(nextWeekButton);
    }

    public static void main(String[] args) {
        GameManager.initializeAthletes();
        GameManager.initializeItems();
        WindowManager.initializeMainWindow();
        GameManager.setConfiguration("HarrysTeam", 12, false);
        GameManager.startGame(GameManager.athletes, 1000);
    }
}
