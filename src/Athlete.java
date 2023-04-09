public class Athlete implements Purchasable {
    private String name;
    private int stamina;
    private int offence;
    private int defence;
    private int current_health;
    private int contractPrice;
    private int sellBackPrice;


    public enum StatType {
        STAMINA,
        OFFENCE,
        DEFENCE,
        CURRENT_HEALTH
    }


    public Athlete(String name, int stamina, int offence, int defence, int current_health, int contractPrice, int sellBackPrice) {
        this.name = name;
        this.stamina = stamina;
        this.offence = offence;
        this.defence = defence;
        this.current_health = current_health;
        this.contractPrice = contractPrice;
        this.sellBackPrice = sellBackPrice;
    }

    public String getName() {
        return this.name;
    }

    public int getStamina() {
        return this.stamina;
    }

    public int getOffence() {
        return this.offence;
    }

    public int getDefence() {
        return this.defence;
    }

    public int getCurrentHealth() {
        return this.current_health;
    }

    public int getContractPrice() {
        return this.contractPrice;
    }

    public int getSellBackPrice() {
        return this.sellBackPrice;
    }

    public String getDescription() {
        return "Stamina: " + this.stamina + "  Offence: " + this.offence + "  Defence: " + this.defence;
    }
}
