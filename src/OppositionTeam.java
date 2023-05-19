import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class the models a Opposition team
 */
public class OppositionTeam {

    private static int numberOfAthletes = 5;

    static private boolean nameScannerIsInitialised = false;
    static private Scanner nameScanner;
    static private Stack<String> oppositionNames;

    private static String name;
    
    
    /**
     * Returns the name of the Opposition Team 
     * Done by collecting a name from {@link OppositionNames.txt} through the {@link initOppositionNameReader()} method
     * 
     * @return the name of the Opposition Team
     */
    public static String getName() {
        initOppositionNameReader();
        name = oppositionNames.pop();
        return name;
    }

    /**
     * Collects a name from the OppositionNames.txt file and returns it
     *
     */
    private static void initOppositionNameReader() {
        if (nameScannerIsInitialised) return;

        oppositionNames = new Stack<String>();
        try {
            File myObj = new File("Resources/OppositionNames.txt");
            nameScanner = new Scanner(myObj);
            while (nameScanner.hasNextLine()) {
                oppositionNames.push(nameScanner.nextLine());
            }
            nameScanner.close();
            nameScannerIsInitialised = true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Creates an array of athletes with randomised stats
     */
    public static ArrayList<Athlete> createOppTeam() {
        ArrayList<Athlete> oppositionAthletes = new ArrayList<Athlete>();
        for (int i = 0; i < numberOfAthletes; i++) {
            oppositionAthletes.add(new Athlete());
        }
        return oppositionAthletes;
    }

    public static ArrayList<ArrayList<Athlete>> generateOpposition(){
        ArrayList<ArrayList<Athlete>> teams = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ArrayList<Athlete> team = createOppTeam();
            teams.add(team);
        }
        return teams;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Athlete>> teams = generateOpposition();
        System.out.println(teams);
    }
}

