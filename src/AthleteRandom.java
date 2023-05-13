/**
 * Random Athlete Class
 * 
 */
public class AthleteRandom{

    /** 
     * Creates a random Athlete when called upon
     * 
     * @param min The minimum value
     * @param max The maximum value
     * @return The Newly Created Athlete
     */
    public static Athlete createRandomAthlete(int min, int max){
        RandomInt rand = new RandomInt();
        String name = "Athlete" + rand.createRandomInt(min, max);
        int stamina = rand.createRandomInt(min, max);
        int offence = rand.createRandomInt(min, max);
        int defence = rand.createRandomInt(min, max);

        PriceCalculator price = new PriceCalculator(stamina, offence, defence);
        int contractPrice = price.createAthleteContractPrice();
        int sellBackPrice = price.createAthleteSellBackPrice();
        Athlete athlete = new Athlete(name, stamina, offence, defence, contractPrice, sellBackPrice);
        return athlete;
    }

    
  
}

