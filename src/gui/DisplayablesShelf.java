package gui;
import data.Displayable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;


/**
 * A DisplayablesShelf is a display panel used to show a collection of {@link DisplayPanel}s
 *
 * @author Harrison Parkes
 */
public class DisplayablesShelf extends JPanel {
    private final Function<Displayable, String> getButtonText;
    private final ActionListener actionListener;
    private final JPanel shelfPanel;


    final MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);

    /**
     * Initialise a new "shelf" to display a group of {@link Displayable}s
     * @param displayables All displayables to show on the shelf
     * @param shelfName A name for the "shelf" to display next to the {@link DisplayPanel}s
     */
    public DisplayablesShelf(Displayable[] displayables, String shelfName) {
        this(displayables, shelfName, null, null);
    }

    /**
     * Initialise a new "shelf" to display a group of {@link Displayable}s
     * @param displayables The displayables to display
     * @param shelfName A name for the "shelf" to display next to the {@link DisplayPanel}s
     * @param getButtonText A function to get the text for the button on each panel
     * @param actionListener A function that takes an {@link ActionEvent} as its only parameter.
     *                       Called when a button on a DisplayPanel is pressed
     */
    public DisplayablesShelf(Displayable[] displayables, String shelfName, Function<Displayable, String> getButtonText, ActionListener actionListener) {
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

        for (Displayable displayable : displayables) {
            addPanel(displayable);
        }
        setVisible(true);
    }

    /**
     * Removes the {@link DisplayPanel} that displays the given {@link Displayable}
     * @param displayable The displayable whose panel should be removed
     */
    public void removePanel(Displayable displayable) {
        for (Component component : shelfPanel.getComponents()) {
            DisplayPanel panel = (DisplayPanel) component;
            if (panel.getDisplayable().equals(displayable)) {
                shelfPanel.remove(panel);
                shelfPanel.revalidate();
                shelfPanel.repaint();
                break;
            }
        }
    }

    /**
     * Removes the given {@link DisplayPanel} from the shelf
     * @param panel The DisplayablePanel to remove
     */
    public void removePanel(DisplayPanel panel) {
        shelfPanel.remove(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    /**
     * Adds a {@link DisplayPanel} to the shelf to display the given {@link Displayable}
     * @param displayable The displayable to display
     */
    public void addPanel(Displayable displayable) {
        DisplayPanel panel = new DisplayPanel(displayable);

        if (actionListener != null) {
            String buttonText = getButtonText.apply(displayable);
            panel.withCustomButton(buttonText, actionListener);
        }

        shelfPanel.add(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    /**
     * Places the provided {@link DisplayPanel} on the shelf
     * @param panel The DisplayPanel to add
     */
    public void addPanel(DisplayPanel panel) {
        shelfPanel.add(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    /**
     * Reloads the shelf to display the given {@link Displayable}s
     * @param displayables The displayables to display
     */
    public void reload(ArrayList<? extends Displayable> displayables) {
        reload(displayables.toArray(new Displayable[0]));
    }

    /**
     * Reloads the shelf to display the given {@link Displayable}s
     * @param displayables The displayables to display
     */
    public void reload(Displayable[] displayables) {
        shelfPanel.removeAll();
        for (Displayable displayable : displayables) {
            if (displayable != null) {
                addPanel(displayable);
            }
        }
    }
}
