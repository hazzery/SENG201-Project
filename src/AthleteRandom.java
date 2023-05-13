/**
 * Creates a random Athlete when called upon
 * 
 * Creates random stats to add to the Athlete
 * 
 * RANDOM NAME GEN IN PROCESS
 * 
 */
import java.util.Random;


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
        String name = "Athlete" + createRandomInt(min, max);
        int stamina = createRandomInt(min, max);
        int offence = createRandomInt(min, max);
        int defence = createRandomInt(min, max);

        PriceCalculator price = new PriceCalculator(stamina, offence, defence);
        int contractPrice = price.createAthleteContractPrice();
        int sellBackPrice = price.createAthleteSellBackPrice();
        Athlete athlete = new Athlete(name, stamina, offence, defence, contractPrice, sellBackPrice);
        return athlete;
    }

    /**
     * Creates a random integer between the min and max values
     * 
     * @param min The minimum value
     * @param max The maximum value
     * @return The random integer
     */
    public static int createRandomInt(int min, int max){
        Random rand = new Random();
        int randomInt = rand.nextInt((max - min) + 1) + min;
        return randomInt;
    }
  
}

