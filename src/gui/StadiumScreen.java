package gui;
import data.OppositionTeam;
import management.GameManager;

import java.awt.event.ActionEvent;
import javax.swing.*;

import java.awt.*;

/**
 * StadiumScreen is the display panel that allows the user to browse and choose from a small selection
 * of weekly matches to compete in. It is a display panel that sits within the {@link GameScreen}
 *
 * @author Harrison Parkes
 */
public class StadiumScreen extends GameScreenPanel {
    final MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);

    private TeamPanel playerTeam;
    private OppositionTeam[] opponents;

    /**
     * Initialize the contents of the frame.
     */
    @Override
    protected void initialize() {
        super.initialize();
        contentPanel.setLayout(new GridLayout(2, 1, 0, 0));

        updateWeeklyPool();

        //    protected JPanel contentPanel;
        JPanel playerTeamPanel = new JPanel();
        playerTeamPanel.setLayout(new GridLayout(0, 1, 0, 0));
        playerTeamPanel.setBorder(marginBorder);
        contentPanel.add(playerTeamPanel);

        playerTeam = new TeamPanel(GameManager.team);
        playerTeamPanel.add(playerTeam);

        JPanel oppositionTeamPanel = new JPanel();
        oppositionTeamPanel.setLayout(new GridLayout(1, 0, 0, 0));
        oppositionTeamPanel.setBorder(marginBorder);
        contentPanel.add(oppositionTeamPanel);

        TeamPanel[] oppositionTeams = new TeamPanel[opponents.length];
        for (int i = 0; i < oppositionTeams.length; i++) {
            oppositionTeams[i] = new TeamPanel(opponents[i]);
            oppositionTeamPanel.add(oppositionTeams[i]);
            oppositionTeams[i].addButton("Choose", this::selectOpponent);
        }
    }

    /**
     * Creates a new StadiumScreen panel with the given game screen as its parent
     * @param gameScreen The {@link GameScreen} that is the parent of this panel
     */
    StadiumScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.STADIUM, gameScreen);
    }

    /**
     * Event handler to select an opponent
     * Called when the user clicks the "Choose" button on an opponent team
     * @param event {@link ActionEvent} containing information about which button was clicked
     */
    private void selectOpponent(ActionEvent event) {
        TeamPanel panel = (TeamPanel) ((JButton)event.getSource()).getParent();
        OppositionTeam team = (OppositionTeam) panel.getTeam();
        GameManager.playMatch(team);
    }

    /**
     * Replaces the selection of opponents with new randomly generated ones
     */
    public void updateWeeklyPool() {
        opponents = OppositionTeam.generateOppositions(4);
    }

    /**
     * Reloads the contents of the screen to reflect changes in the game state
     */
    @Override
    public void reload() {
        playerTeam.reload();
    }
}
