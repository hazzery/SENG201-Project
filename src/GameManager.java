import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameManager {

    static Scanner inputScanner = new Scanner(System.in);

    private final int seasonLength;
    private final String teamName;

    ArrayList<Athlete> athletes = new ArrayList<>(4);
    Athlete cyclistOne =    new Athlete("Cyclist One"   , 1, 4, 1, 1, 1);
    Athlete cyclistTwo =    new Athlete("Cyclist Two"   , 2, 3, 2, 1, 1);
    Athlete cyclistThree =  new Athlete("Cyclist Three" , 3, 2, 3, 1, 1);
    Athlete cyclistFour =   new Athlete("Cyclist Four"  , 4, 1, 4, 1, 1);

    ArrayList<Athlete> team = new ArrayList<>(4);

    public GameManager(String teamName, int seasonLength) {
        this.seasonLength = seasonLength;
        this.teamName = teamName;

        athletes.add(cyclistOne);
        athletes.add(cyclistTwo);
        athletes.add(cyclistThree);
        athletes.add(cyclistFour);
    }

    public void selectInitialTeam() {
        System.out.println("Select your initial team of at least 4 athletes");
        System.out.println("Enter the number to the left of the athlete's name and press enter");
        System.out.println("Enter -1 to finish selecting athletes");

        int counter = 1;
        for (Athlete athlete : athletes) {
            System.out.println(counter + ": " + athlete.getName());
            System.out.println("   " + athlete.getDescription());
            counter++;
        }

        int selection = 0;

        while (true) {  // Loop until user enters negative number
            try {
                selection = inputScanner.nextInt();  // Read user input
            } catch (InputMismatchException e) {
                System.out.println("Season length must be an number integer");
                inputScanner.nextLine();  // Prevent infinite loop
                continue;
            }

            if (selection < 0) {
                if (team.size() < 4) {
                    System.out.println("Team must have at least 4 athletes");
                    continue;
                }
                break;
            }

            try {
                team.add(athletes.get(selection - 1));
                System.out.println("Added " + athletes.get(selection - 1).getName() + " to team");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Please enter one of the above numbers");
            }
        }
    }
}
