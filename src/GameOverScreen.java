import javax.swing.*;
import java.awt.*;


/**
 * GameOverScreen is the display panel that shows the user their final score and inform them the game is over
 * 
 * @Author Harrison Parkes
 */
public class GameOverScreen extends JPanel {
    private final MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);

    private JPanel contentPanel;
        private JLabel gameOverLabel;
        private PurchasablesShelf playerTeamPanel;
        private JLabel bankBalanceLabel;
    private JPanel footerPanel;
        private JButton playAgainButton;
        private JButton exitButton;

    /**
     * Initialize the contents of the Game Over Screen.
    */
    public GameOverScreen() {
        this.setLayout(new BorderLayout(0, 0));

        contentPanel = new JPanel();
        contentPanel.setBorder(marginBorder);
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
        this.add(contentPanel, BorderLayout.CENTER);

        gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        gameOverLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(gameOverLabel);

        playerTeamPanel = new PurchasablesShelf(GameManager.team.getActives(), "Your Team");
        playerTeamPanel.setBorder(marginBorder);
        contentPanel.add(playerTeamPanel);

        bankBalanceLabel = new JLabel("You finished with $" + GameManager.getBankBalance());
        bankBalanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(bankBalanceLabel);

        footerPanel = new JPanel();
        footerPanel.setBorder(marginBorder);
        footerPanel.setLayout(new GridLayout(1, 0, 0, 0));
        this.add(footerPanel, BorderLayout.SOUTH);

        playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(e -> {
//            GameManager.resetGame();
            WindowManager.showInitScreen();
        });
        footerPanel.add(playAgainButton);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        footerPanel.add(exitButton);
    }
}
