import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


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
    void nextWeek() {
        GameManager.setConfiguration("test", 10, false);
        GameManager.startGame(new ArrayList<>(Arrays.asList(new Athlete(), new Athlete(),
                new Athlete(), new Athlete(), new Athlete(), new Athlete())),0);
        assertEquals(1, GameManager.currentWeek());
        GameManager.nextWeek();
        assertEquals(2, GameManager.currentWeek());
    }

    @Test
    void generateAthletes() {
        Athlete[] athletes = GameManager.generateAthletes(10);

        for(int i = 0; i < 10; i++)
            assertNotNull(athletes[i]);
    }

    @Test
    void generateItems() {
        Item[] items = GameManager.generateItems(10);

        for(int i = 0; i < 10; i++)
            assertNotNull(items[i]);
    }

    @Test
    void useItem() {
        GameManager.initializeItems();
        int initialLength = GameManager.getItems().length;
        Athlete athlete = new Athlete("test", 80, 1, 1);
        GameManager.useItem(GameManager.getItems()[0], athlete);
        assertNotEquals(Map.of(
                "Stamina", "80",
                "Offence", "1",
                "Defence", "1",
                "Current Health", "100"
        ), athlete.getStats());
        assertEquals(initialLength - 1, GameManager.getItems().length);
    }

    @Test
    void purchase() {
        // Initialize the player team
        GameManager.setConfiguration("test", 10, false);

        // We should be capable of purchasing an athlete if there is enough money in the bank
        Athlete athlete = new Athlete();
        GameManager.addFunds(athlete.getContractPrice());

        // This athlete should be activated immediately
        GameManager.purchase(athlete, true);
        assertEquals(1, GameManager.team.numActive());

        // This athlete should be placed in the reserves
        Athlete athlete2 = new Athlete();
        GameManager.addFunds(athlete2.getContractPrice());
        GameManager.purchase(athlete2, false);
        assertEquals(1, GameManager.team.numReserves());

        // We should not be capable of purchasing an athlete if there is not enough money in the bank
        assertThrows(IllegalStateException.class, () -> GameManager.purchase(new Athlete(), false));

        // The same function should handle items too
        Item item = new Item("test", Athlete.StatType.OFFENCE, 1);
        GameManager.addFunds(item.getContractPrice());
        System.out.println(Arrays.toString(GameManager.getItems()));
        GameManager.purchase(item, false);
        assertEquals(1, GameManager.getItems().length);
        assertThrows(IllegalStateException.class, () -> GameManager.purchase(new Item("test", Athlete.StatType.OFFENCE, 1), false));
    }

    @Test
    void sell() {
        GameManager.setConfiguration("test", 10, false);

        // Athletes can only be sold if there are at least `Team.TEAM_SIZE + 1` athletes on the team
        // As this there are no athletes on the team, selling should not be possible
        assertThrows(IllegalStateException.class, () -> GameManager.sell(new Athlete()));

        // Add enough athletes to the team to be able to sell one athlete
        // placing exactly `Team.TEAM_SIZE` athletes as actives and one as a reserve
        for (int i = 0; i < Team.TEAM_SIZE; i++)
            GameManager.team.addAthlete(new Athlete(), false);
        GameManager.team.addAthlete(new Athlete(), true);

        // This lets us test that we cannot sell an athlete that is currently active
        // without first activating a reserve
        assertThrows(MustSwapReserveException.class, () -> GameManager.sell(GameManager.team.getActives()[0]));

        int athletePrice = GameManager.team.getReserves()[0].getSellBackPrice();

        // Selling the reserve athlete should be possible as we have enough active athletes
        GameManager.sell(GameManager.team.getReserves()[0]);

        // We had `Team.TEAM_SIZE + 1` athletes on the team, and we sold one, so we should have `Team.TEAM_SIZE` left
        assertEquals(Team.TEAM_SIZE, GameManager.team.getAthletes().length);
        assertEquals(athletePrice, GameManager.getBankBalance());

        Item item = new Item("test", Athlete.StatType.OFFENCE, 1);
        int balanceDecrease = item.getSellBackPrice() - item.getContractPrice();
        GameManager.purchase(item, false);
        GameManager.sell(item);
        assertEquals(athletePrice + balanceDecrease, GameManager.getBankBalance());
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