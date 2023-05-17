import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class the models a Opposition team
 */
public class OppositionTeam {

    private static int numberOfAthletes = 4;

    /**
     * Creates the a {@link Athlete} array to be used for the opposition team
     */
    static Athlete[] oppositionAthletes = createTeam();
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
    public static Athlete[] createTeam() {
        Athlete[] athletes = new Athlete[numberOfAthletes];
        for (int i = 0; i < numberOfAthletes; i++) {
            athletes[i] = new Athlete();
        }
        return athletes;
    }
}

