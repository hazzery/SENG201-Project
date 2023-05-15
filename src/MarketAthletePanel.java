import java.awt.event.ActionListener;
import javax.swing.*;

public class MarketAthletePanel extends AthletePanel {

    private MarketScreen parent;

    @Override
    protected void initialize() {
        super.initialize();
        actionButton = new JButton();
        actionButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(actionButton);
    }

    void configureButton(boolean isOwned) {
        for (ActionListener al : actionButton.getActionListeners())
            actionButton.removeActionListener(al);

        if (isOwned) {
            actionButton.setText("Sell");
            actionButton.addActionListener(e -> parent.sellAthlete(athlete, this));
        } else {
            actionButton.setText("Purchase");
            actionButton.addActionListener(e -> parent.purchaseAthlete(athlete, this));
        }
        revalidate();
        repaint();
    }


    public MarketAthletePanel(Athlete athlete, boolean isOwned, MarketScreen parent) {
        super(athlete);
        this.parent = parent;
        configureButton(isOwned);
    }
}
