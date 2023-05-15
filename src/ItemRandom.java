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
        //Need to determine how to implement random stat type
        Athlete.StatType statType = Athlete.StatType.DEFENCE; 
        /*STAMINA,
        OFFENCE,
        DEFENCE,
        CURRENT_HEALTH */
        return new Item(name, statType, improvementAmount);
    }
}

