package utility;
/**
 * HTMLString is a static utility class that abstracts away Swing's internal use of HTML
 * to format text on screen components.
 *
 * @author Harrison Parkes
 */
public class HTMLString {

    /**
     * Generates a new string that will display each string argument on a new line when displayed on a JComponent
     * @param strings An arbitrary number of strings which should all be displayed on separate lines
     * @return A new multiline string to display on a swing display component
     */
    public static String multiLine(String... strings) {
        StringBuilder builder = new StringBuilder("<html>");

        for (String string : strings) {
            builder.append(string).append("<br>");
        }
        builder.append("</html>");

        return builder.toString();
    }

    /**
     * Generates a new string that will be formatted as a header
     * (Large font size and bold) when displayed on a JComponent
     * @param string Any string
     * @return A new heading formatted string to display on a swing component
     */
    public static String header(String string) {
        return "<html><h1>" + string + "</h1></html>";
    }

    /**
     * Generates a new string that will be formatted as a sub-heading
     * (increased font size and bold) when displayed on a JComponent
     * @param string Any string
     * @return A new sub-heading formatted string to display on a swing component
     */
    public static String subHeading(String string) {
        return "<html><h2>" + string + "</h2></html>";
    }
}
