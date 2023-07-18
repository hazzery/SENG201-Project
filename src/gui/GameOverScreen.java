package gui;
import javax.swing.*;

import management.GameManager;
import management.WindowManager;

import java.awt.*;


/**
 * GameOverScreen is the display panel that shows the user their final score and inform them the game is over
 * 
 * @author Harrison Parkes
 */
public class GameOverScreen extends JPanel {

    /**
     * Initialize the contents of the Game Over Screen.
    */
    public GameOverScreen() {
        this.setLayout(new BorderLayout(0, 0));

        JPanel contentPanel = new JPanel();
        MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);
        contentPanel.setBorder(marginBorder);
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
        this.add(contentPanel, BorderLayout.CENTER);

        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(gameOverLabel);

        DisplayablesShelf playerTeamPanel = new DisplayablesShelf(GameManager.team.getActives(), "Your Team");
        playerTeamPanel.setBorder(marginBorder);
        contentPanel.add(playerTeamPanel);

        JLabel bankBalanceLabel = new JLabel("You finished with $" + GameManager.getBankBalance());
        bankBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(bankBalanceLabel);

        JPanel footerPanel = new JPanel();
        footerPanel.setBorder(marginBorder);
        footerPanel.setLayout(new GridLayout(1, 0, 0, 0));
        this.add(footerPanel, BorderLayout.SOUTH);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> {
//            GameManager.resetGame();
            WindowManager.showInitScreen();
        });
        footerPanel.add(playAgainButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        footerPanel.add(exitButton);
    }
}
