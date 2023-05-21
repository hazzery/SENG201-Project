import java.util.stream.Stream;
import java.util.ArrayList;


/**
 * PlayerTeam is the container class that holds all information relevant to the player's team of {@link Athlete}s
 *
 * @author Harrison Parkes
 */
public class PlayerTeam extends Team {
    public static final int MAX_RESERVES = 5;
    public static final int MAXIMUM_SIZE = TEAM_SIZE + MAX_RESERVES;

    private final ArrayList<Athlete> reserves;

    /**
     * Creates a team with the given name
     *
     * @param name A name for the team
     */
    public PlayerTeam(String name) {
        super(name);
        this.reserves = new ArrayList<>(MAX_RESERVES);
    }

    /**
     * Gets the total number of athletes in the team
     *
     * @return The total number of athletes in this team
     */
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

    /**
     * Gets all active athletes as an array
     * @return An array containing all active athletes
     */
    public Athlete[] getActives() {
        return this.actives.toArray(new Athlete[0]);
    }

    /**
     * Gets all reserved athletes as an array
     * @return An array containing all reserved athletes
     */
    public Athlete[] getReserves() {
        return this.reserves.toArray(new Athlete[0]);
    }

    /**
     * Swaps out an active athlete for a reserved one
     * @param currentlyActive An athlete that is currently active and should be reserved
     * @param currentlyReserved An athlete that is currently reserved and should be activated
     */
    public void swapAthletes(Athlete currentlyActive, Athlete currentlyReserved) {
        System.out.println("Swapping " + currentlyActive.getName() + " with " + currentlyReserved.getName());
        if (!this.actives.contains(currentlyActive) || !this.reserves.contains(currentlyReserved))
            throw new IllegalArgumentException("Cannot swap these athletes");

        this.actives.remove(currentlyActive);
        this.reserves.remove(currentlyReserved);
        this.actives.add(currentlyReserved);
        this.reserves.add(currentlyActive);
    }

    /**
     * Sets a reserved athlete as active
     * @param athlete The athlete to activate
     * @throws IllegalStateException If the athlete is not a reserve
     */
    public void setActive(Athlete athlete) throws IllegalStateException {
        System.out.println("Setting " + athlete.getName() + " as active");

        if (!reserves.contains(athlete))
            throw new IllegalStateException("Cannot activate an athlete that is not a reserve");

        reserves.remove(athlete);
        actives.add(athlete);
    }

    /**
     * Sets an activated athlete as a reserve
     * @param athlete the athlete to reserve
     * @throws IllegalStateException If the athlete is not active
     */
    public void setReserve(Athlete athlete) throws IllegalStateException {
        System.out.println("Setting " + athlete.getName() + " as reserve");

        if (!actives.contains(athlete))
            throw new IllegalStateException("Cannot reserve an athlete that is not active");

        if (this.reserves.size() >= MAX_RESERVES)
            throw new IllegalStateException("Cannot have more than " + MAX_RESERVES + " reserves");

        actives.remove(athlete);
        reserves.add(athlete);
    }

    /**
     * Adds an athlete to the team in the desired pool
     * @param athlete The athlete to add to the team
     * @param reserve {@code true} if the athlete should be a reserve, {@code false} otherwise
     */
    public void addAthlete(Athlete athlete, boolean reserve) {
        if (reserve)
            this.reserves.add(athlete);
        else
            this.actives.add(athlete);
    }

    /**
     *
     * @param athlete
     */
    public void removeAthlete(Athlete athlete) {
        this.actives.remove(athlete);
        this.reserves.remove(athlete);
    }

    public Athlete getActive(int index) {
        return this.actives.get(index);
    }

    public boolean isActive(Athlete athlete) {
        return this.actives.contains(athlete);
    }

    public Athlete[] getAthletes() {
        return Stream.concat(actives.stream(), reserves.stream()).toArray(Athlete[]::new);
    }

    Purchasable[] athletesAndItems() {
        return Stream.concat(Stream.concat(actives.stream(), reserves.stream()), GameManager.itemStream()).toArray(Purchasable[]::new);
    }
}
