/**
 * Class that models an athlete
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;


public class Athlete implements Purchasable {
    private final String name;
    private String nickName;
    public int stamina;
    public int offence;
    public int defence;
    public int current_health;

    static private boolean nameScannerIsInitialised = false;
    static private Scanner nameScanner;
    static private Stack<String> athleteNames;

    ArrayList<PurchasablePanel> purchasablePanels = new ArrayList<PurchasablePanel>();

    /**
	 * Represents the stats of an {@link Athlete}
	 */
    public enum StatType {
        STAMINA,
        OFFENCE,
        DEFENCE,
        CURRENT_HEALTH
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
    @Override
    public String getName() {
        return this.nickName;
    }

    @Override
    public Map<String, String> getStats() {
        return Map.of(
            "Stamina", String.valueOf(this.stamina),
            "Offence", String.valueOf(this.offence),
            "Defence", String.valueOf(this.defence)
        );
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
    @Override
    public int getContractPrice() {
        return (stamina + offence + defence) * 10;
    }

    /**
	 * Gets the sell back price of the athlete
	 * The sell back price is the amount the user can get back for selling an athlete they already own
     *
	 * @return The sell back price of the athlete
	 */
    @Override
    public int getSellBackPrice() {
        return (stamina + offence + defence) * 3 + 100;
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
        String newStatValue;

        switch (item.getStatType()) {
            case STAMINA -> {
                this.stamina += item.getImprovementAmount();
                newStatValue = String.valueOf(this.stamina);
            }
            case OFFENCE ->  {
                this.offence += item.getImprovementAmount();
                newStatValue = String.valueOf(this.offence);
            }
            case DEFENCE ->  {
                this.defence += item.getImprovementAmount();
                newStatValue = String.valueOf(this.defence);
            }
            case CURRENT_HEALTH ->  {
                this.current_health += item.getImprovementAmount();
                newStatValue = String.valueOf(this.current_health);
            }
            default -> throw new IllegalStateException("Unexpected value: " + item.getStatType());
        }

        for (PurchasablePanel panel : purchasablePanels) {
            panel.update(item.getStatType().name().toLowerCase(), newStatValue);
        }
    }

    public void registerPanel(PurchasablePanel panel) {
        purchasablePanels.add(panel);
    }

    public String toString() {
        return this.nickName;
    }
}
