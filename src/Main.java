import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    static Scanner inputScanner = new Scanner(System.in);

    static String getTeamName() {
        boolean valid = false;

        String teamName = "";

        while (!valid) {
            System.out.println("Enter team name");
            teamName = inputScanner.nextLine();  // Read user input

            if (teamName.length() < 3) {
                System.out.println("Team name must be at least 3 characters long");
                continue;
            } else if (teamName.length() > 15) {
                System.out.println("Team name must be at most 15 characters long");
                continue;
            }

            if (teamName.matches("[A-Za-z0-9]+")) {
                valid = true;
            } else {
                System.out.println("Team name must contain only letters and numbers");
            }
        }

        return teamName;
    }

    static int getSeasonLength() {
        boolean valid = false;

        int seasonLength = 0;

        while (!valid) {
            System.out.println("How many weeks would you like the season to last?");

            try {
                seasonLength = inputScanner.nextInt();  // Read user input
            } catch (InputMismatchException e) {
                System.out.println("Season length must be an number integer");
                inputScanner.nextLine(); // Prevent infinite loop
                continue;
            }

            if (seasonLength < 5) {
                System.out.println("Season must be at least 5 weeks long");
            } else if (seasonLength > 15) {
                System.out.println("Season must be at most 15 weeks long");
            } else {
                valid = true;
            }
        }

        return seasonLength;
    }

    public static void main(String[] args) {
        String teamName = getTeamName();
        int seasonLength = getSeasonLength();

        GameManager manager = new GameManager(teamName, seasonLength);

        manager.selectInitialTeam();
    }
}
