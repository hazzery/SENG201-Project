import java.util.ArrayList;

public class Team {
    public static final int MIN_SIZE = 4;

    private final String name;
    private ArrayList<Athlete> athletes;

    public Team(String name) {
        this.name = name;
        this.athletes = new ArrayList<>(MIN_SIZE);
    }

    public String getName() {
        return this.name;
    }

    public int size() {
        return this.athletes.size();
    }

    public boolean contains(Athlete athlete) {
        return this.athletes.contains(athlete);
    }

    public void addAthlete(Athlete athlete, String nickname) {
        athlete.setNickname(nickname);
        this.athletes.add(athlete);
    }

    public void displayAthletes(UserInterface ui) {
        ui.showOutput("Athletes in " + this.name + ":");
        for (Athlete athlete : this.athletes) {
            ui.showOutput(athlete.getName() + ": " + athlete.getNickname());
        }
    }
}
