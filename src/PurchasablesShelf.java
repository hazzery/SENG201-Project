import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.function.Function;

public class PurchasablesShelf extends JPanel {
    private Function<Purchasable, String> getButtonText = null;
    private ActionListener actionListener = null;
    private boolean isOwned;
    JPanel wrapperPanel;


    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);


    public PurchasablesShelf(Purchasable[] purchasables, String shelfName, Function<Purchasable, String> getButtonText, ActionListener actionListener) {
        setBorder(marginBorder);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        JLabel nameLabel = new JLabel();
        nameLabel.setText(shelfName);
        add(nameLabel);

        wrapperPanel = new JPanel();
        wrapperPanel.setBorder(marginBorder);
        wrapperPanel.setLayout(new GridLayout(1, 0, 0,0));
        this.add(wrapperPanel);

        this.getButtonText = getButtonText;
        this.actionListener = actionListener;

        for (Purchasable purchasable : purchasables) {
            addPanel(purchasable);
        }

        setVisible(true);
    }

    public void removePanel(Purchasable purchasable) {
        for (Component component : wrapperPanel.getComponents()) {
            PurchasablePanel panel = (PurchasablePanel) component;
            if (panel.getPurchasable().equals(purchasable)) {
                wrapperPanel.remove(panel);
                wrapperPanel.revalidate();
                wrapperPanel.repaint();
                break;
            }
        }
    }

    public void removePanel(PurchasablePanel panel) {
        wrapperPanel.remove(panel);
        wrapperPanel.revalidate();
        wrapperPanel.repaint();
    }

    public void addPanel(Purchasable purchasable) {
        PurchasablePanel panel = new PurchasablePanel(purchasable);
        String buttonText = getButtonText.apply(purchasable);
        panel.withCustomButton(buttonText, actionListener);
        wrapperPanel.add(panel);
        wrapperPanel.revalidate();
        wrapperPanel.repaint();
    }

    public void addPanel(PurchasablePanel panel) {
        wrapperPanel.add(panel);
        wrapperPanel.revalidate();
        wrapperPanel.repaint();
    }

    public void reload(ArrayList<? extends Purchasable> purchasables) {
        reload(purchasables.toArray(new Purchasable[0]));
    }

    public void reload(Purchasable[] purchasables) {
        wrapperPanel.removeAll();
        for (Purchasable purchasable : purchasables) {
            if (purchasable != null) {
                addPanel(purchasable);
            }
        }
    }
}
