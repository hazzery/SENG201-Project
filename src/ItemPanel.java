import javax.swing.*;
import java.awt.*;

public abstract class ItemPanel extends JPanel {
    MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);

    private JLabel itemNameLabel;
    private JLabel itemImprovementLabel;
    protected JButton itemActionButton;

    protected void initialize(Item item) {
        this.setBorder(marginBorder);
        this.setLayout(new GridLayout(0, 1, 0, 0));

        itemNameLabel = new JLabel();
        itemNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        itemNameLabel.setOpaque(true);
        itemNameLabel.setBackground(Color.LIGHT_GRAY);
        itemNameLabel.setText(item.getName());
        this.add(itemNameLabel);

        itemImprovementLabel = new JLabel();
        itemImprovementLabel.setText("+" + item.getImprovementAmount() + " " + item.getStatType().name().toLowerCase());
        this.add(itemImprovementLabel);
    }

    public ItemPanel(Item item) {
        initialize(item);
        setVisible(true);
    }
}
