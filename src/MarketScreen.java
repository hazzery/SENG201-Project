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
        super.initialize();
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

        athleteShelf = new JPanel();
        athleteShelf.setBorder(marginBorder);
        athleteShelf.setLayout(new BoxLayout(athleteShelf, BoxLayout.X_AXIS));
        contentPanel.add(athleteShelf);

        athletePanels = new AthletePanel[GameManager.athletes.size()];
        for (int i = 0; i < GameManager.athletes.size(); i++) {
            athletePanels[i] = new MarketAthletePanel(GameManager.athletes.get(i),this);
            athleteShelf.add(athletePanels[i]);
        }

        itemShelf = new JPanel();
        itemShelf.setBorder(marginBorder);
        itemShelf.setLayout(new BoxLayout(itemShelf, BoxLayout.X_AXIS));
        contentPanel.add(itemShelf);

        itemPanels = new MarketItemPanel[GameManager.items.size()];
        for (int i = 0; i < GameManager.items.size(); i++) {
            itemPanels[i] = new MarketItemPanel(GameManager.items.get(i), this);
            itemShelf.add(itemPanels[i]);
        }

    }


    public MarketScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.MARKET, gameScreen);
    }

    public void purchaseAthlete(Athlete athlete, MarketAthletePanel marketAthletePanel) {
        try {
            GameManager.purchaseAthlete(athlete);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return; // Don't update the screen if the purchase failed
        }
        // Update the screen
        athleteShelf.remove(marketAthletePanel);
        athleteShelf.revalidate();
        athleteShelf.repaint();
    }

    public void purchaseItem(Item item, MarketItemPanel marketItemPanel) {
        try {
            GameManager.purchaseItem(item);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return; // Don't update the screen if the purchase failed
        }
        // Update the screen
        itemShelf.remove(marketItemPanel);
        itemShelf.revalidate();
        itemShelf.repaint();
    }
}
