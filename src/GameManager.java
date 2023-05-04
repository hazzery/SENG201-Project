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

    static String getValidName(UserInterface ui, String prompt) {
        boolean valid = false;

        String name = null;

        while (!valid) {
            ui.showOutput(prompt);
            name = ui.getString();  // Read user input
            System.out.println(name);

            if (name.isBlank())
                name = ui.getString();

            if (name.length() < 3) {
                ui.showOutput("Name must be at least 3 characters long");
                continue;
            } else if (name.length() > 15) {
                ui.showOutput("Name must be at most 15 characters long");
                continue;
            }

            if (name.matches("[A-Za-z0-9]+")) {
                valid = true;
            } else {
                ui.showOutput("Name must contain only letters and numbers");
            }
        }

        return name;
    }

    public static boolean validateName(String name) {
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters long");
        } else if (name.length() > 15) {
            throw new IllegalArgumentException("Name must be at most 15 characters long");
        }

        if (name.matches("[A-Za-z0-9]+")) {
            return true;
        } else {
            throw new IllegalArgumentException("Name must contain only letters and numbers");
        }
    }

    static int getSeasonLength(UserInterface ui) {
        boolean valid = false;

        int seasonLength = 0;

        while (!valid) {
            ui.showOutput("How many weeks would you like the season to last?");

            seasonLength = ui.getInt();
        
            if (seasonLength < 5) {
                ui.showOutput("Season must be at least 5 weeks long");
            } else if (seasonLength > 15) {
                ui.showOutput("Season must be at most 15 weeks long");
            } else {
                valid = true;
            }
        }

        return seasonLength;
    }

    public static void gameSetup() {
        MainScreen.closeWindow();
        InitScreen initScreen = new InitScreen();
    }

    public static void startGame(String teamName, int seasonLength, ArrayList<Athlete> athletes) {
        MainScreen.closeWindow();
        team = new Team(teamName, athletes);
        GameManager.seasonLength = seasonLength;
    }

    /*
     * Untested Code, 
     */

    public void selectDifficulty(UserInterface ui){
        ui.showOutput("Select your difficulty");
        ui.showOutput("1: Easy");
        ui.showOutput("2: Medium");
        ui.showOutput("3: Hard");
        ui.showOutput("4: Impossible");

        int difficulty;

        while (true) {
            difficulty = ui.getInt(); //Gets user input and completes Integer Check
            if (difficulty < 1 || difficulty > 4) { //Checks if difficulty is between 1 and 4 by checking if input is outside of 1 and 4.
                ui.showOutput("Please enter a valid difficulty");
                continue;
            }
            break;
        }
    }
}
