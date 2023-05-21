import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OppositionTeamTest {

    @Test
    void size() {
        OppositionTeam oppositionTeam = new OppositionTeam();
        assertEquals(Team.TEAM_SIZE, oppositionTeam.size());
    }

    @Test
    void getAthletes() {
        OppositionTeam oppositionTeam = new OppositionTeam();
        for (int i = 0; i < Team.TEAM_SIZE; i++) {
            assertNotNull(oppositionTeam.getAthletes()[i]);
        }
    }

    @Test
    void generateOppositions() {
        OppositionTeam[] oppositionTeams = OppositionTeam.generateOppositions(5);
        assertEquals(5, oppositionTeams.length);
        for (OppositionTeam oppositionTeam : oppositionTeams) {
            assertNotNull(oppositionTeam);
        }
    }
}