package gui;
import java.awt.event.ActionEvent;
import management.GameManager;
import data.PlayerTeam;
import javax.swing.*;
import data.Athlete;
import java.awt.*;
import data.Item;


/**
 * ClubScreen is the display panel that allows the user to change which of their athletes are
 * reserved and which are active. It is a display panel that sits within the {@link GameScreen}
 *
 * @author Harrison Parkes
 */
public class ClubScreen extends GameScreenPanel {
    final MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);

    private PurchasablesShelf activesShelf;
    private PurchasablesShelf reservesShelf;
    private PurchasablesShelf itemsShelf;

    /**
     * Initialise the contents of the frame.
     */
    @Override
    protected void initialize() {
        super.initialize();

        // Indentation of the components below shows hierarchy of elements on the screen
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(marginBorder);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
        this.add(mainPanel, BorderLayout.CENTER);

        activesShelf = new PurchasablesShelf(GameManager.team.getActives(), "Activated", p->"Reserve", this::reserveAthlete);
        mainPanel.add(activesShelf);

        reservesShelf = new PurchasablesShelf(GameManager.team.getReserves(), "Reserved", p->"Activate", this::activateAthlete);
        mainPanel.add(reservesShelf);

        itemsShelf = new PurchasablesShelf(GameManager.getItems(), "Inventory", p->"Use", this::selectAthleteForItem);
        mainPanel.add(itemsShelf);
    }

    /**
     * Reloads the contents of the shelves to reflect the current state of the game
     */
    @Override
    public void reload() {
        activesShelf.reload(GameManager.team.getActives());
        reservesShelf.reload(GameManager.team.getReserves());
        itemsShelf.reload(GameManager.getItems());
    }

    /**
     * Creates a new ClubScreen panel with the given game screen as its parent
     * @param gameScreen The {@link GameScreen} that is the parent of this panel
     */
    ClubScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.CLUB, gameScreen);
    }

    /**
     * Event handler called to reserve an athlete
     * @param event {@link ActionEvent} containing information about which button was clicked
     */
    private void reserveAthlete(ActionEvent event) {
        if (GameManager.team.getReserves().length == 0) {
            JOptionPane.showMessageDialog(null, "There are no reserves to swap with");
            return;
        }
        Athlete swap = (Athlete) JOptionPane.showInputDialog(this,
                "Select an athlete to swap with", "Swap Athlete", JOptionPane.PLAIN_MESSAGE,
                null, GameManager.team.getReserves(), "Choose athlete");

        if (swap == null)
            return;

        DisplayPanel panel = (DisplayPanel)(((JButton)event.getSource()).getParent());
        Athlete athlete = (Athlete) panel.getDisplayable();

        System.out.println("Reserving " + athlete.getName() + " and swapping with " + swap.getName());

        try {
            GameManager.team.swapAthletes(athlete, swap);
            System.out.println("Successfully reserved " + athlete.getName() + " and swapped with " + swap.getName());
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(null,
                    "Cannot have more than " + PlayerTeam.MAX_RESERVES + " reserves");
            return;
        }

        activesShelf.removePanel(panel);
        reservesShelf.addPanel(athlete);

        activesShelf.addPanel(swap);
        reservesShelf.removePanel(swap);
    }

    /**
     * Event handler called to activate an athlete
     * @param event {@link ActionEvent} containing information about which button was clicked
     */
    private void activateAthlete(ActionEvent event) {

        Athlete swap = (Athlete) JOptionPane.showInputDialog(this,
                "Select an athlete to swap with", "Swap Athlete", JOptionPane.PLAIN_MESSAGE,
                null, GameManager.team.getActives(), null);

        if (swap == null)
            return;

        DisplayPanel panel = (DisplayPanel)(((JButton)event.getSource()).getParent());
        Athlete athlete = (Athlete) panel.getDisplayable();

        System.out.println("Activating " + athlete.getName() + " and swapping with " + swap.getName());

        try {
            GameManager.team.swapAthletes(swap, athlete);
            System.out.println("Successfully activated " + athlete.getName() + " and swapped with " + swap.getName());
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(this, error.getMessage());
            return;
        }

        reservesShelf.removePanel(panel);
        activesShelf.addPanel(athlete);

        reservesShelf.addPanel(swap);
        activesShelf.removePanel(swap);
    }

    /**
     * Event handler called to use an item on an athlete
     * @param event {@link ActionEvent} containing information about which button was clicked
     */
    private void selectAthleteForItem(ActionEvent event) {
        Athlete athlete = (Athlete) JOptionPane.showInputDialog(this,
                "Select an athlete to use this item on", "Use Item", JOptionPane.PLAIN_MESSAGE,
                null, GameManager.team.getAthletes(), null);

        if (athlete != null) {
            Item item = (Item) ((DisplayPanel) ((JButton) event.getSource()).getParent()).getDisplayable();
            GameManager.useItem(item, athlete);

            itemsShelf.removePanel(item);
        }
    }
}
