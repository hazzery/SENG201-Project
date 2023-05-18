import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class StadiumScreen extends GameScreenPanel {
    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);

    private JPanel mainPanel;
        private JPanel athletesWrapperPanel;
            private JLabel activeAthletesLabel;
            private JPanel activesShelf;
        private JPanel oppositionWrapperPanel;
            private JLabel oppositionAthletesLabel;
            private JPanel oppositionShelf;
        private JPanel FooterPanel;
            private JButton startMatchButton;

    @Override
    protected void initialize() {
        super.initialize();
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

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

        //SOMETHING TODO WITH ACTIVE ATHLETETS
        activesShelf = new JPanel();
        activesShelf.setBorder(marginBorder);
        activesShelf.setLayout(new BoxLayout(activesShelf, BoxLayout.X_AXIS));

        oppositionWrapperPanel = new JPanel();
        oppositionWrapperPanel.setBorder(marginBorder);
        oppositionWrapperPanel.setLayout(new BoxLayout(oppositionWrapperPanel, BoxLayout.X_AXIS));
        mainPanel.add(oppositionWrapperPanel);

        oppositionAthletesLabel = new JLabel("Opposition");
        oppositionWrapperPanel.add(oppositionAthletesLabel);
        
        //SOMETHING TODO WITH OPPOSITION ATHLETES
        oppositionShelf = new JPanel();
        oppositionShelf.setBorder(marginBorder);
        oppositionShelf.setLayout(new BoxLayout(oppositionShelf, BoxLayout.X_AXIS));
    
        FooterPanel = new JPanel();
        FooterPanel.setBorder(marginBorder);
        FooterPanel.setLayout(new GridLayout(1, 0, 0, 0));
        this.add(FooterPanel, BorderLayout.SOUTH);

        startMatchButton = new JButton();
        startMatchButton.setText("Start Match");
        startMatchButton.addActionListener(e -> startGame());
        FooterPanel.add(startMatchButton);
    
    }

    private Object startGame() {
        return null;
    }

    /**
     *
     */
    @Override
    public void reload() {
        //oppositionShelf.reload(GameManager.team.getActives());
        //activesShelf.reload(GameManager.opposition.createTeam()); //THis creates another team....
    }


    StadiumScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.STADIUM, gameScreen);
    }

    

}
