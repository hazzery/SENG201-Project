import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.awt.*;

public class PurchasablePanel extends JPanel {
    private final MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);
    private final HashMap<String, JLabel> statLabels;
    private final Purchasable purchasable;

    public PurchasablePanel(Purchasable purchasable) {
        this.purchasable = purchasable;

        if (purchasable instanceof Athlete athlete)
            athlete.registerPanel(this);

        this.setLayout(new GridLayout(0, 1, 0, 0));
        this.setBorder(marginBorder);

        JLabel nameLabel = new JLabel(purchasable.getName());
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setOpaque(true);
        nameLabel.setBackground(Color.LIGHT_GRAY);
        this.add(nameLabel);

        statLabels = new HashMap<>();

        for (Map.Entry<String, String> entry : purchasable.getStats().entrySet()) {
            statLabels.put(entry.getKey(), new JLabel(entry.getValue() + ' ' + entry.getKey()));
            statLabels.get(entry.getKey()).setHorizontalAlignment(SwingConstants.CENTER);
            this.add(statLabels.get(entry.getKey()));
        }
    }

    public void addButton(String buttonText, ActionListener actionListener) {
        JButton button = new JButton(buttonText);
        button.addActionListener(actionListener);
        this.add(button);
    }

    public void update(String stat, String value) {
        String titleCase = stat.substring(0, 1).toUpperCase() + stat.substring(1);
        statLabels.get(titleCase).setText(value + ' ' + stat);
    }

    public Purchasable getPurchasable() {
        return purchasable;
    }
}
