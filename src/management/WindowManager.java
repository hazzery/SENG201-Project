package management;
import javax.swing.*;

import gui.GameOverScreen;
import gui.GameScreen;
import gui.InitScreen;
import gui.MatchWindow;
import gui.SplashScreen;
import gui.TeamSelectScreen;


/**
 * WindowManager is a static utility class that manages everything to do with the game's window
 * Used for switching out the various screens involved with the gameplay
 *
 * @author Harrison Parkes
 */
public class WindowManager {

    private static JFrame mainWindow;

    static GameScreen gameScreen;

    /**
     * Initialises the main window
     * Setting the title, size, and close operation
     */
    public static void initializeMainWindow() {
        mainWindow = new JFrame();
        mainWindow.setTitle("Cool Ski game");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setBounds(0, 0, 1920, 1080);
        mainWindow.setVisible(true);
    }

    /**
     * Launches the application window.
     * Initialises the main window and sets it to display the splash screen
     */
    public static void launchApplicationWindow() {
        initializeMainWindow();

        SplashScreen splashScreen = new SplashScreen();
        mainWindow.setContentPane(splashScreen);
    }

    /**
     * Changes the current screen to the provided panel
     * @param panel the panel to display
     */
    private static void setScreen(JPanel panel) {
        if (mainWindow == null)
            initializeMainWindow();
        mainWindow.remove(mainWindow.getContentPane());
        mainWindow.setContentPane(panel);
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    /**
     * Changes the current screen from the splash screen to the game initialisation screen
     */
    public static void showInitScreen() {
        InitScreen initScreen = new InitScreen();
        setScreen(initScreen);
    }

    /**
     * Change the current screen from the initialisation screen to the team selection screen
     */
    public static void showTeamSelection() {
        TeamSelectScreen teamSelectScreen = new TeamSelectScreen();
        setScreen(teamSelectScreen);
    }

    /**
     * Changes the current screen from the game initialisation screen to the game screen
     */
    public static void showGameScreen() {
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }

    /**
     * Changes the current screen from the stadium to the match screen
     */
    public static void showMatchScreen() {
        MatchWindow matchScreen = new MatchWindow();
    	setScreen(matchScreen);
    }

    /**
     * Changes the current screen from the game screen to the game over screen
     */
    public static void showGameOverScreen() {
        GameOverScreen gameOverScreen = new GameOverScreen();
        setScreen(gameOverScreen);
    }

    /**
     * Updates all the content on the game screen to reflect new changes in the game's state
     */
    public static void reloadGameScreen() {
        gameScreen.reload();
    }
}
