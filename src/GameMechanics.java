/*
 * This is just a fun little class to try out some game mechanics 
 */

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

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

    public static int attackType;

    public static boolean isPlayerTurn = true; 
    public static boolean isGameOver = false;
    public static boolean isGameHard = false;
    public static boolean didAthletesWin = false;

    //public static boolean isNextTurnAble = true;

    GameMechanics(int currentRound, ArrayList<Athlete> athleteList, ArrayList<Athlete>oppositionAthletes){
        GameMechanics.oppositionAthletes = oppositionAthletes; //
        GameMechanics.athleteList = athleteList; //Team.getActives();
        GameMechanics.currentRound = currentRound; //GameManager.currentWeek();
        playCMD();
    }

    //This code is scuffed for testing purposes (AUTO PLAYS)
    // public static void playMatch(){
    //     if (isGameOver == false){
    //         isNextTurnAble = false;
    //         //below is testing code to test for functionality
    //         for (int i = 0; i < 30; i++){
    //             endGameCondition();
    //             if (isGameOver == true){
    //                 break;
    //             }
    //             playTurn(attackType);
    //             endGameCondition();
    //         }
    //     } else {
    //         System.out.println("Game Over");
    //     }    
    // }


    //CMD/Basic GUI Game Play
    public static void playCMD() {
        // Display the popup box with three buttons
        int result = JOptionPane.showOptionDialog(null,
                "Choose an Attack:",
                "ATTACK!!!!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"Light Attack", "Heavy Attack", "Heal", "End Game"},
                null);

        // Check which button was clicked and perform corresponding action
        switch (result) {
            case 0:
                playTurn(0);
                break;
            case 1:
                playTurn(1);
                break;
            case 2:
                playTurn(2);
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    
    // public static int attackType(){
    //     //In GUI a player will click a button which will call attackType with the index of button pressed
    //     //0: light attack, 1: Heavy Attack, 2:Heal
    //     if (attackType == 0){
    //         return 0;
    //     } else if (attackType == 1){
    //         return 1;
    //     } else if (attackType == 2){
    //         return 2;
    //     }
    //     return 0;

    // }


    public static void playTurn(int attackType){
        if (oppIndex + 1 >= oppositionAthletes.size()){return;}
        if (oppositionAthletes.get(oppIndex).getCurrentHealth() > 0){
            
            //Testing code
            System.out.println();
            System.out.println("Athlete " + athIndex + " attacks");
            System.out.println("Opposition " + oppIndex + " has " + oppositionAthletes.get(oppIndex).getCurrentHealth() + " health");
            //Testing code

            //attackType = attackType();
            if (attackType == 0){
                double damage = attackLight(athIndex, oppIndex);
                updateOpposition(damage * GameManager.isGameHard());
            } else if (attackType == 1){
                double damage = attackHeavy(athIndex, oppIndex);
                updateOpposition(damage * GameManager.isGameHard());
            } else if (attackType == 2){
                double damage = heal(athIndex);
                updateOpposition(damage);
            }
        } else {
            oppIndex++;
            playTurn(attackType);
        }
        endGameCondition();
        oppositionPlayTurn();
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
                didAthletesWin = true;
            }
            
            endOfGame();
        }
        
    }

    public static void endOfGame(){
        System.out.println("End of Game");

        if (didAthletesWin == true){
            System.out.println("You Win" + "\n" + "You earned $" + afterMatchReward() + " for winning");
            // afterMatchReward(); // commented out to prevent a duel call
            System.out.println("Your balence is now: " + GameManager.getBankBalance());

        } else {
            System.out.println("You Lose");
            System.out.println("Try Again next time xoxo");
        }

        for (int i = 0; i < athleteList.size(); i++){
            if (athleteList.get(i).current_health <= 0){
                athleteList.get(i).stamina -= (int) Math.ceil(0.5 * GameManager.isGameHard() * getOppDiff());
                if (athleteList.get(i).stamina <= 0){

                    athleteList.get(i).stamina = 0;
                    athleteList.get(i).current_health = 0;
                    athleteList.get(i).isInjured = true;
                } else {
                    athleteList.get(i).current_health = 100;
                }
            }

            if (athleteList.get(i).current_health > 0){
                oppositionAthletes.get(i).current_health = 100;
                int statIndex = ThreadLocalRandom.current().nextInt(0, 2);
                int statIncrease = ThreadLocalRandom.current().nextInt(1, 5);
                if (statIndex == 0) {
                    team.getActive(i).defence += statIncrease;
                } else if (statIndex == 1) {
                    team.getActive(i).offence += statIncrease;
                } 
            }
        }
    }


    public static double afterMatchReward(){
        double increase =  10 * getOppDiff() * GameManager.isGameHard() * (0.15 * currentRound);
        GameManager.addFunds((int) (10 * getOppDiff() * GameManager.isGameHard() * (0.15 * currentRound)));
        return increase;
    }

    public static void playerInjury(){
        //Take Player Stanima off
        athleteList.get(athIndex).stamina -= 0.5 * getOppDiff();
    }

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
        if (athIndex >= athleteList.size()){return;}
        if (athleteList.get(athIndex).getCurrentHealth() > 0){

            //Testing code
            System.out.println();  
            System.out.println("Opposition " + oppIndex + " attacks");
            System.out.println("Athlete " + athIndex + " has " + athleteList.get(athIndex).getCurrentHealth() + " health");
            //Testing code

            int oppositionAttack = ThreadLocalRandom.current().nextInt(0, 50);
            if (oppositionAttack < 35){
                double damage = attackLight(oppIndex, athIndex);
                updateAthlete(damage * GameManager.isGameHard());

            } else if (oppositionAttack >= 35 && oppositionAttack < 40){
                double damage = attackHeavy(oppIndex, athIndex);
                updateAthlete(damage * GameManager.isGameHard());

            } else {
                double damage =  heal(oppIndex);
                updateAthlete(damage * GameManager.isGameHard());
            }
        } else {
            athIndex++;
            oppositionPlayTurn();
        }
        endGameCondition();
        playCMD();
    }
    
    public static double attackLight(int i, int j){
        System.out.println("Light Attack");
        double factor1 = athleteList.get(i).getStamina() / 15;
        double factor2 = (athleteList.get(i).getOffence()/athleteList.get(j).getDefence()+1)+1;
        double factor3 = 1 + (100 - athleteList.get(j).getDefence())/10;
        return (factor1 * factor2 *  factor3 + ThreadLocalRandom.current().nextInt(10, 20));
    }
   
    public static double attackHeavy(int i, int j){
        System.out.println("Heavy Attack");
        double factorA = (athleteList.get(i).getStamina()/10) + 1;
        double factorB = (athleteList.get(i).getOffence() / athleteList.get(j).getDefence()) + 1;
        double factorC = ThreadLocalRandom.current().nextDouble(0.1, 3) * 7;
        return (factorA * factorB * factorC);
    }   

    public static double heal(int i){
        System.out.println("Heal Attack");
        double heal = (athleteList.get(i).getStamina()/5) + (athleteList.get(i).getDefence()/10);
        return -1*heal;
    }

    public static double getOppDiff(){
        int oppositionDiff = 0;
        for (int i = 0; i < oppositionAthletes.size(); i++){
            oppositionDiff += oppositionAthletes.get(i).getOffence() + oppositionAthletes.get(i).getDefence() + oppositionAthletes.get(i).getStamina();
        }
        return oppositionDiff / (oppositionAthletes.size() * 3);
    }
}
