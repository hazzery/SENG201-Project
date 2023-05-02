//import javax.swing.*;
//import java.awt.*;
import java.awt.*;
import javax.swing.*;

public class InitScreen {
    private JFrame frame;

    private JPanel teamNameAndSeasonLength;
    private JLabel enterTeamName;
    private JTextField teamName;
    private JLabel enterSeasonLength;
    private JSlider seasonLength;
    private JPanel athleteSelection;
    private JLabel selectAthletes;
    private JButton[] allAthletes;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    InitScreen window = new InitScreen();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public InitScreen() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Ski team game");
        frame.setBounds(0, 0, 1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));

        teamNameAndSeasonLength = new JPanel();
        teamNameAndSeasonLength.setLayout(new GridLayout(2, 2, 0, 0));
        frame.add(teamNameAndSeasonLength);

        enterTeamName = new JLabel();
        enterTeamName.setText("Enter team name:");
        teamNameAndSeasonLength.add(enterTeamName);

        teamName = new JTextField();
        teamNameAndSeasonLength.add(teamName);

        enterSeasonLength = new JLabel();
        enterSeasonLength.setText("Enter season length in weeks:");
        teamNameAndSeasonLength.add(enterSeasonLength);

        seasonLength = new JSlider();
        seasonLength.setMajorTickSpacing(1);
        seasonLength.setPaintTicks(true);
        seasonLength.setPaintLabels(true);
        seasonLength.setMinimum(5);
        seasonLength.setMaximum(15);
        seasonLength.setValue(10);
        teamNameAndSeasonLength.add(seasonLength);

//        athleteSelection = new JPanel();
//        athleteSelection.setLayout(new GridLayout(1, 1, 0, 0));
//        frame.add(athleteSelection);
//
//        selectAthletes = new JLabel();
//        selectAthletes.setText("Select athletes from the below options:");
//        athleteSelection.add(selectAthletes);
//        for (int i = 0; i < allAthletes.length; i++) {
//            allAthletes[i] = new JButton();
//            athleteSelection.add(allAthletes[i]);
//        }
    }

}
