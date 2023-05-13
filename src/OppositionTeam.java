/*
 * UNTESTED CODE, May be Shit, May not work
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
     * Creates a random oppisition Athletes when initializeOppositionAthletes() is called upon
     *  
     */
    public static void initializeOppositionAthletes() {
        for (int i = 0; i < 4; i++) {
            Athlete oppositionAthlete = AthleteRandom.createRandomAthlete(opp_num_min, opp_num_max);
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
     * Gets the name of the oppisition team
     * @return the name of the oppisition team
     */
    public Athlete getOppAthlete(int index) {
        return OppositionTeam.oppositionAthletes.get(index);
    }
}

