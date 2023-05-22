import java.util.Arrays;
import java.util.stream.Stream;

import javax.swing.JOptionPane;

import java.util.ArrayList;

/**
 * GameManager is a static utility class in charge of the game state.
 * It provides game logic functionality to the GUI
 *
 * @author Harrison Parkes
 * @author Daniel Smith
 */
public class GameManager {
    private static int seasonLength;
    private static boolean hardMode;

    public static PlayerTeam team;

    public static boolean gameHasBeenPlayed = false;

    private static int bankBalance = 0;
    private static int currentWeek = 1;

    public static OppositionTeam oppositionTeam;
    public static RandomEvent randomEvent;
    public static final int NUM_ALL_ATHLETES = 10;
    private static final int NUM_ALL_ITEMS = 10;
    static ArrayList<Athlete> athletes = new ArrayList<>(NUM_ALL_ATHLETES);
    private static final ArrayList<Item> items = new ArrayList<>(NUM_ALL_ITEMS);


    //THESE NUMBERS MUST BE CHANGED BASED ON DIFFICULTY AND PROGRESSION IN SEASON
    public static int minimumStatValue;
    public static int maximumStatValue;

    /**
     * Fills the athletes array with {@code NUM_ALL_ATHLETES} random athletes
     */
    public static void initializeAthletes() {
        for (int i = 0; i < NUM_ALL_ATHLETES; i++)
            athletes.add(new Athlete());
    }

    /**
     * Fills the items array with {@code NUM_ALL_ITEMS} random items
     */
    public static void initializeItems() {
        for (int i = 0; i < NUM_ALL_ITEMS; i++)
            items.add(new Item());
    }

    /**
     * Starts the game with the provided parameters
     *
     * @param selectedAthletes the athletes selected by the player
     * @param bankBalance the player's starting bank balance
     */
    public static void startGame(ArrayList<Athlete> selectedAthletes, int bankBalance) {
        
        GameManager.bankBalance = bankBalance;
        for (int i = 0; i < Team.TEAM_SIZE; i++) {
            team.addAthlete(selectedAthletes.get(i), false);
        }
        for (int i = Team.TEAM_SIZE; i < selectedAthletes.size(); i++) {
            team.addAthlete(selectedAthletes.get(i), true);
        }
        WindowManager.showGameScreen();
    }

    /**
     * Gets the game difficulty modifier
     * @return the game difficulty modifier
     */
    public static double isGameHard() {
        if (hardMode) {
            return 1.5;
        } else {
            return 1;
        }
    }

    /**
     * Gets the player's current bank balance
     *
     * @return the player's current bank balance
     */
    public static int getBankBalance() {
        return bankBalance;
    }

    /**
     * Gets the current week within the season
     *
     * @return the current week
     */
    public static int currentWeek() {
        return currentWeek;
    }

    /**
     * Gets the length of the season
     *
     * @return the length of the season
     */
    public static int getSeasonLength() {
        return seasonLength;
    }

    public static Item[] getItems() {
        return items.toArray(new Item[0]);
    }
    public static Stream<Item> itemStream() {
        return items.stream();
    }

    /**
     * Increments the current week by one
     */
    public static void nextWeek() {
        if (currentWeek > seasonLength)
            WindowManager.showGameOverScreen();

        addFunds((int) (300 - 100 * isGameHard()));
        //TODO UPDATE FUNDS ON GUI
        if (!gameHasBeenPlayed)
            byeWeek();
        gameHasBeenPlayed =  false;
        currentWeek++;
    }

    /**
     * Tells all athletes to train for the week, instead of playing a game
     */
    public static void byeWeek(){
         int result = JOptionPane.showOptionDialog(null,
                 "Bye Week",
                 "Would you like to recover all your athletes? or train one?",
                 JOptionPane.DEFAULT_OPTION,
                 JOptionPane.WARNING_MESSAGE,
                 null,
                 new String[]{"Train", "Recover all"},
                 null);

         // Check which button was clicked and perform the corresponding action
        switch (result) {
            case 0 -> {
                Athlete athlete = (Athlete) JOptionPane.showInputDialog(null,
                        "Select an athlete to train", "Swap Athlete", JOptionPane.PLAIN_MESSAGE,
                        null, GameManager.team.getActives(), "Choose athlete");
                athlete.trainAthlete();
                JOptionPane.getRootFrame().dispose();
            }
            case 1 -> {
                for (int i = 0; i < Team.TEAM_SIZE; i++) {
                    team.getActive(i).byeWeek();
                }
                JOptionPane.getRootFrame().dispose();
            }
        }
        RandomEvent rando = new RandomEvent();
        rando.randomEvent();
        WindowManager.reloadGameScreen();
    }

    /**
     * Adds funds to the player's bank balance
     * @param funds The amount of money to add
     */
    public static void addFunds(int funds) {
        bankBalance += funds;
    }

    /**
     * Generate new athletes with randomised stats and a new name from the list of names
     * @param n the number of athletes to generate
     * @return an array of {@code n} random athletes
     */
    public static Athlete[] generateAthletes(int n) {
        Athlete[] athletes = new Athlete[n];

        for (int i = 0; i < n; i++)
            athletes[i] = new Athlete();

        return athletes;
    }

    /**
     * Generate new items with randomised stats and a new name from the list of names
     * @param n the number of items to generate
     * @return an array of {@code n} random items
     */
    public static Item[] generateItems(int n) {
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++)
            items[i] = new Item();

        return items;
    }

    /**
     * Uses the provided item on the provided athlete
     * @param item The item to be used
     * @param athlete The athlete to use the item on
     */
    public static void useItem(Item item, Athlete athlete) {
        athlete.applyItem(item);
        items.remove(item);
    }

    /**
     * Purchases the provided purchasable if the player has enough money.
     * Removes the purchasable's contract price from the player's bank balance
     * and adds the purchasable to the player's inventory
     *
     * @param purchasable        the purchasable to purchase
     * @param activateNewAthlete If true, and the purchasable is an athlete, the athlete will be placed in the active team
     * @throws IllegalStateException if the player does not have enough money to purchase the athlete
     */
    public static void purchase(Purchasable purchasable, boolean activateNewAthlete) throws IllegalStateException {
        System.out.println("Purchasing " + purchasable.getName());

        if (bankBalance < purchasable.getContractPrice())
            throw new IllegalStateException("Insufficient money to make purchase");

        bankBalance -= purchasable.getContractPrice();
        if (WindowManager.gameScreen != null) {
            WindowManager.gameScreen.updateTeamInfo();
        }

        if (purchasable instanceof Athlete athlete)
            team.addAthlete(athlete, !activateNewAthlete);

        else if (purchasable instanceof Item item)
            items.add(item);
    }

    /**
     * Sells the provided purchasable and adds the sell back price to the player's bank balance
     *
     * @param purchasable the purchasable to sell
     * @throws IllegalStateException if the player does not have enough athletes to spare when selling an athlete
     */
    public static void sell(Purchasable purchasable) throws IllegalStateException {
        System.out.println("Selling " + purchasable.getName());

        if (purchasable instanceof Athlete athlete) {
            if (GameManager.team.size() <= Team.TEAM_SIZE)
                throw new IllegalStateException("Cannot sell athlete, must have at least " + Team.TEAM_SIZE + " athletes");

            if (GameManager.team.isActive(athlete) && GameManager.team.numActive() <= Team.TEAM_SIZE)
                throw new MustSwapReserveException();

            team.removeAthlete(athlete);
        }
        else if (purchasable instanceof Item item)
            items.remove(item);

        bankBalance += purchasable.getSellBackPrice();

        if (WindowManager.gameScreen != null)
            WindowManager.gameScreen.updateTeamInfo();
    }

    /**
     * Sets the game configuration
     * @param teamName The name of the player's team
     * @param seasonLength The length of the season
     * @param hardMode `true` if the game is in hard mode, `false` otherwise
     */
    public static void setConfiguration(String teamName, int seasonLength, boolean hardMode) {
        GameManager.seasonLength = seasonLength;
        GameManager.team = new PlayerTeam(teamName);
        GameManager.hardMode = hardMode;
    }

    /**
     * Sets the game into a new match with the provided opposition
     * @param opposition The opposing team the user selected
     */
    public static void playMatch(OppositionTeam opposition) {
        GameMechanics gameMechanics = new GameMechanics();
        GameManager.oppositionTeam = opposition;
        gameMechanics.playGame(currentWeek, new ArrayList<>(Arrays.asList(team.getActives())), opposition.getAthletes());
        WindowManager.showMatchScreen();
    }
}
