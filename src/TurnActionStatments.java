import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class TurnActionStatments{
    static private boolean attackScannerIsInitialised = false;
    static private Scanner attackScanner;
    static private Stack<String> attackNames;
    

    private void initAttackNameReader() {
        if (attackScannerIsInitialised) return;

        attackNames = new Stack<String>();
        try {
            File myObj = new File("Resources/AthleteNames.txt");
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

    static private boolean defeatScannerIsInitialised = false;
    static private Scanner defeatScanner;
    static private Stack<String> defeatNames;

    private void initDefeatNameReader() {
        if (defeatScannerIsInitialised) return;

        defeatNames = new Stack<String>();
        try {
            File myObj = new File("Resources/AthleteNames.txt");
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

    static private boolean healScannerIsInitialised = false;
    static private Scanner healScanner;
    static private Stack<String> healNames;


    private void initHealNameReader() {
        if (healScannerIsInitialised) return;

        healNames = new Stack<String>();
        try {
            File myObj = new File("Resources/AthleteNames.txt");
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
}