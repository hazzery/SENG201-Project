import javax.swing.*;

public class MarketAthletePanel extends AthletePanel {

    private MarketScreen parent;

    @Override
    protected void initialize() {
        super.initialize();
        actionButton = new JButton();
        actionButton.setText("Purchase");
        actionButton.setHorizontalAlignment(SwingConstants.CENTER);
        actionButton.addActionListener(e -> parent.purchaseAthlete(athlete, this));
        this.add(actionButton);
    }

    public MarketAthletePanel(Athlete athlete, MarketScreen parent) {
        super(athlete);
        this.parent = parent;
    }
}
