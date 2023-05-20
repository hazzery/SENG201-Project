import java.util.ArrayList;
import java.util.stream.Stream;

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

    public void swapAthletes(Athlete currentlyActive, Athlete currentlyReserved) {
        System.out.println("Swapping " + currentlyActive.getName() + " with " + currentlyReserved.getName());
        if (!this.actives.contains(currentlyActive) || !this.reserves.contains(currentlyReserved))
            throw new IllegalArgumentException("Cannot swap these athletes");

        this.actives.remove(currentlyActive);
        this.reserves.remove(currentlyReserved);
        this.actives.add(currentlyReserved);
        this.reserves.add(currentlyActive);
    }

    public void setActive(Athlete athlete) throws IllegalStateException {
        System.out.println("Setting " + athlete.getName() + " as active");

        if (!reserves.contains(athlete))
            throw new IllegalStateException("Cannot activate an athlete that is not a reserve");

        reserves.remove(athlete);
        actives.add(athlete);
    }

    public void setReserve(Athlete athlete) throws IllegalStateException {
        System.out.println("Setting " + athlete.getName() + " as reserve");

        if (!actives.contains(athlete))
            throw new IllegalStateException("Cannot reserve an athlete that is not active");

        if (this.reserves.size() >= MAX_RESERVES)
            throw new IllegalStateException("Cannot have more than " + MAX_RESERVES + " reserves");

        actives.remove(athlete);
        reserves.add(athlete);
    }

    public void addAthlete(Athlete athlete, boolean reserve) {
        if (reserve)
            this.reserves.add(athlete);
        else
            this.actives.add(athlete);
    }

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