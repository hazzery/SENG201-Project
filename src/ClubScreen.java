import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClubScreen extends JPanel {
    MarginBorder marginBorder = new MarginBorder(2, Color.RED, 5);

    // Indentation of components below shows hierarchy of elements on the screen
    private JPanel headerPanel;
        private JLabel clubHeaderLabel;
    private JPanel mainPanel;
        private JPanel activesPanel;
            private JPanel athletesPanel;
                private JLabel activeAthletesLabel;
                private AthletePanel[] athletePanels;
        private JPanel inactivesPanel;
                private JLabel inactiveAthletesLabel;
            private JPanel reservesPanel;
                private AthletePanel[] reservePanels;
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

        clubHeaderLabel = new JLabel("Club");
        headerPanel.add(clubHeaderLabel);

        mainPanel = new JPanel();
        mainPanel.setBorder(marginBorder);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
        this.add(mainPanel, BorderLayout.CENTER);

        activesPanel = new JPanel();
        activesPanel.setBorder(marginBorder);
        activesPanel.setLayout(new BoxLayout(activesPanel, BoxLayout.X_AXIS));
        mainPanel.add(activesPanel);

        activeAthletesLabel = new JLabel("Active");
        activesPanel.add(activeAthletesLabel);

        athletesPanel = new JPanel();
        athletesPanel.setBorder(marginBorder);
        athletesPanel.setLayout(new GridLayout(1, 0, 0, 0));
        activesPanel.add(athletesPanel);

        athletePanels = new AthletePanel[GameManager.team.numActive()];

        for (int i = 0; i < GameManager.team.numActive(); i++) {
            athletePanels[i] = new AthletePanel(GameManager.team.getActive(i));
            athletesPanel.add(athletePanels[i]);
        }

        inactivesPanel = new JPanel();
        inactivesPanel.setBorder(marginBorder);
        inactivesPanel.setLayout(new BoxLayout(inactivesPanel, BoxLayout.X_AXIS));
        mainPanel.add(inactivesPanel);

        String reserveText = HTMLString.make("R", "e", "s", "e", "r", "v", "e", "d");
        inactiveAthletesLabel = new JLabel(reserveText);
        inactivesPanel.add(inactiveAthletesLabel);

        reservesPanel = new JPanel();
        reservesPanel.setBorder(marginBorder);
        reservesPanel.setLayout(new GridLayout(1, 0, 0, 0));
        inactivesPanel.add(reservesPanel);

        reservePanels = new AthletePanel[GameManager.team.numReserves()];

        for (int i = 0; i < GameManager.team.numReserves(); i++) {
            reservePanels[i] = new AthletePanel(GameManager.team.getReserve(i));
            reservesPanel.add(reservePanels[i]);
        }

        itemsPanel = new JPanel();
        itemsPanel.setBorder(marginBorder);
        itemsPanel.setLayout(new GridLayout(1, 0, 0, 0));
        mainPanel.add(itemsPanel);

        itemPanels = new ItemPanel[GameManager.items.size()];

        for (int i = 0; i < GameManager.items.size(); i++) {
            itemPanels[i] = new ItemPanel(GameManager.items.get(i));
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
}
