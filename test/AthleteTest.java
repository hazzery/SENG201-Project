import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AthleteTest {

    @Test
    void constructor() {
        Athlete athlete = new Athlete();
        assertNotNull(athlete);
        assertNotNull(athlete.getName());
        assertNotNull(athlete.getNickname());
    }

    @Test
    void getStats() {
        Athlete athlete = new Athlete("Test", 1, 1, 1);
        assertEquals(Map.of(
            "Stamina", "1",
            "Offence", "1",
            "Defence", "1",
            "Current Health", "100"
        ), athlete.getStats());
    }

    @Test
    void getContractPrice() {
        Athlete athlete = new Athlete("Test", 1, 1, 1);
        assertEquals(30, athlete.getContractPrice());
    }

    @Test
    void getSellBackPrice() {
        Athlete athlete = new Athlete("Test", 1, 1, 1);
        assertEquals(109, athlete.getSellBackPrice());
    }

    @Test
    void applyItem() {
        Athlete athlete = new Athlete("Test", 1, 1, 1);
        Item item = new Item("Test", Athlete.StatType.OFFENCE, 5);
        athlete.applyItem(item);
        assertEquals(6, athlete.getOffence());
    }

    @Test
    void byeWeek() {
        Athlete athlete = new Athlete("Test", 1, 1, 1);
        athlete.isInjured = true;
        athlete.current_health = 0;
        athlete.byeWeek();

        assertEquals(100, athlete.getStamina());
        assertEquals(100, athlete.current_health);
        assertFalse(athlete.isInjured);
}

    @Test
    void trainAthlete() {
        Athlete athlete = new Athlete("Test", 1, 1, 1);
        athlete.trainAthlete();
        assertEquals(11,athlete.getOffence());
        assertEquals(11, athlete.getDefence());
    }
}