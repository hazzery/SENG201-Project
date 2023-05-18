import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClubScreen extends GameScreenPanel {
    MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);

    // Indentation of components below shows hierarchy of elements on the screen
    private JPanel mainPanel;
        private JPanel athletesWrapperPanel;
            private JLabel activeAthletesLabel;
            private PurchasablesShelf activesShelf;
        private JPanel reservesWrapperPanel;
            private JLabel reservedAthletesLabel;
            private PurchasablesShelf reservesShelf;
        private JPanel itemsWrapperPanel;
            private JLabel itemsLabel;
            private PurchasablesShelf itemsShelf;

    @Override
    protected void initialize() {
        super.initialize();

        mainPanel = new JPanel();
        mainPanel.setBorder(marginBorder);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
        this.add(mainPanel, BorderLayout.CENTER);

        athletesWrapperPanel = new JPanel();
        athletesWrapperPanel.setBorder(marginBorder);
        athletesWrapperPanel.setLayout(new BoxLayout(athletesWrapperPanel, BoxLayout.X_AXIS));
        mainPanel.add(athletesWrapperPanel);

        activeAthletesLabel = new JLabel("Activated");
        activeAthletesLabel.setOpaque(true);
        athletesWrapperPanel.add(activeAthletesLabel);

        activesShelf = new PurchasablesShelf(GameManager.team.getActives(), p->"Reserve", this::reserveAthlete);
        GameManager.team.addActivesSubscriber(activesShelf);
        athletesWrapperPanel.add(activesShelf);

        reservesWrapperPanel = new JPanel();
        reservesWrapperPanel.setBorder(marginBorder);
        reservesWrapperPanel.setLayout(new BoxLayout(reservesWrapperPanel, BoxLayout.X_AXIS));
        mainPanel.add(reservesWrapperPanel);

        reservedAthletesLabel = new JLabel("Reserved");
        reservesWrapperPanel.add(reservedAthletesLabel);

        reservesShelf = new PurchasablesShelf(GameManager.team.getReserves(), p->"Activate", this::activateAthlete);
        GameManager.team.addReservesSubscriber(reservesShelf);
        reservesWrapperPanel.add(reservesShelf);

        itemsWrapperPanel = new JPanel();
        itemsWrapperPanel.setBorder(marginBorder);
        itemsWrapperPanel.setLayout(new BoxLayout(itemsWrapperPanel, BoxLayout.X_AXIS));
        mainPanel.add(itemsWrapperPanel);

        itemsLabel = new JLabel("Inventory");
        itemsWrapperPanel.add(itemsLabel);

        itemsShelf = new PurchasablesShelf(GameManager.getItems(), p->"Use", this::selectAthleteForItem);
        itemsWrapperPanel.add(itemsShelf);
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

        boolean successfullyReserved = false;
        try {
            GameManager.team.setReserve(athlete);
            successfullyReserved = true;
        } catch (IllegalStateException error) {
            JOptionPane.showMessageDialog(null,
                    "Cannot have more than " + Team.MAX_RESERVES + " reserves");
        }

        if (!successfullyReserved)
            return;

        PurchasablesShelf shelf = (PurchasablesShelf) panel.getParent();
        shelf.remove(panel);
        shelf.revalidate();
        shelf.repaint();

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

        PurchasablesShelf shelf = (PurchasablesShelf) panel.getParent();
        shelf.remove(panel);
        shelf.revalidate();
        shelf.repaint();

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
