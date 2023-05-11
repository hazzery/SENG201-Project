import javax.swing.*;
import java.awt.*;

public class MarketScreen extends GameScreenPanel {
    // Indentation of components below shows hierarchy of elements on the screen

  //protected JPanel contentPanel
        private JPanel athleteShelf;
            private AthletePanel[] athletePanels;
        private JPanel itemShelf;
            private ItemPanel[] itemPanels;

    @Override
    protected void initialize() {
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

        athleteShelf = new JPanel();
        athleteShelf.setBorder(marginBorder);
        athleteShelf.setLayout(new BoxLayout(athleteShelf, BoxLayout.X_AXIS));
        contentPanel.add(athleteShelf);

        athletePanels = new AthletePanel[GameManager.athletes.size()];
        for (int i = 0; i < GameManager.athletes.size(); i++) {
            athletePanels[i] = new AthletePanel(GameManager.athletes.get(i), false, new ClubScreen(parent));
            athleteShelf.add(athletePanels[i]);
        }

        itemShelf = new JPanel();
        itemShelf.setBorder(marginBorder);
        itemShelf.setLayout(new BoxLayout(itemShelf, BoxLayout.X_AXIS));
        contentPanel.add(itemShelf);

        itemPanels = new ItemPanel[GameManager.items.size()];
        for (int i = 0; i < GameManager.items.size(); i++) {
            itemPanels[i] = new ItemPanel(GameManager.items.get(i), new ClubScreen(parent));
            itemShelf.add(itemPanels[i]);
        }

    }


    public MarketScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.MARKET, gameScreen);
    }
}
