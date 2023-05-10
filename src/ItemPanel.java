import javax.swing.*;
import java.awt.*;

public class ItemPanel extends JPanel {
    MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);

    private JLabel itemNameLabel;
    private JLabel itemImprovementLabel;
    private JButton itemUseButton;

    private void useItem() {
//        Object[] possibilities = {"ham", "spam", "yam"};
        String s = (String)JOptionPane.showInputDialog(null, "Choose an athlete to apply this item to:\n",
                "Select athlete", JOptionPane.QUESTION_MESSAGE, null, GameManager.athletes.toArray(),"Choose athlete");

        //If a string was returned, say so.
        if ((s != null) && (s.length() > 0)) {
            JOptionPane.showInputDialog(null, "You entered: " + s);
            return;
        }
        JOptionPane.showInputDialog(null, "You failed to enter a string");
    }

    private void initialize(Item item) {
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

        itemUseButton = new JButton();
        itemUseButton.setText("Use");
        itemUseButton.addActionListener(e -> useItem());
        this.add(itemUseButton);
    }

    public ItemPanel(Item item) {
        initialize(item);
        setVisible(true);
    }
}
