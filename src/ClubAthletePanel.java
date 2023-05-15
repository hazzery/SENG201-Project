import javax.swing.*;
import java.awt.event.ActionListener;

public class ClubAthletePanel extends AthletePanel {
    private ClubScreen parent;

    @Override
    protected void initialize() {
        super.initialize();
        actionButton = new JButton();
        actionButton.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(actionButton);
    }

    void configureButton(boolean isReserved) {
        for (ActionListener al : actionButton.getActionListeners())
            actionButton.removeActionListener(al);

        if (isReserved) {
            actionButton.setText("Activate");
            actionButton.addActionListener(e -> parent.activateAthlete(athlete, this));
        } else {
            actionButton.setText("Reserve");
            actionButton.addActionListener(e -> parent.reserveAthlete(athlete, this));
        }
        revalidate();
        repaint();
    }

    public ClubAthletePanel(Athlete athlete, boolean isReserved, ClubScreen parent) {
        super(athlete);
        this.parent = parent;
        configureButton(isReserved);

    }
}
