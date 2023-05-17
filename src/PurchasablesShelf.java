import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PurchasablesShelf extends JPanel {
    private String buttonText = null;
    private ActionListener actionListener = null;
    private boolean isOwned;


    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);

    public PurchasablesShelf(Purchasable[] purchasables, boolean isOwned) {
        this.isOwned = isOwned;

        setBorder(marginBorder);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        for (Purchasable purchasable : purchasables)
            addPanel(purchasable);

        setVisible(true);
    }

    public PurchasablesShelf(Purchasable[] purchasables, String buttonText, ActionListener actionListener) {
        setBorder(marginBorder);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.buttonText = buttonText;
        this.actionListener = actionListener;

        for (Purchasable purchasable : purchasables)
            addPanel(purchasable, buttonText, actionListener);

        setVisible(true);
    }

    public void removePanel(Purchasable purchasable) {
        for (Component component : getComponents()) {
            PurchasablePanel panel = (PurchasablePanel) component;
            if (panel.getPurchasable().equals(purchasable)) {
                remove(panel);
                revalidate();
                repaint();
                break;
            }
        }
    }

    public void addPanel(Purchasable purchasable) {
        PurchasablePanel panel = new PurchasablePanel(purchasable);

        if (isOwned)
            panel.withSellButton();
        else
            panel.withPurchaseButton();

        add(panel);
        revalidate();
        repaint();
    }

    public void addPanel(Purchasable purchasable, String buttonText, ActionListener actionListener) {
        PurchasablePanel panel = new PurchasablePanel(purchasable);
        panel.withCustomButton(buttonText, actionListener);
        add(panel);
        revalidate();
        repaint();
    }

    public void reload(ArrayList<? extends Purchasable> purchasables) {
        reload(purchasables.toArray(new Purchasable[0]));
    }


    public void reload(Purchasable[] purchasables) {
        removeAll();
        for (Purchasable purchasable : purchasables) {
            if (purchasable != null) {
                if (buttonText != null)
                    addPanel(purchasable, buttonText, actionListener);
                else
                    addPanel(purchasable);
            }
        }
    }
}
