import java.util.ArrayList;

public class GameManager {
    private static int seasonLength;
    private static boolean hardMode;
    public static Team team;


    private static int bankBalance = 10000;
    private static int currentWeek = 1;

    public static final int NUM_ALL_ATHLETES = 10;
    private static final int NUM_ALL_ITEMS = 10;
    static ArrayList<Athlete> athletes = new ArrayList<>(NUM_ALL_ATHLETES);
    private static ArrayList<Item> items = new ArrayList<>(NUM_ALL_ITEMS);


    //THESE NUMBERS MUST BE CHANGED BASED ON DIFFICULTY AND PROGRESSION IN SEASON
    public static int minimumStatValue;
    public static int maximumStatValue;

    /**
     * Fills the `athletes` array with `NUM_ALL_ATHLETES` random athletes
     */
    public static void initializeAthletes() {
        for (int i = 0; i < NUM_ALL_ATHLETES; i++)
            athletes.add(new Athlete());
    }

    public static void initializeOpposition() {
        OppositionTeam.generateOpposition();
    }

    /**
     * Fills the items array with `NUM_ALL_ITEMS` random items
     */
    public static void initializeItems() {
        for (int i = 0; i < NUM_ALL_ITEMS; i++)
            items.add(new Item());
    }

    /**
     * Starts the game with the provided parameters
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

    /**
     * Increments the current week by one
     */
    public static void nextWeek() {
        
        currentWeek++;
    }

    public static Item[] getItems() {
        return items.toArray(new Item[0]);
    }

    public static Athlete[] generateAthletes(int n) {
        Athlete[] athletes = new Athlete[n];

        for (int i = 0; i < n; i++)
            athletes[i] = new Athlete();

        return athletes;
    }

    public static Item[] generateItems(int n) {
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++)
            items[i] = new Item();

        return items;
    }


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
        WindowManager.gameScreen.updateTeamInfo();

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
                throw new MustSwapReserveException("Must swap in a reserve athlete before selling an active athlete");

            team.removeAthlete(athlete);
        }
        else if (purchasable instanceof Item item)
            items.remove(item);

        bankBalance += purchasable.getSellBackPrice();
        WindowManager.gameScreen.updateTeamInfo();
    }

    public static void setConfiguration(String teamName, int seasonLength, boolean hardMode) {
        GameManager.seasonLength = seasonLength;
        GameManager.team = new Team(teamName);
        GameManager.hardMode = hardMode;
    }
}
