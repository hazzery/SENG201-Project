import java.util.concurrent.ThreadLocalRandom;

/**
 * A class that represents a Random Event
 */
public class RandomEvent {
    public int chance;

    public GameManager gameManager;
    public Athlete athlete;

    public Team team;

    public RandomEvent() {
        randomEvent();
    }

    public void randomEvent() {
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

    public void randomStatIncrease() {
        int athleteIndex = ThreadLocalRandom.current().nextInt(0, team.numActive());
        int statIndex = ThreadLocalRandom.current().nextInt(0, 3);
        int statIncrease = ThreadLocalRandom.current().nextInt(10, 25);
        if (statIndex == 0) {
            team.getActive(athleteIndex).stamina += statIncrease + 10;
        } else if (statIndex == 1) {
            team.getActive(athleteIndex).offence += statIncrease;
        } else if (statIndex == 2) {
            team.getActive(athleteIndex).defence += statIncrease;
        }
        System.out.println("Random Event: " + team.getActive(athleteIndex).getName() + "'s " + " increased by " + statIncrease + " points");
    }

    public void randomNewAthlete() {
        if (team.numActive() + team.numReserves() >= team.MAXIMUM_SIZE){
            System.out.println("Random Event: An Athlete tried to join your team but its full");
            return;
        }
        athlete = new Athlete();
        try {
            team.addAthlete(athlete, false);
        } catch (Exception e) {
            team.addAthlete(athlete, true);
        }
    }

    public void randomQuitAthlete() {
        if (team.numActive() == 0){
            System.out.println("Random Event: An Athlete tried to quit your team but you have no active athletes");
            return;
        }
        if (team.numActive() + team.numReserves() < 5 && GameManager.getBankBalance() < 1000){
            System.out.println("Random Event: An Athlete quit your team but you have less than 5 athletes and no money!!!");
            //gameManager.gameOver(); //Gameover call (Not implemented yet)
            return;
        }

        team.removeAthlete(athlete);
    }
}