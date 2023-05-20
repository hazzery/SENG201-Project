import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class that models an opposition team
 */
public class OppositionTeam extends Team {

    private boolean nameScannerIsInitialised = false;
    private Stack<String> oppositionNames;

    public OppositionTeam() {
        this("Unnamed team");
        this.initOppositionNameReader();
        this.name = oppositionNames.pop();
    }

    public OppositionTeam(String name) {
        super(name);
        for (int i = 0; i < TEAM_SIZE; i++) {
            Athlete athlete = new Athlete();

            //Crude State increase based on difficulty
            athlete.stamina += 10 + GameManager.currentWeek() * GameManager.isGameHard();
            athlete.offence += 10 + GameManager.currentWeek() * GameManager.isGameHard();
            athlete.defence += 10 + GameManager.currentWeek() * GameManager.isGameHard();
            //Daniel this almost made me cry why did you make all the variables public

            actives.add(athlete);
        }
    }

    /**
     * @return
     */
    @Override
    public int size() {
        return actives.size();
    }

    /**
     * @return 
     */
    @Override
    public Athlete[] getAthletes() {
        return actives.toArray(new Athlete[0]);
    }

    /**
     * Collects a name from the OppositionNames.txt file and returns it
     *
     */
    private void initOppositionNameReader() {
        if (nameScannerIsInitialised) return;

        oppositionNames = new Stack<String>();
        try {
            File myObj = new File("Resources/OppositionNames.txt");
            Scanner nameScanner = new Scanner(myObj);
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

    public static OppositionTeam[] generateOppositions(int number) {
        OppositionTeam[] teams = new OppositionTeam[number];
        for (int i = 0; i < number; i++) {
            teams[i] = new OppositionTeam();
        }
        return teams;
    }
}
