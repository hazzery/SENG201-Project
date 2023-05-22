import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GameManagerTest {
    @Test
    void startGame() {
        GameManager.setConfiguration("test", 10, false);
        GameManager.startGame(new ArrayList<>(Arrays.asList(new Athlete(), new Athlete(),
                        new Athlete(), new Athlete(), new Athlete(), new Athlete())),0);
        assertEquals(0, GameManager.getBankBalance());
        assertEquals(5, GameManager.team.getActives().length);
        assertEquals(1, GameManager.team.getReserves().length);
    }

    @Test
    void generateAthletes() {
        Athlete[] athletes = GameManager.generateAthletes(10);
        for(int i = 0; i < 10; i++) {
            assertNotNull(athletes[i]);
        }
    }

    @Test
    void generateItems() {
        Item[] items = GameManager.generateItems(10);
        for(int i = 0; i < 10; i++) {
            assertNotNull(items[i]);
        }
    }

    @Test
    void useItem() {
        GameManager.initializeItems();
        int initialLength = GameManager.getItems().length;
        Athlete athlete = new Athlete("irrelevant", 0, 0, 0);
        GameManager.useItem(GameManager.getItems()[0], athlete);
        assertNotEquals(Map.of(
                "Stamina", "0",
                "Offence", "0",
                "Defence", "0",
                "Current Health", "100"
        ), athlete.getStats());
        assertEquals(initialLength - 1, GameManager.getItems().length);
    }

    @Test
    void purchase() {
        GameManager.setConfiguration("test", 10, false);
        Athlete athlete = new Athlete();
        GameManager.addFunds(athlete.getContractPrice());
        GameManager.purchase(athlete, false);
        assertEquals(1, GameManager.team.size());
        assertThrows(IllegalStateException.class, () -> GameManager.purchase(new Athlete(), false));
    }

    @Test
    void sell() {
        GameManager.setConfiguration("test", 10, false);

        Athlete athlete = new Athlete();
        int athletePrice = athlete.getSellBackPrice();

        GameManager.team.addAthlete(athlete, false);

        assertThrows(IllegalStateException.class, () -> GameManager.sell(new Athlete()));

        for (int i = 0; i < Team.TEAM_SIZE; i++) {
            GameManager.team.addAthlete(new Athlete(), false);
        }
        GameManager.sell(athlete);

        assertEquals(Team.TEAM_SIZE, GameManager.team.getAthletes().length);
        assertEquals(athletePrice, GameManager.getBankBalance());
    }

    @Test
    void setConfiguration() {
        GameManager.setConfiguration("test", 10, false);
        assertNotNull(GameManager.team);
        assertEquals("test", GameManager.team.getName());
        assertEquals(10, GameManager.getSeasonLength());
        assertEquals(1, GameManager.isGameHard());
    }

    @Test
    void playMatch() {
        OppositionTeam opposition = new OppositionTeam("test");
        GameManager.playMatch(opposition);
        assertNotNull(GameManager.oppositionTeam);
    }
}