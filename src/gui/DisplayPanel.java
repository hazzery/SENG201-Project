package gui;
import java.awt.event.ActionListener;
import java.util.HashMap;

import data.Displayable;
import data.Purchasable;
import java.util.Map;
import javax.swing.*;
import java.awt.*;


/**
 * PurchasablePanel is a display panel used to provide a visual representation of a {@link data.Displayable} object
 *
 * @author Harrison Parkes
 */
public class DisplayPanel extends JPanel {
    private final HashMap<String, JLabel> statLabels;
    private final Displayable displayable;

    private boolean hasButton = false;

    /**
     * Initialise a new panel to provide a visual representation of a {@link Displayable}
     * @param displayable The {@link Displayable} to represent
     */
    public DisplayPanel(Displayable displayable) {
        this.displayable = displayable;

        displayable.registerPanel(this);

        this.setLayout(new GridLayout(0, 1, 0, 0));
        MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);
        this.setBorder(marginBorder);

        JLabel nameLabel = new JLabel(displayable.getName());
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.LIGHT_GRAY);
        this.add(nameLabel);

        statLabels = new HashMap<>();

        for (Map.Entry<String, String> entry : displayable.getStats().entrySet()) {
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

        hasButton = true;

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
    public Displayable getDisplayable() {
        return displayable;
    }

    /**
     * Gets the {@link DisplayablesShelf} that this panel is a part of.
     * @return the DisplayablesShelf that this panel sits within
     */
    public DisplayablesShelf getShelf() {
        // PurchasablePanel -> JPanel -> JScrollPane -> JPanel -> PurchasablesShelf
        return (DisplayablesShelf) this.getParent().getParent().getParent().getParent();
    }

    /**
     * Creates a nicely formatted string representation of this object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "PurchasablePanel {" + displayable + "}  ";
    }
}
