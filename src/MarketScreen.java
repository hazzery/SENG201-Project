import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MarketScreen extends GameScreenPanel {
    // Indentation of components below shows hierarchy of elements on the screen

  //protected JPanel contentPanel
        private PurchasablesShelf athletesShelf;
        private PurchasablesShelf itemsShelf;
        private PurchasablesShelf teamShelf;
    private ActionListener purchaseAthlete;

    @Override
    protected void initialize() {
        super.initialize();
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

        athletesShelf = new PurchasablesShelf(GameManager.generateAthletes(5), "Purchase", purchaseAthlete);
        contentPanel.add(athletesShelf);

        itemsShelf = new PurchasablesShelf(GameManager.generateItems(5), "Purchase", purchaseItem);
        contentPanel.add(itemsShelf);

        teamShelf = new PurchasablesShelf(GameManager.team.getAll(), "Sell", sellAthlete);
        contentPanel.add(teamShelf);
    }


    public MarketScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.MARKET, gameScreen);
    }

    public void purchaseAthlete(Athlete athlete, MarketAthletePanel marketAthletePanel) {
        try {
            GameManager.purchaseAthlete(athlete);
        } catch (IllegalStateException e) {
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
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return; // Don't update the screen if the purchase failed
        }
        // Update the screen
        itemShelf.remove(marketItemPanel);
        itemShelf.revalidate();
        itemShelf.repaint();

        parent.updateTeamInfo();
    }

    public void sellAthlete(Athlete athlete, MarketAthletePanel marketAthletePanel) {
        GameManager.sellAthlete(athlete);

        // Update the screen
        teamShelf.remove(marketAthletePanel);
        teamShelf.revalidate();
        teamShelf.repaint();

        parent.updateTeamInfo();
    }
}
