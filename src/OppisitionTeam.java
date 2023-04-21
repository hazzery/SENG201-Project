/*
 * UNTESTED CODE, May be Shit, May not work
 * 
 * The idea around this code is to extend the already created
 * team class and to be able to scale stats based off of 
 * difficulty and round number ect. 
 * 
 * This is a skeleton class and nothing has been implmented
 * 
 * 
 */



import java.util.ArrayList;

public class OppisitionTeam extends Team{
    public static final int MIN_SIZE = 4;
    public String name;
    public ArrayList<Athlete> athletes;

    public OppisitionTeam(String name) {
        super(name);
        this.name = name;
        this.athletes = new ArrayList<>(MIN_SIZE);
    }
}

    