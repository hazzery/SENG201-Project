import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;


/**
 * GameMechanics is a static utility class in charge of the game play.
 * It provides game logic and functionality to the GUI for the gameplay part of the game.
 * 
 * @author Daniel Smith
 */
public class GameMechanics {

    public static Team team;
    public static GameManager gameManager;
    public static OppositionTeam oppositionTeam;
    public static StadiumScreen stadiumScreen;
    public static MatchWindow matchWindow;
    public static TurnActionStatments turnActionStatments = new TurnActionStatments();

    public static ArrayList<Athlete> oppositionAthletes;
    public static ArrayList<Athlete> athleteList;

    private static int athIndex = 0;
    private static int oppIndex = 0;
    private static int currentRound;

    private static boolean isGameOver;
    private static boolean didAthletesWin;

    /**
     * The class constructor configures critical elements for the class to run correctly
     * @param currentRound takes the current round and is saved as a local int
     * @param athleteList the Players active team
     * @param oppositionAthletes the chosen opposition team
     */
    public void playGame(int currentRound, ArrayList<Athlete> athleteList, Athlete[] oppositionAthletes){
        System.out.println("PLAY GAME");
        GameMechanics.oppositionAthletes = new ArrayList<>(Arrays.asList(oppositionAthletes));
        GameMechanics.athleteList = athleteList;
        GameMechanics.currentRound = currentRound;
        isGameOver = false;
        athIndex = 0;
        oppIndex = 0;
    }

    /**
     * This method is called by the {@link MatchWindow} class when the user clicks on a game action button. 
     * Then calls the {@link GameMechanics#playTurn(int)} with given attackType
     * 
     * @param index the result of the GUI call in {@link MatchWindow}
     */
    public static void guiButtonPress(int index){
        switch(index){
            case 0 -> playTurn(0);
            case 1 -> playTurn(1);
            case 2 -> playTurn(2);
            case 3 -> exitMatch(); 
        }

    }

    /**
     * Method that informs the player that they are exiting the match and provides a confirmatory message.
     */
    private static void exitMatch() {
        int result = JOptionPane.showOptionDialog(null,
                "You are about to quit",
                "Are you sure you want to exit? The Damage done to your athletes will remain",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                new String[]{"Yes Quit :'(", "No Stay :D"},
                null);

        // Check which button was clicked and perform corresponding action
        switch (result) {
            case 0 -> WindowManager.showGameScreen(); //TODO Have a look 
            case 1 -> JOptionPane.getRootFrame().dispose();
        }
    }

    /**
     * This method is called by the  {@link GameMechanics#playGame(int, ArrayList, Athlete[])} method when the user clicks on the start game action button.
     * by taking the parameter attackType it the calls the appropriate attack method.
     *
     * @param attackType the attackType that play turn will call
     */
    public static void playTurn(int attackType){
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
//            oppIndex++;
            playTurn(attackType);
        }
        System.out.println("END OF ATTACK ATH " + athIndex + " " + oppIndex + "");
        endGameCondition();
        oppositionPlayTurn();
    }

    /**
     * This method is called by the {@link GameMechanics#playTurn(int)} method once the athletes turn has been completed.
     * This method is automatic called when the athlete has attacked.
     */
    public static void oppositionPlayTurn(){ 
        if (isGameOver){return;}
        if (athIndex == 5 || oppIndex ==5){return;}
        if (athleteList.get(athIndex).getCurrentHealth() > 0){
            System.out.println("Opposition " + oppIndex + turnActionStatments.getAttackName() + athIndex + "");    
            
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
//          athIndex++;
            oppositionPlayTurn();
        }
        System.out.println("END OF ATTACK OPP " + athIndex + " " + oppIndex + "");
        endGameCondition();
    }

    /**
     * This method is called by the {@link GameMechanics#playTurn(int)} method once the athletes or opposition turn has been completed.
     * This method is automatically called after the {@link GameMechanics#playTurn(int)}
     * The method will check the health of the Athlete and Opposition, to ensure they are not dead, if they are the next Athlete will be indexed
     */
    public static void checkHealth(){
        if (athleteList.get(athIndex).getCurrentHealth() <= 0){
            System.out.println("Athlete " + athIndex + " is dead");
            gameOutput("Athlete " + athleteList.get(athIndex).getName() + " was defeated as " + turnActionStatments.getDefeatName());
            athIndex++;
        }
        if (oppositionAthletes.get(oppIndex).getCurrentHealth() <= 0){
            System.out.println("Opposition " + oppIndex + " is dead");
            gameOutput("Opposition " + oppositionAthletes.get(oppIndex).getName() + " was defeated as " + turnActionStatments.getDefeatName());
            oppIndex++;
        }
    }


    /**
     * This method is called by the {@link GameMechanics#playTurn(int)} and {@link GameMechanics#oppositionPlayTurn()} methods once the athletes or opposition turn has been completed.
     * This method checks that the game is not over by checking if either teams of athletes are completely defeated if true the {@link GameMechanics#endOfGame()} method is called.
     * This method updates {@link GameMechanics#didAthletesWin}
     */
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

    /**
     * This method is called by the {@link GameMechanics#endGameCondition()} method once the game is over.
     * This method with update the players athletes so that they are returned with full health but a reduced amount of stamina,
     * and will reward the player with a reward based on the difficulty of the game which is determined by the {@link GameMechanics#afterMatchReward()} method.
     * This method checks the {@link GameMechanics#didAthletesWin} boolean and to determine if the player won and then will update the {@link Athlete} objects in {@link GameMechanics#athleteList}
     */
    public static void endOfGame(){
        System.out.println();
        if (didAthletesWin){
            System.out.println("You earned $" + afterMatchReward() + " for winning");
            JOptionPane.showMessageDialog(null, "You earned $" + afterMatchReward() + " for winning, Your balence is now: " + GameManager.getBankBalance());
            // afterMatchReward(); // commented out to prevent a duel call
            System.out.println("Your balence is now: " + GameManager.getBankBalance());
            


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

       
        GameManager.gameHasBeenPlayed = true;
        WindowManager.showGameScreen();
    }


    /**
     * This method is called by the {@link GameMechanics#endOfGame()} method once the game is over, to provide the player with a reward based on the difficulty of the game.
     * @return the amount of money the player has earned based on the difficulty of the game and the round the player is on.
     */
    public static double afterMatchReward(){
        double increase =  10 * getOppDiff() * GameManager.isGameHard() * (0.15 * currentRound);
        GameManager.addFunds((int) (10 * getOppDiff() * GameManager.isGameHard() * (0.15 * currentRound)));
        return increase;
    }

    

    /**
     * This method is called by the {@link GameMechanics#playTurn(int)} method once an attack has been completed to update the values of the Opposition.
     * This method updates {@link Athlete} objects in {@link GameMechanics#oppositionAthletes}
     * @param damage the amount of damage that has been dealt to the opposition.
     */
    public static void updateOpposition(double damage){
        if (damage == 0){
            oppGameOutput("Your attack on opposition " + oppositionAthletes.get(oppIndex).getName()  + " missed");
        }
        
        if (damage >= 0){  
            oppositionAthletes.get(oppIndex).current_health = (int) (oppositionAthletes.get(oppIndex).getCurrentHealth() - damage);
            if (oppositionAthletes.get(oppIndex).current_health < 0){ oppositionAthletes.get(oppIndex).current_health = 0; }
            System.out.println("Opposition " + oppIndex + " takes " + damage + " damage" + ", Health: " + oppositionAthletes.get(oppIndex).getCurrentHealth() + "");
            oppGameOutput("The Opposition Athlete " + oppositionAthletes.get(oppIndex).getName() + " " + turnActionStatments.getAttackName() + " so " + damage + " damage was delt" + ", Health: " + oppositionAthletes.get(oppIndex).getCurrentHealth() + "");
        } else {
            athleteList.get(athIndex).current_health = (int) (athleteList.get(athIndex).getCurrentHealth() - damage);
            System.out.println("Athlete " + athIndex + " heals " + damage + " health" + ", Health: " + athleteList.get(athIndex).getCurrentHealth() + "");
            teamGameOutput("Youre Athlete " + athleteList.get(athIndex).getName() + " " + turnActionStatments.getHealName() + " so " + damage + " damage was reversed" + ", Health: " + athleteList.get(athIndex).getCurrentHealth() + "");

        }
        
    }
    
    /**
     * This method is called by the {@link GameMechanics#oppositionPlayTurn()} method once an attack has been completed to update the values of the Athlete.
     * This mehtod will update the {@link Athlete} objects in {@link GameMechanics#athleteList}.
     * @param damage is the value that the opposition has dealt to the player.
     */
    public static void updateAthlete(double damage){
        if (damage == 0){
            teamGameOutput("The attack on your athlete " + athleteList.get(athIndex).getName()  + " missed");
        }
        if (damage >= 0){
            athleteList.get(athIndex).current_health = (int) (athleteList.get(athIndex).getCurrentHealth() - damage);
            if (athleteList.get(athIndex).current_health < 0){ athleteList.get(athIndex).current_health = 0; }
            System.out.println("Athlete " + athIndex + " takes " + damage + " damage" + ", Health: " + athleteList.get(athIndex).getCurrentHealth() + "");
            teamGameOutput(" " + turnActionStatments.getAttackName() + "so your Athlete: " + athleteList.get(athIndex).getName() + " was delt " + damage + ", Health is now: " + athleteList.get(athIndex).getCurrentHealth() + "");

        } else {
            oppositionAthletes.get(oppIndex).current_health = (int) (oppositionAthletes.get(oppIndex).getCurrentHealth() - damage);
            System.out.println("Opposition " + oppIndex + " heals " + damage + " health" + ", Health: " + oppositionAthletes.get(oppIndex).getCurrentHealth() + "");
            oppGameOutput(" " + turnActionStatments.getHealName() + " so the Opposition Athlete: " + oppositionAthletes.get(oppIndex).getName() +  " had "+ damage +" damage was reversed" + ", Health is now: " + oppositionAthletes.get(oppIndex).getCurrentHealth() + "");

        }
        
    }
    
    /**
     * This method is called by the {@link GameMechanics#playTurn(int)} and {@link GameMechanics#oppositionPlayTurn()} methods to determine the amount of damage dealt to the recipient through a light attack.
     * @param i the index of the attacker Athlete object in an arraylist
     * @param j the index of the attacked Athlete object in a narraylist
     * @return the damage that the attack will give
     */
    public static double attackLight(int i, int j){
        System.out.println("Light Attack");
        int chance = ThreadLocalRandom.current().nextInt(0, 10);
        if (chance >= 1){
            double factor1 = 100 / (5* (100 - athleteList.get(i).getStamina() + 1));
            double factor2 = (athleteList.get(i).getOffence()/oppositionAthletes.get(j).getDefence() + 1) + 1;
            return (factor1 * factor2 + ThreadLocalRandom.current().nextInt(15, 25));
        } else {
            return 0;
        }
        
    }

    /**
     * This method is called by the {@link GameMechanics#playTurn(int)} and {@link GameMechanics#oppositionPlayTurn()} methods to determine the amount of damage dealt to the recipient through a heavy attack.
     * @param i the index of the attacker Athlete object in an arraylist
     * @param j the index of the attacked Athlete object in an arraylist
     * @return the damage that the attack will give
     */
    public static double attackHeavy(int i, int j){
        System.out.println("Heavy Attack");
         int chance = ThreadLocalRandom.current().nextInt(0, 10);
         if (chance > 6){
             return ThreadLocalRandom.current().nextInt(30, 60) + ((athleteList.get(i).getOffence() + oppositionAthletes.get(i).getStamina()) / 10) ;
         } else {
             return 0;
         }
    }

    /**
     * This method is called by the {@link GameMechanics#playTurn(int)} and {@link GameMechanics#oppositionPlayTurn()} methods to determine the amount of health to be received.
     * @param i the index of the healee Athlete object in an arraylist
     * @return the amount of health that the healee will give
     */
    public static double heal(int i){
        System.out.println("Heal Attack");
        double heal = (athleteList.get(i).getStamina()/5) + (athleteList.get(i).getDefence()/10);
        return -1*heal;
    }

    /**
     * This method is called to determine the overall difficulty of the opposition.
     * @return the overall difficulty of the opposition.
     */
    public static double getOppDiff(){
        int oppositionDiff = 0;
        for (int i = 0; i < oppositionAthletes.size(); i++){
            oppositionDiff += oppositionAthletes.get(i).getOffence() + oppositionAthletes.get(i).getDefence();
        }
        return oppositionDiff / (oppositionAthletes.size() * 2);
    }

    public static void teamGameOutput(String string){
        MatchWindow.teamOutput.setText(string);
        MatchWindow.teamOutput.revalidate();
    }

    public static void oppGameOutput(String string){
        MatchWindow.oppositionOutput.setText(string);
        MatchWindow.oppositionOutput.revalidate();
    }

    public static void gameOutput(String string){
        MatchWindow.gameOutput.setText(string);
        MatchWindow.gameOutput.revalidate();
    }
 }
