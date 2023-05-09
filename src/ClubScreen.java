import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ClubScreen extends JPanel {
    private static final int BORDER_WIDTH = 2;
    private static final Color BORDER_COLOUR = Color.BLACK;

    // Indentation of components below shows hierarchy of elements on the screen
    private JPanel mainPanel;
        private JPanel athletesPanel;
            private JPanel[] athletePanels;
                private JLabel[] athleteNameLabels;
                private JLabel[] athleteOffenceLabels;
                private JLabel[] athleteDefenceLabels;
                private JLabel[] athleteStaminaLabels;
                private JProgressBar[] athleteHealthLabels;
        private JPanel itemsPanel;
            private JPanel[] itemPanels;
                private JLabel[] itemNameLabels;
                private JLabel[] itemImprovementAmountLabels;
                private JLabel[] itemStatTypeLabels;
                private JButton[] itemUseButtons;
        private JPanel goElsewherePanel;
            private JButton goToMarketButton;
            private JButton goToStadiumButton;


    GameScreen parent;

    void initialize() {
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(new LineBorder(BORDER_COLOUR, BORDER_WIDTH));

        mainPanel = new JPanel();
        mainPanel.setBorder(new LineBorder(BORDER_COLOUR, BORDER_WIDTH));
        mainPanel.setLayout(new GridLayout(2, 1, 0, 0));
        this.add(mainPanel, BorderLayout.CENTER);

        athletesPanel = new JPanel();
        athletesPanel.setBorder(new LineBorder(BORDER_COLOUR, BORDER_WIDTH));
        athletesPanel.setLayout(new GridLayout(1, 0, 0, 0));
        mainPanel.add(athletesPanel);

        athletePanels = new JPanel[GameManager.team.size()];
        athleteNameLabels = new JLabel[GameManager.team.size()];
        athleteOffenceLabels = new JLabel[GameManager.team.size()];
        athleteDefenceLabels = new JLabel[GameManager.team.size()];
        athleteStaminaLabels = new JLabel[GameManager.team.size()];
        athleteHealthLabels = new JProgressBar[GameManager.team.size()];

        for (int i = 0; i < GameManager.team.size(); i++) {
            athletePanels[i] = new JPanel();
            athletePanels[i].setBorder(new LineBorder(BORDER_COLOUR, BORDER_WIDTH));
            athletePanels[i].setLayout(new GridLayout(0, 1, 0, 0));
            athletesPanel.add(athletePanels[i]);

            athleteNameLabels[i] = new JLabel();
            athleteNameLabels[i].setText(GameManager.team.get(i).getNickname());
            athletePanels[i].add(athleteNameLabels[i]);

            athleteOffenceLabels[i] = new JLabel();
            athleteOffenceLabels[i].setText("Offence: " + GameManager.team.get(i).getOffence());
            athletePanels[i].add(athleteOffenceLabels[i]);

            athleteDefenceLabels[i] = new JLabel();
            athleteDefenceLabels[i].setText("Defence: " + GameManager.team.get(i).getDefence());
            athletePanels[i].add(athleteDefenceLabels[i]);

            athleteStaminaLabels[i] = new JLabel();
            athleteStaminaLabels[i].setText("Stamina: " + GameManager.team.get(i).getStamina());
            athletePanels[i].add(athleteStaminaLabels[i]);

            athleteHealthLabels[i] = new JProgressBar();
            athleteHealthLabels[i].setValue(GameManager.team.get(i).getCurrentHealth());
            athleteHealthLabels[i].setStringPainted(true);
            athletePanels[i].add(athleteHealthLabels[i]);
        }

        itemsPanel = new JPanel();
        itemsPanel.setBorder(new LineBorder(BORDER_COLOUR, BORDER_WIDTH));
        itemsPanel.setLayout(new GridLayout(1, 0, 0, 0));
        mainPanel.add(itemsPanel);

        itemPanels = new JPanel[GameManager.items.size()];
        itemNameLabels = new JLabel[GameManager.items.size()];
        itemImprovementAmountLabels = new JLabel[GameManager.items.size()];
        itemStatTypeLabels = new JLabel[GameManager.items.size()];
        itemUseButtons = new JButton[GameManager.items.size()];

        for (int i = 0; i < GameManager.items.size(); i++) {
            itemPanels[i] = new JPanel();
            itemPanels[i].setBorder(new LineBorder(BORDER_COLOUR, BORDER_WIDTH));
            itemPanels[i].setLayout(new GridLayout(0, 1, 0, 0));
            itemsPanel.add(itemPanels[i]);

            itemNameLabels[i] = new JLabel();
            itemNameLabels[i].setText(GameManager.items.get(i).getName());
            itemPanels[i].add(itemNameLabels[i]);

            itemImprovementAmountLabels[i] = new JLabel();
            itemImprovementAmountLabels[i].setText("+" + GameManager.items.get(i).getImprovementAmount());
            itemPanels[i].add(itemImprovementAmountLabels[i]);

            itemStatTypeLabels[i] = new JLabel();
            itemStatTypeLabels[i].setText("Type: " + GameManager.items.get(i).getStatType());
            itemPanels[i].add(itemStatTypeLabels[i]);

            itemUseButtons[i] = new JButton();
            itemUseButtons[i].setText("Use");
            itemUseButtons[i].addActionListener(e -> JOptionPane.showMessageDialog(this,
                    "Which Athlete?", "Use Item", JOptionPane.INFORMATION_MESSAGE));
            itemPanels[i].add(itemUseButtons[i]);
        }

        goElsewherePanel = new JPanel();
        goElsewherePanel.setBorder(new LineBorder(BORDER_COLOUR, BORDER_WIDTH));
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
