public class Athlete {
    private String name;
    private int stamina;
    private int offence;
    private int defence;
    private int current_health;


    public static enum StatType {
        STAMINA,
        OFFENCE,
        DEFENCE,
        CURRENT_HEALTH
    }


    public Athlete(String name, int stamina, int offence, int defence, int current_health) {
        this.name = name;
        this.stamina = stamina;
        this.offence = offence;
        this.defence = defence;
        this.current_health = current_health;
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


}
