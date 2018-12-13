package utilities;

public class StringManager {
    //Constants
    private static final int TOTAL_CHAR = 39;

    /**
     * Generates a center-aligned heading in a Menu using dash lines on each side
     *
     * @param message  Heading message.
     * @param lineType Any Type of dash line is supported. "-" & "=" are most frequently used.
     * @return the generated heading.
     */
    public static String generateDashLine(String message, String lineType) {
        String result = "";
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < (TOTAL_CHAR - message.length() - i) / 2; j++) {
                result = result.concat(lineType);
            }
            if (i == 0) {
                result = result.concat(message);
            }
        }
        return result;
    }
}
