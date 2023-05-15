/**
 * Class that models an athlete
 */

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;


public class Athlete implements Purchasable {
    private final String name;
    private String nickName;
    private int stamina;
    private int offence;
    private int defence;
    private int current_health;

    static private boolean nameScannerIsInitialised = false;
    static private Scanner nameScanner;
    static private Stack<String> athleteNames;


    /**
	 * Represents the stats of an {@link Athlete}
	 */
    public enum StatType {
        STAMINA,
        OFFENCE,
        DEFENCE,
        CURRENT_HEALTH
    }

    /**
     * Creates an athlete with randomised stats
     */
    public Athlete() {
        initAthleteNameReader();

        this.name = athleteNames.pop();
        this.nickName = this.name;
        this.stamina = ThreadLocalRandom.current().nextInt(0, 101);
        this.offence = ThreadLocalRandom.current().nextInt(0, 101);
        this.defence = ThreadLocalRandom.current().nextInt(0, 101);
        this.current_health = 100;
    }

    private void initAthleteNameReader() {
        if (nameScannerIsInitialised) return;

        athleteNames = new Stack<String>();
        try {
            File myObj = new File("Resources/AthleteNames.txt");
            nameScanner = new Scanner(myObj);
            while (nameScanner.hasNextLine()) {
                athleteNames.push(nameScanner.nextLine());
            }
            nameScanner.close();
            nameScannerIsInitialised = true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
	 * Creates an athlete with the given stats
	 *
	 * @param name A descriptive name for the Athlete
     * @param stamina The athlete's stamina value
     * @param offence The athlete's offence value
     * @param defence The athlete's defence value
	 */
    public Athlete(String name, int stamina, int offence, int defence) {
        this.name = name;
        this.nickName = name;
        this.stamina = stamina;
        this.offence = offence;
        this.defence = defence;
        this.current_health = 100;
    }

    /**
	 * Gets the name of the athlete
	 *
	 * @return The athlete's descriptive name
	 */
    public String getName() {
        return this.name;
    }

    /**
	 * Gets the athlete's nickname
	 *
	 * @return The athlete's nickname
	 */
    public String getNickname() {
        return this.nickName;
    }

    /**
	 * Gets the athlete's stamina stat
	 *
	 * @return The value of the athlete's stamina stat
	 */
    public int getStamina() {
        return this.stamina;
    }

    /**
	 * Gets the athlete's offence stat
	 *
	 * @return The value of the athlete's offence stat
	 */
    public int getOffence() {
        return this.offence;
    }

    /**
	 * Gets the athlete's defence stat
	 *
	 * @return The value of the athlete's defence stat
	 */
    public int getDefence() {
        return this.defence;
    }

    /**
	 * Gets the current health of the athlete
	 *
	 * @return The current health of the athlete
	 */
    public int getCurrentHealth() {
        return this.current_health;
    }

    /**
	 * Gets the contract sale price of the athlete
     * The contract price is the amount the athlete originally costs to purchase
	 *
	 * @return The contract price of the athlete
	 */
    public int getContractPrice() {
        return (stamina + offence + defence) * 10;
    }

    /**
	 * Gets the sell back price of the athlete
	 * The sell back price is the amount the user can get back for selling an athlete they already own
     *
	 * @return The sell back price of the athlete
	 */
    public int getSellBackPrice() {
        return (stamina + offence + defence) * 3 + 100;
    }

    /**
	 * Generates a string description of the athlete
	 *
	 * @return A short description of the athlete
	 */
    public String getDescription() {
        return "Stamina: " + this.stamina + ",  Offence: " + this.offence + ",  Defence: " + this.defence;
    }

    /**
	 * Sets a new nickname for the athlete
     *
     * @param nickName The new nickname for the athlete
	 */
    public void setNickname(String nickName) {
        this.nickName = nickName;
    }

    /**
     * Applies an item to the athlete,
     * increasing the athlete's stats by the amount specified by the item
     * @param item The item to apply to the athlete
     */
    public void applyItem(Item item) {
        switch (item.getStatType()) {
            case STAMINA -> this.stamina += item.getImprovementAmount();
            case OFFENCE -> this.offence += item.getImprovementAmount();
            case DEFENCE -> this.defence += item.getImprovementAmount();
            case CURRENT_HEALTH -> this.current_health += item.getImprovementAmount();
        }
    }

    public String toString() {
        return this.nickName;
    }
}
