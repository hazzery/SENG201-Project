import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class that models an item
 */
public class Item implements Purchasable {

    private final String name;
    private final Athlete.StatType statType;
    private final int improvementAmount;


    static private boolean nameScannerIsInitialised = false;
    static private Stack<String> itemNames;


    /**
     * Creates am item with randomised stats
     */
    public Item() {
        initItemNameReader();
        this.name = itemNames.pop().trim();
        this.statType = Athlete.StatType.values()[ThreadLocalRandom.current().nextInt(0, 3)];
        this.improvementAmount = ThreadLocalRandom.current().nextInt(1, 101);
    }

    private void initItemNameReader() {
        if (nameScannerIsInitialised) return;

        try {
            itemNames = new Stack<String>();
            File itemNameFile = new File("Resources/ItemNames.txt");
            Scanner nameScanner = new Scanner(itemNameFile);
            while (nameScanner.hasNextLine()) {
                itemNames.push(nameScanner.nextLine());
            }
            nameScanner.close();
            nameScannerIsInitialised = true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
	 * Creates an item with the given stats
	 *
     * @param statType The stat in which consuming this item improves
     * @param improvementAmount The amount in which consuming this item improves the stat
	 */
    public Item(String name, Athlete.StatType statType, int improvementAmount) {
        this.name = name;
        this.statType = statType;
        this.improvementAmount = improvementAmount;
    }

    /**
     * Gets the name of the item
     *
     * @return The name of the item
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Gets the stat type of the item
     *
     * @return The stat type of the item
     */
    public Athlete.StatType getStatType() {
        return this.statType;
    }

    /**
     * Gets the improvement amount of the item
     *
     * @return The improvement amount of the item
     */
    public int getImprovementAmount() {
        return this.improvementAmount;
    }

    /**
     * Gets the contract sale price of the athlete
     * The contract price is the amount the athlete originally costs to purchase
     *
     * @return The contract price of the athlete
     */
    @Override
    public int getContractPrice() {
        return improvementAmount * 5 + 175;
    }

    @Override
    public Map<String, String> getStats() {
        return Map.of(statType.name(), String.valueOf(improvementAmount));
    }

    /**
     * Gets the sell back price of the athlete
     * The sell back price is the amount the user can get back for selling an athlete they already own
     *
     * @return The sell back price of the athlete
     */
    @Override
    public int getSellBackPrice() {
        return improvementAmount * 2 + 150;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
