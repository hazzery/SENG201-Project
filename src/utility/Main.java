package utility;
import management.GameManager;
import management.WindowManager;

/**
 * The starting point of the program!
 *
 * @author Harrison Parkes
 */
public class Main {

    /**
     * Launches the main application window
     * @param args An arbitrary number of command line arguments to the program [UNUSED]
     */
    public static void main(String[] args) {
        GameManager.initializeAthletes();
        WindowManager.launchApplicationWindow();
    }
}
