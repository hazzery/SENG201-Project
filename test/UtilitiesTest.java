import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilitiesTest {

    @Test
    void validateName() {
        assertThrows(IllegalArgumentException.class, () -> Utilities.validateName("ThisNameIsALitteTooLong", false));
        assertThrows(IllegalArgumentException.class, () -> Utilities.validateName("No", false));
        assertThrows(IllegalArgumentException.class, () -> Utilities.validateName("Symbols?", false));
        assertThrows(IllegalArgumentException.class, () -> Utilities.validateName("Space not cool", false));
        assertThrows(IllegalArgumentException.class, () -> Utilities.validateName("Space is cool", false));

        assertDoesNotThrow(() -> Utilities.validateName("ValidName", false));
        assertDoesNotThrow(() -> Utilities.validateName("ValidName", true));
        assertDoesNotThrow(() -> Utilities.validateName("John Smith", true));
    }
}