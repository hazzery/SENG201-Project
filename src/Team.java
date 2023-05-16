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
    public ArrayList<PurchasablesShelf> activesSubscribers = new ArrayList<>();
    public ArrayList<PurchasablesShelf> reservesSubscribers = new ArrayList<>();

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

    public int size() {
        return this.actives.size() + this.reserves.size();
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

    public Athlete[] getActives() {
        return this.actives.toArray(new Athlete[0]);
    }

    public Athlete[] getReserves() {
        return this.reserves.toArray(new Athlete[0]);
    }

    public Athlete getActive(int index) {
        return this.actives.get(index);
    }

    public Athlete getReserve(int index) {
        return this.reserves.get(index);
    }

    public boolean setActive(Athlete athlete) throws IllegalStateException {
        if (reserves.contains(athlete)) {
            reserves.remove(athlete);
            actives.add(athlete);
        }
        else {
            throw new IllegalStateException("Cannot activate an athlete that is not a reserve");
        }
        return true;
    }

    public boolean setReserve(Athlete athlete) throws IllegalStateException {
        if (this.reserves.size() >= MAX_RESERVES) {
            throw new IllegalStateException("Cannot have more than " + MAX_RESERVES + " reserves");
        }
        this.reserves.add(athlete);
        actives.remove(athlete);
        return true;
    }

    Athlete[] getAll() {
        return Stream.concat(actives.stream(), reserves.stream()).toArray(Athlete[]::new);
    }

    /**
	 * Determines if the team contains the given athlete
	 *
     * @param athlete An Athlete to check membership of
	 * @return `true` if the team contains the athlete, `false` otherwise
     */
    public boolean contains(Athlete athlete) {
        for (Athlete a : this.getAll()) {
            if (a.equals(athlete))
                return true;
        }
        return false;
    }

    public void addAthlete(Athlete athlete, boolean reserve) {
        if (reserve)
            this.reserves.add(athlete);
        else
            this.actives.add(athlete);
    }

    public void removeAthlete(Athlete athlete) {
        try {
            this.actives.remove(athlete);
        } catch (Exception e) {
            this.reserves.remove(athlete);
        }
    }

    public void addActivesSubscriber(PurchasablesShelf shelf) {
        this.activesSubscribers.add(shelf);
    }

    public void addReservesSubscriber(PurchasablesShelf shelf) {
        this.reservesSubscribers.add(shelf);
    }
}
