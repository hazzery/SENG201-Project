/*
 * UNTESTED CODE, may not work, may be shit
 * 
 * 
 * This is a skeleton class as of 15:30 nzt 21/4/23
 * The mechanics of this class is to set up the match
 * via created 3 random teams to compete against,
 * these teams will have stats that are privatly scaled,
 * ie a player with def of 30 and attack of 45 may have
 * stats of 67 and 100 on the hardest difficulty on the
 * final game, 
 * 
 * BIG NOTE: This scale factor, will have to work in a way
 * that there is a defult range of stats ie 25-42 to begin
 * with and the scale factor will increase the week by week
 * player ratings ie on easy: 
 * w1 att 28 def 35, 
 * w8 att 39 def 49
 * w15 att 49 def 61
 * with a difficulty + week increase ie 1 + 0.05 * week num
 * difficulty numbers are easy = 0.5 med = 1, hard = 1.5,
 * impossible = 2
 * 
 * Once the 3 teams have been created the info is sent to UI
 * were the UI will call for items like team1, team2, team3
 * for aspects like list of athletes for each team including
 * there stats ect
 * 
 * Once a team has been selected the selected teams info
 * will be used for the actual game.
 * 
 */





import java.util.ArrayList;

public class MatchSetUp {
    private static final int NUM_MATCHES = 3;

    ArrayList<OppisitionTeam> oppisitionTeams = new ArrayList<>(NUM_MATCHES);
    OppisitionTeam match1 = new OppisitionTeam("Metro FC");
    OppisitionTeam match2 = new OppisitionTeam("Garden FC");
    OppisitionTeam match3 = new OppisitionTeam("Country FC");


    private int difficulty;
    private int numMatches;


    public MatchSetUp(int numMatches, int difficulty) {
        this.numMatches = numMatches;
        this.difficulty = difficulty;
    }

    public double difficultyScaler(){
        double difficultyScaler = difficulty * 0.5;
        return difficultyScaler + 0.05 * numMatches;
    }

    

    /**
     * Generates NUM_MATCHES number of matches
     */
    


}
