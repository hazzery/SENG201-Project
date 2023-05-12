import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Class the models a team
 */
public class Team {
    public static final int MIN_SIZE = 4;
    public static final int MAX_RESERVES = 5;
    private final String name;
    private final ArrayList<Athlete> actives;
    private final ArrayList<Athlete> reserves;

    /**
	 * Creates a team with the given name
     * 
     * @param name A name for the team
     * @param athletes All athletes to be put in the team
	 */
    public Team(String name, ArrayList<Athlete> athletes) {
        this.name = name;
        this.actives = new ArrayList<>(athletes.size());
        this.actives.addAll(athletes);
        this.reserves = new ArrayList<>(5);
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
	 * Gets the number of athletes actively participating in the team
	 *
	 * @return The number of athletes in the team
	 */
    public int numActive() {
        return this.actives.size();
    }

    /**
     * Gets the number of athletes set as reserves
     *
     * @return The number of athletes in the team
     */
    public int numReserves() {
        return this.reserves.size();
    }

    public Athlete getActive(int index) {
        return this.actives.get(index);
    }

    public Athlete getReserve(int index) {
        return this.reserves.get(index);
    }

    public void setActive(Athlete athlete) {
        this.actives.add(athlete);
        reserves.remove(athlete);
    }

    public boolean setReserve(Athlete athlete) throws IllegalStateException {
        if (this.reserves.size() >= MAX_RESERVES) {
            throw new IllegalStateException("Cannot have more than " + MAX_RESERVES + " reserves");
        }
        this.reserves.add(athlete);
        actives.remove(athlete);
        return true;
    }

    Athlete[] fullTeam() {
        return Stream.concat(actives.stream(), reserves.stream()).toArray(Athlete[]::new);
    }

    /**
	 * Determines if the team contains the given athlete
	 *
     * @param athlete An Athlete to check membership of
	 * @return `true` if the team contains the athlete, `false` otherwise
     */
    public boolean contains(Athlete athlete) {
        for (Athlete a : this.fullTeam()) {
            if (a.equals(athlete))
                return true;
        }
        return false;
    }

    public void addAthlete(Athlete athlete) {
        this.actives.add(athlete);
    }
}
