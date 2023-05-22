package data;
import java.util.concurrent.ThreadLocalRandom;
import utility.NameFileReader;
import gui.PurchasablePanel;
import java.util.*;


/**
 * Item is the container class that holds all information relevant to the in-game items
 */
public class Item implements Purchasable {

    private final String name;
    private final Athlete.StatType statType;
    private final int improvementAmount;
    private static NameFileReader nameReader = new NameFileReader("Resources/ItemNames.txt");

    /**
     * Creates am item with randomised stats
     */
    public Item() {
        this.name = nameReader.next();
        this.statType = Athlete.StatType.values()[ThreadLocalRandom.current().nextInt(0, 3)];
        this.improvementAmount = ThreadLocalRandom.current().nextInt(1, 101);
    }

    /**
	 * Creates an item with the given stats
	 *
     * @param statType The stat in which consuming this item improves
     * @param improvementAmount The amount in which consuming this item improves the stat
	 */
    public Item(String name, Athlete.StatType statType, int improvementAmount) {
        this.name = name;
        this.statType = statType;
        this.improvementAmount = improvementAmount;
    }

    /**
     * Gets the name of the item
     *
     * @return The name of the item
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the stat type of the item
     *
     * @return The stat type of the item
     */
    public Athlete.StatType getStatType() {
        return this.statType;
    }

    /**
     * Gets the improvement amount of the item
     *
     * @return The improvement amount of the item
     */
    public int getImprovementAmount() {
        return this.improvementAmount;
    }

    /**
     * Gets the contract sale price of the athlete
     * The contract price is the amount the athlete originally costs to purchase
     *
     * @return The contract price of the athlete
     */
    @Override
    public int getContractPrice() {
        return improvementAmount * 5 + 175;
    }

    /**
     * Gets a dictionary mapping the athlete stat type which it boosts, to the amount it increases the stat
     * @return A map which is used to display information on a {@link PurchasablePanel}
     */
    @Override
    public Map<String, String> getStats() {
        return Map.of(statType.name(), String.valueOf(improvementAmount));
    }

    /**
     * Gets the sell back price of the athlete
     * The sell back price is the amount the user can get back for selling an athlete they already own
     *
     * @return The sell back price of the athlete
     */
    @Override
    public int getSellBackPrice() {
        return improvementAmount * 2 + 150;
    }

    /**
     * Gives the item's name when displaying it as a string
     * @return The item's name
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
