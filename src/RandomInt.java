import java.util.Random;

public class RandomInt {
    /**
     * Creates a random integer between the min and max values
     * 
     * @param min The minimum value
     * @param max The maximum value
     * @return The random integer
     */
    public int createRandomInt(int min, int max){
        Random rand = new Random();
        int randomInt = rand.nextInt((max - min) + 1) + min;
        return randomInt;
    }
}
