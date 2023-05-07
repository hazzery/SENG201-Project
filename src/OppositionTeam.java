/*
 * UNTESTED CODE, May be Shit, May not work
 *
 * The idea around this code is to extend the already created
 * team class and to be able to scale stats based off of
 * difficulty and round number ect.
 */


// NEED TO DISCUSS FUTURE


import java.util.ArrayList;

public class OppositionTeam extends Team{
    /**
     * Creates a team with the given name
     *
     * @param name     A name for the team
     * @param athletes All athletes to be put in the team
     */
    public OppositionTeam(String name, ArrayList<Athlete> athletes) {
        super(name, athletes);
    }
}

