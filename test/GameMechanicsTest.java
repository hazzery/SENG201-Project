import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class GameMechanicsTest {

    @Test
    void constructor() {
        GameManager.initializeAthletes();
        GameManager.initializeItems();
        WindowManager.initializeMainWindow();
        GameManager.setConfiguration("DanielsTeam", 12, false);
        GameManager.startGame(GameManager.athletes, 10000);

        OppositionTeam oppositionTeam = new OppositionTeam();
        GameManager.oppositionTeam = oppositionTeam;
        Athlete[] oppositionAthletes = oppositionTeam.getAthletes();
        GameMechanics gameMechanics = new GameMechanics();
        gameMechanics.playGame(1, GameManager.athletes, oppositionAthletes);
    }

    @Test
    void playGameTest(){
        assertEquals(0, GameMechanics.athIndex);
        assertEquals(0, GameMechanics.oppIndex);
        assertEquals(false, GameMechanics.isGameOver);
        assertEquals(1, GameMechanics.currentRound);
    }

    @Test
    void afterMatchRewardTest() {
        assertEquals(10 * GameMechanics.getOppDiff() * GameManager.isGameHard() * (0.15 * GameMechanics.currentRound), GameMechanics.afterMatchReward());
    }

    @Test
    void getOppDiff() {
        int oppositionDiff = 0;
        for (Athlete oppositionAthlete : GameMechanics.oppositionAthletes)
            oppositionDiff += oppositionAthlete.getOffence() + oppositionAthlete.getDefence();
        assertEquals(oppositionDiff / (GameMechanics.oppositionAthletes.size() * 2), GameMechanics.getOppDiff());
    }

    @Test
    void lightAttackTest(){
        int i = 0;
        int j = 0;
        int chance = 1;
        double damage = 0;
        if (chance >= 1){
            double factor1 = 100 / (5* (100 - GameMechanics.athleteList.get(i).getStamina() + 1));
            double factor2 = (GameMechanics.athleteList.get(i).getOffence()/GameMechanics.oppositionAthletes.get(j).getDefence() + 1) + 1;
            damage = (factor1 * factor2 + chance + 15);
        } else {
            damage = 0;
        }
        assertEquals(damage, GameMechanics.attackLight(chance,i,j));
    }

    @Test
    void heavyAttackTest(){
        int i = 0;
        int j = 0;
        int chance = 0;
        double damage = 0;
        if (chance > 6){
            damage = 5 * chance + 20 + ((GameMechanics.athleteList.get(i).getOffence() + GameMechanics.oppositionAthletes.get(i).getStamina()) / 10) ;
        } else {
            damage = 0;
        }
        assertEquals(damage, GameMechanics.attackHeavy(chance,i, j));

    }

    @Test
    void healTest(){
        int i = 0;
        double heal = (GameMechanics.athleteList.get(i).getStamina()/5) + (GameMechanics.athleteList.get(i).getDefence()/10);
        assertEquals(-1 * heal, GameMechanics.heal(i));
    }

}
