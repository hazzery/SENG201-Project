import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class PurchasablesShelf extends JPanel {
    private final String shelfName;
    private Function<Purchasable, String> getButtonText = null;
    private ActionListener actionListener = null;
    private boolean isOwned;
    private final JPanel shelfPanel;


    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);


    /**
     * Initialise a new "shelf" to display a group of {@link Purchasable}s
     * @param purchasables The purchasables to display
     * @param shelfName A name for the "shelf" to display next to the {@link PurchasablePanel}s
     * @param getButtonText A function to get the text for the button on each panel
     * @param actionListener A function that takes an {@link ActionEvent} as its only parameter.
     *                       Called when a button on a PurchasablePanel is pressed
     */
    public PurchasablesShelf(Purchasable[] purchasables, String shelfName, Function<Purchasable, String> getButtonText, ActionListener actionListener) {
        this.shelfName = shelfName;
        this.getButtonText = getButtonText;
        this.actionListener = actionListener;

        setBorder(marginBorder);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel nameLabel = new JLabel();
        nameLabel.setText(shelfName);
        add(nameLabel);

        shelfPanel = new JPanel();
        shelfPanel.setBorder(marginBorder);
        shelfPanel.setLayout(new GridLayout(1, 0, 5,0));

        JScrollPane shelfScrollPane = new JScrollPane(shelfPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(shelfScrollPane);

        for (Purchasable purchasable : purchasables) {
            addPanel(purchasable);
        }
        setVisible(true);
    }

    /**
     * Removes the {@link PurchasablePanel} that displays the given {@link Purchasable}
     * @param purchasable The purchasable whose panel should be removed
     */
    public void removePanel(Purchasable purchasable) {
        for (Component component : shelfPanel.getComponents()) {
            PurchasablePanel panel = (PurchasablePanel) component;
            if (panel.getPurchasable().equals(purchasable)) {
                shelfPanel.remove(panel);
                shelfPanel.revalidate();
                shelfPanel.repaint();
                break;
            }
        }
    }

    /**
     * Removes the given {@link PurchasablePanel} from the shelf
     * @param panel The {@link PurchasablePanel} to remove
     */
    public void removePanel(PurchasablePanel panel) {
        shelfPanel.remove(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    /**
     * Adds a {@link PurchasablePanel} to the shelf to display the given {@link Purchasable}
     * @param purchasable The {@link Purchasable} to display
     */
    public void addPanel(Purchasable purchasable) {
        PurchasablePanel panel = new PurchasablePanel(purchasable);
        String buttonText = getButtonText.apply(purchasable);
        panel.withCustomButton(buttonText, actionListener);
        shelfPanel.add(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    /**
     * Places the provided {@link PurchasablePanel} on the shelf
     * @param panel The PurchasablePanel to add
     */
    public void addPanel(PurchasablePanel panel) {
        shelfPanel.add(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    /**
     * Reloads the shelf to display the given {@link Purchasable}s
     * @param purchasables The purchasables to display
     */
    public void reload(ArrayList<? extends Purchasable> purchasables) {
        reload(purchasables.toArray(new Purchasable[0]));
    }

    /**
     * Reloads the shelf to display the given {@link Purchasable}s
     * @param purchasables The purchasables to display
     */
    public void reload(Purchasable[] purchasables) {
        shelfPanel.removeAll();
        for (Purchasable purchasable : purchasables) {
            if (purchasable != null) {
                addPanel(purchasable);
            }
        }
    }
}
