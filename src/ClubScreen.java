import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ClubScreen extends JPanel {
    MarginBorder marginBorder = new MarginBorder(2, Color.BLACK, 5);

    // Indentation of components below shows hierarchy of elements on the screen
    private JPanel headerPanel;
        private JLabel clubHeaderLabel;
    private JPanel mainPanel;
        private JPanel teamPanel;
            private JPanel athletesPanel;
                private AthletePanel[] athletePanels;
            private JPanel reservesPanel;
                private AthletePanel[] reservePanels;
        private JPanel itemsPanel;
            private JPanel[] itemPanels;
                private JLabel[] itemNameLabels;
                private JPanel[] itemImprovementPanels;
                    private JLabel[] itemImprovementAmountLabels;
                    private JLabel[] itemStatTypeLabels;
                private JButton[] itemUseButtons;
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

        athletesPanel = new JPanel();
        athletesPanel.setBorder(marginBorder);
        athletesPanel.setLayout(new GridLayout(1, 0, 0, 0));
        mainPanel.add(athletesPanel);

        athletePanels = new AthletePanel[GameManager.team.numActive()];

        for (int i = 0; i < GameManager.team.numActive(); i++) {
            athletePanels[i] = new AthletePanel(GameManager.team.getActive(i));
            athletesPanel.add(athletePanels[i]);
        }

        reservesPanel = new JPanel();
        reservesPanel.setBorder(marginBorder);
        reservesPanel.setLayout(new GridLayout(1, 0, 0, 0));
        mainPanel.add(reservesPanel);

        reservePanels = new AthletePanel[GameManager.team.numReserves()];

        for (int i = 0; i < GameManager.team.numReserves(); i++) {
            reservePanels[i] = new AthletePanel(GameManager.team.getReserve(i));
            reservesPanel.add(reservePanels[i]);
        }

        itemsPanel = new JPanel();
        itemsPanel.setBorder(marginBorder);
        itemsPanel.setLayout(new GridLayout(1, 0, 0, 0));
        mainPanel.add(itemsPanel);

        itemPanels = new JPanel[GameManager.items.size()];
        itemNameLabels = new JLabel[GameManager.items.size()];
        itemImprovementPanels = new JPanel[GameManager.items.size()];
        itemImprovementAmountLabels = new JLabel[GameManager.items.size()];
        itemStatTypeLabels = new JLabel[GameManager.items.size()];
        itemUseButtons = new JButton[GameManager.items.size()];

        for (int i = 0; i < GameManager.items.size(); i++) {
            itemPanels[i] = new JPanel();
            itemPanels[i].setBorder(marginBorder);
            itemPanels[i].setLayout(new GridLayout(0, 1, 0, 0));
            itemsPanel.add(itemPanels[i]);

            Item item = GameManager.items.get(i);

            itemNameLabels[i] = new JLabel();
            itemNameLabels[i].setText(item.getName());
            itemPanels[i].add(itemNameLabels[i]);

            itemImprovementPanels[i] = new JPanel();
            itemImprovementPanels[i].setBorder(marginBorder);
            itemImprovementPanels[i].setLayout(new BoxLayout(itemImprovementPanels[i], BoxLayout.X_AXIS));
            itemPanels[i].add(itemImprovementPanels[i]);

            itemImprovementAmountLabels[i] = new JLabel();
            itemImprovementAmountLabels[i].setText("+" + item.getImprovementAmount());
            itemImprovementPanels[i].add(itemImprovementAmountLabels[i]);

            itemImprovementPanels[i].add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(7, 0)));

            itemStatTypeLabels[i] = new JLabel();
            itemStatTypeLabels[i].setText(item.getStatType().name().toLowerCase());
            itemImprovementPanels[i].add(itemStatTypeLabels[i]);

            itemUseButtons[i] = new JButton();
            itemUseButtons[i].setText("Use");
            itemUseButtons[i].addActionListener(e -> JOptionPane.showMessageDialog(this,
                    "Which Athlete?", "Use Item", JOptionPane.INFORMATION_MESSAGE));
            itemPanels[i].add(itemUseButtons[i]);
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
