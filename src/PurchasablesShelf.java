import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PurchasablesShelf extends JPanel {
    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);

    private PurchasablePanel[] purchasablePanels;

    public PurchasablesShelf(Purchasable[] purchasables, String buttonText, ActionListener actionListener) {
        setBorder(marginBorder);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        for (Purchasable purchasable : purchasables) {
            PurchasablePanel panel = new PurchasablePanel(purchasable);
            panel.addButton(buttonText, actionListener);
            add(panel);
        }
        setVisible(true);
    }

    static ActionListener actionListener = e -> {
        PurchasablePanel panel = (PurchasablePanel)(((JButton)e.getSource()).getParent());
        panel.remove((JButton)(e.getSource()));
        panel.revalidate();
        panel.repaint();
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        Purchasable[] purchasables = new Purchasable[] {new Athlete("John Cena", 7, 9, 8), new Athlete("John Cena", 7, 9, 8), new Athlete("John Cena", 7, 9, 8)};
        PurchasablesShelf shelf = new PurchasablesShelf(purchasables, "Purchase", actionListener);
        frame.add(shelf);
        frame.setVisible(true);
    }
}
