package data;
import gui.DisplayPanel;

import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Team is the abstract base class behind {@link PlayerTeam} and {@link OppositionTeam}.
 *
 * @author Harrison Parkes
 */
public abstract class Team implements Displayable {
    public static final int TEAM_SIZE = 5;

    protected String name;
    protected final ArrayList<Athlete> actives;

    private final ArrayList<DisplayPanel> displayPanels = new ArrayList<>();

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
    @Override
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
        int size = this.actives.size() == 0 ? 1 : this.actives.size();
        return total / size;
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

        int size = this.actives.size() == 0 ? 1 : this.actives.size();
        return total / size;
    }

    /**
     * Gets the team's difficulty score, calculated off of average offence and defence
     * @return the average of the average offence and defence
     */
    public double getDifficulty() {
        return (this.getAverageOffence() + this.getAverageDefence()) / 2;
    }

    protected double averageStat(Athlete.StatType stat) {
        return this.actives.stream().mapToInt(athlete -> athlete.getStat(stat)).average().orElse(0);
    }

    @Override
    public Map<String, String> getStats() {
        return Arrays.stream(Athlete.StatType.values())
                        .collect(Collectors.toMap(
                                        String::valueOf,
                                        value -> String.valueOf(averageStat(value))));
    }

    @Override
    public void registerPanel(DisplayPanel panel) {
        displayPanels.add(panel);
    }
}
