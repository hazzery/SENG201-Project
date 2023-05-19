import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class that represents a Random Event
 */
public class RandomEvent {
    public int chance;

    public GameManager GameManager;
    public static Athlete athlete;

    static ArrayList<Athlete> athleteList;


    public RandomEvent(ArrayList<Athlete> athleteList) {
        RandomEvent.athleteList = athleteList;
        randomEvent();
    }


    public static void randomEvent() {
        int chance = ThreadLocalRandom.current().nextInt(0, 15);
        if (chance == 3) {
            randomStatIncrease();
        } else if (chance == 10) {
            randomNewAthlete();
        } else if (chance == 14) {
            randomQuitAthlete();
        }
    }

    public static void randomStatIncrease() {
        int athleteIndex = ThreadLocalRandom.current().nextInt(0, athleteList.size());
        int statIndex = ThreadLocalRandom.current().nextInt(0, 3);
        int statIncrease = ThreadLocalRandom.current().nextInt(1, 5);
        if (statIndex == 0) {
            athleteList.get(athleteIndex).stamina += statIncrease;
        } else if (statIndex == 1) {
            athleteList.get(athleteIndex).offence += statIncrease;
        } else if (statIndex == 2) {
            athleteList.get(athleteIndex).defence += statIncrease;
        }
        
    }

    public static void randomNewAthlete() {
        athlete = new Athlete();
        athleteList.add(athlete);
    }

    public static void randomQuitAthlete() {
        athleteList.remove(athlete);
    }
}