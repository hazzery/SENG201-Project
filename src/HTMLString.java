public class HTMLString {
    static String make(String... strings) {
        StringBuilder builder = new StringBuilder("<html>");

        for (String string : strings) {
            builder.append(string).append("<br>");
        }
        builder.append("</html>");

        return builder.toString();
    }
}
