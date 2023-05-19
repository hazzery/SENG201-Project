import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class MatchScreen {
    
    MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);
    
    private JPanel mainPanel;
    private JPanel oppositionWrapperPanel;
        //TODO add opposition related stuff here
    private JPanel athletesWrapperPanel;
        //TODO add athlete related stuff here
    private JPanel FooterPanel;
        private JButton attackLightButton;
        private JButton attackHeavyButton;
        private JButton healButton;
        private JButton nextTurnButton;

    protected void initialize() {
        mainPanel = new JPanel();
        mainPanel.setBorder(marginBorder);
        mainPanel.setLayout(new GridLayout(0, 1, 0, 0));

        oppositionWrapperPanel = new JPanel();
        oppositionWrapperPanel.setBorder(marginBorder);
        oppositionWrapperPanel.setLayout(new BoxLayout(oppositionWrapperPanel, BoxLayout.X_AXIS));
        mainPanel.add(oppositionWrapperPanel);

        //TODO Active Athletes in Game are displayed here

        athletesWrapperPanel = new JPanel();
        athletesWrapperPanel.setBorder(marginBorder);
        athletesWrapperPanel.setLayout(new BoxLayout(athletesWrapperPanel, BoxLayout.X_AXIS));
        mainPanel.add(athletesWrapperPanel);

        //TODO Opposition athletes in Game are displayed here

        FooterPanel = new JPanel();
        FooterPanel.setBorder(marginBorder);
        FooterPanel.setLayout(new BoxLayout(FooterPanel, BoxLayout.X_AXIS));
        mainPanel.add(FooterPanel);

        attackLightButton = new JButton("Light Attack");
        attackLightButton.addActionListener((ActionListener) this);
        FooterPanel.add(attackLightButton);

        attackHeavyButton = new JButton("Heavy Attack");
        attackHeavyButton.addActionListener((ActionListener) this);
        FooterPanel.add(attackHeavyButton);

        healButton = new JButton("Heal");
        healButton.addActionListener((ActionListener) this);
        FooterPanel.add(healButton);

        nextTurnButton = new JButton("Next Turn");
        nextTurnButton.addActionListener((ActionListener) this);
        FooterPanel.add(nextTurnButton);
        

    }


    public static void attackLightButtonActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attackLightButtonActionPerformed'");
    }

    public static void attackHeavyButtonActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'attackHeavyButtonActionPerformed'");
    }
        
    public static void healButtonActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'healButtonActionPerformed'");
    }

    public static void nextTurnButtonActionPerformed(ActionEvent evt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextTurnButtonActionPerformed'");
    }
}
