//package management;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.concurrent.ThreadLocalRandom;
//import javax.swing.JOptionPane;
//
//import data.Athlete;
//import data.OppositionTeam;
//import data.Team;
//import gui.MatchWindow;
//import gui.StadiumScreen;
//import utility.NameFileReader;
//
//
///**
// * GameMechanics is a static utility class in charge of the game play.
// * It provides game logic and functionality to the GUI for the gameplay part of the game.
// *
// * @author Daniel Smith
// */
//public class GameMechanics {
//
//        public static Team team;
//        public static GameManager gameManager;
//        public static OppositionTeam oppositionTeam;
//        public static StadiumScreen stadiumScreen;
//        public static MatchWindow matchWindow;
//        public static NameFileReader attackText = new NameFileReader("Resources/SkierAttackOptions.txt");
//        public static NameFileReader defeatText = new NameFileReader("Resources/SkierDefeatOptions.txt");
//        public static NameFileReader healText = new NameFileReader("Resources/SkierHealOptions.txt");
//        public static ArrayList<Athlete> oppositionAthletes;
//        public static ArrayList<Athlete> athleteList;
//        public static int athleteIndex = 0;
//        public static int oppositionIndex = 0;
//        public static int currentRound;
//        public static boolean isGameOver;
//        public static boolean didAthletesWin;
//        public static int chance = ThreadLocalRandom.current().nextInt(0,10);
//
//        /**
//         * The class constructor configures critical elements for the class to run correctly
//         * @param currentRound takes the current round and is saved as a local int
//         * @param athleteList the Players active team
//         * @param oppositionAthletes the chosen opposition team
//         */
//        public void playGame(int currentRound, ArrayList<Athlete> athleteList, Athlete[] oppositionAthletes){
//                System.out.println("PLAY GAME");
//                GameMechanics.oppositionAthletes = new ArrayList<>(Arrays.asList(oppositionAthletes));
//                GameMechanics.athleteList = athleteList;
//                GameMechanics.currentRound = currentRound;
//                isGameOver = false;
//                athleteIndex = 0;
//                oppositionIndex = 0;
//        }
//
//        /**
//         * This method is called by the {@link MatchWindow} class when the user clicks on a game action button.
//         * Then calls the {@link GameMechanics#playTurn(int)} with given attackType
//         *
//         * @param index the result of the GUI call in {@link MatchWindow}
//         */
//        public static void guiButtonPress(int index){
//                switch(index){
//                        case 0 -> playTurn(0);
//                        case 1 -> playTurn(1);
//                        case 2 -> playTurn(2);
//                        case 3 -> exitMatch();
//                }
//
//        }
//
//        /**
//         * Method that informs the player that they are exiting the match and provides a confirmatory message.
//         */
//        private static void exitMatch() {
//                int result = JOptionPane.showOptionDialog(null,
//                                "You are about to quit",
//                                "Are you sure you want to exit? The Damage done to your athletes will remain",
//                                JOptionPane.DEFAULT_OPTION,
//                                JOptionPane.WARNING_MESSAGE,
//                                null,
//                                new String[]{"Yes Quit :'(", "No Stay :D"},
//                                null);
//
//                // Check which button was clicked and perform the corresponding action
//                if (result == 0)
//                        WindowManager.showGameScreen();
//                else
//                        JOptionPane.getRootFrame().dispose();
//        }
//
//        /**
//         * This method is called by the {@link GameMechanics#playGame(int, ArrayList, Athlete[])} method
//         * when the user clicks on the start game action button.
//         *
//         * @param attackType the attackType that play turn will call
//         */
//        public static void playTurn(int attackType){
//                if (athleteIndex == 5 || oppositionIndex ==5){return;}
//                if (oppositionAthletes.get(oppositionIndex).getCurrentHealth() > 0){
//                        System.out.println("Athlete " + athleteIndex + " attacks " + oppositionIndex);
//                        if (attackType == 0){
//                                double damage = attackLight(chance, athleteIndex, oppositionIndex);
//                                updateOpposition(damage * GameManager.isGameHard());
//                                checkHealth();
//                        } else if (attackType == 1){
//                                double damage = attackHeavy(chance, athleteIndex, oppositionIndex);
//                                updateOpposition(damage * GameManager.isGameHard());
//                                checkHealth();
//                        } else if (attackType == 2){
//                                double damage = heal(athleteIndex);
//                                updateOpposition(damage);
//                        }
//                } else {
//                        playTurn(attackType);
//                }
//                endGameCondition();
//                oppositionPlayTurn();
//        }
//
//        /**
//         * This method is called by the {@link GameMechanics#playTurn(int)} method once the athletes turn has been completed.
//         * This method is called when the athlete has attacked.
//         */
//        public static void oppositionPlayTurn() {
//                if (isGameOver)
//                        return;
//
//                if (athleteIndex == 5 || oppositionIndex == 5)
//                        return;
//
//                if (athleteList.get(athleteIndex).getCurrentHealth() > 0){
//                        int oppositionAttack = ThreadLocalRandom.current().nextInt(0, 50);
//                        if (oppositionAttack < 35){
//                                double damage = attackLight(chance, oppositionIndex, athleteIndex);
//                                updateAthlete(damage * GameManager.isGameHard());
//                                checkHealth();
//                        } else if (oppositionAttack < 40){
//                                double damage = attackHeavy(chance, oppositionIndex, athleteIndex);
//                                updateAthlete(damage * GameManager.isGameHard());
//                                checkHealth();
//                        } else {
//                                double damage =  heal(oppositionIndex);
//                                updateAthlete(damage * GameManager.isGameHard());
//                        }
//                } else {
//                        oppositionPlayTurn();
//                }
//                endGameCondition();
//        }
//
//        /**
//         * This method is called by the {@link GameMechanics#playTurn(int)} method once the athletes or opposition turn has been completed.
//         * This method is automatically called after the {@link GameMechanics#playTurn(int)}
//         * The method will check the health of the Athlete and Opposition, to ensure they are not dead, if they are the next Athlete will be indexed
//         */
//        public static void checkHealth(){
//                if (athleteList.get(athleteIndex).getCurrentHealth() <= 0){
//                        gameOutput("Athlete " + athleteList.get(athleteIndex).getName() + " was defeated as " + defeatText.next());
//                        athleteIndex++;
//                }
//                if (oppositionAthletes.get(oppositionIndex).getCurrentHealth() <= 0){
//                        gameOutput("Opposition " + oppositionAthletes.get(oppositionIndex).getName() + " was defeated as " + defeatText.next());
//                        oppositionIndex++;
//                }
//        }
//
//
//        /**
//         * This method is called by the {@link GameMechanics#playTurn(int)} and {@link GameMechanics#oppositionPlayTurn()} methods once the athletes or opposition turn has been completed.
//         * This method checks that the game is not over by checking if either teams of athletes are completely defeated if true the {@link GameMechanics#endOfGame()} method is called.
//         * This method updates {@link GameMechanics#didAthletesWin}
//         */
//        public static void endGameCondition(){
//                boolean deadAthletes = athleteList.stream().allMatch(obj -> obj.getCurrentHealth() <= 0);
//                boolean deadOpposition = oppositionAthletes.stream().allMatch(obj -> obj.getCurrentHealth() <= 0);
//                if (deadAthletes || deadOpposition){
//                        isGameOver = true;
//                        System.out.println();
//                        System.out.println("Game Over");
//                        JOptionPane.showMessageDialog(null, "Game Over");
//                        if (deadAthletes){
//                                System.out.println("Opposition Wins");
//                                didAthletesWin = false;
//                        } else {
//                                System.out.println("Athletes Win");
//                                didAthletesWin = true;
//                        }
//                        endOfGame();
//                }
//
//        }
//
//        /**
//         * This method is called by the {@link GameMechanics#endGameCondition()} method once the game is over.
//         * This method with update the player's athletes so that they are returned with full health but a reduced amount of stamina,
//         * and will reward the player with a reward based on the difficulty of the game which is determined by the {@link GameMechanics#afterMatchReward()} method.
//         * This method checks the {@link GameMechanics#didAthletesWin} boolean and to determine if the player won and then will update the {@link Athlete} objects in {@link GameMechanics#athleteList}
//         */
//        public static void endOfGame(){
//                System.out.println();
//                if (didAthletesWin){
//                        JOptionPane.showMessageDialog(null, "You earned $" + afterMatchReward() + " for winning, Your balance is now: " + GameManager.getBankBalance());
//
//                } else {
//                        JOptionPane.showMessageDialog(null, "Try Again next time!!");
//                }
//
//                for (Athlete athlete : athleteList){
//                        if (athlete.getCurrentHealth() <= 0){
//                                athlete.improveStat(Athlete.StatType.STAMINA, (int) Math.ceil(0.5 * GameManager.isGameHard() * getOppDiff()));
//                                if (athlete.getStamina() <= 0){
//                                        athlete.injure();
//                                } else {
//                                        athlete.current_health = 100;
//                                }
//                        } else {
//                                oppositionAthletes.get(i).current_health = 100;
//                                int statIndex = ThreadLocalRandom.current().nextInt(0, 2);
//                                int statIncrease = ThreadLocalRandom.current().nextInt(1, 5);
//                                if (statIndex == 0) {
//                                        athlete.defence += statIncrease;
//                                } else if (statIndex == 1) {
//                                        athlete.offence += statIncrease;
//                                }
//                        }
//                }
//                GameManager.gameHasBeenPlayed = true;
//                WindowManager.showGameScreen();
//        }
//
//
//        /**
//         * This method is called by the {@link GameMechanics#endOfGame()} method once the game is over, to provide the player with a reward based on the difficulty of the game.
//         * @return the amount of money the player has earned based on the difficulty of the game and the round the player is on.
//         */
//        public static double afterMatchReward(){
//                double increase =  10 * getOppDiff() * GameManager.isGameHard() * (0.15 * currentRound);
//                GameManager.addFunds((int) (10 * getOppDiff() * GameManager.isGameHard() * (0.15 * currentRound)));
//                return increase;
//        }
//
//
//
//        /**
//         * This method is called by the {@link GameMechanics#playTurn(int)} method once an attack has been completed to update the values of the Opposition.
//         * This method updates {@link Athlete} objects in {@link GameMechanics#oppositionAthletes}
//         * @param damage the amount of damage that has been dealt to the opposition.
//         */
//        public static void updateOpposition(double damage){
//                if (damage == 0)
//                        oppGameOutput("Your attack on opposition " + oppositionAthletes.get(oppositionIndex).getName()  + " missed");
//                if (damage >= 0){
//                        oppositionAthletes.get(oppositionIndex).current_health = (int) (oppositionAthletes.get(oppositionIndex).getCurrentHealth() - damage);
//                        if (oppositionAthletes.get(oppositionIndex).current_health < 0){ oppositionAthletes.get(oppositionIndex).current_health = 0; }
//                        oppGameOutput("The Opposition Athlete " + oppositionAthletes.get(oppositionIndex).getName() + " " + attackText.next() + " so " + damage + " damage was dealt" + ", Health: " + oppositionAthletes.get(oppositionIndex).getCurrentHealth() + "");
//                } else {
//                        athleteList.get(athleteIndex).current_health = (int) (athleteList.get(athleteIndex).getCurrentHealth() - damage);
//                        teamGameOutput("Your Athlete " + athleteList.get(athleteIndex).getName() + " " + healText.next() + " so " + damage + " damage was reversed" + ", Health: " + athleteList.get(athleteIndex).getCurrentHealth() + "");
//
//                }
//
//        }
//
//        /**
//         * This method is called by the {@link GameMechanics#oppositionPlayTurn()} method once an attack has been completed to update the values of the Athlete.
//         * This method will update the {@link Athlete} objects in {@link GameMechanics#athleteList}.
//         * @param damage is the value that the opposition has dealt to the player.
//         */
//        public static void updateAthlete(double damage){
//                if (damage == 0)
//                        teamGameOutput("The attack on your athlete " + athleteList.get(athleteIndex).getName()  + " missed");
//                if (damage >= 0){
//                        athleteList.get(athleteIndex).current_health = (int) (athleteList.get(athleteIndex).getCurrentHealth() - damage);
//                        if (athleteList.get(athleteIndex).current_health < 0){ athleteList.get(athleteIndex).current_health = 0; }
//                        teamGameOutput(" " + attackText.next() + "so your Athlete: " + athleteList.get(athleteIndex).getName() + " was dealt " + damage + ", Health is now: " + athleteList.get(athleteIndex).getCurrentHealth() + "");
//
//                } else {
//                        oppositionAthletes.get(oppositionIndex).current_health = (int) (oppositionAthletes.get(oppositionIndex).getCurrentHealth() - damage);
//                        oppGameOutput(" " + healText.next() + " so the Opposition Athlete: " + oppositionAthletes.get(oppositionIndex).getName() +  " had "+ damage +" damage was reversed" + ", Health is now: " + oppositionAthletes.get(oppositionIndex).getCurrentHealth() + "");
//
//                }
//
//        }
//
//        /**
//         * This method is called by the {@link GameMechanics#playTurn(int)} and {@link GameMechanics#oppositionPlayTurn()} methods to determine the amount of damage dealt to the recipient through a light attack.
//         *
//         * @param chance the chance that the attack will be successful
//         * @param i the index of the attacker Athlete object in an arraylist
//         * @param j the index of the attacked Athlete object in an arraylist
//         * @return the damage that the attack will give
//         */
//        public static double attackLight(int chance, int i, int j){
//                if (chance >= 1){
//                        double factor1 = 100 / (5* (100 - athleteList.get(i).getStamina() + 1));
//                        double factor2 = (athleteList.get(i).getOffence()/oppositionAthletes.get(j).getDefence() + 1) + 1;
//                        return (factor1 * factor2 + chance + 15);
//                } else {
//                        return 0;
//                }
//
//        }
//
//        /**
//         * This method is called by the {@link GameMechanics#playTurn(int)} and {@link GameMechanics#oppositionPlayTurn()} methods to determine the amount of damage dealt to the recipient through a heavy attack.
//         *
//         * @param chance the chance of the attack hitting
//         * @param i the index of the attacker Athlete object in an arraylist
//         * @param j the index of the attacked Athlete object in an arraylist
//         * @return the damage that the attack will give
//         */
//        public static double attackHeavy(int chance, int i, int j){
//                if (chance > 6){
//                        return 5 * chance + 20 + ((athleteList.get(i).getOffence() + oppositionAthletes.get(j).getDefence()) / 10) ;
//                } else {
//                        return 0;
//                }
//        }
//
//        /**
//         * This method is called by the {@link GameMechanics#playTurn(int)} and {@link GameMechanics#oppositionPlayTurn()} methods to determine the amount of health to be received.
//         * @param i the index of the Athlete to be healed object in an arraylist
//         * @return the amount of health that the athlete will receive
//         */
//        public static double heal(int i){
//                double heal = (athleteList.get(i).getStamina()/5) + (athleteList.get(i).getDefence()/10);
//                return -1*heal;
//        }
//
//        /**
//         * This method is called to determine the overall difficulty of the opposition.
//         * @return the overall difficulty of the opposition.
//         */
//        public static double getOppDiff(){
//                int oppositionDiff = 0;
//                for (Athlete oppositionAthlete : oppositionAthletes)
//                        oppositionDiff += oppositionAthlete.getOffence() + oppositionAthlete.getDefence();
//
//                return oppositionDiff / (oppositionAthletes.size() * 2);
//        }
//
//        /**
//         * Updates {@link MatchWindow#teamOutput} with what String is required
//         * @param string the String that is being used to update the GUI
//         */
//        public static void teamGameOutput(String string){
//                MatchWindow.teamOutput.setText(string);
//                MatchWindow.teamOutput.revalidate();
//        }
//
//        /**
//         * Updates {@link MatchWindow#oppositionOutput} with what String is required
//         * @param string the String that is being used to update the GUI
//         */
//        public static void oppGameOutput(String string){
//                MatchWindow.oppositionOutput.setText(string);
//                MatchWindow.oppositionOutput.revalidate();
//        }
//
//        /**
//         * Updates {@link MatchWindow#gameOutput} with what String is required
//         * @param string the String that is being used to update the GUI
//         */
//        public static void gameOutput(String string){
//                MatchWindow.gameOutput.setText(string);
//                MatchWindow.gameOutput.revalidate();
//        }
//}
