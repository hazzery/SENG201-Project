/*
 * This is just a fun little class to try out some game mechanics 
 */

import java.util.ArrayList;

public class GameMechanics {

    public static Team team;
    public static GameManager gameManager;
    public static OppositionTeam oppositionTeam;
    static ArrayList<Athlete> oppositionAthletes = new ArrayList<>();
    

    public static Athlete[] getTeamAthletes(){
        return team.getActives();
    }

    public static ArrayList<Athlete> getOppositionAthletes(){
        return GameManager.getOpposition();
    }


    // FOR ALL CODE FROM THIS POINT IS USING main(String[] args) method to create two teams of 4 

    /**
     * 
     */
    public static void currentVs(){

    }

    /**
     * 
     */
    public static void attack1(){

    }

    /**
     * 
     */
    public static void attack2(){

    }

    /**
     * 
     */
    public static void attack3(){

    }

    /**
     * 
     */
    public static void defend(){
        	
    }


    public static void main(String[] args) {
        ArrayList<Athlete> athleteList = new ArrayList<Athlete>(4);
        for (int i = 0; i < 4; i++){
            athleteList.add(new Athlete());
        }
        
        team = new Team("Team", athleteList);
        oppositionTeam = new OppositionTeam();
        gameManager = new GameManager();
        GameManager.initializeAthletes();
        oppositionAthletes = OppositionTeam.createTeam();
         
        
        System.out.println(athleteList);
        System.out.println(oppositionAthletes);

        
        System.out.println(athleteList.get(0).getStats());
        System.out.println(oppositionAthletes.get(0).getStats());
    }
}
