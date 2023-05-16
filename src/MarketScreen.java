import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MarketScreen extends GameScreenPanel {
    // Indentation of components below shows hierarchy of elements on the screen

  //protected JPanel contentPanel
        private PurchasablesShelf athletesShelf;
        private PurchasablesShelf itemsShelf;
        private PurchasablesShelf teamShelf;

    @Override
    protected void initialize() {
        super.initialize();
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

        athletesShelf = new PurchasablesShelf(GameManager.generateAthletes(5), "Purchase", this::purchaseAthlete);
        contentPanel.add(athletesShelf);

        itemsShelf = new PurchasablesShelf(GameManager.generateItems(5), "Purchase", this::purchaseItem);
        contentPanel.add(itemsShelf);

        teamShelf = new PurchasablesShelf(GameManager.team.getAll(), "Sell", this::sellAthlete);
        GameManager.team.addActivesSubscriber(teamShelf);
        GameManager.team.addReservesSubscriber(teamShelf);
        contentPanel.add(teamShelf);
    }


    public MarketScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.MARKET, gameScreen);
    }

    private final void purchaseItem(ActionEvent e) {
        System.out.println("purchaseItem");
        PurchasablePanel panel = (PurchasablePanel) ((JButton) e.getSource()).getParent();
        Item item = (Item) panel.getPurchasable();

        boolean success = false;
        try {
            GameManager.purchaseItem(item);
            success = true;
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(this, error.getMessage());
        }

        if (success) {
            PurchasablesShelf shelf = (PurchasablesShelf) panel.getParent();
            shelf.remove(panel);
            shelf.revalidate();
            shelf.repaint();
        }
    }

    private final void purchaseAthlete(ActionEvent e)  {
        System.out.println("purchaseAthlete");
        PurchasablePanel panel = (PurchasablePanel) ((JButton) e.getSource()).getParent();
        Athlete athlete = (Athlete) panel.getPurchasable();

        try {
            GameManager.purchaseAthlete(athlete);
            panel.getParent().remove(panel);
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(this, error.getMessage());
        }
    }


    private final void sellAthlete(ActionEvent e) {
        System.out.println("sellAthlete");
        PurchasablePanel panel = (PurchasablePanel) ((JButton) e.getSource()).getParent();
        Athlete athlete = (Athlete) panel.getPurchasable();

        GameManager.sellAthlete(athlete);
    }
}
