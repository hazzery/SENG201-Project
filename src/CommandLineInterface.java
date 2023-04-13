import java.util.InputMismatchException;
import java.util.Scanner;

public class CommandLineInterface extends UserInterface {

    static Scanner inputScanner = new Scanner(System.in);

    @Override
    String getString() {
        return inputScanner.nextLine();  // Read user input
    }

    @Override
    int getInt() {
        while (true) {
            try {
                return inputScanner.nextInt();  // Read user input
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer");
                inputScanner.nextLine();  // Prevent unbreakable infinite loop
            }
        }
    }

    @Override
    <T> void showOutput(T output) {
        System.out.println(output);
    }
}
