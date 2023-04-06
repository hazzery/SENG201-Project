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
    public static void main(String[] args) {
        System.out.println("Hello world! Its me dalieosplif the legendary programmer!");
        String teamName = getTeamName();
    }
}
