package utility;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;

public class NameFileReader {
    private final Stack<String> names;

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

    public String next() {
        return names.pop().trim();
    }
}
