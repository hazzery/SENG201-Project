import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.awt.*;


/**
 * PurchasablePanel is a display panel used to provide a visual representation of a {@link Purchasable} object
 *
 * @author Harrison Parkes
 */
public class PurchasablePanel extends JPanel {
    private final MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);
    private final HashMap<String, JLabel> statLabels;
    private final Purchasable purchasable;

    private boolean hasButton = false;

    /**
     * Initialise a new panel to provide a visual representation of a {@link Purchasable}
     * @param purchasable The {@link Purchasable} to represent
     */
    public PurchasablePanel(Purchasable purchasable) {
        this.purchasable = purchasable;

        if (purchasable instanceof Athlete athlete)
            athlete.registerPanel(this);

        this.setLayout(new GridLayout(0, 1, 0, 0));
        this.setBorder(marginBorder);

        JLabel nameLabel = new JLabel(purchasable.getName());
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.LIGHT_GRAY);
        this.add(nameLabel);

        statLabels = new HashMap<>();

        for (Map.Entry<String, String> entry : purchasable.getStats().entrySet()) {
            statLabels.put(entry.getKey(), new JLabel(entry.getValue() + ' ' + entry.getKey()));
            statLabels.get(entry.getKey()).setHorizontalAlignment(SwingConstants.CENTER);
            this.add(statLabels.get(entry.getKey()));
        }

        setVisible(true);
    }

    /**
     * Adds a button to the bottom of the panel
     * @param buttonText the text to display on the button
     * @param actionListener the action to perform when the button is clicked
     * @throws IllegalStateException if this panel already has a button
     */
    public void withCustomButton(String buttonText, ActionListener actionListener) throws IllegalStateException {
        if (hasButton)
            throw new IllegalStateException("This panel already has a purchase button.");

        JButton button = new JButton(buttonText);
        button.addActionListener(actionListener);
        this.add(button);
    }

    /**
     * Updates the value of a stat label on the GUI
     * @param stat the particular stat to update
     * @param value the new value of the stat
     */
    public void update(String stat, String value) {
        String titleCase = stat.substring(0, 1).toUpperCase() + stat.substring(1);
        statLabels.get(titleCase).setText(value + ' ' + stat);
    }

    /**
     * Gets the {@link Purchasable} that this panel represents.
     * @return the {@link Purchasable} that this panel represents
     */
    public Purchasable getPurchasable() {
        return purchasable;
    }

    /**
     * Gets the {@link PurchasablesShelf} that this panel is a part of.
     * @return the {@link PurchasablesShelf} that this panel sits within
     */
    public PurchasablesShelf getShelf() {
        // PurchasablePanel -> JPanel -> JScrollPane -> JPanel -> PurchasablesShelf
        return (PurchasablesShelf) this.getParent().getParent().getParent().getParent();
    }

    /**
     * Creates a nicely formatted string representation of this object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "PurchasablePanel {" + purchasable + "}  ";
    }
}
