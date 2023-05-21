import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;


/**
 * DANIEL I HAVE NO CLUE WHAT THIS CLASS DOES PLEASE WRITE THE JAVADOC FOR THIS CLASS
 *
 * @author Daniel Smith
 * @param <ActionListener> DANIEL WHY TF IS THIS A PARAMETERISED GENERIC CLASS
 */
public class GameMechanics<ActionListener> {

    public static Team team;
    public static GameManager gameManager;
    public static OppositionTeam oppositionTeam;
    public static TurnActionStatments turnActionStatments = new TurnActionStatments();

    private static ArrayList<Athlete> oppositionAthletes;
    private static ArrayList<Athlete> athleteList;

    public static int indexer ;

    public static int athIndex = 0;
    public static int oppIndex = 0;
    public static int currentRound;

    public static int attackType;


    public static boolean isGameOver;
    public static boolean didAthletesWin;
    public static boolean isNextTurnAble = true;

    //public static boolean isNextTurnAble = true;

    public static void playGame(int currentRound, ArrayList<Athlete> athleteList, Athlete[] oppositionAthletes){
        GameMechanics.oppositionAthletes = new ArrayList<>(Arrays.asList(oppositionAthletes));
        GameMechanics.athleteList = athleteList; //Team.getActives();
        GameMechanics.currentRound = currentRound; //GameManager.currentWeek();
        isGameOver = false;
        //cdmInit();
    }

    public static void guiButtonPress(int index){
        
        switch(index){
            case 0 -> playTurn(0);
            case 1 -> playTurn(1);
            case 2 -> playTurn(2);
            //case 3 -> nextTurn();
        }

    }

    public static void cdmInit(){
        int result = JOptionPane.showOptionDialog(null,
                "Your Team: " + athleteList + "\n" + "Opposition Team: " + oppositionAthletes + "\n" + "Round: " + currentRound,
                "Start Game",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"Start Game", "Quit"},
                null);

        // Check which button was clicked and perform corresponding action
        switch (result) {
            case 0 -> playCMD();
            case 1 -> System.exit(0);
        }
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
        if (isGameOver){return;}
        // Display the popup box with three buttons
        int result = JOptionPane.showOptionDialog(null,
                "Athlete " + athleteList.get(athIndex).getName() + " will be attacking Opposition: " + oppositionAthletes.get(oppIndex).getName() + "",
                "ATTACK!!!!",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"Light Attack (90%)", "Heavy Attack (25%)", "Heal", "End Game"},
                null);

        // Check which button was clicked and perform corresponding action
        switch (result) {
            case 0 -> playTurn(0);
            case 1 -> playTurn(1);
            case 2 -> playTurn(2);
            case 3 -> System.exit(0);
        }
    }

    public static void playTurn(int attackType){
        // if (isGameOver == true){return;}
        if (athIndex == 5 || oppIndex ==5){return;}

        if (oppositionAthletes.get(oppIndex).getCurrentHealth() > 0){
            System.out.println("Athlete " + athIndex + " attacks " + oppIndex + "");
            if (attackType == 0){
                double damage = attackLight(athIndex, oppIndex);
                updateOpposition(damage * GameManager.isGameHard());
                checkHealth();
            } else if (attackType == 1){
                double damage = attackHeavy(athIndex, oppIndex);
                updateOpposition(damage * GameManager.isGameHard());
                checkHealth();
            } else if (attackType == 2){
                double damage = heal(athIndex);
                updateOpposition(damage);
            }
        } else {
            // System.out.println("Opposition " + oppIndex + " is dead");
            // oppIndex++;
            playTurn(attackType);
        }
        System.out.println("END OF ATTACK ATH " + athIndex + " " + oppIndex + "");
        endGameCondition();
        oppositionPlayTurn();
    }

    
    public static void oppositionPlayTurn(){ 
        if (isGameOver){return;}
        // if (athIndex >= athleteList.size()){return;}
        if (athIndex == 5 || oppIndex ==5){return;}

        if (athleteList.get(athIndex).getCurrentHealth() > 0){

            //Testing code
            System.out.println();  
            System.out.println("Opposition " + oppIndex + turnActionStatments.getAttackName() + athIndex + "");
            //Testing code
            JOptionPane.showMessageDialog(null, 
            "The Opposition Athlete: " + oppositionAthletes.get(oppIndex).getName() + " is going to attack: " + athleteList.get(athIndex).getName() + ""
            );
            int oppositionAttack = ThreadLocalRandom.current().nextInt(0, 50);
            if (oppositionAttack < 35){
                double damage = attackLight(oppIndex, athIndex);
                updateAthlete(damage * GameManager.isGameHard());
                checkHealth();

            } else if (oppositionAttack < 40){
                double damage = attackHeavy(oppIndex, athIndex);
                updateAthlete(damage * GameManager.isGameHard());
                checkHealth();

            } else {
                double damage =  heal(oppIndex);
                updateAthlete(damage * GameManager.isGameHard());

            }
        } else {
            // System.out.println("Athlete " + athIndex + " is dead");
            // athIndex++;
            oppositionPlayTurn();
        }
        System.out.println("END OF ATTACK OPP " + athIndex + " " + oppIndex + "");
        endGameCondition();
        // nextTurn();
    }

    // public static void nextTurn(){
    //     int result = JOptionPane.showOptionDialog(null,
    //             "Next Turn or Quit:",
    //             "Turn???",
    //             JOptionPane.DEFAULT_OPTION,
    //             JOptionPane.WARNING_MESSAGE,
    //             null,
    //             new String[]{"Next Turn", "Quit"},
    //             null);

    //     switch (result) {
    //         case 0 -> playCMD();
    //         case 1 -> System.exit(0);
    //     }
    // }

    public static void checkHealth(){
        if (athleteList.get(athIndex).getCurrentHealth() <= 0){
            System.out.println("Athlete " + athIndex + " is dead");
            JOptionPane.showMessageDialog(null, "Athlete " + athleteList.get(athIndex).getName() + " \n Was defeated as " + turnActionStatments.getDefeatName());
            
            athIndex++;
        }
        if (oppositionAthletes.get(oppIndex).getCurrentHealth() <= 0){
            System.out.println("Opposition " + oppIndex + " is dead");
            JOptionPane.showMessageDialog(null, "Opposition " + oppositionAthletes.get(oppIndex).getName() + " \n Was defeated as" + turnActionStatments.getDefeatName());
            
            oppIndex++;
        }
    }
    
    public static void endGameCondition(){
        boolean deadAthletes = athleteList.stream().allMatch(obj -> obj.getCurrentHealth() <= 0);
        boolean deadOpposition = oppositionAthletes.stream().allMatch(obj -> obj.getCurrentHealth() <= 0);
        if (deadAthletes || deadOpposition){
            isGameOver = true;
            System.out.println();
            System.out.println("Game Over");
            JOptionPane.showMessageDialog(null, "Game Over");
            if (deadAthletes){
                System.out.println("Opposition Wins");
                didAthletesWin = false;
            } else {
                System.out.println("Athletes Win");
                didAthletesWin = true;
            }
            endOfGame();
        }
        
    }

    public static void endOfGame(){
        System.out.println();
        if (didAthletesWin){
            System.out.println("You earned $" + afterMatchReward() + " for winning");
            JOptionPane.showMessageDialog(null, "You earned $" + afterMatchReward() + " for winning");
            // afterMatchReward(); // commented out to prevent a duel call
            System.out.println("Your balence is now: " + GameManager.getBankBalance());
            JOptionPane.showMessageDialog(null, "Your balence is now: " + GameManager.getBankBalance());

        } else {
            System.out.println("Try Again next time xoxo");
            JOptionPane.showMessageDialog(null, "Try Again next time xoxo");
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
            } else {
                oppositionAthletes.get(i).current_health = 100;
                int statIndex = ThreadLocalRandom.current().nextInt(0, 2);
                int statIncrease = ThreadLocalRandom.current().nextInt(1, 5);
                if (statIndex == 0) {
                    athleteList.get(i).defence += statIncrease;
                } else if (statIndex == 1) {
                    athleteList.get(i).offence += statIncrease;
                } 
            }
        }

        System.out.println("\n Your Athletes have been reset and Updated");
        for (Athlete athlete : athleteList) {
            System.out.println(athlete.getStats());
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
        if (damage == 0){
            JOptionPane.showMessageDialog(null, 
            "Your attack on opposition " + oppositionAthletes.get(oppIndex).getName()  + " missed");
        }
        
        if (damage >= 0){  
            oppositionAthletes.get(oppIndex).current_health = (int) (oppositionAthletes.get(oppIndex).getCurrentHealth() - damage);
            System.out.println("Opposition " + oppIndex + " takes " + damage + " damage" + ", Health: " + oppositionAthletes.get(oppIndex).getCurrentHealth() + "");
            JOptionPane.showMessageDialog(null, 
            "The Opposition Athlete " + oppositionAthletes.get(oppIndex).getName() + " " + turnActionStatments.getAttackName() + " \n So " + damage + " damage was delt" + ", Health: " + oppositionAthletes.get(oppIndex).getCurrentHealth() + ""
            );
        } else {
            
            athleteList.get(athIndex).current_health = (int) (athleteList.get(athIndex).getCurrentHealth() - damage);
            System.out.println("Athlete " + athIndex + " heals " + damage + " health" + ", Health: " + athleteList.get(athIndex).getCurrentHealth() + "");
            JOptionPane.showMessageDialog(null, 
            "Youre Athlete " + athleteList.get(athIndex).getName() + " " + turnActionStatments.getHealName() + " \n So " + damage + " damage was reversed" + ", Health: " + athleteList.get(athIndex).getCurrentHealth() + ""
            );
        }
        
    }
    
    public static void updateAthlete(double damage){
        if (damage == 0){
            JOptionPane.showMessageDialog(null, 
            "The attack on your athlete " + athleteList.get(athIndex).getName()  + " missed");
        }
        if (damage >= 0){
            athleteList.get(athIndex).current_health = (int) (athleteList.get(athIndex).getCurrentHealth() - damage);
            System.out.println("Athlete " + athIndex + " takes " + damage + " damage" + ", Health: " + athleteList.get(athIndex).getCurrentHealth() + "");
            JOptionPane.showMessageDialog(null, 
            "Your Athlete" + athleteList.get(athIndex).getName() + " " + turnActionStatments.getAttackName() + " \n So " + damage + " damage was delt" + ", Health: " + athleteList.get(athIndex).getCurrentHealth() + ""
            );
        } else {
            
            oppositionAthletes.get(oppIndex).current_health = (int) (oppositionAthletes.get(oppIndex).getCurrentHealth() - damage);
            System.out.println("Opposition " + oppIndex + " heals " + damage + " health" + ", Health: " + oppositionAthletes.get(oppIndex).getCurrentHealth() + "");
            JOptionPane.showMessageDialog(null, 
            "The Opposition Athlete " + oppositionAthletes.get(oppIndex).getName() + " " + turnActionStatments.getHealName() + " \n So " + damage + " damage was reversed" + ", Health: " + oppositionAthletes.get(oppIndex).getCurrentHealth() + ""
            );
        }
        
    }
    
    public static double attackLight(int i, int j){
        System.out.println("Light Attack");
        int chance = ThreadLocalRandom.current().nextInt(0, 10);
        if (chance >= 1){
            double factor1 = 100 / (5* (100 - athleteList.get(i).getStamina() + 1));
            double factor2 = (athleteList.get(i).getOffence()/athleteList.get(j).getDefence() + 1) + 1;
            return (factor1 * factor2 + ThreadLocalRandom.current().nextInt(15, 25));
        } else {
            return 0;
        }
        
    }
   
    public static double attackHeavy(int i, int j){
        System.out.println("Heavy Attack");
        int chance = ThreadLocalRandom.current().nextInt(0, 4);
        if (chance == 2){
            return ThreadLocalRandom.current().nextInt(30, 60) + ((athleteList.get(i).getOffence() + athleteList.get(i).getStamina()) / 10) ;
        } else {
            return 0;
        }
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
