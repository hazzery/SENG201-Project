/**
 * Class that models an item
 */
public class Item implements Purchasable {

    private String name;
    private Athlete.StatType statType;
    private int improvementAmount;
    private int contractPrice;
    private int sellBackPrice;


    /**
	 * Creates an item with the given information
	 *
	 * @param contractPrice The contract price for the item
     * @param sellBackPrice The sell back price for the item
     * @param statType The stat in which consuming this item improves
     * @param improvementAmount The amount in which consuming this item improves the stat
	 */
    public Item(String name, Athlete.StatType statType, int improvementAmount, int contractPrice, int sellBackPrice) {
        this.name = name;
        this.statType = statType;
        this.improvementAmount = improvementAmount;
        this.contractPrice = contractPrice;
        this.sellBackPrice = sellBackPrice;
    }

    /**
     * Gets the name of the item
     *
     * @return The name of the item
     */
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
     * Generates a string description of the item
     *
     * @return A short description of the item
     */
    public String getDescription() {
        return "Improves " + this.statType + " by " + this.improvementAmount;
    }

    /**
     * Gets the contract sale price of the athlete
     * The contract price is the amount the athlete originally costs to purchase
     *
     * @return The contract price of the athlete
     */
    public int getContractPrice() {
        return this.contractPrice;
    }

    /**
     * Gets the sell back price of the athlete
     * The sell back price is the amount the user can get back for selling an athlete they already own
     *
     * @return The sell back price of the athlete
     */
    public int getSellBackPrice() {
        return this.sellBackPrice;
    }
}
