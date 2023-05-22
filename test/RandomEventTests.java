//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.ThreadLocalRandom;
//
//public class RandomEventTest {
//    private RandomEvent randomEvent;
//    private GameManager gameManager;
//    private Team team;
//
//    @Before
//    public void setup() {
//        // Create a mock instance of the GameManager and Team
//        gameManager = new GameManager();
//        team = new Team();
//        // Add some dummy athletes to the team for testing
//        team.addAthlete(new Athlete("Athlete 1"), false);
//        team.addAthlete(new Athlete("Athlete 2"), false);
//        // Create an instance of the RandomEvent class
//        randomEvent = new RandomEvent();
//        randomEvent.gameManager = gameManager;
//        randomEvent.athlete = team.getActive(0);
//    }
//
//    @Test
//    public void testRandomEventStatIncrease() {
//        // Arrange
//        int expectedStatIndex = randomEvent.statIndex;
//        int expectedStatIncrease = randomEvent.statIncrease;
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        // Act
//        randomEvent.randomStatIncrease();
//
//        // Assert
//        // Verify that the correct stat was increased by the expected amount
//        if (expectedStatIndex == 0) {
//            Assert.assertEquals(expectedStatIncrease + 10, team.getActive(0).stamina);
//        } else if (expectedStatIndex == 1) {
//            Assert.assertEquals(expectedStatIncrease, team.getActive(0).offence);
//        } else if (expectedStatIndex == 2) {
//            Assert.assertEquals(expectedStatIncrease, team.getActive(0).defence);
//        }
//        // Verify the expected output is generated
//        Assert.assertTrue(outputStream.toString().contains("Random Event: " + team.getActive(0).getName() + "'s increased by " + expectedStatIncrease + " points"));
//    }
//
//    @Test
//    public void testRandomEventNewAthleteWithSpace() {
//        // Arrange
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        // Act
//        randomEvent.randomNewAthlete();
//
//        // Assert
//        // Verify that a new athlete is added to the team
//        Assert.assertEquals(3, team.numActive());
//        // Verify the expected output is generated
//        Assert.assertTrue(outputStream.toString().contains("Random Event: An Athlete tried to join your team but its full"));
//    }
//
//    @Test
//    public void testRandomEventNewAthleteWithoutSpace() {
//        // Arrange
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        // Add enough athletes to the team to reach the maximum size
//        for (int i = 0; i < PlayerTeam.MAXIMUM_SIZE - 2; i++) {
//            team.addAthlete(new Athlete("Athlete " + (i + 3)), false);
//        }
//
//        // Act
//        randomEvent.randomNewAthlete();
//
//        // Assert
//        // Verify that a new athlete is not added to the team
//        Assert.assertEquals(PlayerTeam.MAXIMUM_SIZE, team.numActive() + team.numReserves());
//        // Verify the expected output is generated
//        Assert.assertTrue(outputStream.toString().contains("Random Event: An Athlete tried to join your team but its full"));
//    }
//
//    @Test
//    public void testRandomEventQuitAthleteWithActiveAthletes() {
//        // Arrange
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        // Act
//        randomEvent.randomQuitAthlete();
//
//        // Assert
//        // Verify that an athlete is removed from the team
//        Assert.assertEquals(1, team.numActive());
//        // Verify the expected output is generated
//        Assert.assertTrue(outputStream.toString().contains("Random Event: An Athlete tried to quit your team but you have no active athletes"));
//    }
//
//    @Test
//    public void testRandomEventQuitAthleteWithoutActiveAthletes() {
//        // Arrange
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        // Remove all active athletes from the team
//        team.removeAthlete(team.getActive(0));
//
//        // Act
//        randomEvent.randomQuitAthlete();
//
//        // Assert
//        // Verify that no athlete is removed from the team
//        Assert.assertEquals(0, team.numActive());
//        // Verify the expected output is generated
//        Assert.assertTrue(outputStream.toString().contains("Random Event: An Athlete tried to quit your team but you have no active athletes"));
//    }
//
//    @Test
//    public void testRandomEventQuitAthleteWithoutSpaceAndLowBalance() {
//        // Arrange
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//
//        // Set the bank balance to a low value
//        gameManager.setBankBalance(500);
//
//        // Act
//        randomEvent.randomQuitAthlete();
//
//        // Assert
//        // Verify that no athlete is removed from the team
//        Assert.assertEquals(2, team.numActive());
//        // Verify the expected output is generated
//        Assert.assertTrue(outputStream.toString().contains("Random Event: An Athlete quit your team but you have less than 5 athletes and no money!!!"));
//    }
//}
//
