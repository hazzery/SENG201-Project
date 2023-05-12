import javax.swing.*;
import java.awt.*;

public class ClubScreen extends GameScreenPanel {
    MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);

    // Indentation of components below shows hierarchy of elements on the screen
    private JPanel mainPanel;
        private JPanel athletesWrapperPanel;
            private JLabel activeAthletesLabel;
            private JPanel athletesPanel;
                private ClubAthletePanel[] athletePanels;
        private JPanel reservesWrapperPanel;
            private JLabel inactiveAthletesLabel;
            private JPanel reservesPanel;
                private ClubAthletePanel[] reservePanels;
        private JPanel itemsWrapperPanel;
            private JLabel itemsLabel;
            private JPanel itemsPanel;
                private JPanel[] itemPanels;

    @Override
    protected void  initialize() {
        super.initialize();

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

        athletePanels = new ClubAthletePanel[GameManager.NUM_ALL_ATHLETES];

        for (int i = 0; i < GameManager.team.numActive(); i++) {
            athletePanels[i] = new ClubAthletePanel(GameManager.team.getActive(i), false, this);
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

        reservePanels = new ClubAthletePanel[Team.MAX_RESERVES];

        for (int i = 0; i < GameManager.team.numReserves(); i++) {
            reservePanels[i] = new ClubAthletePanel(GameManager.team.getReserve(i), true, this);
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
            itemPanels[i] = new ClubItemPanel(GameManager.items.get(i), this);
            itemsPanel.add(itemPanels[i]);
        }
    }

    ClubScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.CLUB, gameScreen);
    }

    void reserveAthlete(Athlete athlete, ClubAthletePanel athletePanel) {
        System.out.println("Reserving " + athlete.getName());
        boolean successfullyReserved = false;
        try {
            successfullyReserved = GameManager.team.setReserve(athlete);
            System.out.println("Successfully reserved " + athlete.getName());
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

    void activateAthlete(Athlete athlete, ClubAthletePanel athletePanel) {
        System.out.println("Activating " + athlete.getName());
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
