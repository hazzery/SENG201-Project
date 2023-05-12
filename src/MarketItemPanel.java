import javax.swing.*;

public class MarketItemPanel extends ItemPanel {

    MarketScreen parent;

    @Override
    protected void initialize(Item item) {
        super.initialize(item);
        itemActionButton = new JButton();
        itemActionButton.setText("Purchase");
        itemActionButton.addActionListener(e -> parent.purchaseItem(item, this));
        this.add(itemActionButton);
    }

    public MarketItemPanel(Item item, MarketScreen parent) {
        super(item);
        this.parent = parent;
    }
}
