//DELETE THIS CLASS ASAP
//ONLY FOR TESTING PURPOSES

import java.util.ArrayList;


public class Run {
    public static void main(String[] args) {
        ArrayList<Athlete> athleteList = new ArrayList<Athlete>(4);
        for (int i = 0; i < 4; i++){
            athleteList.add(new Athlete());
        }
        
        Team team = new Team("Team", athleteList);
        OppositionTeam oppositionTeam = new OppositionTeam();
        GameManager gameManager = new GameManager();
        GameManager.initializeAthletes();
        ArrayList<Athlete> oppositionAthletes = OppositionTeam.createTeam();
         
        
        System.out.println(athleteList);
        System.out.println(oppositionAthletes);

        int currentRoundTest = 0;
        System.out.println("PLAY GAME");
        GameMechanics gameMechanics = new GameMechanics(currentRoundTest, athleteList, oppositionAthletes);
    }
}
