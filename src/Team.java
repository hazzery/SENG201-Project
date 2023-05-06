import java.util.ArrayList;

/**
 * Class the models a team
 */
public class Team {
    public static final int MIN_SIZE = 4;
    private final String name;
    private final Athlete[] athletes;

    /**
	 * Creates a team with the given name
     * 
     * @param name A name for the team
	 */
    public Team(String name, ArrayList<Athlete> athletes) {
        this.name = name;
        this.athletes = athletes.toArray(new Athlete[0]);
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
	 * Gets the size of the team
	 *
	 * @return The number of athletes in the team
	 */
    public int size() {
        return this.athletes.length;
    }

    /**
	 * Determines if the team contains the given athlete
	 *
     * @param athlete An Athlete to check membership of
	 * @return `true` if the team contains the athlete, `false` otherwise
     */
    public boolean contains(Athlete athlete) {
        for (Athlete a : this.athletes) {
            if (a.equals(athlete)) {
                return true;
            }
        }
        return false;
    }
}
