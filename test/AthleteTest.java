import data.Athlete;
import data.Item;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AthleteTest {

    @Test
    void constructor() {
        Athlete athlete = new Athlete();
        assertTrue(athlete.getName().length() >= 3);
        assertEquals(athlete.getName(), athlete.getNickname());
        assertTrue(athlete.getStamina() >= 80 && athlete.getStamina() <= 100);
        assertTrue(athlete.getOffence() >= 1 && athlete.getStamina() <= 100);
        assertTrue(athlete.getDefence() >= 1 && athlete.getStamina() <= 100);
        assertEquals(100, athlete.getCurrentHealth());
    }

    @Test
    void setNickName() {
        Athlete athlete = new Athlete();
        athlete.setNickname("Test");
        assertEquals("Test", athlete.toString());
    }

    @Test
    void getStats() {
        Athlete athlete = new Athlete("Test", 80, 1, 1);
        assertEquals(Map.of(
            "Stamina", "80",
            "Offence", "1",
            "Defence", "1",
            "Current Health", "100"
        ), athlete.getStats());

        Athlete athlete2 = new Athlete("Test", -999, -999, -999);
        assertEquals(Map.of(
                "Stamina", "80",
                "Offence", "1",
                "Defence", "1",
                "Current Health", "100"
        ), athlete2.getStats());

        Athlete athlete3 = new Athlete("Test", 100, 100, 100);
        assertEquals(Map.of(
                "Stamina", "100",
                "Offence", "100",
                "Defence", "100",
                "Current Health", "100"
        ), athlete3.getStats());
    }

    @Test
    void getContractPrice() {
        // Parameterised constructor sets stamina, offence, and defence to 80, 1, and 1 respectively
        // if negative values are passed
        Athlete athlete = new Athlete("Test", -999, -999, -999);
        assertEquals(820, athlete.getContractPrice());

        athlete = new Athlete("Test", 80, 1, 1);
        assertEquals(820, athlete.getContractPrice());

        athlete = new Athlete("Test", 100, 100, 100);
        assertEquals(3000, athlete.getContractPrice());
    }

    @Test
    void getSellBackPrice() {
        Athlete athlete = new Athlete("Test", -999, -999, -999);
        assertEquals(346, athlete.getSellBackPrice());

        Athlete athlete2 = new Athlete("Test", 80, 1, 1);
        assertEquals(346, athlete2.getSellBackPrice());

        Athlete athlete3 = new Athlete("Test", 100, 100, 100);
        assertEquals(1000, athlete3.getSellBackPrice());
    }

    @Test
    void applyItem() {
        Athlete athlete = new Athlete("Test", 80, 1, 1);

        Item item = new Item("Test", Athlete.StatType.STAMINA, 5);
        athlete.applyItem(item);
        assertEquals(85, athlete.getStamina());

        Item item2 = new Item("Test", Athlete.StatType.OFFENCE, 5);
        athlete.applyItem(item2);
        assertEquals(6, athlete.getOffence());


        Item item3 = new Item("Test", Athlete.StatType.DEFENCE, 5);
        athlete.applyItem(item3);
        assertEquals(6, athlete.getDefence());
    }

    @Test
    void byeWeek() {
        Athlete athlete = new Athlete("Test", 80, 1, 1);
        athlete.injure();
        athlete.byeWeek();

        assertEquals(100, athlete.getStamina());
        assertEquals(100, athlete.getCurrentHealth());
        assertFalse(athlete.getInjury());
}

    @Test
    void trainAthlete() {
        Athlete athlete = new Athlete("Test", 80, 1, 1);
        athlete.trainAthlete();
        assertEquals(26, athlete.getOffence());
        assertEquals(26, athlete.getDefence());
    }
}