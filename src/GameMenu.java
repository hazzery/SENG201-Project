import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.SwingConstants;

public class GameMenu {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameMenu window = new GameMenu();
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
    public GameMenu() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Ski Team Menu");
        frame.setBounds(0, 0, 1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(39, 193, 310, 97);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("PLAY A GAME");
        lblNewLabel.setBounds(107, 42, 86, 14);
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(39, 301, 152, 51);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Market");
        lblNewLabel_1.setBounds(52, 11, 48, 14);
        panel_1.add(lblNewLabel_1);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBounds(197, 301, 152, 51);
        frame.getContentPane().add(panel_1_1);
        panel_1_1.setLayout(null);

        JLabel lblNewLabel_1_1 = new JLabel("Quit");
        lblNewLabel_1_1.setBounds(49, 11, 48, 14);
        panel_1_1.add(lblNewLabel_1_1);

        JLabel lblNewLabel_2 = new JLabel("THE GAME");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBackground(new Color(128, 128, 128));
        lblNewLabel_2.setBounds(112, 74, 152, 58);
        frame.getContentPane().add(lblNewLabel_2);
    }
}
