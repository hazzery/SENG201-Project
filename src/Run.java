//DELETE THIS CLASS ASAP
//ONLY FOR TESTING PURPOSES

import java.util.ArrayList;
import java.util.Arrays;


public class Run {
    public static void main(String[] args) {
        ArrayList<Athlete> athleteList = new ArrayList<>(4);
        for (int i = 0; i < 5; i++){
            athleteList.add(new Athlete());
        }
        
        Team team = new PlayerTeam("Team");
        OppositionTeam oppositionTeam = new OppositionTeam();
        GameManager.initializeAthletes();
        Athlete[] oppositionAthletes = oppositionTeam.getAthletes();
         
        
        System.out.println(athleteList);
        System.out.println(Arrays.toString(oppositionAthletes));
        System.out.println("PLAY GAME");


        for (Athlete athlete : athleteList) {
            System.out.println(athlete.getCurrentHealth());
        }
        System.out.println("OPPOSITION");
        for (Athlete oppositionAthlete : oppositionAthletes) {
            System.out.println(oppositionAthlete.getCurrentHealth());
        }

        GameMechanics.playGame(1, athleteList, oppositionAthletes);
    }
}
