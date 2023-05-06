import java.util.ArrayList;

public class GameManager {
    private static int seasonLength;
    private static Team team;


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

    public void launchSplashScreen() {
        MainScreen mainScreen = new MainScreen();
    }

    public static void validateName(String name) {
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters long");
        } else if (name.length() > 15) {
            throw new IllegalArgumentException("Name must be at most 15 characters long");
        }

        if (name.matches("[A-Za-z0-9]+")) {
        } else {
            throw new IllegalArgumentException("Name must contain only letters and numbers");
        }
    }

    public static void startGame() {
        MainScreen.closeWindow();
        InitScreen initScreen = new InitScreen();
    }
}
