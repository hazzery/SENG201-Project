import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClubScreen extends JPanel {
    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);

    // Indentation of components below shows hierarchy of elements on the screen
    private JPanel headerPanel;
        private JLabel clubHeaderLabel;
    private JPanel mainPanel;
        private JPanel athletesWrapperPanel;
            private JLabel activeAthletesLabel;
            private JPanel athletesPanel;
                private AthletePanel[] athletePanels;
        private JPanel reservesWrapperPanel;
            private JLabel inactiveAthletesLabel;
            private JPanel reservesPanel;
                private AthletePanel[] reservePanels;
        private JPanel itemsWrapperPanel;
            private JLabel itemsLabel;
            private JPanel itemsPanel;
                private JPanel[] itemPanels;

    private JPanel goElsewherePanel;
        private JButton goToMarketButton;
        private JButton goToStadiumButton;


    GameScreen parent;

    void initialize() {
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(marginBorder);
        this.setBorder(new EmptyBorder(5, 5, 5, 5));

        headerPanel = new JPanel();
        headerPanel.setBorder(marginBorder);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        this.add(headerPanel, BorderLayout.NORTH);

        clubHeaderLabel = new JLabel(HTMLString.header("Club"));
        headerPanel.add(clubHeaderLabel);

        mainPanel = new JPanel();
        mainPanel.setBorder(marginBorder);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
        this.add(mainPanel, BorderLayout.CENTER);

        athletesWrapperPanel = new JPanel();
        athletesWrapperPanel.setBorder(marginBorder);
        athletesWrapperPanel.setLayout(new BoxLayout(athletesWrapperPanel, BoxLayout.X_AXIS));
        mainPanel.add(athletesWrapperPanel);

        activeAthletesLabel = new JLabel("Activated");
        activeAthletesLabel.setOpaque(true);
        athletesWrapperPanel.add(activeAthletesLabel);

        athletesPanel = new JPanel();
        athletesPanel.setBorder(marginBorder);
        athletesPanel.setLayout(new GridLayout(1, 0, 0, 0));
        athletesWrapperPanel.add(athletesPanel);

        athletePanels = new AthletePanel[GameManager.NUM_ALL_ATHLETES];

        for (int i = 0; i < GameManager.team.numActive(); i++) {
            athletePanels[i] = new AthletePanel(GameManager.team.getActive(i), false, this);
            athletesPanel.add(athletePanels[i]);
        }

        reservesWrapperPanel = new JPanel();
        reservesWrapperPanel.setBorder(marginBorder);
        reservesWrapperPanel.setLayout(new BoxLayout(reservesWrapperPanel, BoxLayout.X_AXIS));
        mainPanel.add(reservesWrapperPanel);

        inactiveAthletesLabel = new JLabel("Reserved");
        reservesWrapperPanel.add(inactiveAthletesLabel);

        reservesPanel = new JPanel();
        reservesPanel.setBorder(marginBorder);
        reservesPanel.setLayout(new GridLayout(1, 0, 0, 0));
        reservesWrapperPanel.add(reservesPanel);

        reservePanels = new AthletePanel[Team.MAX_RESERVES];

        for (int i = 0; i < GameManager.team.numReserves(); i++) {
            reservePanels[i] = new AthletePanel(GameManager.team.getReserve(i), true, this);
            reservesPanel.add(reservePanels[i]);
        }

        itemsWrapperPanel = new JPanel();
        itemsWrapperPanel.setBorder(marginBorder);
        itemsWrapperPanel.setLayout(new BoxLayout(itemsWrapperPanel, BoxLayout.X_AXIS));
        mainPanel.add(itemsWrapperPanel);

        itemsLabel = new JLabel("Inventory");
        itemsWrapperPanel.add(itemsLabel);

        itemsPanel = new JPanel();
        itemsPanel.setBorder(marginBorder);
        itemsPanel.setLayout(new GridLayout(1, 0, 0, 0));
        itemsWrapperPanel.add(itemsPanel);

        itemPanels = new ItemPanel[GameManager.items.size()];

        for (int i = 0; i < GameManager.items.size(); i++) {
            itemPanels[i] = new ItemPanel(GameManager.items.get(i), this);
            itemsPanel.add(itemPanels[i]);
        }

        goElsewherePanel = new JPanel();
        goElsewherePanel.setBorder(marginBorder);
        goElsewherePanel.setLayout(new GridLayout(1, 0, 0, 0));
        this.add(goElsewherePanel, BorderLayout.SOUTH);

        goToStadiumButton = new JButton();
        goToStadiumButton.setText("Go to Stadium");
        goToStadiumButton.addActionListener(e -> parent.goToStadium());
        goElsewherePanel.add(goToStadiumButton);

        goToMarketButton = new JButton();
        goToMarketButton.setText("Go to Market");
        goToMarketButton.addActionListener(e -> parent.goToMarket());
        goElsewherePanel.add(goToMarketButton);
    }

    ClubScreen(GameScreen gameScreen) {
        parent = gameScreen;
        initialize();
        setVisible(true);
    }

    void reserveAthlete(Athlete athlete, AthletePanel athletePanel) {
        boolean successfullyReserved = false;
        try {
            successfullyReserved = GameManager.team.setReserve(athlete);
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(null,
                    "Cannot have more than " + Team.MAX_RESERVES + " reserves");
        }

        if (successfullyReserved) {
            reservesPanel.setVisible(true);

            athletesPanel.remove(athletePanel);
            athletePanel.configureButton(true);
            reservesPanel.add(athletePanel);
            reservesPanel.revalidate();
            reservesPanel.repaint();
        }
    }

    void activateAthlete(Athlete athlete, AthletePanel athletePanel) {
        GameManager.team.setActive(athlete);
        reservesPanel.remove(athletePanel);
        athletePanel.configureButton(false);
        athletesPanel.add(athletePanel);
        athletesPanel.revalidate();
        athletesPanel.repaint();

        if (GameManager.team.numReserves() == 0) {
            reservesPanel.setVisible(false);
        }
    }

    public void updateAthletePanels() {
        for (AthletePanel athletePanel : athletePanels) {
            athletePanel.updateStats();
            athletePanel.revalidate();
            athletePanel.repaint();
        }

        for (AthletePanel reservePanel : reservePanels) {
            if (reservePanel != null) {
                reservePanel.updateStats();
                reservePanel.revalidate();
                reservePanel.repaint();
            }
        }
    }

    public void useItem(Item item, Athlete athlete, ItemPanel itemPanel) {
        athlete.applyItem(item);
        updateAthletePanels();
        GameManager.items.remove(item);
        itemsPanel.remove(itemPanel);
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }
}
