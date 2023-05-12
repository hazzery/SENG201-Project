import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class AthletePanel extends JPanel {
    MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);
    
    private JLabel athleteNameLabel;
    private JLabel athleteOffenceLabel;
    private JLabel athleteDefenceLabel;
    private JLabel athleteStaminaLabel;
    private JLabel athleteHealthLabel;
    protected JButton actionButton;


    protected Athlete athlete;
    protected GameScreenPanel parent;

    public void updateStats() {
        athleteOffenceLabel.setText("Offence: " + athlete.getOffence());
        athleteDefenceLabel.setText("Defence: " + athlete.getDefence());
        athleteStaminaLabel.setText("Stamina: " + athlete.getStamina());
        athleteHealthLabel.setText("Health: " + athlete.getCurrentHealth());
    }
    
    protected void initialize() {
        this.setBorder(marginBorder);
        this.setLayout(new GridLayout(0, 1, 0, 0));

        athleteNameLabel = new JLabel();
        athleteNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        athleteNameLabel.setOpaque(true);
        athleteNameLabel.setBackground(Color.LIGHT_GRAY);
        athleteNameLabel.setText(athlete.getNickname());
        this.add(athleteNameLabel);

        athleteOffenceLabel = new JLabel();
        athleteOffenceLabel.setText("Offence: " + athlete.getOffence());
        this.add(athleteOffenceLabel);

        athleteDefenceLabel = new JLabel();
        athleteDefenceLabel.setText("Defence: " + athlete.getDefence());
        this.add(athleteDefenceLabel);

        athleteStaminaLabel = new JLabel();
        athleteStaminaLabel.setText("Stamina: " + athlete.getStamina());
        this.add(athleteStaminaLabel);

        athleteHealthLabel = new JLabel();
        athleteHealthLabel.setText("Health: " + athlete.getCurrentHealth());
        this.add(athleteHealthLabel);
    }
    
    public AthletePanel(Athlete athlete) {
        this.athlete = athlete;
        initialize();
        setVisible(true);
    }
}
