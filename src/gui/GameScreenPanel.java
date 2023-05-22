package gui;
import javax.swing.*;
import utility.HTMLString;
import java.awt.*;


/**
 * GameScreenPanel is the abstract base class that powers all the screens that can sit inside the {@link GameScreen}
 * ({@link ClubScreen}, {@link MarketScreen}, and {@link StadiumScreen}) It provides these screens with a common header
 * and footer to provide consistency across the user interface.
 *
 * @author Harrison Parkes
 */
public abstract class GameScreenPanel extends JPanel {
    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);
    protected JPanel contentPanel;
    protected final GameScreen parent;
    private final GameScreen.Screen screenType;

    /**
     * Initialize the contents of the frame.
     */
    protected void initialize() {
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(marginBorder);

        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(marginBorder);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        this.add(headerPanel, BorderLayout.NORTH);

        JLabel clubHeaderLabel = new JLabel(HTMLString.header(screenType.toString()));
        clubHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(clubHeaderLabel);

        contentPanel = new JPanel();
        contentPanel.setBorder(marginBorder);
        this.add(contentPanel, BorderLayout.CENTER);

        JPanel goElsewherePanel = new JPanel();
        goElsewherePanel.setBorder(marginBorder);
        goElsewherePanel.setLayout(new GridLayout(1, 0, 0, 0));
        this.add(goElsewherePanel, BorderLayout.SOUTH);

        JButton goLeftButton = new JButton();
        goLeftButton.setText("Go left");
        goLeftButton.addActionListener(e -> parent.setScreen(screenType.previous()));
        goElsewherePanel.add(goLeftButton);

        JButton goRightButton = new JButton();
        goRightButton.setText("Go Right");
        goRightButton.addActionListener(e -> parent.setScreen(screenType.next()));
        goElsewherePanel.add(goRightButton);

    }

    /**
     * Creates and initializes the Game Screen Panel with the given screen type and parent GameScreen
     * @param screenType The type of screen that this panel is
     * @param gameScreen The {@link GameScreen} that is the parent of this panel
     */
    public GameScreenPanel(GameScreen.Screen screenType, GameScreen gameScreen) {
        this.screenType = screenType;
        this.parent = gameScreen;
        initialize();
        setVisible(true);
    }

    public abstract void reload();
}
