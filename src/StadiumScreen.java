import javax.swing.*;
import java.awt.*;

public class StadiumScreen extends GameScreenPanel {
    private final MarginBorder marginBorder = new MarginBorder(0, Color.BLACK, 5);

    @Override
    protected void initialize() {
        super.initialize();
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
    }


    public StadiumScreen(GameScreen gameScreen) {
        super(GameScreen.Screen.STADIUM, gameScreen);
    }
}
