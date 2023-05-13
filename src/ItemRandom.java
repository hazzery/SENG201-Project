/**
 * ItemRandom.java
 */
public class ItemRandom {

    /** 
     * Creates a random Item when called upon
     * 
     * @param min The minimum value
     * @param max The maximum value
     * @return The Newly Created Item
     */
    public static Item createRandomItem(int min, int max){
        RandomInt rand = new RandomInt();
        String name = "Item" + rand.createRandomInt(min, max);
        int improvementAmount = rand.createRandomInt(min, max);
        int contractPrice = createItemContractPrice(improvementAmount);
        int sellBackPrice = createAthleteSellBackPrice(improvementAmount);
        //Need to determine how to implement random stat type
        Athlete.StatType statType = Athlete.StatType.DEFENCE; 
        /*STAMINA,
        OFFENCE,
        DEFENCE,
        CURRENT_HEALTH */
        return new Item(name, statType, improvementAmount, contractPrice, sellBackPrice);
    }

    /**
     * Creates a random Item when called upon
     * 
     * @param min The minimum value
     * @param max The maximum value
     * @return The Newly Created Item
     */
    public static int createItemContractPrice(int improvementAmount){
        return improvementAmount * 5 + 175;
    }

    /**
     * Creates a random Item when called upon
     * 
     * @param min The minimum value
     * @param max The maximum value
     * @return The Newly Created Item
     */
    public static int createAthleteSellBackPrice(int improvementAmount){
        return  improvementAmount * 2 + 150;
    }

}

