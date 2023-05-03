import java.util.ArrayList;

/**
 * Class the models a team
 */
public class Team {
    public static final int MIN_SIZE = 4;
    private final String name;
    private ArrayList<Athlete> athletes;

    /**
	 * Creates a team with the given name
     * 
     * @param name A name for the team
	 */
    public Team(String name) {
        this.name = name;
        this.athletes = new ArrayList<>(MIN_SIZE);
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
        return this.athletes.size();
    }

    /**
	 * Determines if the team contains the given athlete
	 *
     * @param athlete An Athlete to check membership of
	 * @return `true` if the team contains the athlete, `false` otherwise
     */
    public boolean contains(Athlete athlete) {
        return this.athletes.contains(athlete);
    }

    /**
	 * Adds an athlete to the team with a specified nickname
	 *  
     * @param athlete The athlete to add to the team
	 * @param nickname A nickname for the athlete
     */
    public void addAthlete(Athlete athlete, String nickname) {
        athlete.setNickname(nickname);
        this.athletes.add(athlete);
    }

    /**
	 * Displays all athletes in the team to the given interface
	 */
    public void displayAthletes(UserInterface ui) {
        ui.showOutput("Athletes in " + this.name + ":");
        for (Athlete athlete : this.athletes) {
            ui.showOutput(athlete.getName() + ": " + athlete.getNickname());
        }
    }
}
