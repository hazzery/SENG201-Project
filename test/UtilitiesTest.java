import org.junit.jupiter.api.Test;
import utility.Utilities;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    @Test
    void validateName() {
        assertThrows(IllegalArgumentException.class, () -> Utilities.validateName("ThisNameIsALittleTooLong", false));
        assertThrows(IllegalArgumentException.class, () -> Utilities.validateName("No", false));
        assertThrows(IllegalArgumentException.class, () -> Utilities.validateName("Symbols?", false));
        assertThrows(IllegalArgumentException.class, () -> Utilities.validateName("Space not cool", false));
        assertThrows(IllegalArgumentException.class, () -> Utilities.validateName("Space is cool", false));
        assertThrows(IllegalArgumentException.class, () -> Utilities.validateName("Space 15 cool", true));

        assertDoesNotThrow(() -> Utilities.validateName("ValidName", false));
        assertDoesNotThrow(() -> Utilities.validateName("ValidName", true));
        assertDoesNotThrow(() -> Utilities.validateName("John Smith", true));
    }
}