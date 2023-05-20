import java.awt.event.ActionListener;
import java.util.function.Function;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class PurchasablesShelf extends JPanel {
    private String shelfName;
    private Function<Purchasable, String> getButtonText = null;
    private ActionListener actionListener = null;
    private boolean isOwned;
    private final JPanel shelfPanel;


    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);


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
        shelfPanel.setLayout(new GridLayout(1, 0, 2,0));

        JScrollPane shelfScrollPane = new JScrollPane(shelfPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(shelfScrollPane);

        for (Purchasable purchasable : purchasables) {
            addPanel(purchasable);
        }
        setVisible(true);
    }

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

    public void removePanel(PurchasablePanel panel) {
        shelfPanel.remove(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    public void addPanel(Purchasable purchasable) {
        PurchasablePanel panel = new PurchasablePanel(purchasable);
        String buttonText = getButtonText.apply(purchasable);
        panel.withCustomButton(buttonText, actionListener);
        shelfPanel.add(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    public void addPanel(PurchasablePanel panel) {
        shelfPanel.add(panel);
        shelfPanel.revalidate();
        shelfPanel.repaint();
    }

    public void reload(ArrayList<? extends Purchasable> purchasables) {
        reload(purchasables.toArray(new Purchasable[0]));
    }

    public void reload(Purchasable[] purchasables) {
        shelfPanel.removeAll();
        for (Purchasable purchasable : purchasables) {
            if (purchasable != null) {
                addPanel(purchasable);
            }
        }
    }
}
