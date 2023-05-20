//DELETE THIS CLASS ASAP
//ONLY FOR TESTING PURPOSES

import java.util.ArrayList;


public class Run {
    public static void main(String[] args) {
        ArrayList<Athlete> athleteList = new ArrayList<Athlete>(4);
        for (int i = 0; i < 5; i++){
            athleteList.add(new Athlete());
        }
        
        Team team = new Team("Team");
        OppositionTeam oppositionTeam = new OppositionTeam();
        GameManager.initializeAthletes();
        ArrayList<Athlete> oppositionAthletes = oppositionTeam.createOppTeam();
         
        
        System.out.println(athleteList);
        System.out.println(oppositionAthletes);
        System.out.println("PLAY GAME");
        

        for (int i = 0; i < athleteList.size(); i++){
            System.out.println(athleteList.get(i).getCurrentHealth());
        }
        System.out.println("OPPOSITION");
        for (int i = 0; i < oppositionAthletes.size(); i++){
            System.out.println(oppositionAthletes.get(i).getCurrentHealth());
        }

        GameMechanics.playGame(1, athleteList, oppositionAthletes);
    }
}
