package gui;
import management.GameManager;
import management.WindowManager;

import javax.swing.*;

import java.awt.*;


/**
 * GameScreen is the screen that is shown once the user has selected their initial team members
 * The game screen only provides the header and footer panels and acts as a container
 * for the {@link ClubScreen}, {@link MarketScreen}, and {@link StadiumScreen} which sit inside the center
 *
 * @author Harrison Parkes
 */
public class GameScreen extends JPanel {
    final MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);


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


    /**
     * The Screen enum provides a clean way of changing between the three panels that sit within the game screen
     * namely the {@link ClubScreen}, {@link MarketScreen}, and {@link StadiumScreen}.
     *
     * @author Harrison Parkes
     */
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
     * Creates a new GameScreen for displaying on the main window
     */
    public GameScreen() {
        initialize();
        setVisible(true);
    }

    /**
     * Updates the team related information shown in the left of the game screen header
     * to reflect new changes in the game's state
     */
    public void updateTeamInfo() {
        teamNameLabel.setText(GameManager.team.getName());
        bankBalanceLabel.setText("$" + GameManager.getBankBalance());
        teamInfoPanel.revalidate();
        teamInfoPanel.repaint();
    }

    /**
     * Updates the season related information shown in the right of the game screen header
     * to reflect new changes in the game's state
     */
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

        // Indentation of components below shows hierarchy of elements on the screen
        JPanel headerPanel = new JPanel();
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

        JPanel footerPanel = new JPanel();
        footerPanel.setBorder(marginBorder);
        footerPanel.setLayout(new GridLayout(1, 0, 0, 0));
        this.add(footerPanel, BorderLayout.SOUTH);

        JButton nextWeekButton = new JButton();
        nextWeekButton.setText("Next Week");
        nextWeekButton.addActionListener(e -> updateSeasonInfo());
        footerPanel.add(nextWeekButton);
    }

    /**
     * Refreshes all the shelves to show updated content
     */
    public void reload() {
        clubScreen.reload();
        marketScreen.reload();
        stadiumScreen.reload();
    }

    /**
     * Launch the application straight into the GameScreen
     * Skips the team configuration screen
     */
    public static void main(String[] args) {
        GameManager.initializeAthletes();
        GameManager.initializeItems();
        WindowManager.initializeMainWindow();
        GameManager.setConfiguration("TeamName", 12, false);
        GameManager.startGame(GameManager.athletes, 10000);
    }
}
