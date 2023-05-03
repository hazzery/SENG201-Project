import java.util.ArrayList;

public class GameManager {
    static UserInterface ui = new CommandLineInterface();
    private int seasonLength;
    static Team team;


    ArrayList<Athlete> athletes = new ArrayList<>(4);
    Athlete cyclistOne =    new Athlete("Cyclist One"   , 1, 4, 1, 1, 1);
    Athlete cyclistTwo =    new Athlete("Cyclist Two"   , 2, 3, 2, 1, 1);
    Athlete cyclistThree =  new Athlete("Cyclist Three" , 3, 2, 3, 1, 1);
    Athlete cyclistFour =   new Athlete("Cyclist Four"  , 4, 1, 4, 1, 1);

    public GameManager() {
        athletes.add(cyclistOne);
        athletes.add(cyclistTwo);
        athletes.add(cyclistThree);
        athletes.add(cyclistFour);
    }

    static String getValidName(String prompt) {
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

    static int getSeasonLength() {
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

    public static void startGame() {
        MainScreen.closeWindow();
        InitScreen initScreen = new InitScreen();
    }

    public void showAllAthletes() {
        int counter = 1;
        for (Athlete athlete : athletes) {
            ui.showOutput(counter + ": " + athlete.getName());
            ui.showOutput("   " + athlete.getDescription());
            counter++;
        }
    }

    public void selectInitialTeam() {
        ui.showOutput("Select your initial team of at least 4 athletes");
        ui.showOutput("Enter the number to the left of the athlete's name and press enter");
        ui.showOutput("Enter -1 to finish selecting athletes");

        showAllAthletes();

        int athlete;
        while (true) {  // Loop until user enters negative number
            athlete = ui.getInt();

            if (athlete < 0) {
                if (team.size() < Team.MIN_SIZE) {
                    ui.showOutput("Team must have at least 4 athletes");
                    continue;
                }
                break;  // End loop if user enters negative number
            }

            if (athlete == 0 || athlete > athletes.size()) {
                ui.showOutput("Please enter a valid athlete number, or -1 to finish");
                ui.showOutput("Valid athlete numbers are 1 to " + athletes.size());
                continue;
            }

            if (team.contains(athletes.get(athlete - 1))) {
                ui.showOutput("Athlete already in team");
                continue;
            }

            String nickname = getValidName("Enter a nickname for " + athletes.get(athlete - 1).getName());

            team.addAthlete(athletes.get(athlete - 1), nickname);
            ui.showOutput("Added " + athletes.get(athlete - 1).getName() + " to team as " + nickname);

        }

        team.displayAthletes(ui);
    }

    /*
     * Untested Code, 
     */

    public void selectDifficulty(){
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


    public void setUpSeason() {
        String teamName = getValidName("Enter a name for your team");
        this.seasonLength = getSeasonLength();

        team = new Team(teamName);

        selectInitialTeam();
    }
}
