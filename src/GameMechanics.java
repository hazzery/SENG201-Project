/*
 * This is just a fun little class to try out some game mechanics 
 */

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GameMechanics<ActionListener> {

    public static Team team;
    public static GameManager gameManager;
    public static OppositionTeam oppositionTeam;
    private static ArrayList<Athlete> oppositionAthletes;
    private static ArrayList<Athlete> athleteList;


    public static int indexer ;

    public static int athIndex;
    public static int oppIndex;
    public static int currentRound;

    public static boolean isPlayerTurn = true; 
    public static boolean isGameOver = false;

    GameMechanics(int currentRound, ArrayList<Athlete> athleteList, ArrayList<Athlete>oppositionAthletes){
        this.oppositionAthletes = oppositionAthletes;
        this.athleteList = athleteList;
        this.currentRound = currentRound;
        playRound();
    }

    //This code is scuffed for testing purposes
    public static void playRound(){
        if (isGameOver == false){
            for (int i = 0; i < 30; i++){
                endGameCondition();
                if (isGameOver == true){
                    break;
                }
                playTurn();
            }
        } else {
            System.out.println("Game Over");
        }    
    }

    public static void endGameCondition(){
        boolean deadAthletes = athleteList.stream().allMatch(obj -> obj.getCurrentHealth() <= 0);
        boolean deadOpposition = oppositionAthletes.stream().allMatch(obj -> obj.getCurrentHealth() <= 0);
        if (deadAthletes || deadOpposition){
            isGameOver = true;
            if (deadAthletes == true){
                System.out.println("Opposition Wins");
            } else {
                System.out.println("Athletes Win");
            }
            
        }
        
        

    }

    public static void playTurn(){
        if (oppositionAthletes.get(oppIndex).getCurrentHealth() > 0){
            System.out.println();
            
            // code which will scan for attack type then call the appropriate attack
            // ScannerCODE to find which attack is used, then calls attackOpposition(AttackType attackType)
            System.out.println("Athlete " + athIndex + " attacks");
            double damage = attackLight(athIndex, oppIndex);
            updateOpposition(damage);     
            
        } else {
            oppIndex++;
            playTurn();
        }
        oppositionPlayTurn();
    }

    /**
     * 
     * @param damage
     */
    public static void updateOpposition(double damage){
        if (damage >= 0){
            System.out.println("Opposition " + oppIndex + " takes " + damage + " damage");
            oppositionAthletes.get(oppIndex).current_health = (int) (oppositionAthletes.get(oppIndex).getCurrentHealth() - damage);
        } else {
            System.out.println("Athlete " + athIndex + " heals " + damage + " health");
            athleteList.get(athIndex).current_health = (int) (athleteList.get(athIndex).getCurrentHealth() - damage);
        }
        
    }
    
    public static void updateAthlete(double damage){
        if (damage >= 0){
            System.out.println("Athlete " + oppIndex + " takes " + damage + " damage");
            athleteList.get(athIndex).current_health = (int) (athleteList.get(athIndex).getCurrentHealth() - damage);
        } else {
            System.out.println("Opposition " + athIndex + " heals " + damage + " health");
            oppositionAthletes.get(oppIndex).current_health = (int) (oppositionAthletes.get(oppIndex).getCurrentHealth() - damage);
        }
        
    }

    public static void oppositionPlayTurn(){ 
        if (athleteList.get(athIndex).getCurrentHealth() > 0){
            System.out.println();  
            System.out.println("Opposition " + oppIndex + " attacks");
            int oppositionAttack = ThreadLocalRandom.current().nextInt(0, 50);

            if (oppositionAttack < 25){
                double damage = attackLight(oppIndex, athIndex);
                updateAthlete(damage);

            } else if (oppositionAttack >= 25 && oppositionAttack < 40){
                double damage = attackHeavy(oppIndex, athIndex);
                updateAthlete(damage);

            } else {
                double damage =  heal(oppIndex);
                updateAthlete(damage);
            }
        } else {
            athIndex++;
            oppositionPlayTurn();
        }
    }

    /**
     * 
     * @param i index of the attacking athlete in a team
     * @param j index of the attacked athlete in the opposite team
     */
    public static double attackLight(int i, int j){
        double factor1 = athleteList.get(i).getStamina() / 15;
        double factor2 = (athleteList.get(i).getOffence()/athleteList.get(j).getDefence())+1;
        double factor3 = 1 + (100 - athleteList.get(j).getDefence())/10;
        return (factor1 * factor2 *  factor3 + ThreadLocalRandom.current().nextInt(10, 20));
    }

    /**
     * 
     * @param i index of the attacking athlete in a team
     * @param j index of the attacked athlete in the opposite team
     */
    public static double attackHeavy(int i, int j){
        double factorA = (athleteList.get(i).getStamina()/10);
        double factorB = (athleteList.get(i).getOffence() / athleteList.get(j).getDefence());
        double factorC = ThreadLocalRandom.current().nextDouble(0.1, 3) * 7;
        return (factorA * factorB * factorC);
    }   


    /**
     * 
     * @param i index of the affected athlete in a team
     */
    public static double heal(int i){
        double heal = (athleteList.get(i).getStamina()/5) + (athleteList.get(i).getDefence()/10);
        return -1*heal;
    }
}
