import java.awt.event.ActionListener;
import java.util.function.Function;
import javax.swing.*;
import java.awt.*;

public class Shelf<DisplayableType extends Displayable> extends JPanel {
    private final String shelfName;
    private Function<DisplayableType, String> getButtonText = null;
    private ActionListener actionListener = null;
    private boolean isOwned;
    private final JPanel shelfPanel;


    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);


    public Shelf(DisplayableType[] displayables, String shelfName, Function<DisplayableType, String> getButtonText, ActionListener actionListener) {
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

        JScrollPane shelfScrollPane = new JScrollPane(shelfPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(shelfScrollPane);

        for (DisplayableType displayable : displayables) {
            addPanel(displayable);
        }
        setVisible(true);
    }

    public void removePanel(DisplayableType displayable) {
        for (Component component : shelfPanel.getComponents()) {
            DisplayablePanel panel = (DisplayablePanel) component;
            if (panel.getDisplayable().equals(displayable)) {
                shelfPanel.remove(panel);
                shelfPanel.revalidate();
                shelfPanel.repaint();
                break;
            }
        }
    }

    public void removePanel(DisplayablePanel panel) {
        shelfPanel.remove(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    public void addPanel(DisplayableType displayable) {
        DisplayablePanel panel = new DisplayablePanel(displayable);
        String buttonText = getButtonText.apply(displayable);
        panel.withCustomButton(buttonText, actionListener);
        shelfPanel.add(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    public void addPanel(DisplayablePanel panel) {
        shelfPanel.add(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    public void reload(DisplayableType[] displayables) {
        shelfPanel.removeAll();
        for (DisplayableType displayable : displayables) {
            if (displayable != null) {
                addPanel(displayable);
            }
        }
    }
}
