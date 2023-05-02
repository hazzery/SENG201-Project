
/**
 * Class that models an Item
 */
public class Item implements Purchasable {

    private Athlete.StatType statType;
    private int improvementAmount;
    private int contractPrice;
    private int sellBackPrice;
    private String description;

    /**
	 * Creates an Item Class with the given infomation.
	 *
	 * @param contractPrice The contract price for the Item
     * @param sellBackPrice The sell back price for the Item
     * @param description The description for the Item
     * @param statType The stat type for the Item
     * @param improvementAmount The improvement amount for the Item
     * 
	 */
    public Item(int contractPrice, int sellBackPrice, String description, Athlete.StatType statType, int improvementAmount) {
        this.contractPrice = contractPrice;
        this.sellBackPrice = sellBackPrice;
        this.description = description;
        this.statType = statType;
        this.improvementAmount = improvementAmount;
    }

    /**
	 * Gets the Contract Price of Item.
	 *
	 * @return The Contract Price of Item.
	 */
    public int getContractPrice() {
        return this.contractPrice;
    }

    /**
	 * Gets the Stat Type of Item.
	 *
	 * @return The Stat Type of Item.
	 */
    public int getSellBackPrice() {
        return this.sellBackPrice;
    }

    /**
	 * Gets the Stat Type of Item.
	 *
	 * @return The Stat Type of Item.
     */
        public String getDescription() {
        return this.description;
    }
}
