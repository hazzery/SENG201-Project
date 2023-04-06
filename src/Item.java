public class Item implements Purchasable {

    private Athlete.StatType statType;
    private int improvementAmount;
    private int contractPrice;
    private int sellBackPrice;
    private String description;

    public Item(int contractPrice, int sellBackPrice, String description, Athlete.StatType statType, int improvementAmount) {
        this.contractPrice = contractPrice;
        this.sellBackPrice = sellBackPrice;
        this.description = description;
        this.statType = statType;
        this.improvementAmount = improvementAmount;
    }

    public int getContractPrice() {
        return this.contractPrice;
    }

    public int getSellBackPrice() {
        return this.sellBackPrice;
    }

    public String getDescription() {
        return this.description;
    }
}
