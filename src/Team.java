import java.util.ArrayList;

/**
 * Class the models a team
 */
public abstract class Team {
    public static final int TEAM_SIZE = 5;

    protected String name;
    protected final ArrayList<Athlete> actives;

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

    public abstract int size();

    public abstract Athlete[] getAthletes();

    public double getAverageOffence() {
        double total = 0;
        for (Athlete athlete : this.actives) {
            total += athlete.getOffence();
        }
        return total / this.actives.size();
    }

    public double getAverageDefence() {
        double total = 0;
        for (Athlete athlete : this.actives) {
            total += athlete.getDefence();
        }
        return total / this.actives.size();
    }

    public double getDifficulty() {
        return (this.getAverageOffence() + this.getAverageDefence()) / 2;
    }
}
