
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class RandomEventTests {

    @Test
    void hello(){
        RandomEvent randomEvent = new RandomEvent();
        randomEvent.randomEvent();
    }

}
