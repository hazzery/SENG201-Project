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
public class AthleteRandom {

    /** 
     * Creates a random Athlete when called upon
     * 
     * @return The Newly Created Athlete
     */
    public static Athlete createRandomAthlete(){
        Random rand = new Random();
        String name = "Athlete" + rand.nextInt(100);
        int stamina = rand.nextInt(100);
        int offence = rand.nextInt(100);
        int defence = rand.nextInt(100);
        int contractPrice = rand.nextInt(100);
        int sellBackPrice = rand.nextInt(100);
        Athlete athlete = new Athlete(name, stamina, offence, defence, contractPrice, sellBackPrice);
        return athlete;
    }
  
}

