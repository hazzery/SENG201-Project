/**
 * PriceCalculator
 * 
 * for calculating the prices of an athlete
 */


public class PriceCalculator {

    private int stamina;
    private int offence;
    private int defence;

    /**
     * Creates a PriceCalculator with the given information
     * @param stamina
     * @param offence
     * @param defence
     */
    public PriceCalculator(int stamina, int offence, int defence){
        this.stamina = stamina;
        this.offence = offence;
        this.defence = defence;
    }

    /**
     * Creates a contract price for the athlete
     * @return The contract price
     */
    public int createAthleteContractPrice(){
        return (stamina + offence + defence) * 10;
    }

    /**
     * Creates a sell back price for the athlete
     * @return The sell back price
     */
    public int createAthleteSellBackPrice(){
        return (stamina + offence + defence) * 3 + 100;
    }



}
