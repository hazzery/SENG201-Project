import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;

public class PurchasablesShelf extends JPanel {
    private Function<Purchasable, String> getButtonText = null;
    private ActionListener actionListener = null;
    private boolean isOwned;


    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);

    public PurchasablesShelf(Purchasable[] purchasables, Function<Purchasable, String> getButtonText, ActionListener actionListener) {
        setBorder(marginBorder);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        this.getButtonText = getButtonText;
        this.actionListener = actionListener;

        for (Purchasable purchasable : purchasables) {
            addPanel(purchasable);
        }

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
        String buttonText = getButtonText.apply((Purchasable) purchasable);
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
                String buttonText = getButtonText.apply((Purchasable) purchasable);
                addPanel(purchasable);
            }
        }
    }
}
