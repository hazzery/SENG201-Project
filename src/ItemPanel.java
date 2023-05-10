import javax.swing.*;
import java.awt.*;

public class ItemPanel extends JPanel {
    MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);

    private JLabel itemNameLabel;
    private JLabel itemImprovementLabel;
    private JButton itemUseButton;

    private ClubScreen parent;

    private void useItem(Item item) {
        Athlete athlete = (Athlete) JOptionPane.showInputDialog(null, "Choose an athlete to apply this item to:\n",
                "Select athlete", JOptionPane.QUESTION_MESSAGE, null, GameManager.team.fullTeam(),"Choose athlete");

        if (athlete != null)
            parent.useItem(item, athlete, this);
        else
            JOptionPane.showMessageDialog(null, "You failed to enter an athlete.");
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
        itemUseButton.addActionListener(e -> useItem(item));
        this.add(itemUseButton);
    }

    public ItemPanel(Item item, ClubScreen parent) {
        this.parent = parent;
        initialize(item);
        setVisible(true);
    }
}
