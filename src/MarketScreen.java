import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

public class MarketScreen extends GameScreenPanel {
    MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);

    // Indentation of components below shows hierarchy of elements on the screen
  //protected JPanel contentPanel
        private JPanel athletesWrapperPanel;
            private JLabel activeAthletesLabel;
            private PurchasablesShelf athletesShelf;
        private JPanel itemsWrapperPanel;
            private JLabel itemsLabel;
            private PurchasablesShelf itemsShelf;
        private JPanel teamWrapperPanel;
            private JLabel teamLabel;
            private PurchasablesShelf teamShelf;


    private final int WEEKLY_POOL_SIZE = 5;
    private Athlete[] weeklyAthletePool;
    private Item[] weeklyItemPool;

    @Override
    protected void initialize() {
        super.initialize();
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

        updateWeeklyPool();

        athletesWrapperPanel = new JPanel();
        athletesWrapperPanel.setBorder(marginBorder);
        athletesWrapperPanel.setLayout(new BoxLayout(athletesWrapperPanel, BoxLayout.X_AXIS));
        contentPanel.add(athletesWrapperPanel);

        activeAthletesLabel = new JLabel("Athletes ");
        athletesWrapperPanel.add(activeAthletesLabel);

        athletesShelf = new PurchasablesShelf(weeklyAthletePool, Utilities::purchaseButtonText, this::purchase);
        athletesWrapperPanel.add(athletesShelf);

        itemsWrapperPanel = new JPanel();
        itemsWrapperPanel.setBorder(marginBorder);
        itemsWrapperPanel.setLayout(new BoxLayout(itemsWrapperPanel, BoxLayout.X_AXIS));
        contentPanel.add(itemsWrapperPanel);

        itemsLabel = new JLabel("Items    ");
        itemsWrapperPanel.add(itemsLabel);

        itemsShelf = new PurchasablesShelf(weeklyItemPool, Utilities::purchaseButtonText, this::purchase);
        itemsWrapperPanel.add(itemsShelf);

        teamWrapperPanel = new JPanel();
        teamWrapperPanel.setBorder(marginBorder);
        teamWrapperPanel.setLayout(new BoxLayout(teamWrapperPanel, BoxLayout.X_AXIS));
        contentPanel.add(teamWrapperPanel);

        teamLabel = new JLabel("Inventory");
        teamWrapperPanel.add(teamLabel);

        teamShelf = new PurchasablesShelf(GameManager.team.getAll(), Utilities::sellButtonText, this::sell);
        GameManager.team.addActivesSubscriber(teamShelf);
        GameManager.team.addReservesSubscriber(teamShelf);
        teamWrapperPanel.add(teamShelf);

    }

    public MarketScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.MARKET, gameScreen);
    }

    /**
     * Event handler to make a market purchase
     * @param event the event that triggered the purchase
     */
    private void purchase(ActionEvent event)  {
        System.out.println("purchase");
        PurchasablePanel panel = (PurchasablePanel) ((JButton) event.getSource()).getParent();
        Purchasable purchasable = panel.getPurchasable();

        try {
            GameManager.purchase(purchasable);
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(this, error.getMessage());
            return;
        }

        PurchasablesShelf shelf = (PurchasablesShelf) panel.getParent();
        shelf.remove(panel);
        shelf.revalidate();
        shelf.repaint();

        // Delete the purchasable from the weekly pool
        if (purchasable instanceof Athlete athlete) {
            for (int i = 0; i < weeklyAthletePool.length; i++) {
                if (weeklyAthletePool[i] == athlete) {
                    weeklyAthletePool[i] = null;
                    break;
                }
            }
        }
        else if (purchasable instanceof Item item) {
            for (int i = 0; i < weeklyItemPool.length; i++) {
                if (weeklyItemPool[i] == item) {
                    weeklyItemPool[i] = null;
                    break;
                }
            }
        }
    }

    private void sell(ActionEvent event) {
        System.out.println("sell");
        PurchasablePanel panel = (PurchasablePanel) ((JButton) event.getSource()).getParent();
        Purchasable purchasable = panel.getPurchasable();

        GameManager.sell(purchasable);

        PurchasablesShelf shelf = (PurchasablesShelf) panel.getParent();
        shelf.remove(panel);
        shelf.revalidate();
        shelf.repaint();
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
