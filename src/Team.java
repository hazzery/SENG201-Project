import java.util.ArrayList;

/**
 * Class the Models a Team.
 */
public class Team {
    /**
     * Sets the minimum size of a Team.
	 */
    public static final int MIN_SIZE = 4;

    private final String name;
    private ArrayList<Athlete> athletes;

    /**
	 * Creates a Team with the given name.
     * 
     * @param name The name for the Team
     * 
	 */
    public Team(String name) {
        this.name = name;
        this.athletes = new ArrayList<>(MIN_SIZE);
    }

     /**
	 * Gets the Name of Team.
	 *
	 * @return The Name of the Team.
	 */
    public String getName() {
        return this.name;
    }

    /**
	 * Gets the Size of Team.
	 *
	 * @return The Size of the Team.
	 */
    public int size() {
        return this.athletes.size();
    }

    /**
	 * Gets the Athlete of Team.
	 *
     * @param athlete The athlete to get from the Team.
	 * @return The Athlete of the Team.
     */
    public boolean contains(Athlete athlete) {
        return this.athletes.contains(athlete);
    }

    /**
	 * Adds an Athlete to the Team with the given nickname.
	 *  
     * @param athlete The athlete to add to the Team
	 * @param nickname The nickname for the Athlete
     *
     */
    public void addAthlete(Athlete athlete, String nickname) {
        athlete.setNickname(nickname);
        this.athletes.add(athlete);
    }

    /**
	 * User Interface for displaying Athletes.
	 * 
	 */
    public void displayAthletes(UserInterface ui) {
        ui.showOutput("Athletes in " + this.name + ":");
        for (Athlete athlete : this.athletes) {
            ui.showOutput(athlete.getName() + ": " + athlete.getNickname());
        }
    }
}
