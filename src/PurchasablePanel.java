import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import java.awt.*;

public class PurchasablePanel extends JPanel {
    private final MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);
    private final HashMap<String, JLabel> statLabels;
    private Purchasable purchasable;

    PurchasablePanel(Purchasable purchasable) {
        this.purchasable = purchasable;
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
        statLabels.get(stat).setText(value + ' ' + stat);
    }

    public Purchasable getPurchasable() {
        return purchasable;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        PurchasablePanel panel = new PurchasablePanel(new Athlete("John Cena", 7, 9, 8));
        panel.addButton("Purchase", e -> panel.update("Offence", "69"));
        frame.add(panel);
        frame.setVisible(true);
    }
}
