/**
 * Class that models an athlete
 */
public class Athlete implements Purchasable {
    private String name;
    private String nickName;
    private int stamina;
    private int offence;
    private int defence;
    private int current_health;
    private int contractPrice;
    private int sellBackPrice;


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
	 * Creates an athlete with the given stats
	 *
	 * @param name A descriptive name for the Athlete
     * @param stamina The athlete's stamina value
     * @param offence The athlete's offence value
     * @param defence The athlete's defence value
     * @param contractPrice The contract price for the athlete
     * @param sellBackPrice The sell back price for the athlete
	 */
    public Athlete(String name, int stamina, int offence, int defence, int contractPrice, int sellBackPrice) {
        this.name = name;
        this.nickName = name;
        this.stamina = stamina;
        this.offence = offence;
        this.defence = defence;
        this.current_health = 100;
        this.contractPrice = contractPrice;
        this.sellBackPrice = sellBackPrice;
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
        return this.contractPrice;
    }

    /**
	 * Gets the sell back price of  the athlete
	 * The sell back price is the amount the user can get back for selling an athlete they already own
     *
	 * @return The sell back price of the athlete
	 */
    public int getSellBackPrice() {
        return this.sellBackPrice;
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
