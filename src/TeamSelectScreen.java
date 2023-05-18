import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class TeamSelectScreen extends JPanel {

    private final MarginBorder marginBorder = new MarginBorder(1, Color.BLACK, 5);
    private static final ArrayList<Athlete> selectedAthletes = new ArrayList<>(Team.TEAM_SIZE);

    private JPanel headerPanel;
        private JLabel headerLabel;
    private JPanel athleteSelectionPanel;
        private JLabel selectAthletesLabel;
        private JPanel buttonsWrapperPanel;
            private PurchasablesShelf selectableAthletesShelf;
            private PurchasablesShelf selectedAthletesShelf;
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

        headerLabel = new JLabel();
        headerLabel.setText("You Have: $" + GameManager.getBankBalance());
        headerPanel.add(headerLabel);

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

        selectableAthletesShelf = new PurchasablesShelf(GameManager.generateAthletes(8), this::selectButtonText, this::selectAthlete);
        buttonsWrapperPanel.add(selectableAthletesShelf);

        buttonsWrapperPanel.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 10000)));

        selectedAthletesShelf = new PurchasablesShelf(selectedAthletes.toArray(new Athlete[0]), this::unselectButtonText, this::unselectAthlete);
        GameManager.team.addActivesSubscriber(selectedAthletesShelf);
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
        return HTMLString.multiLine("Select", "- $" + purchasable.getContractPrice());
    }
    private String unselectButtonText (Purchasable purchasable) {
        return HTMLString.multiLine("Unselect", "+ $" + purchasable.getContractPrice());
    }

    private void selectAthlete(ActionEvent event) {
        PurchasablePanel panel = (PurchasablePanel) ((JButton) event.getSource()).getParent();
        Athlete athlete = (Athlete) panel.getPurchasable();

        if (!selectedAthletes.contains(athlete)) {
            String nickName = JOptionPane.showInputDialog("Choose a nickname for " + athlete.getName() + ":");
            try {
                Utilities.validateName(nickName, true);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
                return;
            }

            athlete.setNickname(nickName);
            selectedAthletes.add(athlete);
            selectedAthletesShelf.addPanel(athlete);

            PurchasablesShelf parent = (PurchasablesShelf) panel.getParent();
            parent.remove(panel);
            parent.revalidate();
            parent.repaint();
        }
    }

    /**
     * Remove an athlete from the selected athletes list and update the GUI
     * @param event The action event that triggered this method
     */
    private void unselectAthlete(ActionEvent event) {
        PurchasablePanel panel = (PurchasablePanel) ((JButton) event.getSource()).getParent();
        Athlete athlete = (Athlete) panel.getPurchasable();

        selectedAthletes.remove(athlete);
        selectedAthletesShelf.remove(panel);
        selectableAthletesShelf.revalidate();
        selectableAthletesShelf.repaint();
    }

    /**
     * Remove all athletes from the selected athletes list and update the GUI
     */
    private void resetAthletes() {
        selectedAthletes.clear();
        selectedAthletesShelf.removeAll();
        selectedAthletesShelf.revalidate();
        selectedAthletesShelf.repaint();
    }

    /**
     * Confirm the selected athletes and move to the next screen
     */
    private void acceptTeam() {

        if (selectedAthletes.size() < Team.TEAM_SIZE) {
            JOptionPane.showMessageDialog(this, "You must select at least " + Team.TEAM_SIZE + " athletes");
            return;
        }

        GameManager.startGame(selectedAthletes);
    }

}