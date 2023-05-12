import javax.swing.*;

public class ClubItemPanel extends ItemPanel {

    ClubScreen parent;

    @Override
    protected void initialize(Item item) {
        super.initialize(item);
        itemActionButton = new JButton();
        itemActionButton.setText("Use");
        itemActionButton.addActionListener(e -> useItem(item));
        this.add(itemActionButton);
    }

    public ClubItemPanel(Item item, ClubScreen parent) {
        super(item);
        this.parent = parent;
    }

    private void useItem(Item item) {
        Athlete athlete = (Athlete) JOptionPane.showInputDialog(null, "Choose an athlete to apply this item to:\n",
                "Select athlete", JOptionPane.QUESTION_MESSAGE, null, GameManager.team.fullTeam(),"Choose athlete");

        if (athlete != null)
            parent.useItem(item, athlete, this);
        else
            JOptionPane.showMessageDialog(null, "You failed to enter an athlete.");
    }
}
