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
        GameManager.team.addActivesSubscriber(activesShelf);
        mainPanel.add(activesShelf);

        reservesShelf = new PurchasablesShelf(GameManager.team.getReserves(), "Reserved", p->"Activate", this::activateAthlete);
        GameManager.team.addReservesSubscriber(reservesShelf);
        mainPanel.add(reservesShelf);

        itemsShelf = new PurchasablesShelf(GameManager.getItems(), "Inventory", p->"Use", this::selectAthleteForItem);
        GameManager.registerItemSubscriber(itemsShelf);
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
        PurchasablePanel panel = (PurchasablePanel)(((JButton)event.getSource()).getParent());
        Athlete athlete = (Athlete) panel.getPurchasable();

        System.out.println("Reserving " + athlete.getName());

        try {
            GameManager.team.setReserve(athlete);
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(null,
                    "Cannot have more than " + Team.MAX_RESERVES + " reserves");
            return;
        }

        PurchasablesShelf shelf = (PurchasablesShelf) panel.getShelf();
        shelf.removePanel(panel);
        reservesShelf.addPanel(athlete);
    }

    private void activateAthlete(ActionEvent event) {
        PurchasablePanel panel = (PurchasablePanel)(((JButton)event.getSource()).getParent());
        Athlete athlete = (Athlete) panel.getPurchasable();

        System.out.println("Activating " + athlete.getName());

        try {
            GameManager.team.setActive(athlete);
            System.out.println("Successfully reserved " + athlete.getName());
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(null, error.getMessage());
            return;
        }

        PurchasablesShelf shelf = (PurchasablesShelf) panel.getShelf();
        shelf.removePanel(panel);
        activesShelf.addPanel(athlete);
    }

    private void selectAthleteForItem(ActionEvent event) {
        Athlete athlete = (Athlete) JOptionPane.showInputDialog(null,
                "Select an athlete to use this item on", "Use Item", JOptionPane.PLAIN_MESSAGE,
                null, GameManager.team.getAll(), "Choose athlete");

        if (athlete != null) {
            Item item = (Item) ((PurchasablePanel) ((JButton) event.getSource()).getParent()).getPurchasable();
            GameManager.useItem(item, athlete);

            itemsShelf.removePanel(item);
        }
    }
}
