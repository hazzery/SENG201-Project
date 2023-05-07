import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameManager {
    private static int seasonLength;
    private static Team team;
    private static boolean hardMode;

    private static int bankBalance = 0;

    private static JFrame mainWindow;
    private static SplashScreen splashScreen;
    private static InitScreen initScreen;


    public static final ArrayList<Athlete> athletes = new ArrayList<>(4);
    private static final Athlete skierOne =    new Athlete("Skier One"   , 1, 4, 1, 1, 1);
    private static final Athlete skierTwo =    new Athlete("Skier Two"   , 2, 3, 2, 1, 1);
    private static final Athlete skierThree =  new Athlete("Skier Three" , 3, 2, 3, 1, 1);
    private static final Athlete skierFour =   new Athlete("Skier Four"  , 4, 1, 4, 1, 1);
    private static final Athlete skierFive =   new Athlete("Skier Five"  , 4, 1, 4, 1, 1);
    private static final Athlete skierSix =    new Athlete("Skier Six"  , 4, 1, 4, 1, 1);
    private static final Athlete skierSeven =  new Athlete("Skier Seven"  , 4, 1, 4, 1, 1);
    private static final Athlete skierEight =  new Athlete("Skier Eight"  , 4, 1, 4, 1, 1);

    public GameManager() {
        athletes.add(skierOne);
        athletes.add(skierTwo);
        athletes.add(skierThree);
        athletes.add(skierFour);
        athletes.add(skierFive);
        athletes.add(skierSix);
        athletes.add(skierSeven);
        athletes.add(skierEight);
    }

    public static String getTeamName() {
        return team.getName();
    }

    public static int getBankBalance() {
        return bankBalance;
    }

    public static int currentWeek() {
        return 1;
    }

    public static int getSeasonLength() {
        return seasonLength;
    }

    public static void initializeMainWindow() {
        mainWindow = new JFrame();
        mainWindow.setTitle("Cool Ski game");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setBounds(0, 0, 1920, 1080);
        mainWindow.setUndecorated(true);
        mainWindow.setVisible(true);
    }

    public void launchSplashScreen() {
        initializeMainWindow();

        splashScreen = new SplashScreen();
        mainWindow.setContentPane(splashScreen);
    }

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

    private static void setScreen(JPanel panel) {
        mainWindow.remove(mainWindow.getContentPane());
        mainWindow.setContentPane(panel);
        mainWindow.revalidate();
        mainWindow.repaint();
    }

    public static void initializeGame() {
        initScreen = new InitScreen();
        setScreen(initScreen);
    }
    public static void startGame(String teamName, int seasonLength, ArrayList<Athlete> selectedAthletes, boolean hardMode) {
        GameManager.seasonLength = seasonLength;
        GameManager.team = new Team(teamName, selectedAthletes);
        GameManager.hardMode = hardMode;

        GameScreen gameScreen = new GameScreen();
        setScreen(gameScreen);
    }
}
