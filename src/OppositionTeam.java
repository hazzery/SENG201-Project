/*
 * UNTESTED CODE
 *
 * The idea around this code is to extend the already created
 * team class and to be able to scale stats based off of
 * difficulty and round number ect.
 */


import java.util.ArrayList;

public class OppositionTeam {
    private final String name;
    static ArrayList<Athlete> oppositionAthletes = new ArrayList<>(4);
    
    public OppositionTeam(String name, ArrayList<Athlete> oppositionAthletes) {
        this.name = name;
        initializeOppositionAthletes();
        OppositionTeam.oppositionAthletes = oppositionAthletes;
    }

    
    public static int opp_num_min = 60;
    public static int opp_num_max = 80;

    /**
     * Creates a random opposition Athletes when initializeOppositionAthletes() is called upon
     *  
     */
    public static void initializeOppositionAthletes() {
        for (int i = 0; i < 4; i++) {
            Athlete oppositionAthlete = new Athlete();
            oppositionAthletes.add(oppositionAthlete);
          }
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
     * Gets the name of the opposition team
     * @return the name of the opposition team
     */
    public Athlete getOppAthlete(int index) {
        return OppositionTeam.oppositionAthletes.get(index);
    }
}

