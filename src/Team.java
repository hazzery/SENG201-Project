import java.util.ArrayList;

/**
 * Class the models a team
 */
public class Team {
    public static final int MIN_SIZE = 4;
    public static final int MAX_RESERVES = 5;
    private final String name;
    private final ArrayList<Athlete> athletes;
    private final ArrayList<Athlete> reserves;

    /**
	 * Creates a team with the given name
     * 
     * @param name A name for the team
     * @param athletes All athletes to be put in the team
	 */
    public Team(String name, ArrayList<Athlete> athletes) {
        this.name = name;
        this.athletes = new ArrayList<>();

        for (Athlete athlete : athletes) {
            this.athletes.add(athlete);
        }

        this.reserves = new ArrayList<>();
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
        return this.athletes.size();
    }

    /**
     * Gets the number of athletes set as reserves
     *
     * @return The number of athletes in the team
     */
    public int numReserves() {
        return this.reserves.size();
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

    public Athlete getActive(int index) {
        return this.athletes.get(index);
    }
    public Athlete getReserve(int index) {
        return this.reserves.get(index);
    }

    public void setActive(Athlete athlete) {
        this.athletes.add(athlete);
        reserves.remove(athlete);
    }

    public void setReserve(Athlete athlete) {
        if (this.reserves.size() >= MAX_RESERVES) {
            throw new IllegalStateException("Cannot have more than 2 reserves");
        }
        this.reserves.add(athlete);
        athletes.remove(athlete);
    }

}
