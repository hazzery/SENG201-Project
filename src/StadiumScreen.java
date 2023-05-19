import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class StadiumScreen extends GameScreenPanel {
    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);

    private JPanel mainPanel;
        private JPanel oppositionWrapperPanel;

            //Opposition team GUI Elements
            //TODO: Add Opposition Team GUI Elements
            //Formate of a card with a team label (name), a team gen rating, and a button to select the team
            private JLabel oppositionTeamLabel;
            private JPanel oppositionShelf;
            private JButton selectOppositionButton;

        private JPanel athletesWrapperPanel;
            private JLabel activeAthletesLabel;
            private JPanel activesShelf;
        
        private JPanel FooterPanel;
            private JButton startMatchButton;


    private OppositionTeam[] oppositionTeams;

    @Override
    protected void initialize() {
        super.initialize();
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

        mainPanel = new JPanel();
        mainPanel.setBorder(marginBorder);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
        this.add(mainPanel, BorderLayout.CENTER);

        oppositionWrapperPanel = new JPanel();
        oppositionWrapperPanel.setBorder(marginBorder);
        oppositionWrapperPanel.setLayout(new BoxLayout(oppositionWrapperPanel, BoxLayout.X_AXIS));
        mainPanel.add(oppositionWrapperPanel);

        //TODO 4 Opposition teams are presented each with

        //0: Name of team, just call OppositionTeam.getName()
        //1: Quick Athlete Stats
        //2: A button to select the team, then calls selectedOppositionTeam(OppositionTeam team)



        athletesWrapperPanel = new JPanel();
        athletesWrapperPanel.setBorder(marginBorder);
        athletesWrapperPanel.setLayout(new BoxLayout(athletesWrapperPanel, BoxLayout.X_AXIS));
        mainPanel.add(athletesWrapperPanel);



        
           
    
        FooterPanel = new JPanel();
        FooterPanel.setBorder(marginBorder);
        FooterPanel.setLayout(new GridLayout(1, 0, 0, 0));
        this.add(FooterPanel);

        startMatchButton = new JButton();
        startMatchButton.setText("Start Match");
        startMatchButton.addActionListener(e -> startGame());
        FooterPanel.add(startMatchButton);
    }

    @Override
    public void reload() {
        //oppositionShelf.reload(GameManager.team.getActives());
        //activesShelf.reload(GameManager.opposition.createTeam()); //THis creates another team....
    }

    StadiumScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.STADIUM, gameScreen);
    }

    
    //method which opens match screen JPanel when start match button is clicked
    private void startGame() {
        // TODO add your handling code here:
    }
}
