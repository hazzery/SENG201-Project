import javax.swing.*;
import java.awt.*;


public abstract class GameScreenPanel extends JPanel {
    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);


    private JPanel headerPanel;
        private JLabel clubHeaderLabel;
    protected JPanel contentPanel;
    private JPanel goElsewherePanel;
        private JButton goLeftButton;
        private JButton goRightButton;


    protected final GameScreen parent;
    private final GameScreen.Screen screenType;


    protected void initialize() {
        this.setLayout(new BorderLayout(0, 0));
        this.setBorder(marginBorder);

        headerPanel = new JPanel();
        headerPanel.setBorder(marginBorder);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        this.add(headerPanel, BorderLayout.NORTH);

        clubHeaderLabel = new JLabel(HTMLString.header(screenType.toString()));
        clubHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerPanel.add(clubHeaderLabel);

        contentPanel = new JPanel();
        contentPanel.setBorder(marginBorder);
        this.add(contentPanel, BorderLayout.CENTER);

        goElsewherePanel = new JPanel();
        goElsewherePanel.setBorder(marginBorder);
        goElsewherePanel.setLayout(new GridLayout(1, 0, 0, 0));
        this.add(goElsewherePanel, BorderLayout.SOUTH);

        goLeftButton = new JButton();
        goLeftButton.setText("Go left");
        goLeftButton.addActionListener(e -> parent.setScreen(screenType.previous()));
        goElsewherePanel.add(goLeftButton);

        goRightButton = new JButton();
        goRightButton.setText("Go Right");
        goRightButton.addActionListener(e -> parent.setScreen(screenType.next()));
        goElsewherePanel.add(goRightButton);

    }

    public GameScreenPanel(GameScreen.Screen screenType, GameScreen gameScreen) {
        this.screenType = screenType;
        this.parent = gameScreen;
        initialize();
        setVisible(true);
    }

    public abstract void reload();
}
