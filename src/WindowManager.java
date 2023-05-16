import javax.swing.*;

public class WindowManager {

    private static JFrame mainWindow;
    private static SplashScreen splashScreen;
    private static InitScreen initScreen;
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

        splashScreen = new SplashScreen();
        mainWindow.setContentPane(splashScreen);
    }

    /**
     * Changes the current screen to the provided panel
     * @param panel the panel to display
     */
    private static void setScreen(JPanel panel) {
        mainWindow.remove(mainWindow.getContentPane());
        mainWindow.setContentPane(panel);
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    /**
     * Changes the current screen from the splash screen to the game initialisation screen
     */
    public static void showInitScreen() {
        initScreen = new InitScreen();
        setScreen(initScreen);
    }

    /**
     * Changes the current screen from the game initialisation screen to the game screen
     */
    public static void showGameScreen() {
        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
