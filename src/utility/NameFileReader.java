package utility;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;


/**
 * NameFileReader is a class that reads names from a file
 * These files are used to generate names for {@link data.Item}, {@link data.Athlete}, and {@link data.Team} objects
 *
 * @author Harrison Parkes
 */
public class NameFileReader {
    private final Stack<String> names;

    /**
     * Initialise a new NameFileReader
     * @param fileName The name of the file to read names from
     */
    public NameFileReader(String fileName) {
        names = new Stack<>();
        try {
            File nameFile = new File(fileName);
            Scanner nameScanner = new Scanner(nameFile);
            while (nameScanner.hasNextLine()) {
                names.push(nameScanner.nextLine());
            }
            nameScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Get the next name from the file
     * @return A name from the file
     */
    public String next() {
        return names.pop().trim();
    }
}
