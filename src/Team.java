import java.util.ArrayList;

/**
 * Team is the abstract base class behind {@link PlayerTeam} and {@link OppositionTeam}
 * It provides a common interface for use with {@link TeamPanel}
 *
 * @author Harrison Parkes
 */
public abstract class Team {
    public static final int TEAM_SIZE = 5;

    protected String name;
    protected final ArrayList<Athlete> actives;

    /**
     * Instantiates a new team with the provided name
     *
     * @param name a name for the team
     */
    public Team(String name) {
        this.name = name;
        this.actives = new ArrayList<>(TEAM_SIZE);
    }

    /**
	 * Gets the name of the team
	 *
	 * @return The team's name
	 */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the number of athletes in the team
     *
     * @return the total number of athletes in the team
     */
    public abstract int size();

    /**
     * Gets the athletes in the team as an array
     *
     * @return An array containing all athletes in the team
     */
    public abstract Athlete[] getAthletes();

    /**
     * Gets the average offence stat of all the active athletes in the team
     *
     * @return the team's average offence stat
     */
    public double getAverageOffence() {
        double total = 0;
        for (Athlete athlete : this.actives) {
            total += athlete.getOffence();
        }
        return total / this.actives.size();
    }

    /**
     * Gets the average defence stat of all the active athletes in the team
     *
     * @return the team's average defence stat
     */
    public double getAverageDefence() {
        double total = 0;
        for (Athlete athlete : this.actives) {
            total += athlete.getDefence();
        }
        return total / this.actives.size();
    }

    /**
     * Gets the team's difficulty score, calculated off of average offence and defence
     * @return the average of the average offence and defence
     */
    public double getDifficulty() {
        return (this.getAverageOffence() + this.getAverageDefence()) / 2;
    }
}
