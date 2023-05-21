/**
 * Utilities is a static utility class validates names to make sure the meet the required form
 *
 * @author Harrison Parkes
 */
public class Utilities {

    /**
     * Checks to see if the provided string meets the criteria for a valid name
     * Valid names must be between 3 and 15 characters long and contain only letters and numbers
     * @param name the name to validate
     * @throws IllegalArgumentException if the name is invalid
     */
    public static void validateName(String name, boolean allowSpaces) throws IllegalArgumentException{
        if (name.length() < 3) {
            throw new IllegalArgumentException("Name must be at least 3 characters long");
        } else if (name.length() > 15) {
            throw new IllegalArgumentException("Name must be at most 15 characters long");
        }
        if (allowSpaces) {
            if (!name.matches("[A-Za-z ]+"))
                throw new IllegalArgumentException("Name must contain only letters, and spaces");
        }
        else if (!name.matches("[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Name must contain only letters and numbers");
        }
    }
}
