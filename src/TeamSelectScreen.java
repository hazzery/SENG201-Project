import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class TeamSelectScreen extends JPanel {

    private final MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);
    private final ArrayList<Athlete> selectedAthletes = new ArrayList<>(Team.TEAM_SIZE);
    private final Athlete[] athletePool = GameManager.generateAthletes(9);

    private int bankBalance = 8500;

    private JPanel headerPanel;
        private JLabel bankBalanceLabel;
    private JPanel athleteSelectionPanel;
        private JLabel selectAthletesLabel;
        private JPanel buttonsWrapperPanel;
            private Shelf selectableAthletesShelf;
            private Shelf selectedAthletesShelf;
    private JPanel FooterPanel;
        private JButton resetAthletesButton;
        private JButton acceptAthletesButton;

    /**
     * Create the panel.
     */
    public TeamSelectScreen() {
        initialise();
        setVisible(true);
    }

    private void initialise() {
        this.setLayout(new BorderLayout(0, 0));

        headerPanel = new JPanel();
        headerPanel.setBorder(marginBorder);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        this.add(headerPanel, BorderLayout.NORTH);

        bankBalanceLabel = new JLabel();
        bankBalanceLabel.setText("You Have: $" + bankBalance);
        headerPanel.add(bankBalanceLabel);

        athleteSelectionPanel = new JPanel();
        athleteSelectionPanel.setBorder(marginBorder);
        athleteSelectionPanel.setLayout(new BorderLayout(0, 0));
        this.add(athleteSelectionPanel, BorderLayout.CENTER);

        selectAthletesLabel = new JLabel();
        selectAthletesLabel.setText("Select athletes from the below options:");
        athleteSelectionPanel.add(selectAthletesLabel, BorderLayout.NORTH);

        buttonsWrapperPanel = new JPanel();
        buttonsWrapperPanel.setBorder(marginBorder);
        buttonsWrapperPanel.setLayout(new BoxLayout(buttonsWrapperPanel, BoxLayout.Y_AXIS));
        athleteSelectionPanel.add(buttonsWrapperPanel, BorderLayout.CENTER);

        selectableAthletesShelf = new Shelf<>(athletePool, "Available", this::selectButtonText, this::selectAthlete);
        buttonsWrapperPanel.add(selectableAthletesShelf);

        buttonsWrapperPanel.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 10000)));

        selectedAthletesShelf = new Shelf<>(selectedAthletes.toArray(new Athlete[0]), "Selected", this::unselectButtonText, this::unselectAthlete);
        buttonsWrapperPanel.add(selectedAthletesShelf);

        FooterPanel = new JPanel();
        FooterPanel.setBorder(marginBorder);
        FooterPanel.setLayout(new GridLayout(1, 2, 0, 0));
        this.add(FooterPanel, BorderLayout.SOUTH);

        resetAthletesButton = new JButton();
        resetAthletesButton.setText("Reset team");
        resetAthletesButton.addActionListener(e -> resetAthletes());
        FooterPanel.add(resetAthletesButton);

        acceptAthletesButton = new JButton();
        acceptAthletesButton.setText("Accept team and continue");
        acceptAthletesButton.addActionListener(e -> acceptTeam());
        FooterPanel.add(acceptAthletesButton);

    }

    private String selectButtonText (Purchasable purchasable) {
        return HTMLString.multiLine("Select", "$" + purchasable.getContractPrice());
    }

    private String unselectButtonText (Purchasable purchasable) {
        return HTMLString.multiLine("Unselect", "+ $" + purchasable.getContractPrice());
    }

    private void selectAthlete(ActionEvent event) {
        DisplayablePanel panel = (DisplayablePanel) ((JButton) event.getSource()).getParent();
        Athlete athlete = (Athlete) panel.getDisplayable();

        if (bankBalance < athlete.getContractPrice()) {
            JOptionPane.showMessageDialog(this, "You cannot afford this athlete");
            return;
        }
        if (!selectedAthletes.contains(athlete)) {
            String nickName = JOptionPane.showInputDialog("Choose a nickname for" + athlete.getName() + ":");
            if (nickName == null)
                return; // User cancelled

            if (nickName.length() > 0) {
                try {
                    Utilities.validateName(nickName, true);
                    athlete.setNickname(nickName);
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    return;
                }
            }

            bankBalance -= athlete.getContractPrice();
            bankBalanceLabel.setText("You Have: $" + bankBalance);

            selectedAthletes.add(athlete);
            selectedAthletesShelf.addPanel(athlete);

            selectableAthletesShelf.removePanel(panel);
        }
        else {
            JOptionPane.showMessageDialog(this, "You have already selected this athlete");
        }
    }

    /**
     * Remove an athlete from the selected athletes list and update the GUI
     * @param event The action event that triggered this method
     */
    private void unselectAthlete(ActionEvent event) {
        DisplayablePanel panel = (DisplayablePanel) ((JButton) event.getSource()).getParent();
        Athlete athlete = (Athlete) panel.getDisplayable();

        bankBalance += athlete.getContractPrice();
        bankBalanceLabel.setText("You Have: $" + bankBalance);

        selectedAthletes.remove(athlete);
        selectedAthletesShelf.removePanel(panel);
        selectableAthletesShelf.addPanel(athlete);
    }

    /**
     * Remove all athletes from the selected athletes list and update the GUI
     */
    private void resetAthletes() {
        selectedAthletes.clear();
        selectedAthletesShelf.reload(new Athlete[0]);
        selectableAthletesShelf.reload(athletePool);
        bankBalance = 8500;
    }

    /**
     * Confirm the selected athletes and move to the next screen
     */
    private void acceptTeam() {

        if (selectedAthletes.size() < Team.TEAM_SIZE) {
            JOptionPane.showMessageDialog(this, "You must select at least " + Team.TEAM_SIZE + " athletes");
            return;
        }

        GameManager.startGame(selectedAthletes, bankBalance);
    }
}
