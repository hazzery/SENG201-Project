import java.util.Map;

public interface Purchasable {
    String getName();
    Map<String, String> getStats();

    int getContractPrice();
    int getSellBackPrice();

    String getDescription();

}
