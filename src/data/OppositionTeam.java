package data;

import management.GameManager;
import utility.NameFileReader;


/**
 * Opposition is the container class that holds all information relevant to the many
 * randomly generated oppositions that are generated each week
 *
 * @author Daniel Smith
 * @author Harrison Parkes
 */
public class OppositionTeam extends Team {
    private static final NameFileReader nameReader = new NameFileReader("Resources/OppositionNames.txt");

    /**
     * Instantiates a new opposition with random {@link Athlete}s and a name from the list of opposition names
     */
    public OppositionTeam() {
        this("Unnamed team");
        this.name = nameReader.next();
    }

    /**
     * Instantiates a new opposition with random {@link Athlete}s and a specified name
     * @param name A name for the team
     */
    public OppositionTeam(String name) {
        super(name);
        for (int i = 0; i < TEAM_SIZE; i++) {
            Athlete athlete = new Athlete();

            //Crude State increase based on difficulty
            athlete.offence += 10 + GameManager.currentWeek() * GameManager.isGameHard();
            athlete.defence += 10 + GameManager.currentWeek() * GameManager.isGameHard();
            //Daniel this almost made me cry why did you make all the variables public

            actives.add(athlete);
        }
    }

    /**
     * Gets the number of {@link Athlete}s in the opposition team
     * @return The number of athletes in the team
     */
    @Override
    public int size() {
        return actives.size();
    }

    /**
     * Gets the {@link Athlete}s in the opposition team as an array
     * @return An array containing all the athletes in this opposition team
     */
    @Override
    public Athlete[] getAthletes() {
        return actives.toArray(new Athlete[0]);
    }

    /**
     * Generates an array of opposition teams
     * @param number The number of teams to generate
     * @return An array of {@code n} opposition teams
     */
    public static OppositionTeam[] generateOppositions(int number) {
        OppositionTeam[] teams = new OppositionTeam[number];
        for (int i = 0; i < number; i++) {
            teams[i] = new OppositionTeam();
        }
        return teams;
    }
}
