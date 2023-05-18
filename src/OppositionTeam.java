import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class the models a Opposition team
 */
public class OppositionTeam {

    private static int numberOfAthletes = 4;

    
    static ArrayList<Athlete> oppositionAthletes = new ArrayList<Athlete>(numberOfAthletes);
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
    public static ArrayList<Athlete> createTeam() {
        for (int i = 0; i < numberOfAthletes; i++) {
            oppositionAthletes.add(new Athlete());
        }
        return oppositionAthletes;
    }

    public static String size() {
        return oppositionAthletes.size() + "";
    }

    public String getOppositionAthletes() {
        return null;
    }

}

