import java.util.concurrent.ThreadLocalRandom;


/**
 * DANIEL ........
 *
 * @author Daniel Smith
 */
public class RandomEvent {

    public GameManager gameManager;
    public Athlete athlete;

    public RandomEvent() {
        randomEvent();
    }

    public int chance = ThreadLocalRandom.current().nextInt(0, 15);
    public void randomEvent() {

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

    public int athleteIndex = ThreadLocalRandom.current().nextInt(0, GameManager.team.numActive());
    public int statIndex = ThreadLocalRandom.current().nextInt(0, 3);
    public int statIncrease = ThreadLocalRandom.current().nextInt(10, 25);
    public void randomStatIncrease() {

        if (statIndex == 0) {
            GameManager.team.getActive(athleteIndex).stamina += statIncrease + 10;
        } else if (statIndex == 1) {
            GameManager.team.getActive(athleteIndex).offence += statIncrease;
        } else if (statIndex == 2) {
            GameManager.team.getActive(athleteIndex).defence += statIncrease;
        }
        System.out.println("Random Event: " + GameManager.team.getActive(athleteIndex).getName() + "'s " + " increased by " + statIncrease + " points");
    }

    public void randomNewAthlete() {
        if (GameManager.team.numActive() + GameManager.team.numReserves() >= PlayerTeam.MAXIMUM_SIZE){
            System.out.println("Random Event: An Athlete tried to join your team but its full");
            return;
        }
        athlete = new Athlete();
        try {
            GameManager.team.addAthlete(athlete, false);
        } catch (Exception e) {
            GameManager.team.addAthlete(athlete, true);
        }
    }

    public void randomQuitAthlete() {
        if (GameManager.team.numActive() == 0){
            System.out.println("Random Event: An Athlete tried to quit your team but you have no active athletes");
            return;
        }
        if (GameManager.team.numActive() + GameManager.team.numReserves() < 5 && GameManager.getBankBalance() < 1000){
            System.out.println("Random Event: An Athlete quit your team but you have less than 5 athletes and no money!!!");
            //gameManager.gameOver(); //Gameover call (Not implemented yet)
            return;
        }

        GameManager.team.removeAthlete(athlete);
    }
}