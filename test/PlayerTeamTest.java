import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTeamTest {

    @Test
    void size() {
        PlayerTeam team = new PlayerTeam("Test");
        assertEquals(0, team.size());
        team.addAthlete(new Athlete("Test", 0, 0, 0), false);
        assertEquals(1, team.size());
    }

    @Test
    void swapAthletes() {
        PlayerTeam team = new PlayerTeam("Test");
        Athlete active = new Athlete("Test", 0, 0, 0);
        Athlete reserve = new Athlete("Test", 0, 0, 0);
        team.addAthlete(active, false);
        team.addAthlete(reserve, true);

        team.swapAthletes(active, reserve);
        assertEquals(1, team.numActive());
        assertEquals(1, team.numReserves());
        assertEquals(reserve, team.getActives()[0]);
        assertEquals(active, team.getReserves()[0]);
    }

    @Test
    void setActive() {
        PlayerTeam team = new PlayerTeam("Test");
        Athlete athlete = new Athlete("Test", 0, 0, 0);
        team.addAthlete(athlete, true);

        team.setActive(athlete);
        assertEquals(1, team.numActive());
        assertEquals(0, team.numReserves());
        assertEquals(athlete, team.getActives()[0]);

        assertThrows(IllegalStateException.class, () -> team.setActive(athlete));
    }

    @Test
    void setReserve() {
        PlayerTeam team = new PlayerTeam("Test");
        Athlete athlete = new Athlete("Test", 0, 0, 0);
        team.addAthlete(athlete, false);

        team.setReserve(athlete);
        assertEquals(0, team.numActive());
        assertEquals(1, team.numReserves());
        assertEquals(athlete, team.getReserves()[0]);

        assertThrows(IllegalStateException.class, () -> team.setReserve(athlete));
    }

    @Test
    void addAthlete() {
        PlayerTeam team = new PlayerTeam("Test");

        Athlete active = new Athlete("Test", 0, 0, 0);
        Athlete reserve = new Athlete("Test", 0, 0, 0);
        team.addAthlete(active, false);
        team.addAthlete(reserve, true);

        assertEquals(1, team.numActive());
        assertEquals(1, team.numReserves());
        assertEquals(active, team.getActives()[0]);
        assertEquals(reserve, team.getReserves()[0]);
    }

    @Test
    void removeAthlete() {
        PlayerTeam team = new PlayerTeam("Test");
        Athlete athlete = new Athlete("Test", 0, 0, 0);
        team.addAthlete(athlete, false);

        team.removeAthlete(athlete);
        assertEquals(0, team.numActive());
        assertEquals(0, team.numReserves());
    }

    @Test
    void isActive() {
        PlayerTeam team = new PlayerTeam("Test");
        Athlete active = new Athlete("Test", 0, 0, 0);
        Athlete reserve = new Athlete("Test", 0, 0, 0);

        team.addAthlete(active, false);
        team.addAthlete(reserve, true);

        assertTrue(team.isActive(active));
        assertFalse(team.isActive(reserve));
    }

    @Test
    void athletesAndItems() {
        PlayerTeam team = new PlayerTeam("Test");
        Athlete active = new Athlete();
        Athlete reserve = new Athlete();
        GameManager.initializeItems();
        int num_items = GameManager.getItems().length;

        team.addAthlete(active, false);
        team.addAthlete(reserve, true);

        assertEquals(1, team.numActive());
        assertEquals(1, team.numReserves());

        assertEquals(num_items + 2, team.athletesAndItems().length);
    }
}