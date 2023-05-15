import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager {
    private static int seasonLength;
    private static boolean hardMode;

    public static Team team;


    private static int bankBalance = 0;
    private static int currentWeek = 1;

    private static JFrame mainWindow;
    private static SplashScreen splashScreen;
    private static InitScreen initScreen;
    private static GameScreen gameScreen;

    public static final int NUM_ALL_ATHLETES = 12;
    private static final int NUM_ALL_ITEMS = 10;
    static ArrayList<Athlete> athletes = new ArrayList<>(NUM_ALL_ATHLETES);
    public static ArrayList<Item> items = new ArrayList<>();

    //THESE NUMBERS MUST BE CHANGED BASED ON DIFFICULTY AND PROGRESSION IN SEASON
    public static int num_max;
    public static int num_min;
    
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
     * Fills the `athletes` array with `NUM_ALL_ATHLETES` random athletes
     */
    public static void initializeAthletes() {
        for (int i = 0; i < NUM_ALL_ATHLETES; i++)
            athletes.add(new Athlete());
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
     * Checks to see if the provided string meets the criteria for a valid name
     * Valid names must be between 3 and 15 characters long and contain only letters and numbers
     * @param name the name to validate
     * @throws IllegalArgumentException if the name is invalid
     */
    public static void validateName(String name, boolean allowSpaces) throws IllegalArgumentException{
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters long");
        } else if (name.length() > 15) {
            throw new IllegalArgumentException("Name must be at most 15 characters long");
        }
        if (allowSpaces) {
            if (!name.matches("[A-Za-z ]+"))
                throw new IllegalArgumentException("Name must contain only letters, and spaces");
        }
        else if (!name.matches("[A-Za-z0-9]+")) {
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
     * Changes the current screen from the splash screen to the game initialisation screen
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

        team.setReserve(GameManager.athletes.get(6));
        team.setReserve(GameManager.athletes.get(7));

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

    /**
     * Fills the items array with `NUM_ALL_ITEMS` random items
     */
    public static void initializeItems() {
        for (int i = 0; i < NUM_ALL_ITEMS; i++)
            items.add(new Item());
    }

    /**
     * Purchases the provided athlete if the player has enough money.
     * Removes the athlete's contract price from the player's bank balance
     * and adds the athlete to the player's team
     * @param athlete the athlete to purchase
     * @throws IllegalStateException if the player does not have enough money to purchase the athlete
     */
    public static void purchaseAthlete(Athlete athlete) throws IllegalStateException {
        if (bankBalance < athlete.getContractPrice()) {
            throw new IllegalStateException("Not enough money to purchase athlete");
        }
        bankBalance -= athlete.getContractPrice();
        team.addAthlete(athlete, false);
    }

    /**
     * Purchases the provided item if the player has enough money.
     * Removes the item's contract price from the player's bank balance
     * and adds the item to the player's items
     * @param item the item to purchase
     * @throws IllegalStateException if the player does not have enough money to purchase the item
     */
    public static void purchaseItem(Item item) throws IllegalArgumentException{
        if (bankBalance < item.getContractPrice()) {
            throw new IllegalStateException("Not enough money to purchase item");
        }
        bankBalance -= item.getContractPrice();
        items.add(item);
    }

    public static void sellAthlete(Athlete athlete) {
        bankBalance += athlete.getSellBackPrice();
        team.removeAthlete(athlete);
    }
}
