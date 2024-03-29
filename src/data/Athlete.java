package data;
import java.util.concurrent.ThreadLocalRandom;
import utility.NameFileReader;
import gui.DisplayPanel;
import java.util.ArrayList;
import java.util.Map;


/**
 * Athlete is the container class that holds all of an athlete's stats and other relevant information.
 *
 * @author Harrison Parkes
 * @author Daniel Smith
 */
public class Athlete implements Purchasable {
    private final String name;
    private String nickName;
    private int stamina;
    private int offence;
    private int defence;
    private int current_health;
    private boolean isInjured = false;

    static private final NameFileReader nameReader = new NameFileReader("Resources/AthleteNames.txt");

    final ArrayList<DisplayPanel> displayPanels = new ArrayList<>();

        public boolean getInjury() {
                return this.isInjured;
        }

        /**
	 * Represents the stats of an athlete
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
        this.name = nameReader.next();
        this.nickName = this.name;
        this.stamina = ThreadLocalRandom.current().nextInt(80, 101);
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
        this.stamina = Math.max(stamina, 80);
        this.offence = Math.max(offence, 1);
        this.defence = Math.max(defence, 1);
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

    public int getStat(StatType stat) {
        return switch (stat) {
            case STAMINA -> this.stamina;
            case OFFENCE -> this.offence;
            case DEFENCE -> this.defence;
            case CURRENT_HEALTH -> this.current_health;
        };
    }

    /**
     * Gets a dictionary mapping each of the athletes stat value names to their value
     * @return A map of each athlete stat value name and its value
     */
    @Override
    public Map<String, String> getStats() {
        return Map.of(
            "Stamina", String.valueOf(this.stamina),
            "Offence", String.valueOf(this.offence),
            "Defence", String.valueOf(this.defence),
            "Current Health", String.valueOf(this.current_health)
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
     * Improves the specified stat by the specified amount
     *
     * @param statType The stat type to improve
     * @param improvementAmount The amount to improve the stat by
     *
     * @throws IllegalStateException if the statType is not one of the above
     */
    public void improveStat(StatType statType, int improvementAmount) {
        switch (statType) {
            case STAMINA -> this.stamina += improvementAmount;
            case OFFENCE -> this.offence += improvementAmount;
            case DEFENCE -> this.defence += improvementAmount;
            case CURRENT_HEALTH -> this.current_health += improvementAmount;

            default -> throw new IllegalStateException("Unexpected value: " + statType);
        }

        for (DisplayPanel panel : displayPanels)
            panel.update(statType.toString(), String.valueOf(getStat(statType)));
    }

    /**
     * Applies an item to the athlete,
     * increasing the athlete's stats by the amount specified by the item
     * @param item The item to apply to the athlete
     */
    public void applyItem(Item item) {
        improveStat(item.getStatType(), item.getImprovementAmount());
    }

    /**
     * Registers a purchasable panel as a listener for when the athlete's stats change
     * @param panel The panel to register as a listener
     */
    public void registerPanel(DisplayPanel panel) {
        displayPanels.add(panel);
    }

    /**
     * Generates a string representation of the athlete
     * @return A string representation of the athlete
     */
    public String toString() {
        return this.nickName;
    }

    /**
     * Resets the athletes' health/stamina to full and removes any injuries
     */
    public void byeWeek() {
        this.current_health = 100;
        this.stamina = 100;
        this.isInjured = false;
    }

    /**
     * Improves the athlete's offence and defence values by 25
     */
    public void trainAthlete() {
        this.offence += 25;
        this.defence += 25;
        System.out.println("Trained: " + this.name );
    }

    /**
     * Injures the athlete, setting their health to 0
     */
    public void injure() {
        this.isInjured = true;
        this.current_health = 0;
        this.stamina = 0;
    }
}
