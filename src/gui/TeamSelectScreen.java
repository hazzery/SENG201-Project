package gui;
import data.Athlete;
import data.Displayable;
import data.Purchasable;
import data.Team;
import management.GameManager;
import utility.HTMLString;
import utility.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * TeamSelectScreen is the user interface that allows the user to choose which athletes they would like
 * in their initial team, from a selection of randomly generated athletes
 *
 * @author Harrison Parkes
 */
public class TeamSelectScreen extends JPanel {
    private final MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);
    private final ArrayList<Athlete> selectedAthletes = new ArrayList<>(Team.TEAM_SIZE);
    private final Athlete[] athletePool = GameManager.generateAthletes(9);

    private int bankBalance = 10000;

    private JLabel bankBalanceLabel;
    private DisplayablesShelf selectableAthletesShelf;
            private DisplayablesShelf selectedAthletesShelf;

    /**
     * Instantiates a new TeamSelectScreen
     */
    public TeamSelectScreen() {
        initialise();
        setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialise() {
        this.setLayout(new BorderLayout(0, 0));

        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(marginBorder);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
        this.add(headerPanel, BorderLayout.NORTH);

        bankBalanceLabel = new JLabel();
        bankBalanceLabel.setText("You Have: $" + bankBalance);
        headerPanel.add(bankBalanceLabel);

        JPanel athleteSelectionPanel = new JPanel();
        athleteSelectionPanel.setBorder(marginBorder);
        athleteSelectionPanel.setLayout(new BorderLayout(0, 0));
        this.add(athleteSelectionPanel, BorderLayout.CENTER);

        JLabel selectAthletesLabel = new JLabel();
        selectAthletesLabel.setText("Select athletes from the below options:");
        athleteSelectionPanel.add(selectAthletesLabel, BorderLayout.NORTH);

        JPanel buttonsWrapperPanel = new JPanel();
        buttonsWrapperPanel.setBorder(marginBorder);
        buttonsWrapperPanel.setLayout(new BoxLayout(buttonsWrapperPanel, BoxLayout.Y_AXIS));
        athleteSelectionPanel.add(buttonsWrapperPanel, BorderLayout.CENTER);

        selectableAthletesShelf = new DisplayablesShelf(athletePool, "Available", this::selectButtonText, this::selectAthlete);
        buttonsWrapperPanel.add(selectableAthletesShelf);

        buttonsWrapperPanel.add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 10000)));

        selectedAthletesShelf = new DisplayablesShelf(selectedAthletes.toArray(new Athlete[0]), "Selected", this::unselectButtonText, this::unselectAthlete);
        buttonsWrapperPanel.add(selectedAthletesShelf);

        JPanel footerPanel = new JPanel();
        footerPanel.setBorder(marginBorder);
        footerPanel.setLayout(new GridLayout(1, 2, 0, 0));
        this.add(footerPanel, BorderLayout.SOUTH);

        JButton resetAthletesButton = new JButton();
        resetAthletesButton.setText("Reset team");
        resetAthletesButton.addActionListener(e -> resetAthletes());
        footerPanel.add(resetAthletesButton);

        JButton acceptAthletesButton = new JButton();
        acceptAthletesButton.setText("Accept team and continue");
        acceptAthletesButton.addActionListener(e -> acceptTeam());
        footerPanel.add(acceptAthletesButton);

    }

    /**
     * Function that creates the button text for a select athlete button
     * Uses the provided athlete to get its price
     * @param displayable Any purchasable object
     * @return The string to put on the button
     * @throws RuntimeException if `displayable` is not Purchasable
     */
    private String selectButtonText (Displayable displayable) {
        if (displayable instanceof Purchasable purchasable)
            return HTMLString.multiLine("Select", "$" + purchasable.getContractPrice());
        else
            throw new RuntimeException("Must call with Purchasable object!");
    }

    /**
     * Function that creates the button text for an unselect athlete button
     * Uses the provided athlete to get its price
     * @param displayable Any purchasable object
     * @return The string to put on the button
     * @throws RuntimeException if `displayable` is not Purchasable
     */
    private String unselectButtonText(Displayable displayable) throws RuntimeException {
        if (displayable instanceof Purchasable purchasable)
            return HTMLString.multiLine("Unselect", "+ $" + purchasable.getContractPrice());
        else
            throw new RuntimeException("Must call with Purchasable object!");
    }

    /**
     * Event handler for select athlete button
     * @param event An {@link ActionEvent} containing information about which button called the handler
     */
    private void selectAthlete(ActionEvent event) {
        DisplayPanel panel = (DisplayPanel) ((JButton) event.getSource()).getParent();
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
        DisplayPanel panel = (DisplayPanel) ((JButton) event.getSource()).getParent();
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
