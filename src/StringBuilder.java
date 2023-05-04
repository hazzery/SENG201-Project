public class StringBuilder {
    static String make(String... strings) {
        String builder = "<html>";

        for (String string : strings) {
            builder += string + "<br>";
        }
        builder += "</html>";

        return builder;
    }
}
