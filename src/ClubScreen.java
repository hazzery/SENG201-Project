import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClubScreen extends GameScreenPanel {
    MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);

    // Indentation of components below shows hierarchy of elements on the screen
    private JPanel mainPanel;
        private PurchasablesShelf activesShelf;
        private PurchasablesShelf reservesShelf;
        private PurchasablesShelf itemsShelf;

    @Override
    protected void initialize() {
        super.initialize();

        mainPanel = new JPanel();
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
     *
     */
    @Override
    public void reload() {
        activesShelf.reload(GameManager.team.getActives());
        reservesShelf.reload(GameManager.team.getReserves());
        itemsShelf.reload(GameManager.getItems());
    }

    ClubScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.CLUB, gameScreen);
    }

    private void reserveAthlete(ActionEvent event) {
        Athlete swap = (Athlete) JOptionPane.showInputDialog(this,
                "Select an athlete to swap with", "Swap Athlete", JOptionPane.PLAIN_MESSAGE,
                null, GameManager.team.getReserves(), "Choose athlete");

        if (swap == null)
            return;

        PurchasablePanel panel = (PurchasablePanel)(((JButton)event.getSource()).getParent());
        Athlete athlete = (Athlete) panel.getPurchasable();

        System.out.println("Reserving " + athlete.getName() + " and swapping with " + swap.getName());

        try {
            GameManager.team.swapAthletes(athlete, swap);
            System.out.println("Successfully reserved " + athlete.getName() + " and swapped with " + swap.getName());
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(null,
                    "Cannot have more than " + Team.MAX_RESERVES + " reserves");
            return;
        }

        activesShelf.removePanel(panel);
        reservesShelf.addPanel(athlete);

        activesShelf.addPanel(swap);
        reservesShelf.removePanel(swap);
    }

    private void activateAthlete(ActionEvent event) {

        Athlete swap = (Athlete) JOptionPane.showInputDialog(this,
                "Select an athlete to swap with", "Swap Athlete", JOptionPane.PLAIN_MESSAGE,
                null, GameManager.team.getActives(), null);

        if (swap == null)
            return;

        PurchasablePanel panel = (PurchasablePanel)(((JButton)event.getSource()).getParent());
        Athlete athlete = (Athlete) panel.getPurchasable();

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

    private void selectAthleteForItem(ActionEvent event) {
        Athlete athlete = (Athlete) JOptionPane.showInputDialog(this,
                "Select an athlete to use this item on", "Use Item", JOptionPane.PLAIN_MESSAGE,
                null, GameManager.team.getAll(), null);

        if (athlete != null) {
            Item item = (Item) ((PurchasablePanel) ((JButton) event.getSource()).getParent()).getPurchasable();
            GameManager.useItem(item, athlete);

            itemsShelf.removePanel(item);
        }
    }
}
