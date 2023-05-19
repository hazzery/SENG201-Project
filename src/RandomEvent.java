import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A class that represents a Random Event
 */
public class RandomEvent {
    public int chance;

    public GameManager GameManager;
    public static Athlete athlete;

    public static Team team;

    public RandomEvent() {
        randomEvent();
    }


    public static void randomEvent() {
        int chance = ThreadLocalRandom.current().nextInt(0, 15);
        if (chance < 10) {
            randomStatIncrease();
        } else if (chance >= 10 && chance <= 13) {
            randomQuitAthlete();
        } else if (chance == 14) {
            randomNewAthlete();
        } else{
            System.out.println("Random Event: Nothing Happened");
        }
    }

    public static void randomStatIncrease() {
        int athleteIndex = ThreadLocalRandom.current().nextInt(0, team.numActive());
        int statIndex = ThreadLocalRandom.current().nextInt(0, 3);
        int statIncrease = ThreadLocalRandom.current().nextInt(1, 5);
        if (statIndex == 0) {
            team.getActive(athleteIndex).stamina += statIncrease;
        } else if (statIndex == 1) {
            team.getActive(athleteIndex).offence += statIncrease;
        } else if (statIndex == 2) {
            team.getActive(athleteIndex).defence += statIncrease;
        }
        System.out.println("Random Event: " + team.getActive(athleteIndex).getName() + "'s " + " increased by " + statIncrease + " points");
    }

    public static void randomNewAthlete() {
        athlete = new Athlete();
        try {
            team.addAthlete(athlete, false);
        } catch (Exception e) {
            team.addAthlete(athlete, true);
        }
    }

    public static void randomQuitAthlete() {
        team.removeAthlete(athlete);
    }
}