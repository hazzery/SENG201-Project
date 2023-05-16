import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class MarketScreen extends GameScreenPanel {
    MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);

    // Indentation of components below shows hierarchy of elements on the screen
  //protected JPanel contentPanel
        private PurchasablesShelf athletesShelf;
        private PurchasablesShelf itemsShelf;
        private PurchasablesShelf teamShelf;


    private final int WEEKLY_POOL_SIZE = 5;
//    private Athlete[] weeklyAthletePool =  new Athlete[WEEKLY_POOL_SIZE];
//    private Item[] weeklyItemPool =  new Item[WEEKLY_POOL_SIZE];
    private Athlete[] weeklyAthletePool;
    private Item[] weeklyItemPool;

    @Override
    protected void initialize() {
        super.initialize();
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

        updateWeeklyPool();

        athletesShelf = new PurchasablesShelf(weeklyAthletePool, "Purchase", this::purchaseAthlete);
        contentPanel.add(athletesShelf);

        itemsShelf = new PurchasablesShelf(weeklyItemPool, "Purchase", this::purchaseItem);
        contentPanel.add(itemsShelf);

        teamShelf = new PurchasablesShelf(GameManager.team.getAll(), "Sell", this::sellAthlete);
        GameManager.team.addActivesSubscriber(teamShelf);
        GameManager.team.addReservesSubscriber(teamShelf);
        contentPanel.add(teamShelf);
    }


    public MarketScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.MARKET, gameScreen);
    }

    private void purchaseItem(ActionEvent e) {
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

    private void purchaseAthlete(ActionEvent e)  {
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


    private void sellAthlete(ActionEvent e) {
        System.out.println("sellAthlete");
        PurchasablePanel panel = (PurchasablePanel) ((JButton) e.getSource()).getParent();
        Athlete athlete = (Athlete) panel.getPurchasable();

        GameManager.sellAthlete(athlete);
    }

    public void updateWeeklyPool() {
        weeklyAthletePool = GameManager.generateAthletes(WEEKLY_POOL_SIZE);
        weeklyItemPool = GameManager.generateItems(WEEKLY_POOL_SIZE);
    }

    @Override
    public void reload() {
        athletesShelf.reload(weeklyAthletePool);
        itemsShelf.reload(weeklyItemPool);
        teamShelf.reload(GameManager.team.getAll());
    }
}
