import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void getContractPrice() {
        Item item = new Item("test", Athlete.StatType.STAMINA, 1);
        assertEquals(180, item.getContractPrice());
    }

    @Test
    void getStats() {
        Item item = new Item("test", Athlete.StatType.STAMINA, 1);
        assertEquals(Map.of(
                "STAMINA", "1"
        ), item.getStats());
    }

    @Test
    void getSellBackPrice() {
        Item item = new Item("test", Athlete.StatType.STAMINA, 1);
        assertEquals(152, item.getSellBackPrice());
    }
}