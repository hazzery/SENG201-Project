import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;

public class MarketScreen extends GameScreenPanel {
    MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);

    // Indentation of components below shows hierarchy of elements on the screen
  //protected JPanel contentPanel
        private PurchasablesShelf athletesShelf;
        private PurchasablesShelf itemsShelf;
        private PurchasablesShelf teamShelf;

    private final int WEEKLY_POOL_SIZE = 5;
    private Athlete[] weeklyAthletePool;
    private Item[] weeklyItemPool;

    /**
     * Initialize the contents of the frame.
     */
    @Override
    protected void initialize() {
        super.initialize();
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));

        updateWeeklyPool();

        athletesShelf = new PurchasablesShelf(weeklyAthletePool, "Athletes ", Utilities::purchaseButtonText, this::purchase);
        contentPanel.add(athletesShelf);

        itemsShelf = new PurchasablesShelf(weeklyItemPool, "Items    ", Utilities::purchaseButtonText, this::purchase);
        contentPanel.add(itemsShelf);

        teamShelf = new PurchasablesShelf(GameManager.team.athletesAndItems(), "Inventory", Utilities::sellButtonText, this::sell);
        contentPanel.add(teamShelf);
    }

    /**
     * Creates a new MarketScreen panel with the given game screen as its parent
     * @param gameScreen The {@link GameScreen} that is the parent of this panel
     */
    public MarketScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.MARKET, gameScreen);
    }

    /**
     * Event handler to purchase a {@link Purchasable}
     * @param event the event that triggered the purchase
     */
    private void purchase(ActionEvent event)  {
        System.out.println("purchase");

        PurchasablePanel panel = (PurchasablePanel) ((JButton) event.getSource()).getParent();
        Purchasable purchasable = panel.getPurchasable();

        if (purchasable instanceof Athlete athlete) {
            purchaseAthlete(athlete);
            return;
        }

        try {
            GameManager.purchase(purchasable, true);
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(this, error.getMessage());
            return;
        }

        panel.getShelf().removePanel(panel);

        // Delete the purchasable from the weekly pool
        for (int i = 0; i < weeklyItemPool.length; i++) {
            if (weeklyItemPool[i] == purchasable) {
                weeklyItemPool[i] = null;
                break;
            }
        }
    }

    /**
     * Event handler to sell a {@link Purchasable}
     * @param event the event that triggered the sell
     */
    private void sell(ActionEvent event) {
        System.out.println("sell");
        PurchasablePanel panel = (PurchasablePanel) ((JButton) event.getSource()).getParent();
        Purchasable purchasable = panel.getPurchasable();

        try {
            GameManager.sell(purchasable);
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(this, error.getMessage());
            return;
        } catch (MustSwapReserveException error) {
            Athlete swap = (Athlete) JOptionPane.showInputDialog(this,
                HTMLString.multiLine("Must maintain at least " + Team.TEAM_SIZE + " athletes." +
                                "Select a reserve to take" + purchasable.getName() + "'s position"),
                    "Swap Athlete", JOptionPane.PLAIN_MESSAGE,null, GameManager.team.getReserves(), "Choose athlete");

            if (swap == null) {
                JOptionPane.showMessageDialog(this, "Must swap reserve to sell athlete");
                return;
            }

            GameManager.team.setActive(swap);
            GameManager.sell(purchasable);
        }

        panel.getShelf().removePanel(panel);
    }

    /**
     * Purchases an {@link Athlete} from the market
     * @param athlete the {@link Athlete} to purchase
     */
    private void purchaseAthlete(Athlete athlete) {
        if (GameManager.getBankBalance() < athlete.getContractPrice()) {
            JOptionPane.showMessageDialog(this,
                    "Insufficient funds to purchase " + athlete.getName());
            return;
        }

        if (GameManager.team.size() >= PlayerTeam.MAXIMUM_SIZE) {
            JOptionPane.showMessageDialog(this,
                    "You have too many athletes, you must sell one to purchase another");
            return;
        }

        boolean willBeActive = false;
        int activateNewAthlete = JOptionPane.showConfirmDialog(this,
                "Would you like to place" + athlete.getName() + " as an active athlete?",
                "Swap Athlete", JOptionPane.YES_NO_OPTION);

        if (activateNewAthlete == JOptionPane.YES_OPTION) {
            willBeActive = true;
            Athlete swap = (Athlete) JOptionPane.showInputDialog(this,
                    "Which active athlete would you like to reserve to activate" + athlete.getName() + '?',
                    "Reserve athlete", JOptionPane.PLAIN_MESSAGE,
                    null, GameManager.team.getActives(), null);

            if (swap == null) {
                JOptionPane.showMessageDialog(this,
                        "Must reserve an athlete to place new athlete in active team");
                return;
            }

            try {
                GameManager.team.setReserve(swap);
            } catch (IllegalStateException error) {
                JOptionPane.showMessageDialog(this, error.getMessage());
                return;
            }
        }

        try {
            GameManager.purchase(athlete, willBeActive);
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(this, error.getMessage());
        }

        for (int i = 0; i < weeklyAthletePool.length; i++) {
            if (weeklyAthletePool[i] == athlete) {
                weeklyAthletePool[i] = null;
                break;
            }
        }
    }

    /**
     * Updates the weekly pool of {@link Athlete}s and {@link Item}s available for purchase
     */
    public void updateWeeklyPool() {
        weeklyAthletePool = GameManager.generateAthletes(WEEKLY_POOL_SIZE);
        weeklyItemPool = GameManager.generateItems(WEEKLY_POOL_SIZE);
    }

    /**
     * Reloads the contents of the shelves to reflect the current state of the game
     */
    @Override
    public void reload() {
        athletesShelf.reload(weeklyAthletePool);
        itemsShelf.reload(weeklyItemPool);
        teamShelf.reload(GameManager.team.athletesAndItems());
    }
}
