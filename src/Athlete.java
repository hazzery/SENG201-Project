

/**
 * Class that models an Athlete
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
	 * Represents the Stats of a {@link Athlete} 
	 */
    public enum StatType {
        STAMINA,
        OFFENCE,
        DEFENCE,
        CURRENT_HEALTH
    }


    /**
	 * Creates an Athletes Class with the given stats.
	 *
	 * @param name The name for the Athlete
     * @param nickName The nickname for the Athlete
     * @param stamina The stamina for the Athlete
     * @param offence The offence for the Athlete
     * @param defence The defence for the Athlete
     * @param contractPrice The contract price for the Athlete
     * @param sellBackPrice The sell back price for the Athlete
     * 
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
	 * Gets the Name of Athlete.
	 *
	 * @return The Name of the Athlete.
	 */
    public String getName() {
        return this.name;
    }

    /**
	 * Gets the Nickname of Athlete.
	 *
	 * @return The Nickname of the Athlete.
	 */
    public String getNickname() {
        return this.nickName;
    }

    /**
	 * Gets the Stamina of Athlete.
	 *
	 * @return The Stamina of the Athlete.
	 */
    public int getStamina() {
        return this.stamina;
    }

    /**
	 * Gets the Offence of Athlete.
	 *
	 * @return The Offence of the Athlete.
	 */
    public int getOffence() {
        return this.offence;
    }

    /**
	 * Gets the Defense of Athlete.
	 *
	 * @return The Defense of the Athlete.
	 */
    public int getDefence() {
        return this.defence;
    }

    /**
	 * Gets the Current Health of Athlete.
	 *
	 * @return The Current Health of the Athlete.
	 */
    public int getCurrentHealth() {
        return this.current_health;
    }

    /**
	 * Gets the ContractPrice of Athlete.
	 *
	 * @return The ContractPrice of the Athlete.
	 */
    public int getContractPrice() {
        return this.contractPrice;
    }

    /**
	 * Gets the SellBackPrice of Athlete.
	 *
	 * @return The SellBackPrice of the Athlete.
	 */
    public int getSellBackPrice() {
        return this.sellBackPrice;
    }


    /**
	 * Gets the Description of Athlete.
	 *
	 * @return The Description of the Athlete.
	 */
    public String getDescription() {
        return "Stamina: " + this.stamina + "  Offence: " + this.offence + "  Defence: " + this.defence;
    }


    /**
	 * Gets the Name of Athlete.
	 *
	 * @return The Name of the Athlete.
	 */
    public void setNickname(String nickName) {
        this.nickName = nickName;
    }
}
