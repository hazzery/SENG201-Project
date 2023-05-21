import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;


/**
 * MarketScreen is the display panel that allows the user to purchase and sell {@link Athlete}s
 * and {@link Item}s. It is a display panel that sits within the {@link GameScreen}
 *
 * @author Harrison Parkes
 */
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

        athletesShelf = new PurchasablesShelf(weeklyAthletePool, "Athletes ", MarketScreen::purchaseButtonText, this::purchase);
        contentPanel.add(athletesShelf);

        itemsShelf = new PurchasablesShelf(weeklyItemPool, "Items    ", MarketScreen::purchaseButtonText, this::purchase);
        contentPanel.add(itemsShelf);

        teamShelf = new PurchasablesShelf(GameManager.team.athletesAndItems(), "Inventory", MarketScreen::sellButtonText, this::sell);
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

        boolean shouldReserve = false;
        if (purchasable instanceof Athlete athlete) {
            shouldReserve = purchaseAthlete(athlete);
        }

        try {
            GameManager.purchase(purchasable, shouldReserve);
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(this, error.getMessage());
            return;
        }

        panel.getShelf().removePanel(panel);

        if (purchasable instanceof Item item) {
//            teamShelf.addPanel(new PurchasablePanel(item, MarketScreen::sellButtonText, this::sell));
            // Delete the purchasable from the weekly pool
            for (int i = 0; i < weeklyItemPool.length; i++) {
                if (weeklyItemPool[i] == item) {
                    weeklyItemPool[i] = null;
                    break;
                }
            }
        } else if (purchasable instanceof Athlete athlete) {
            for (int i = 0; i < weeklyAthletePool.length; i++) {
                if (weeklyAthletePool[i] == athlete) {
                    weeklyAthletePool[i] = null;
                    break;
                }
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
     * @param athlete the athlete to purchase
     */
    private boolean purchaseAthlete(Athlete athlete) throws IllegalStateException {
        if (GameManager.getBankBalance() < athlete.getContractPrice()) {
            JOptionPane.showMessageDialog(this,
                    "Insufficient funds to purchase " + athlete.getName());
            throw new IllegalStateException("Insufficient funds to purchase " + athlete.getName());
        }

        if (GameManager.team.size() >= PlayerTeam.MAXIMUM_SIZE) {
            JOptionPane.showMessageDialog(this,
                    "You have too many athletes, you must sell one to purchase another");
            throw new IllegalStateException("You have too many athletes, you must sell one to purchase another");
        }

        int activateNewAthlete = JOptionPane.showConfirmDialog(this,
                "Would you like to place" + athlete.getName() + " as an active athlete?",
                "Swap Athlete", JOptionPane.YES_NO_OPTION);

        if (activateNewAthlete == JOptionPane.YES_OPTION) {
            Athlete swap = (Athlete) JOptionPane.showInputDialog(this,
                    "Which active athlete would you like to reserve to activate" + athlete.getName() + '?',
                    "Reserve athlete", JOptionPane.PLAIN_MESSAGE,
                    null, GameManager.team.getActives(), null);

            if (swap == null)
                throw new IllegalStateException("Must reserve an athlete to place new athlete in active team");

            GameManager.team.setReserve(swap);

            return true;
        }

        return false;
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

    /**
     * Function that creates the button text for a purchase athlete button
     * Uses the provided athlete to get its price
     * @param purchasable Any purchasable object
     * @return The string to put on the button
     */
    public static String purchaseButtonText(Purchasable purchasable) {
        return HTMLString.multiLine("Purchase", "$" + purchasable.getContractPrice());
    }

    /**
     * Function that creates the button text for a sell athlete button
     * Uses the provided athlete to get its resale price
     * @param purchasable Any purchasable object
     * @return The string to put on the button
     */
    public static String sellButtonText(Purchasable purchasable) {
        return HTMLString.multiLine("Sell", "$" + purchasable.getSellBackPrice());
    }
}
