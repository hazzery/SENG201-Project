import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager {
    private static int seasonLength;
    private static boolean hardMode;

    public static Team team;
    public static ArrayList<Item> items = new ArrayList<>();

    private static int bankBalance = 0;
    private static int currentWeek = 1;

    private static JFrame mainWindow;
    private static SplashScreen splashScreen;
    private static InitScreen initScreen;
    private static GameScreen gameScreen;

    public static final int NUM_ALL_ATHLETES = 8;
    public static ArrayList<Athlete> athletes = new ArrayList<>(NUM_ALL_ATHLETES);
    private static final Athlete skierOne =    new Athlete("Skier One"   , 4, 8, 1, 1, 1);
    private static final Athlete skierTwo =    new Athlete("Skier Two"   , 4, 7, 2, 1, 1);
    private static final Athlete skierThree =  new Athlete("Skier Three" , 4, 6, 3, 1, 1);
    private static final Athlete skierFour =   new Athlete("Skier Four"  , 4, 5, 4, 1, 1);
    private static final Athlete skierFive =   new Athlete("Skier Five"  , 4, 4, 5, 1, 1);
    private static final Athlete skierSix =    new Athlete("Skier Six"  , 4, 3, 6, 1, 1);
    private static final Athlete skierSeven =  new Athlete("Skier Seven"  , 4, 2, 7, 1, 1);
    private static final Athlete skierEight =  new Athlete("Skier Eight"  , 4, 1, 8, 1, 1);

    /**
     * Initializes the main window
     * Setting the title, size, and close operation
     */
    public static void initializeMainWindow() {
        mainWindow = new JFrame();
        mainWindow.setTitle("Cool Ski game");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setBounds(0, 0, 1920, 1080);
        mainWindow.setVisible(true);
    }

    public static void initializeAthletes() {
        athletes.add(skierOne);
        athletes.add(skierTwo);
        athletes.add(skierThree);
        athletes.add(skierFour);
        athletes.add(skierFive);
        athletes.add(skierSix);
        athletes.add(skierSeven);
        athletes.add(skierEight);
    }

    /**
     * Launches the application window
     * initializes the main window and sets it to display the splash screen
     */
    public static void launchApplicationWindow() {
        initializeMainWindow();

        splashScreen = new SplashScreen();
        mainWindow.setContentPane(splashScreen);
    }

    /**
     * Checks to see if the provided string meets the criteria for a valid name
     * Valid names must be between 3 and 15 characters long and contain only letters and numbers
     * @param name the name to validate
     * @throws IllegalArgumentException if the name is invalid
     */
    public static void validateName(String name) {
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters long");
        } else if (name.length() > 15) {
            throw new IllegalArgumentException("Name must be at most 15 characters long");
        }

        if (!name.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Name must contain only letters and numbers");
        }
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
     * Changes the current screen from the splash screen to the game initialization screen
     */
    public static void initializeGame() {
        initScreen = new InitScreen();
        setScreen(initScreen);
    }

    /**
     * Starts the game with the provided parameters
     * @param teamName the name of the player's team
     * @param seasonLength the length of the season in weeks
     * @param selectedAthletes the athletes selected by the player
     * @param hardMode `true` if the game is in hard mode, `false` otherwise
     */
    public static void startGame(String teamName, int seasonLength, ArrayList<Athlete> selectedAthletes, boolean hardMode) {
        GameManager.seasonLength = seasonLength;
        GameManager.team = new Team(teamName, selectedAthletes);
        GameManager.hardMode = hardMode;

        gameScreen = new GameScreen();
        setScreen(gameScreen);
    }

    /**
     * Gets the name of the player's team
     * @return the name of the player's team
     */
    public static String getTeamName() {
        return team.getName();
    }

    /**
     * Gets the player's current bank balance
     * @return the player's current bank balance
     */
    public static int getBankBalance() {
        return bankBalance;
    }

    /**
     * Gets the current week within the season
     * @return the current week
     */
    public static int currentWeek() {
        return currentWeek;
    }

    /**
     * Gets the length of the season
     * @return the length of the season
     */
    public static int getSeasonLength() {
        return seasonLength;
    }

    /**
     * Increments the current week by one
     */
    public static void nextWeek() {
        currentWeek++;
    }

    public static void initializeItems() {
        Item item1 = new Item("Item One", Athlete.StatType.OFFENCE, 1, 10, 5);
        Item item2 = new Item("Item Two", Athlete.StatType.DEFENCE, 1, 10, 5);
        Item item3 = new Item("Item Three", Athlete.StatType.OFFENCE, 2, 20, 10);
        Item item4 = new Item("Item Four", Athlete.StatType.DEFENCE, 2, 20, 10);
        Item item5 = new Item("Item Five", Athlete.StatType.OFFENCE, 3, 30, 20);
        Item item6 = new Item("Item Six", Athlete.StatType.DEFENCE, 3, 30, 20);
        Item item7 = new Item("Item Seven", Athlete.StatType.OFFENCE, 4, 40, 30);
        Item item8 = new Item("Item Eight", Athlete.StatType.DEFENCE, 4, 40, 30);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);
        items.add(item8);
    }
}
