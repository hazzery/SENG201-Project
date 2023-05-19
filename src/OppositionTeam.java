import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Class the models a Opposition team
 */
public class OppositionTeam {

    private int numberOfAthletes = 5;

    private boolean nameScannerIsInitialised = false;
    private Scanner nameScanner;
    private Stack<String> oppositionNames;

    private String name;
    
    
    /**
     * Returns the name of the Opposition Team 
     * Done by collecting a name from {@link OppositionNames.txt} through the {@link initOppositionNameReader()} method
     * 
     * @return the name of the Opposition Team
     */
    public String getName() {
        this.initOppositionNameReader();
        this.name = oppositionNames.pop();
        return name;
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
    public ArrayList<Athlete> createOppTeam() {
        ArrayList<Athlete> oppositionAthletes = new ArrayList<Athlete>();
        for (int i = 0; i < numberOfAthletes; i++) {
            oppositionAthletes.add(new Athlete());
            //Crude State increase based on difficulty
            oppositionAthletes.get(i).stamina += 10 + GameManager.currentWeek() * GameManager.isGameHard();
            oppositionAthletes.get(i).offence += 10 + GameManager.currentWeek() * GameManager.isGameHard();
            oppositionAthletes.get(i).defence += 10 + GameManager.currentWeek() * GameManager.isGameHard();
        }
        return oppositionAthletes;
    }

    public ArrayList<ArrayList<Athlete>> generateOpposition(){
        ArrayList<ArrayList<Athlete>> teams = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ArrayList<Athlete> team = createOppTeam();
            teams.add(team);
        }
        return teams;
    }
}

