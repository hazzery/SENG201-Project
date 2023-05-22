import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;


/**
 * Turn action statments is a class that holds all the statments that are used when a turn action is performed
 * This is to allow for easy presentation of the statments
 *
 * @author Daniel Smith
 */
public class TurnActionStatments{

    /**
     * Instantiates a new Turn action statments and allows the statments to be read from the files
     * 
     */
    public TurnActionStatments(){
        initAttackNameReader();
        initDefeatNameReader();
        initHealNameReader();
    }


    static private boolean attackScannerIsInitialised = false;
    static private Scanner attackScanner;
    static private Stack<String> attackNames;
    

    private void initAttackNameReader() {
        if (attackScannerIsInitialised) return;

        attackNames = new Stack<String>();
        try {
            File myObj = new File("Resources/SkierAttackOptions.txt");
            attackScanner = new Scanner(myObj);
            while (attackScanner.hasNextLine()) {
                attackNames.push(attackScanner.nextLine());
            }
            attackScanner.close();
            attackScannerIsInitialised = true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gets the attack statment.
     * @return the attack statment 
     */
    public String getAttackName() {
        return attackNames.pop();
    }

    static private boolean defeatScannerIsInitialised = false;
    static private Scanner defeatScanner;
    static private Stack<String> defeatNames;

    private void initDefeatNameReader() {
        if (defeatScannerIsInitialised) return;

        defeatNames = new Stack<String>();
        try {
            File myObj = new File("Resources/SkierDefeatOptions.txt");
            defeatScanner = new Scanner(myObj);
            while (defeatScanner.hasNextLine()) {
                defeatNames.push(defeatScanner.nextLine());
            }
            defeatScanner.close();
            defeatScannerIsInitialised = true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gets the defeat statment.
     * @return the defeat statment 
     */
    public String getDefeatName() {
        return defeatNames.pop();
    }

    
    static private boolean healScannerIsInitialised = false;
    static private Scanner healScanner;
    static private Stack<String> healNames;

    
    private void initHealNameReader() {
        if (healScannerIsInitialised) return;

        healNames = new Stack<String>();
        try {
            File myObj = new File("Resources/SkierHealOptions.txt");
            healScanner = new Scanner(myObj);
            while (healScanner.hasNextLine()) {
                healNames.push(healScanner.nextLine());
            }
            healScanner.close();
            healScannerIsInitialised = true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Gets the heal statment.
     * @return the heal statment
     */
    public String getHealName() {
        return healNames.pop();
    }
}