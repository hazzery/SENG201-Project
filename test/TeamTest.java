import data.Athlete;
import data.PlayerTeam;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    @Test
    void getAverageOffence() {
        PlayerTeam team = new PlayerTeam("Test");
        assertEquals(0, team.getAverageOffence());

        Athlete athlete1 = new Athlete("Test", 1, 5, 1);
        Athlete athlete2 = new Athlete("Test", 1, 10, 1);
        Athlete athlete3 = new Athlete("Test", 1, 15, 1);
        team.addAthlete(athlete1, false);
        team.addAthlete(athlete2, false);
        team.addAthlete(athlete3, false);

        assertEquals(10, team.getAverageOffence());
    }

    @Test
    void getAverageDefence() {
        PlayerTeam team = new PlayerTeam("Test");
        assertEquals(0, team.getAverageDefence());

        Athlete athlete1 = new Athlete("Test", 1, 1, 5);
        Athlete athlete2 = new Athlete("Test", 1, 1, 10);
        Athlete athlete3 = new Athlete("Test", 1, 1, 15);
        team.addAthlete(athlete1, false);
        team.addAthlete(athlete2, false);
        team.addAthlete(athlete3, false);

        assertEquals(10, team.getAverageDefence());
    }

    @Test
    void getDifficulty() {
        PlayerTeam team = new PlayerTeam("Test");
        assertEquals(0, team.getDifficulty());

        Athlete athlete1 = new Athlete("Test", 1, 5, 5);
        Athlete athlete2 = new Athlete("Test", 1, 10, 10);
        Athlete athlete3 = new Athlete("Test", 1, 15, 15);
        team.addAthlete(athlete1, false);
        team.addAthlete(athlete2, false);
        team.addAthlete(athlete3, false);

        assertEquals(10, team.getDifficulty());
    }
}